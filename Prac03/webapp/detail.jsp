<%@page import="com.dto.Wifi"%>
<%@page import="com.controller.WifiController"%>
<%@page import="com.controller.BookMarkController"%>
<%@page import="com.dto.BookMark"%>
<%@page import="java.util.List" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<title>와이파이 정보 구하기</title>
					<style>
						th {
							width: 20%;
						}
					</style>
					<script>
  						function submitForm() {
    					var selectedBookmark = document.getElementById('bookMark').value;
    					if (selectedBookmark === '') {
      					alert('북마크 그룹을 선택해주세요.');
      					return;
    				}

    				document.getElementById('bookmarkForm').action = 'add_bookmark.jsp';
    				document.getElementById('bookmarkForm').submit();
  				}
				</script>
				</head>

				<link rel="stylesheet" href="style.css" type="text/css">

				<body>
					<jsp:include page="header.jsp"></jsp:include>
						<% 
							String distance = request.getParameter("distance");
							String mgNum = request.getParameter("x_swifi_mgr_no");
							WifiController wifiController = new WifiController();
							Wifi wifi = new Wifi();
							wifi = wifiController.detailWifi(mgNum);
						%>
					<div>
						<%	
							BookMarkController bookMarkController = new BookMarkController();
							List<BookMark> bookMarkList = bookMarkController.getBookMarkList();
						%>
						<form action="add_bookmark.jsp" method="post">
						<select name = "bookmarkId" id = "bookmarkId">
						<option value="">북마크 그룹 이름 선택</option>
						<% for(BookMark bookMark : bookMarkList) {%>
						<option value="<%= bookMark.getId()%>"><%= bookMark.getBookMark_Name()%></option>
						<%}%>
						</select>
							<input type="hidden" name="mgNum" value="<%= mgNum %>">
							<input type="submit" value="즐겨찾기 추가하기">
						</form>
						
					</div>
						
						<table>
						<tbody>
								<tr>
									<th>거리(Km)</th><td><%= distance%></td>
								</tr>
								<tr>
									<th>관리번호</th><td><%= mgNum%></td>
								</tr>
								<tr>
									<th>자치구</th><td><%= wifi.getX_SWIFI_WRDOFC()%></td>
								</tr>
								<tr>
									<th>와이파이명</th><td><%= wifi.getX_SWIFI_MAIN_NM()%></td>
								</tr>
								<tr>
									<th>도로명주소</th><td><%= wifi.getX_SWIFI_ADRES1()%></td>
								</tr>
								<tr>
									<th>상세주소</th><td><%= wifi.getX_SWIFI_ADRES2()%></td>
								</tr>
								<tr>
									<th>설치위치(층)</th><td><%= wifi.getX_SWIFI_INSTL_FLOOR()%></td>
								</tr>
								<tr>
									<th>설치유형</th><td><%= wifi.getX_SWIFI_INSTL_TY()%></td>
								</tr>
								<tr>
									<th>설치기관</th><td><%= wifi.getX_SWIFI_INSTL_MBY()%></td>
								</tr>
								<tr>
									<th>서비스구분</th><td><%= wifi.getX_SWIFI_SVC_SE()%></td>
								</tr>
								<tr>
									<th>망종류</th><td><%= wifi.getX_SWIFI_CMCWR()%></td>
								</tr>
								<tr>
									<th>설치년도</th><td><%= wifi.getX_SWIFI_CNSTC_YEAR()%></td>
								</tr>
								<tr>
									<th>실내외구분</th><td><%= wifi.getX_SWIFI_INOUT_DOOR()%></td>
								</tr>
								<tr>
									<th>WIFI접속환경</th><td><%= wifi.getX_SWIFI_REMARS3()%></td>
								</tr>
								<tr>
									<th>X좌표</th><td><%= wifi.getLNT()%></td>
								</tr>
								<tr>
									<th>Y좌표</th><td><%= wifi.getLAT()%></td>
								</tr>
								<tr>
									<th>작업일자</th><td><%= wifi.getWORK_DTTM()%></td>
								</tr>
							</tbody>
						</table>
				</body>

				</html>