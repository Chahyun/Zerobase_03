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
      							alert("북마크 그룹 정보를 삭제하였습니다.");
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
						
						<form action="delete_bookMarkGroup.jsp" id = "delete_bookMarkGroup"  onsubmit="showSubmitAlert()">
						<input type="hidden" name="id" value ="<%=bookMark.getId()%>">
						<p>북마크 그룹 이름을 삭제하시겠습니까?</p>
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
											<a href="javascript:history.go(-1)">뒤로가기</a> | <input type="submit" form="delete_bookMarkGroup" value="삭제">
										</td>
									</tr>	
								</tbody>
							</table>
							</form>
								
								<% 	
									
									String name = request.getParameter("name");
									String number = request.getParameter("number");
									bookMarkController.deleteBookMark(bookMark.getId());
									bookMarkController.deleteBookMarkList(bookMark.getId());
									if (name != null && !name.isEmpty() && number != null && !number.isEmpty()) {
									response.sendRedirect("bookMarkGroup.jsp");
									} 
								%>
					</body>

					</html>