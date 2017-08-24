<?php
class User{

	private $table = "t_user";

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
        
        $text = "SELECT user_id, user_name, user_email, user_auth_token FROM $this->table 
            WHERE user_email = '$email' AND user_password = '$password' LIMIT 0,1";
        $query = mysql_query($text);
        if(mysql_num_rows($query) == 1){//HAS TO BE EXACT 1 RESULT
            $row = mysql_fetch_assoc($query);
            $result = $row;
        }
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

}
?>