<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Networking Site - Monitoring client</title>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css" />

<script language="javascript">
	var BASE_URL = "http://localhost:8080/socialnetworking/";

	onload = function() {
		getProcessingTime();
	};

	function getProcessingTime() {
		try {
			var request = new XMLHttpRequest();
			request.open("GET", BASE_URL + 'RESTQ/monitor/processingtime',
					false);
			request.onreadystatechange = function() {
				if (request.status === 200) {
					//TODO
					document.getElementById("content").innerHTML = content;
				} else {
					document.getElementById("content").innerHTML = "Unable to load the content";
					alert('Error');
				}
			};
			request.send(null);
		} catch (err) {
			alert(err);
			document.getElementById("content").innerHTML += "\nXMLHttprequest error: "
					+ err.description;
		}
	}

	function getQueueDepth() {
		try {
			var request = new XMLHttpRequest();
			request.open("GET", BASE_URL + 'RESTQ/monitor/queuedepth', false);
			request.onreadystatechange = function() {
				if (request.status === 200) {
					var det = eval("(" + request.responseText + ")");
					alert("Number of pending html requests being handled by the service: "
							+ det);
				} else {
					alert('Error');
				}
			};
			request.send(null);
		} catch (err) {
			alert(err);
		}
	}

	function getQueueProcessService() {
		try {
			var request = new XMLHttpRequest();
			var x = document.getElementById("ddlResolution").selectedIndex;
			var resoultionBy = document.getElementsByTagName("option")[x].value;
			request.open("GET", BASE_URL + 'RESTQ/monitor/qps' + '?resoultion='
					+ resoultionBy, false);
			request.onreadystatechange = function() {
				if (request.status === 200) {
					//TODO: here get the collection of Message object
					document.getElementById("content").innerHTML = content;
				} else {
					document.getElementById("content").innerHTML = "Unable to load the button";
					alert('Error');
				}
			};
			request.send(null);
		} catch (err) {
			alert(err);
			document.getElementById("content").innerHTML += "\nXMLHttprequest error: "
					+ err.description;
		}
	}

	function getMessageWithError() {
		try {
			var request = new XMLHttpRequest();
			var type = document.getElementById("txtError").value;
			if (type != "") {
				request.open("GET", BASE_URL + 'RESTQ/monitor/errors'
						+ '?type=' + type, false);
				request.onreadystatechange = function() {
					if (request.status === 200) {
						//TODO: here get the collection of Message object
						document.getElementById("content").innerHTML = content;
					} else {
						document.getElementById("content").innerHTML = "Unable to find the message for entered error code";
						alert('Error');
					}
				};
			} else {
				alert("Enter the Error Code!");
				return;
			}
			request.send(null);
		} catch (err) {
			alert(err);
			document.getElementById("content").innerHTML += "\nXMLHttprequest error: "
					+ err.description;
		}
	}
</script>
</head>
<body>
	<div id="container_monitor">
		<div id="nav_bar">

			<div style='width: 400px; float: left;'>
				<a href="#" onclick="getProcessingTime();">Processing Time</a>&nbsp;|&nbsp;
				<a href="#" onclick="getQueueDepth();">Queue Depth</a>
			</div>
		</div>
		<table width="100%" class="monitor_container">
			<tr>
				<td width="50%">
					<div style="margin-bottom: 5px; font-weight: bold;">
						<img src="icons/2.png">&nbsp;Message With Error
					</div> <b>Error Code:</b> <input type="text" id="txtError">
					<div style="width: 100%; clear: both;" align="right">
						<br> <input type="submit" value=" Show Message "
							class="button" onclick="getMessageWithError();">
					</div>
				</td>
				<td width="50%">
					<div style="margin-bottom: 5px; font-weight: bold;">
						<img src="icons/2.png">&nbsp;Queue Process Message
					</div> <b>Resolution By:</b> <select id="ddlResolution">
						<option value="Minutes">Minutes</option>
						<option value="Hours">Hours</option>
						<option value="Days">Days</option>
						<option value="Months">Months</option>
				</select>
					<div style="width: 100%; clear: both;" align="right">
						<br> <input type="button" value=" Show Message "
							class="button" onclick="getQueueProcessService();">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan=2><div id="content"></div></td>
			</tr>
			<tr>
				<td colspan=2><div id="footer" style="text-align: center;">
						&copy; Milson Munakami | <b><a href="http://www.milson.com.np"
							target="_blank">Milstein</a></b>
					</div></td>
			</tr>
		</table>
	</div>

</body>
</html>