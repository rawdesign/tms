<?php
class Mail{
	
	public function mail_forget_password($name, $password){
		$result = " 
	        <!DOCTYPE html>
	        <html>
	        <head>
	        <title></title>
	        </head>
	        <body>
	        Dear ".$name."
	        <br/><br/>
	        Berikut password kamu di TMS One<br/>
	        <strike><b>".$password."</b></strike><br/>
	        <br/><br/>
	        Selamat.<br/>
	        Regards,
	        <br/><br/>
	        TMS One
	        <br/><br/>
	        <em>*Do not reply to this e-mail.<br/><br/>Thank you!</em>
	        </body>
	        </html>
        	";
        return $result;
	}

}
?>