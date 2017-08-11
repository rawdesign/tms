<?php
class Property{

	private $table = "t_property";

	public function insert_data($token, $owner, $title, $status){
		$result = 0;

		$text = "INSERT INTO $this->table (property_token, property_owner, property_title, property_status) 
			VALUES('$token', '$owner', '$title', '$status')";
		$query = mysql_query($text);
		if($query){
			$result = 1;
		}
		return $result;
	}

}
?>