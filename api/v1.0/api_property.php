<?php
header("content-type: application/json");
header("access-control-allow-origin: *");
require_once(dirname(__FILE__)."/../../packages/require.php");

if(isset($_GET['action'])){

	$headers = getAllHeaders();
	$authorization = isset($headers['Authorization']) ? $headers['Authorization'] : "";

	if($headers['Accept'] == "application/json"){

		if(isAuthorize($authorization, $access_token['api-property'])){

			$version = "v1.0";

			require_once($global['root-url-model']."/Connection.php");
			$obj_connect = new Connection();
			
			require_once($global['root-url-model'].$version."/Property.php");
			$obj_property = new Property();

			require_once($global['root-url-model'].$version."/PropertyImage.php");
			$obj_image = new PropertyImage();

			require_once($global['root-url-model'].$version."/User.php");
			$obj_user = new User();

			//===================================== insert property ========================================
			//start insert property
			if($_GET['action'] == 'insert_data' && isset($_REQUEST['user_id']) && isset($_REQUEST['auth_token'])){
				$obj_connect->up();
				$R_message = array("status" => "400", "message" => "Insert Data Failed");
				$N_user_id = mysql_real_escape_string($_REQUEST['user_id']);
				$N_auth_token = mysql_real_escape_string($_REQUEST['auth_token']);

				//field
				$N_token = mysql_real_escape_string($_REQUEST['token']);
				$N_owner = mysql_real_escape_string($_REQUEST['owner']);
				$N_title = mysql_real_escape_string($_REQUEST['title']);
				$N_status = "success";

				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					$result = $obj_property->insert_data($N_token, $N_owner, $N_title, $N_status);
					//var_dump($result);
					if($result == 1){
						//insert image
						require_once($global['root-url']."packages/SimpleImage.php"); // class simple image
						if(isset($_FILES['image']['name'])){
							for($i = 0; $i < count($_FILES['image']['name']); $i++){
								if(!empty($_FILES['image']['name'][$i])){
									$allowed_ext = array('jpg', 'jpeg', 'png', 'gif');
									$file_name = cleanSpace($_FILES['image']['name'][$i]);
									$file_ext = strtolower(end(explode('.', $file_name)));
									$file_size = $_FILES['image']['size'][$i];
									$file_tmp = $_FILES['image']['tmp_name'][$i];
									$ran = rand();
						    		$timestamp = time();
									
									if(in_array($file_ext, $allowed_ext) === true){
										if($file_size < 10044070){
											//save image in server
											$file_loc = $global['root-url']."uploads/property/".$timestamp.$ran.$file_name; 
											$file_locThmb = $global['root-url']."uploads/property-thmb/".$timestamp.$ran.$file_name;

											//save image in database
											$file_loc1 = "uploads/property/".$timestamp.$ran.$file_name; 
											$file_locThmb1 = "uploads/property-thmb/".$timestamp.$ran.$file_name;

											if(move_uploaded_file($file_tmp, $file_loc))
											{
												$image = new SimpleImage();
												$image->load($file_loc);
												$image->resize(200,200);
												$image->save($file_locThmb);
											}
											
											$obj_image->insert_data($N_token, $file_loc1, $file_locThmb1);
										}
									}
								}
							}
						}

						$R_message = array("status" => "200", "message" => "Insert Data Success");
					}
				}//check code
				else{
					$R_message = array("status" => "401", "message" => "Unauthorized");
				}

				$obj_connect->down();
				echo json_encode($R_message);	
			}//end insert property

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