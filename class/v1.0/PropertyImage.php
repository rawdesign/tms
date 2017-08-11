<?php
class PropertyImage{

	private $table = "t_property_image";

	public function insert_data($property, $img, $img_thmb){
		$result = 0;

		$text = "INSERT INTO $this->table (pi_property, pi_img, pi_img_thmb) 
			VALUES('$property', '$img', '$img_thmb')";
		$query = mysql_query($text);
		if($query){
			$result = 1;
		}
		return $result;
	}

}
?>