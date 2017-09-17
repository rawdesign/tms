<?php
class PropertyImage{

	private $table = "t_property_image";

    public function get_image_sync_by_property($user_id, $property, $datas){
        $result = 0;
        $data = json_decode($datas);
        if(is_array($data)){
            $q_string = "";
            $count = 1;
            foreach($data as $i){
                $seperate = $count == count($data) ? "" : ",";
                $q_string .= "'".$i->token."'".$seperate;
                $count++;
            }
            $cond = "AND pi_token NOT IN(".$q_string.")";
        }else{
            $cond = "";
        }

        $text = "SELECT pi_token, pi_property, pi_img, pi_img_thmb, pi_status FROM $this->table 
            WHERE pi_status = 'success' AND pi_property = '$property' $cond";
        $query = mysql_query($text);
        if(mysql_num_rows($query) >= 1){
            $result = array();
            while($row = mysql_fetch_assoc($query)){
                $result[] = $row;
            }
        }
        //$result = $text;
        return $result;
    }

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