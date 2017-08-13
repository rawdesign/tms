<?php
class Property{

	private $table = "t_property";

	public function insert_data($token, $owner, $title, $price, $address, $description, $type, $status_property, $bed, $bath, $floor, $luas_apartment, $luas_bangunan, $luas_tanah, $lebar_depan, $sertifikat, $status, $create_date){
		$result = 0;

		$text = "INSERT INTO $this->table (property_token, property_owner, property_title, property_price, property_address, property_description, property_type, property_status_property, property_bed, property_bath, property_floor, property_luas_apartment, property_luas_bangunan, property_luas_tanah, property_lebar_depan, property_sertifikat, property_status, property_create_date) 
			VALUES('$token', '$owner', '$title', '$price', '$address', '$description', '$type', '$status_property', '$bed', '$bath', '$floor', '$luas_apartment', '$luas_bangunan', '$luas_tanah', '$lebar_depan', '$sertifikat', '$status', '$create_date')";
		$query = mysql_query($text);
		if($query){
			$result = 1;
		}
		return $result;
	}

}
?>