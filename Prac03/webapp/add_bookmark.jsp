<%@page import="com.dto.BookMark"%>
<%@page import="com.controller.BookMarkController"%>
<%@page import="com.dto.Wifi"%>
<%@page import="com.controller.WifiController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String bookmarkId = request.getParameter("bookmarkId");
	String mgNum = request.getParameter("mgNum");

	BookMarkController bookMarkController = new BookMarkController();
	bookMarkController.addBookMarkList(bookmarkId, mgNum);
	response.sendRedirect("bookMark.jsp");
%>