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
				$N_page = isset($_REQUEST['page']) ? $_REQUEST['page'] : 1;
				
				if($obj_user->check_code($N_auth_token, $N_user_id)){//check code
					$result = $obj_owner->get_owner($N_page);
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