<%@page import="com.dto.BookMark"%>
<%@page import="com.controller.BookMarkController"%>
<%@page import="java.util.List" %>
				<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">

					<body>
						<jsp:include page="header.jsp"></jsp:include>

							<a href="makeBookMark.jsp"><button>북마크 생성하기</button></a>
							

							<table>
								<thead>
									<tr>
										<th>ID</th>
										<th>북마크 이름</th>
										<th>순서</th>
										<th>등록일자</th>
										<th>수정일자</th>
										<th>비고</th>
									
									</tr>
								</thead>
								<% 
								BookMarkController bookMarkController = new BookMarkController();
								List<BookMark> bookMarkList = bookMarkController.getBookMarkList();
								%>
								<tbody>	
								<%if(bookMarkList.size() == 0){%>
									<tr height ="80">
											<td colspan="6" style="text-align: center;"> 지정한 북마크위치가 없습니다. </td>
										</tr>
										<%
								} else {
										for(BookMark bookMark : bookMarkList) {
										%>
											<tr>
												<td><%= bookMark.getId()%></td>
												<td><%= bookMark.getBookMark_Name()%></td>
												<td><%= bookMark.getBookMark_Num()%></td>
												<td><%= bookMark.getCreateDay()%></td>
												<td><%= bookMark.getUpdateDay() == null ? "" : bookMark.getUpdateDay()%></td>
												<td>
            										<a href="modifyBookMark.jsp?id=<%= bookMark.getId()%>">수정</a>
            										<a href="delete_bookMarkGroup.jsp?id=<%= bookMark.getId()%>">삭제</a>
          										</td>
											</tr>
										<%}
										
										}%>
								</tbody>
							</table>
					</body>

					</html>