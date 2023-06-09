<%@page import="java.io.IOException"%>
<%@page import="java.util.Map"%>
<%@page import="com.dto.BookMark"%>
<%@page import="com.controller.BookMarkController"%>
				<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<!DOCTYPE html>
					<html>
					<style>
						th {
							width: 20%;
						}
					</style>
					 <script>
    						function showSubmitAlert() {
      							alert("북마크 정보를 삭제하였습니다.");
    						}
  					</script>
					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">
						
					
					<body>
						<jsp:include page="header.jsp"></jsp:include>
						<%
							String id = request.getParameter("id");
							BookMarkController bookMarkController = new BookMarkController();
							Map<String,Object> bookMark = bookMarkController.getUserBookMark(id);

						%>
					
						<form action="delete_bookMarkList.jsp" method="POST" id = "delete_bookMarkList"  onsubmit="showSubmitAlert()">
						<p>북마크를 삭제하시겠습니까?</p>
						<input type="hidden" name="id" value ="<%=bookMark.get("id")%>">
							<table>
								<tbody>
									<tr>
										<th>
											북마크 이름
										</th>
										<td style="text-align: left;">
											<%=bookMark.get("bookMarkName")%>
										</td>
									</tr>
									<tr>
										<th>
											와이파이명
										</th>
										<td style="text-align: left;">
											<%=bookMark.get("wifiName")%>
										</td>
									</tr>
									<tr>
										<tr>
										<th>
											등록일자
										</th>
										<td style="text-align: left;">
											<%=bookMark.get("inputWifiDay")%>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<a href="javascript:history.go(-1)">뒤로가기</a> | <input type="submit" form="delete_bookMarkList" value="삭제">
										</td>
									</tr>	
								</tbody>
							</table>
							</form>
							<%
							if (request.getMethod().equals("POST")) {
						    	try {
						        	bookMarkController.deleteUserBookMark(id);
						        	response.sendRedirect("bookMark.jsp");
						    	} catch (IOException e) {
						        	e.printStackTrace();
						    	}
							}
								%>
					</body>

					</html>