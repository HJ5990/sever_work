<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>피자 주문 페이지</h1>
	
	<h2>오늘의 날짜</h2>
	<%
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 DD일");
	%>
	
	<h4><%=sdf.format(today) %></h4>
	
	<br><br>
	
	<!-- 
		주문자정보, 주문정보
		pizzaPayment.jsp < pizza폴더
		pizzaServlet.java < 컨트롤러
	-->
	<form action="/JSP02/confirmPizza.do" method="get">
		<fieldset>
			<legend>주문자정보</legend>
			<table>
				<tr>
					<td>이름</td>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" required></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" required></td>
				</tr>
				<tr>
					<td>요청메세지</td>
					<td><textarea type="text" name="message" ></textarea></td>
				</tr>
			</table>
		</fieldset>
		<br>
		<fieldset>
			<legend>주문정보</legend>
			<table>
				<tr>
					<th>피자</th>
					<td>
						<select name="pizza">
							<option>콤비네이션</option>
							<option>치즈피자</option>
							<option>포테이토피자</option>
							<option>고구마피자</option>
							<option>불고기피자</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>토핑</th>
					<td>
						<input type="checkbox" name="topping" value="고구마무스">고구마무스
						<input type="checkbox" name="topping" value="콘크림무스">콘크림무스
						<input type="checkbox" name="topping" value="파인애플토핑">파인애플토핑
						<br>
						<input type="checkbox" name="topping" value="치즈바이트">치즈바이트
						<input type="checkbox" name="topping" value="치즈크러스트">치즈크러스트
						<input type="checkbox" name="topping" value="치즈토핑">치즈토핑
					</td>
				</tr>
				<tr>
					<th>사이드메뉴</th>
					<td>
						<input type="checkbox" name="side" value="콜라">콜라
						<input type="checkbox" name="side" value="사이다">사이다
						<input type="checkbox" name="side" value="갈릭소스">갈릭소스
						<br>
						<input type="checkbox" name="side" value="핫소스">핫소스
						<input type="checkbox" name="side" value="피클">피클
						<input type="checkbox" name="side" value="파마산가루">파마산가루
					</td>
				</tr>
				<tr>
					<th>결제방식</th>
					<td>
						<input type="radio" name="payment" value="card" checked> 카드결제
						<input type="radio" name="payment" value="cash"> 현금결제
					</td>
				</tr>
			</table>
		</fieldset>
	
		<br>
		<input type="submit" value="주문">
		<input type="reset">
	</form>
	
</body>
</html>