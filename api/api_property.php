<?php
header('content-type: application/json');
header("access-control-allow-origin: *");
require_once("../packages/require.php");

if(isset($_GET['action'])){

	require_once($global['root-url-model']."/Connection.php");
	$obj_connect = new Connection();
	
	require_once($global['root-url-model']."/Property.php");
	$obj_property = new Property();

	//===================================== get property ========================================
	//start get property
	if($_GET['action'] == 'get_property'){
		$obj_connect->up();	
		$R_message = array("status" => "400", "message" => "No Data");

		$result = $obj_property->get_data();
		if(is_array($result)){
			$R_message = array("status" => "200", "message" => "Data Exist", 
				"num_data" => count($result), 
				"remaining" => 0,
				"data" => $result);
		}

		$obj_connect->down();	
		echo json_encode($R_message);	
	}//end get property

	//===================================== insert property ========================================
	//start insert property
	else if($_GET['action'] == 'insert_data'){
		$obj_connect->up();	

		//field
		$N_owner = mysql_real_escape_string($_REQUEST['owner']);
		$N_title = mysql_real_escape_string($_REQUEST['title']);
		$N_address = mysql_real_escape_string($_REQUEST['address']);
		$N_price = mysql_real_escape_string($_REQUEST['price']);
		
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

					$result = $obj_property->insert_data($N_owner, $N_title, $N_address, $N_price, $file_loc1, $file_locThmb1);
					if($result >= 1){
						$datas = $obj_property->get_data_detail($result);
						$R_message = array("status" => "200", "message" => "Insert Data Success", "data" => $datas);
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

		$obj_connect->down();
		echo json_encode($R_message);	
	}//end insert property

} else{
	$R_message = array("status" => "404", "message" => "Not Found");
	echo json_encode($R_message);
}
?>