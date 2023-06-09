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
      							alert("북마크 그룹 정보를 생성하였습니다.");
    						}
  					</script>
					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">
				
					

					<body>
						<jsp:include page="header.jsp"></jsp:include>
						<form action="makeBookMark.jsp" id = "makeBookMark"  onsubmit="showSubmitAlert()">
							<table>

								<tbody>
										
									<tr>
										<th>
											북마크 이름
										</th>
										<td style="text-align: left;">
											<input style="margin-left: 10px;" type="text" name="name">
										</td>
											</tr>
											<tr>
												<th>
													순서
												</th>
												<td style="text-align: left;">
													<input style="margin-left: 10px;" type="text" name="number">
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<input type="submit" form="makeBookMark" value="생성">
												</td>
											</tr>
										
								</tbody>
							</table>
							</form>
								<% 
									String name = request.getParameter("name");
									String number = request.getParameter("number");
									BookMarkController bookMarkController = new BookMarkController();

									if (name != null && !name.isEmpty() && number != null && !number.isEmpty()) {
									bookMarkController.inputBookMark(name, number);
									response.sendRedirect("bookMarkGroup.jsp");
									} 
								%>
					</body>

					</html>