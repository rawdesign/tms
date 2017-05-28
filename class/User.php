<?php
class User{

	private $table = "t_user";

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

}
?>