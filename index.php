<?php 
include("package/require.php");
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Testing</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>
	<div style="margin: 50px auto; width: 767px;max-width: 100%;">
	<h1>Testing API</h1>
	<form name="add" action="api/api_property.php?action=insert_data" enctype="multipart/form-data" method="post">
		<input id="owner" type="text" name="owner" placeholder="owner"><br/><br/>
		<input id="address" type="text" name="address" placeholder="address"><br/><br/>
		<input id="price" type="text" name="price" placeholder="price"><br/><br/>
		<input id="image" type="file" name="image"><br/><br/>
		<button id="simpan" type="submit">Submit Data</button>
	</form>
</body>	
</html>