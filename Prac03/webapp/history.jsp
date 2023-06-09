<%@page import="com.dto.History"%>
<%@page import="com.controller.WifiController" %>
        <%@page import="java.util.List" %>
                <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="UTF-8">
                        <title>와이파이 정보 구하기</title>
                        <script>
                        	function deleteHistory(id) {
                            	if (confirm("정말로 삭제하시겠습니까?")) {
                                	// Ajax 요청 보내기
                                	var xhr = new XMLHttpRequest();
                                	xhr.open("POST", "delete_history.jsp", true);
                                	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                	xhr.onreadystatechange = function() {
                                    if (xhr.readyState === 4 && xhr.status === 200) {
                                        // 요청 완료 후 테이블 업데이트
                                    location.reload();
                                    	}
                                	};
                                	xhr.send("id=" + encodeURIComponent(id));
                           		}
                        	}
                        </script>
                    </head>

                    <link rel="stylesheet" href="style.css" type="text/css">

                    <body>
                      <jsp:include page="header.jsp"></jsp:include>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>X좌표</th>
                                    <th>Y좌표</th>
                                    <th>조회일자</th>
                                    <th>비고</th>
                                </tr>
                            </thead>

                            <tbody>
                            <%
                            	WifiController wifiController = new WifiController();
                         		List<History> hitoryList = wifiController.getHistoryList();
                            %>
                            <%if(hitoryList.size() == 0) {%>
                            	<tr height ="80">
									<td colspan="5" style="text-align: center;"> 히스토리가 존재하지 않습니다. </td>
								</tr>
								
                            	<% } else {
                            	for(History history : hitoryList){ %>
                                <tr>                                  
                                  <td><%= history.getId() %> </td>
                                  <td><%= history.getLat() %></td>
                                  <td><%= history.getLnt() %></td>
                                  <td><%= history.getInquiryDate() %></td>                    
                                  <td>
                                  <button type="button" onclick="deleteHistory('<%= history.getId() %>')">삭제</button>
                                  </td>
                                </tr>
                                <% } 
                                }%>
                                
                            </tbody>
                        </table>
                    </body>

                    </html>