<?php
	 $response = array();
	 $response["success"] = false;
	function send_notification ($tokens,$message){
 		global $response;
		$url='https://fcm.googleapis.com/fcm/send';
		$fields = array(
			'registration_ids' => $tokens,
			'data' => $message
			);
	$headers = array(
			'Authorization:key = 
				AAAA5UiwYdQ:APA91bEI8qsHmGNHH59FWdz1jtOVVqrlXKidn8SrWpxMWEizeE7VppLi6qBcs3hwxohhwPm3aWB8t-_StlzGgi4G0-8wTfRCpsN_Hdn9XnoWTw_5CAbsF-8HZQvuDfhHZwoQJ64Gs2NH',
			'Content-Type: application/json'
			);
		
	   	$ch = curl_init();
       		curl_setopt($ch, CURLOPT_URL, $url);
       		curl_setopt($ch, CURLOPT_POST, true);
       		curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
      		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       		curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       		curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       		$result = curl_exec($ch);      
		echo $result . "<br>";     
       		if ($result === FALSE) {
           			die('Curl failed: ' . curl_error($ch));
       		}else{
			 $response["success"] = true;
		}
       		curl_close($ch);
		return $result;
	}
	
 	$conn=mysqli_connect("localhost","whi2020","h20151616!","whi2020");
	mysqli_query($conn,'SET NAMES utf8');
	$msg="12312536135";
	$title="123";
	$sql="Select Token From users where id=3";
	$result=mysqli_query($conn,$sql);
	$tokens=array();
	$row = mysqli_fetch_assoc($result);
	$tokens[]=$row["Token"];
	mysqli_close($conn);
	$message=array("title"=>$title,"message" => $msg);
	$message_status=send_notification($tokens,$message);


 ?>