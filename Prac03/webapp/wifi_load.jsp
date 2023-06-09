<%@page import="com.controller.WifiController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>>와이파이 정보 구하기</title>
</head>
<body>
<%
	WifiController wifiController = new WifiController();
	wifiController.inputWifi();
%>
 
<h1 style="text-align: center;"><%=wifiController.getWifiCnt()%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
 <div style="text-align: center;"><a href="index.jsp">홈으로 가기</a></div>
</body>
</html>