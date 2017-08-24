<?php
header("content-type: application/json");
header("access-control-allow-origin: *");
require_once(dirname(__FILE__)."/../../packages/require.php");

if(isset($_GET['action'])){

	$headers = getAllHeaders();
	$authorization = isset($headers['Authorization']) ? $headers['Authorization'] : "";

	if($headers['Accept'] == "application/json"){

		if(isAuthorize($authorization, $access_token['api-user'])){

			$version = "v1.0";

			require_once($global['root-url-model']."/Connection.php");
			$obj_connect = new Connection();
			
			require_once($global['root-url-model']."/Encryption.php");
			$obj_encrypt = new Encryption();

			require_once($global['root-url-model'].$version."/User.php");
			$obj_user = new User();

			require_once($global['root-url-model'].$version."/Mail.php");
			$obj_mail = new Mail();

			//===================================== login ========================================
			//start login
			if($_GET['action'] == 'login' && isset($_POST['email']) && isset($_POST['password'])){
				$obj_connect->up();	
				$R_message = array("status" => "400", "message" => "Login Failed");
				
				$N_email = mysql_real_escape_string($_POST['email']);
				$N_password = mysql_real_escape_string($_POST['password']);
				$password = $obj_encrypt->encode($N_password);

				$result = $obj_user->login($N_email, $password);
				if(is_array($result)){
					$R_message = array("status" => "200", "message" => "Login Success", "data" => $result);
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end login

			//===================================== forget password ========================================
			//start forget password
			else if($_GET['action'] == 'forget_password' && isset($_POST['email'])){
				$obj_connect->up();	
				$R_message = array("status" => "400", "message" => "Forget Password Failed");
				
				$N_email = mysql_real_escape_string($_POST['email']);

		        $result = $obj_user->get_forget_password($N_email);
				if(is_array($result)){
					$subject = "Forget Password TMS One";
					$message_html = $obj_mail->mail_forget_password($result[0]['user_name'], $obj_encrypt->decode($result[0]['user_password']));
					$mail = smtpmailer($N_email, $smtp['url'], $smtp['from'], $smtp['password'], $seo['company-name'], $subject, $message_html);
					if($mail){
						$R_message = array("status" => "200", "message" => "Forget password Success");
					}
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end forget password

			//===================================== update data ========================================
			//start update data
			else if($_GET['action'] == 'update_data' && isset($_REQUEST['user_id']) && isset($_REQUEST['auth_token'])){
				$obj_connect->up();	
				$R_message = array("status" => "400", "message" => "Edit profile failed");
				$N_user_id = mysql_real_escape_string($_REQUEST['user_id']);
				$N_auth_token = mysql_real_escape_string($_REQUEST['auth_token']);

				$N_name = mysql_real_escape_string($_REQUEST['name']);

				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					$result = $obj_user->update_data($N_user_id, $N_name);
					if($result == 1){
						$R_message = array("status" => "200", "message" => "Edit profile success");
					}
				}//check code
				else{
					$R_message = array("status" => "401", "message" => "Unauthorized");
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end update data

			//===================================== change password ========================================
			//start change password
			else if($_GET['action'] == 'change_password' && isset($_REQUEST['user_id']) && isset($_REQUEST['auth_token'])){
				$obj_connect->up();	
				$R_message = array("status" => "404", "message" => "Change password failed");
				$N_user_id = mysql_real_escape_string($_REQUEST['user_id']);
				$N_auth_token = mysql_real_escape_string($_REQUEST['auth_token']);

				$N_old_password = mysql_real_escape_string($_REQUEST['old_password']);
				$N_new_password = mysql_real_escape_string($_REQUEST['new_password']);
				$N_confirm_password = mysql_real_escape_string($_REQUEST['confirm_password']);
				$old_password = $obj_encrypt->encode($N_old_password);
				$new_password = $obj_encrypt->encode($N_new_password);

				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					$check_password = $obj_user->check_password($N_user_id, $old_password);
					if($check_password == 1){
						if($N_new_password == $N_confirm_password){
							$result = $obj_user->update_password($N_user_id, $old_password, $new_password);
							if($result == 1){
								$R_message = array("status" => "200", "message" => "Change password success");
							}
						}else{
							$R_message = array("status" => "401", "message" => "Confirm password doesn't match");
						}
					}else{
						$R_message = array("status" => "401", "message" => "Your old password doesn't match");
					}
				}//check code
				else{
					$R_message = array("status" => "401", "message" => "Unauthorized");
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end change password

			else{
				$R_message = array("status" => "404", "message" => "Action Not Found");
				echo json_encode($R_message);
			}

		}else{
			$R_message = array("status" => "401", "message" => "Access denied due to unauthorized process, please check access token api");
			echo json_encode($R_message);
		}

	}else{
		$R_message = array("status" => "403", "message" => "Invalid JSON format or header");
		echo json_encode($R_message);
	}

} else{
	$R_message = array("status" => "404", "message" => "Not Found");
	echo json_encode($R_message);
}
?>