<%@page import="com.controller.WifiController" %>
	<%@page import="com.dto.Wifi" %>
		<%@page import="java.util.List" %>
			<%@page import="java.util.Map" %>
				<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>와이파이 정보 구하기</title>
					</head>

					<link rel="stylesheet" href="style.css" type="text/css">
					<script>
						function clickBtn() {
							// BOM의 navigator객체의 하위에 geolocation객체가 새로 추가되었음.

							alert("내 위치 가져오기 완료");
							window.navigator.geolocation.getCurrentPosition(function (position) { //OK
								var lat = position.coords.latitude;
								var lnt = position.coords.longitude;

								document.getElementById('myLat').value = lat;
								document.getElementById('myLnt').value = lnt;
							});
						}

						function selWifi() {
							document.getElementById('selWifi').submit();
						}
					</script>
					<script type="text/javascript">
						document.addEventListener('DOMContentLoaded', function() {
							var links = document.querySelectorAll('.submit-link');
							for (var i = 0; i < links.length; i++) {
								links[i].addEventListener('click', function(e) {
									e.preventDefault();
									var row = e.target.closest('tr');
									var distance = row.querySelector('.distance').innerText;
									var mgrNo = row.querySelector('.mgr-no').innerText;
					
									var form = document.createElement('form');
									form.method = "POST";
									form.action = "./detail.jsp";
					
									var input1 = document.createElement('input');
									input1.type = 'hidden';
									input1.name = 'distance';
									input1.value = distance;
					
									var input2 = document.createElement('input');
									input2.type = 'hidden';
									input2.name = 'x_swifi_mgr_no';
									input2.value = mgrNo;
					
									form.appendChild(input1);
									form.appendChild(input2);
									document.body.appendChild(form);
					
									form.submit();
								});
							}
						});
						</script>

					<body>
						<jsp:include page="header.jsp"></jsp:include>


						<% 
							String lat=request.getParameter("myLat")==null ? "0.0" : request.getParameter("myLat");
							String lnt=request.getParameter("myLnt")==null ? "0.0" : request.getParameter("myLnt");
							
							%>
							<div style="float:left; margin-top : 10px">
								<form action="index.jsp" method="get" id="location">
									<label>LAT: </label><input type="text" id="myLat" name="myLat" value=<%=lat%>>
									<label> , LNT: </label><input type="text" id="myLnt" name="myLnt" value=<%=lnt%>>
								</form>
							</div>
							<div style="float:left; margin-left: 5px; margin-top: 10px;">
								<button onclick="clickBtn()">내 위치 가져오기</button>
								<button type="submit" form="location">근처 WIFI 정보 가져오기</button>
							</div>

							<table>
								<thead>
									<tr>
										<th>거리(Km)</th>
										<th>관리번호</th>
										<th>자치구</th>
										<th>와이파이명</th>
										<th>도로명주소</th>
										<th>상세주소</th>
										<th>설치위치(층)</th>
										<th>설치유형</th>
										<th>설치기관</th>
										<th>서비스구분</th>
										<th>망종류</th>
										<th>설치년도</th>
										<th>실내외구분</th>
										<th>WIFI접속환경</th>
										<th>X좌표</th>
										<th>Y좌표</th>
										<th>작업일자</th>
									</tr>
								</thead>

								<tbody>
									<% if(lat=="0.0" || lnt=="0.0" || request.getParameter("myLat")==null ||
										request.getParameter("myLnt")==null || request.getParameter("myLat").isEmpty()
										|| request.getParameter("myLnt").isEmpty() ){ %>


										<tr height ="80">
											<td colspan="17" style="text-align: center;"> 위치 정보를 입력한 후에 조회해 주세요. </td>
										</tr>
										<% }else { 
											WifiController wifiController=new WifiController(); 
											List<Wifi>wifiList = wifiController.myPosWifi(lat, lnt);
											wifiController.inputHistory(lat, lnt);	
											for (Wifi wifi : wifiList) {
											%>

											<tr>
												<td class="distance">
													<%= wifi.getDistance()%>
												</td>
												<td class="mgr-no">
													<%= wifi.getX_SWIFI_MGR_NO() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_WRDOFC() %>
												</td>
												<td>
													<a class="submit-link" href="./detail.jsp">
														<%= wifi.getX_SWIFI_MAIN_NM() %>
													</a>
												</td>
												<td>
													<%= wifi.getX_SWIFI_ADRES1() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_ADRES2() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_INSTL_FLOOR() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_INSTL_TY() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_INSTL_MBY() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_SVC_SE() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_CMCWR() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_CNSTC_YEAR() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_INOUT_DOOR() %>
												</td>
												<td>
													<%= wifi.getX_SWIFI_REMARS3() %>
												</td>
												<td>
													<%= wifi.getLNT() %>
												</td>
												<td>
													<%= wifi.getLAT() %>
												</td>
												<td>
													<%= wifi.getWORK_DTTM() %>
												</td>

											</tr>
											<% } } %>
								</tbody>
							</table>
					</body>

					</html>