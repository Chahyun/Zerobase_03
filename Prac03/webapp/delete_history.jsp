<%@ page import="com.controller.WifiController" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String id = request.getParameter("id");
    WifiController wifiController = new WifiController();
    wifiController.deleteHistory(id);
%>