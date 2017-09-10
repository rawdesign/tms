<?php
class User{

	private $table = "t_user";

    public function check_password($id, $password){
        $result = 0;
    
        $text = "SELECT user_password FROM $this->table WHERE user_id = '$id' AND user_password = '$password' LIMIT 0,1";
        $query = mysql_query($text);
        if(mysql_num_rows($query) == 1){
            $result = 1;
        }
        return $result;
    }

    public function check_code($auth_token, $id){//check the token and id before changing the content
        $result = 0;

        $text = "SELECT user_id FROM $this->table WHERE user_auth_token = '$auth_token' AND user_id = '$id' LIMIT 0,1";
        $query = mysql_query($text);
        if(mysql_num_rows($query) == 1){
            $result = 1;//can be used
        }
        return $result;  
    }

    public function login($email, $password){
        $result = 0;//FAILED
        
        $text = "SELECT user_id, user_no_tmc, user_name, user_card_name, user_email, user_province, user_city,
            user_height, user_weight, user_size, user_work, user_tempat_lahir, user_birthday, user_gender,
            user_status_menikah, user_warga_negara, user_ktp, user_address, user_mail_address, user_religion,
            user_phone1, user_phone2, user_phone3, user_npwp, user_instagram, user_whatsapp, user_bank, 
            user_cabang, user_no_rek, user_img, user_scan, user_auth_token FROM $this->table 
            WHERE user_email = '$email' AND user_password = '$password' LIMIT 0,1";
        $query = mysql_query($text);
        if(mysql_num_rows($query) == 1){//HAS TO BE EXACT 1 RESULT
            $row = mysql_fetch_assoc($query);
            $result = $row;
        }
        //$result = $text;
        return $result;
    }

    public function get_forget_password($email){
        $result = 0;
    
        $text = "SELECT user_id, user_name, user_email, user_password 
            FROM $this->table WHERE user_email = '$email' LIMIT 0,1";
        $query = mysql_query($text);
        if(mysql_num_rows($query) >= 1){
            $result = array();
            while($row = mysql_fetch_assoc($query)){
                $result[] = $row;
            }
        }
        return $result;
    }

    public function update_data($id, $name){
        $result = 0;

        $text = "UPDATE $this->table SET user_name = '$name' WHERE user_id = '$id'";
        $query = mysql_query($text);    
        if(mysql_affected_rows() == 1){
            $result = 1;
        }
        return $result;
    }

    public function update_password($id, $old_password, $new_password){
        $result = 0;

        $text = "UPDATE $this->table SET user_password = '$new_password' WHERE user_id = '$id' AND user_password = '$old_password'";
        $query = mysql_query($text);
        if(mysql_affected_rows() == 1){
            $result = 1;
        }
        return $result;
    }

}
?>