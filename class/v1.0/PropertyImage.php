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

	public function delete_data($token, $path){
        $result = 0;
        $this->remove_image($token, $path); //remove image before

        $text = "DELETE FROM $this->table WHERE pi_token = '$token'";
        $query = mysql_query($text);
        if(mysql_affected_rows() == 1){
            $result = 1;
        }
        return $result;
    }

    public function remove_image($token, $path){
        $result = 0;
        $flag_img = 0;
        $flag_img_thmb = 0;

        $text = "SELECT pi_img, pi_img_thmb FROM $this->table WHERE pi_token = '$token'";
        $query = mysql_query($text);
        if(mysql_num_rows($query) == 1){
            $row = mysql_fetch_assoc($query);
            if($row['pi_img'] != "" && $row['pi_img_thmb'] != ""){
                $deleteImg = $path.$row['pi_img'];
                if (file_exists($deleteImg)) {
                    unlink($deleteImg);
                    $flag_img = 1;
                }

                $deleteImgThmb = $path.$row['pi_img_thmb'];
                if (file_exists($deleteImgThmb)) {
                    unlink($deleteImgThmb);
                    $flag_img_thmb = 1;
                }
                
                if($flag_img == 1 && $flag_img_thmb ==1){
                    $result = 1;
                }
            }
        }
        return $result;
    }

}
?>