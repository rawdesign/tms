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