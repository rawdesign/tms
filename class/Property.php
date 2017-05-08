<?php
class Property{
	private $table = "t_property";

	public function get_data(){
		$result = 0;
		$text = "SELECT * FROM $this->table ORDER BY property_create_date DESC";
		$query = mysql_query($text);
		$total_data = mysql_num_rows($query);
		if(mysql_num_rows($query) >= 1){
			$result = array();
			while($row = mysql_fetch_assoc($query)){
				$result[] = $row;
			}
		}
		if(is_array($result)){
            $result[0]['total_data'] = $total_data;
        }
		return $result;
	}
 
	public function get_data_detail($id){
		$result = 0;
		$text = "SELECT * FROM $this->table WHERE property_id = '$id'";
		$query = mysql_query($text);
		if(mysql_num_rows($query) >= 1){
			$result = array();
			while($row = mysql_fetch_assoc($query)){
				$result[] = $row;
			}
		}
		return $result;
	}	
	public function insert_data($owner, $address, $price, $img, $img_thmb){
		$result = 0;
		$text = "INSERT INTO $this->table (property_owner, property_address, property_price, property_img, property_img_thmb) VALUES('$owner', '$address', '$price', '$img', '$img_thmb')";
		$query = mysql_query($text);
		if($query){
			$result = 1;
		}
		return $result;
	}
	public function update_data($id, $owner, $address, $price){
		$result = 0;
		$text = "UPDATE $this->table SET property_owner = '$owner', property_address = '$address', property_price = '$price' WHERE property_id = '$id'";
		$query = mysql_query($text);	
		if(mysql_affected_rows() == 1){
			$result = 1;
		}
		return $result;
	}
	public function update_data_image($id, $owner, $address, $price, $img, $img_thmb){
		$result = 0;
		$this->remove_image($id); //remove image before
		$text = "UPDATE $this->table SET property_owner = '$owner', property_address = '$address', property_price = '$price', property_img = '$img', property_img_thmb = '$img_thmb' WHERE property_id = '$id'";
		$query = mysql_query($text);
		if(mysql_affected_rows() == 1){
			$result = 1;
		}
		return $result;
	}
	public function delete_data($id){
		$result = 0;
		$this->remove_image($id); //remove image before
		$text = "DELETE FROM $this->table WHERE property_id = '$id'";
		$query = mysql_query($text);
		if(mysql_affected_rows() == 1){
			$result = 1;
		}
		return $result;
	}
	public function remove_image($id){
		$result = 0;
		$flag_img = 0;
		$flag_img_thmb = 0;
		$text = "SELECT property_id, property_img, property_img_thmb FROM $this->table WHERE property_id = '$id'";
		$query = mysql_query($text);
		if(mysql_num_rows($query) == 1){
			$row = mysql_fetch_assoc($query);
            $deleteImg = $_SERVER['DOCUMENT_ROOT']."/devel/tms/".$row['property_img'];
            if (file_exists($deleteImg)) {
                unlink($deleteImg);
                $flag_img = 1;
            }
            $deleteImgThmb = $_SERVER['DOCUMENT_ROOT']."/devel/tms/".$row['property_img_thmb'];
            if (file_exists($deleteImgThmb)) {
                unlink($deleteImgThmb);
                $flag_img_thmb = 1;
            }
            if($flag_img == 1 && $flag_img_thmb ==1){
            	$result = 1;
            }
		}
		return $result;
	}
//END FUNCTION FOR ADMIN PAGE
}
?>