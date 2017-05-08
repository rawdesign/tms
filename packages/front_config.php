<?php 
$global['absolute-url'] = "http://www.bonitajewelery.com/devel/tms/";
$global['root-url'] = $_SERVER['DOCUMENT_ROOT']."/devel/tms/";
$global['root-url-model'] = $global['root-url']."class/";
$global['api'] = $global['absolute-url']."api/";
$global['path-head'] = "packages/head.php";
$global['path-config'] = "packages/front_config.php";

//config for smtp email
$smtp['url'] = "www.eannovate.com";
$smtp['to'] = "dean11.cliff@gmail.com";
$smtp['from'] = "developer@eannovate.com";
$smtp['password'] = "Developer88";
//config for setup firebase
$firebase['base-url'] = "https://fcm.googleapis.com/fcm/send";
$firebase['server-key'] = "AAAAH1jTEdA:APA91bGT8hE9njW5UTIXxlFhXxgOmnVgp22SKQmegblDLfju8F4G4zH-RN348iUufK2h6QWe39TLnBhBiBudkAhYGaxA1cA6QGdFNYWGFvQ2npINTHwhFpGzeLBFwXWTcKUvVWtEyfRD";
?>