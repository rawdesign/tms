<?php
header('content-type: application/json');
header("access-control-allow-origin: *");
require_once("../packages/require.php");

if(isset($_GET['action'])){

	require_once($global['root-url-model']."/Connection.php");
	$obj_connect = new Connection();
	
	require_once($global['root-url-model']."/User.php");
	$obj_user = new User();

	require_once($global['root-url-model']."/Encryption.php");
	$obj_encrypt = new Encryption();

	//===================================== login ========================================
	//start login
	if($_GET['action'] == 'login' && isset($_REQUEST['email']) && isset($_REQUEST['password'])){
		$obj_connect->up();	
		$R_message = array("status" => "400", "message" => "Login Failed");
		
		$N_email = mysql_real_escape_string($_REQUEST['email']);
		$N_password = mysql_real_escape_string($_REQUEST['password']);
		$password = $obj_encrypt->encode($N_password);

		$result = $obj_user->login($N_email, $password);
		if(is_array($result)){
			$R_message = array("status" => "200", "message" => "Login Success", "data" => $result);
		}

		$obj_connect->down();
		echo json_encode($R_message);	
	}//end login

	else{
		$R_message = array("status" => "404", "message" => "Action Not Found");
		echo json_encode($R_message);
	}
} else{
	$R_message = array("status" => "404", "message" => "Not Found");
	echo json_encode($R_message);
}
?>