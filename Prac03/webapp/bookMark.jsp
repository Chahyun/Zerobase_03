<%@page import="java.util.List"%>
<%@page import="com.controller.BookMarkController"%>
<%@page import="com.dto.BookMark"%>
<%@page import="com.controller.WifiController" %>
<%@page import="com.dto.Wifi" %>
<%@page import="java.util.Map" %>
				<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">
				
					<%
						BookMarkController bookMarkController = new BookMarkController();
						List<Map<String, Object>> userBookMarkList = bookMarkController.getUserBookList();
					%>

					<body>
						<jsp:include page="header.jsp"></jsp:include>
							<table>
								<thead>
									<tr>
										<th>ID</th>
										<th>북마크 이름</th>
										<th>와이파이명</th>
										<th>등록일자</th>
										<th>비고</th>
									
									</tr>
								</thead>

								<tbody>
								<%if(userBookMarkList.size() == 0){%>
									<tr height ="80">
											<td colspan="5" style="text-align: center;"> 지정한 북마크가 없습니다. </td>
										</tr>
									
								<%} else {
									for(Map<String, Object> bookMark : userBookMarkList){
										String id = bookMark.get("id").toString();
									%>
									<tr>
										<td><%= bookMark.get("id") %></td>
										<td><%= bookMark.get("bookMarkName") %></td>
										<td><%= bookMark.get("wifiName") %></td>
										<td><%= bookMark.get("inputWifiDay") %></td>
										<td><a href="delete_bookMarkList.jsp?id=<%=id%>">삭제</a></td>
									</tr>
									<%}
									}%>
								</tbody>
							</table>
					</body>

					</html>