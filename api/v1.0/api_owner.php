<?php
header("content-type: application/json");
header("access-control-allow-origin: *");
require_once(dirname(__FILE__)."/../../packages/require.php");

if(isset($_GET['action'])){

	$headers = getAllHeaders();
	$authorization = isset($headers['Authorization']) ? $headers['Authorization'] : "";

	if($headers['Accept'] == "application/json"){

		if(isAuthorize($authorization, $access_token['api-owner'])){

			$version = "v1.0";

			require_once($global['root-url-model']."/Connection.php");
			$obj_connect = new Connection();
			
			require_once($global['root-url-model'].$version."/Owner.php");
			$obj_owner = new Owner();

			require_once($global['root-url-model'].$version."/User.php");
			$obj_user = new User();

			//===================================== get owner ========================================
			//start get owner
			if($_GET['action'] == 'get_owner' && isset($_REQUEST['user_id']) && isset($_REQUEST['auth_token'])){//START get_owner
				$obj_connect->up();	
				$R_message = array("status" => "400", "message" => "No Data");
				
				$N_user_id = mysql_real_escape_string($_REQUEST['user_id']);
				$N_auth_token = mysql_real_escape_string($_REQUEST['auth_token']);
				$N_keyword = isset($_REQUEST['keyword']) ? $_REQUEST['keyword'] : "";
				$N_page = isset($_REQUEST['page']) ? $_REQUEST['page'] : 1;
				
				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					$result = $obj_owner->get_owner($N_page, $N_keyword);
					//var_dump($result);
					if(is_array($result)){
						$itemperpage = 6;
						$total_data = $result[0]['total_data_all'];
						$remaining = $total_data - ((($N_page-1) * $itemperpage) + count($result));
						$R_message = array("status" => "200", "message" => "Data Exist", "num_data" => count($result), "remaining" => $remaining, "data" => $result);
					}
				}//check code
				else{
					$R_message = array("status" => "401", "message" => "Unauthorized");
				}

				$obj_connect->down();	
				echo json_encode($R_message);	
			}//end get owner

			//===================================== insert owner ========================================
			//start insert owner
			else if($_GET['action'] == 'insert_data' && isset($_REQUEST['user_id']) && isset($_REQUEST['auth_token'])){
				$obj_connect->up();
				$R_message = array("status" => "400", "message" => "Insert Data Failed");
				$N_user_id = mysql_real_escape_string($_REQUEST['user_id']);
				$N_auth_token = mysql_real_escape_string($_REQUEST['auth_token']);

				//field
				$N_token = mysql_real_escape_string($_REQUEST['token']);
				$N_name = mysql_real_escape_string($_REQUEST['name']);
				$N_location = mysql_real_escape_string($_REQUEST['location']);
				$N_phone = mysql_real_escape_string($_REQUEST['phone']);
				$N_email = mysql_real_escape_string($_REQUEST['email']);
				$N_ktp = mysql_real_escape_string($_REQUEST['ktp']);
				$N_birthday = mysql_real_escape_string($_REQUEST['birthday']);
				$N_num_property = mysql_real_escape_string($_REQUEST['num_property']);
				$N_create_date = mysql_real_escape_string($_REQUEST['create_date']);
				$N_status = "success";
				$file_loc1 = "";
				$file_locThmb1 = "";

				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					if(isset($_FILES['image']['name'])){
						if(!empty($_FILES['image']['name'])){
							require_once($global['root-url']."packages/SimpleImage.php"); // class simple image
							$allowed_ext = array('jpg', 'jpeg', 'png', 'gif');
							$file_name = cleanSpace($_FILES['image']['name']);
							$file_ext = strtolower(end(explode('.', $file_name)));
							$file_size = $_FILES['image']['size'];
							$file_tmp = $_FILES['image']['tmp_name'];
							$ran = rand();
				    		$timestamp = time();
							
							if(in_array($file_ext, $allowed_ext) === true){
								if($file_size < 10044070){
									//save image in server
									$file_loc = $global['root-url']."uploads/owner/".$timestamp.$ran.$file_name; 
									$file_locThmb = $global['root-url']."uploads/owner-thmb/".$timestamp.$ran.$file_name;

									//save image in database
									$file_loc1 = "uploads/owner/".$timestamp.$ran.$file_name; 
									$file_locThmb1 = "uploads/owner-thmb/".$timestamp.$ran.$file_name;

									if(move_uploaded_file($file_tmp, $file_loc))
									{
										$image = new SimpleImage();
										$image->load($file_loc);
										$image->resize(200,200);
										$image->save($file_locThmb);
									}
								}else{
									$R_message = array("status" => "413", "message" => "ERROR: file size max 10 MB!");
								}
							}else{
								$R_message = array("status" => "415", "message" => "ERROR: extension file invalid!");
							}
						}else{
							$R_message = array("status" => "404", "message" => "Upload file is empty");
						}
					}

					$result = $obj_owner->insert_data($N_user_id, $N_token, $N_name, $N_location, $N_phone, $N_email, $N_ktp, $N_birthday, $file_loc1, $file_locThmb1, $N_status, $N_num_property, $N_create_date);
					//var_dump($result);
					if($result == 1){
						$R_message = array("status" => "200", "message" => "Insert Data Success");
					}
				}//check code
				else{
					$R_message = array("status" => "401", "message" => "Unauthorized");
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end insert owner

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