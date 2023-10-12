<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{color:red;}
</style>
</head>
<body>
	<h1>스크립팅 원소</h1>
	
	<!-- HTML주석 : 개발자도구 탭에 노출됨 -->
	<%-- JSP주석 : 개발자도구 탭에 노출 안됨 --%>
	
	<%
		// 스크립틀릿 : 이안에 일반적인 자바코드 작성 (변수 선언 및 초기화, 제어문 등등)
		int sum = 0;
		for (int i=1; i <= 100; i++){
			sum += i;
		}			
	%>
	
	<p>
		화면으로 출력하고자 한다면 <br>
		스크립틀릿 이용해서 출력 가능 : <% out.println(sum); %> <br>
		표현식(출력식) 이용해서 출력가능 : <%=sum %>
	</p>
	
	<%
		String[] nameArr = {"최지원", "김개똥", "홍길동"};
	%>
	
	<h5>배열의 길이 : <%=nameArr.length %></h5>
	<h5>배열에 담긴 값 : <%= String.join(",", nameArr) %></h5>
	
	<h4>반복문을 통해 html요소 반복적으로 화면에 출력할 수 있다.</h4>
	
	<ul>
		<% for(int i = 0; i < nameArr.length; i++){ %>
			<li><%=nameArr[i] %></li>
		<%} %>
		<br>
		<% for(String name : nameArr){ %>
			<li><%=name %></li>
		<%} %>
	</ul>
	
	

</body>
</html>