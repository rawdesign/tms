<?php
class PropertyImage{

	private $table = "t_property_image";

	public function insert_data($token, $property, $img, $img_thmb, $status){
		$result = 0;

		$text = "INSERT INTO $this->table (pi_token, pi_property, pi_img, pi_img_thmb, pi_status) 
			VALUES('$token', '$property', '$img', '$img_thmb', '$status')";
		$query = mysql_query($text);
		if($query){
			$result = 1;
		}
		return $result;
	}

}
?>