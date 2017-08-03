<?php
class Owner{

	private $table = "t_owner";
    private $itemPerPage = 6;

    public function get_owner_sync($user_id, $datas){
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
            $cond = "AND owner_token NOT IN(".$q_string.")";
        }else{
            $cond = "";
        }

        $text = "SELECT owner_id, owner_user_id, owner_token, owner_name, owner_location, owner_phone,
            owner_email, owner_ktp, owner_birthday, owner_img, owner_status, owner_num_property,
            owner_create_date FROM $this->table WHERE owner_status = 'success' AND owner_user_id = '$user_id' 
            $cond ORDER BY owner_create_date ASC";
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

    public function get_owner($page=1, $keyword){
        $result = 0;
        $cond = "";
        if($keyword != ""){
            $keywords = explode(" ", $keyword);
            if(is_array($keywords)){
                $q_string = "";
                $last_index = intval(count($keywords)) - 1;
                for($i = 0; $i < count($keywords); $i++){
                    $q_string = $q_string . " owner_name LIKE '%".$keywords[$i]."%' ";
                    if($i != $last_index){
                      $q_string = $q_string . " OR ";
                    }
                }
                $cond = "AND ".$q_string;
            }
        }

        //get total data
        $text_total = "SELECT owner_id FROM $this->table WHERE owner_status = 'success' $cond";
        $query_total = mysql_query($text_total);
        $total_data = mysql_num_rows($query_total);
        $total_data = $total_data < 1 ? 0 : $total_data;

        //get total page
        $total_page = ceil($total_data / $this->itemPerPage);
        $limitBefore = $page <= 1 || $page == null ? 0 : ($page-1) * $this->itemPerPage;

        $text = "SELECT * FROM $this->table WHERE owner_status = 'success' $cond
            ORDER BY owner_create_date DESC LIMIT $limitBefore, $this->itemPerPage";
        $query = mysql_query($text);
        if(mysql_num_rows($query) >= 1){
            $result = array();
            while($row = mysql_fetch_assoc($query)){
                $result[] = $row;
            }
        }
        if(is_array($result)){
            $result[0]['total_page'] = $total_page;
            $result[0]['total_data_all'] = $total_data;
            $result[0]['total_data'] = count($result);
        }
        //$result = $text;
        return $result;
    }

    public function insert_data($user_id, $token, $name, $location, $phone, $email, $ktp, $birthday, $img, $img_thmb, $status, $num_property, $create_date){
        $result = 0;

        $text = "INSERT INTO $this->table (owner_user_id, owner_token, owner_name, owner_location, owner_phone, owner_email, owner_ktp, owner_birthday, owner_img, owner_img_thmb, owner_status, owner_num_property, owner_create_date) 
            VALUES ('$user_id', '$token', '$name', '$location', '$phone', '$email', '$ktp', '$birthday', '$img', '$img_thmb', '$status', '$num_property', '$create_date')";
        $query = mysql_query($text);
        if($query){
            $result = 1;
        }
        //$result = $text;
        return $result;
    }

}
?>