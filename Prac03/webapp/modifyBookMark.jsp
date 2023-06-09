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
      							alert("북마크 그룹 정보를 수정하였습니다.");
    						}
  					</script>
					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">
						<%
						String id = request.getParameter("id");
						BookMarkController bookMarkController = new BookMarkController();
						BookMark bookMark = bookMarkController.getBookMarkInfo(id);
						%>
					
					<body>
						<jsp:include page="header.jsp"></jsp:include>
						
						<form action="modifyBookMark.jsp" id = "modifyBookMark"  onsubmit="showSubmitAlert()">
						<input type="hidden" name="id" value ="<%=bookMark.getId()%>">
							<table>
								<tbody>
									<tr>
										<th>
											북마크 이름
										</th>
										<td style="text-align: left;">
											<input style="margin-left: 10px;" type="text" name="name" value ="<%=bookMark.getBookMark_Name()%>">
										</td>
									</tr>
									<tr>
										<th>
											순서
										</th>
										<td style="text-align: left;">
											<input style="margin-left: 10px;" type="text" name="number" value ="<%=bookMark.getBookMark_Num()%>">
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="submit" form="modifyBookMark" value="수정">
										</td>
									</tr>	
								</tbody>
							</table>
							</form>
								
								<% 	
									
									String name = request.getParameter("name");
									String number = request.getParameter("number");

									if (name != null && !name.isEmpty() && number != null && !number.isEmpty()) {
									bookMarkController.modifyBookMark(bookMark.getId(), name, number);
									response.sendRedirect("bookMarkGroup.jsp");
									} 
								%>
					</body>

					</html>