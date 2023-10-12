package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET방식으로 요청시 해당 doGet메서드가 자동으로 호출된다.
		//System.out.println("GET요청 정상적으로 받았습니다.");
		
		/**
		 * 첫번째 매개변수인 request에는 요청시 전달된 내용들이 담겨있음 (사용자가 입력한 값, 요청전송방식, 요청자의 ip주소 등등)
		 * 두번째 매개변수인 response는 요청 처리 후 응답할때 사용되는 객체
		 * 
		 * 요청 처리를 위해서 요청시 전달된 값들 추출
		 * request의 parameter영역 안에 존재 (키=벨류 세트로 담겨있음)
		 * 
		 * 따라서 request의 parameter영역으로부터 전달된 데이터 추출하는 메서드
		 * > request.getParameter("키") : String형
		 */
		
		String name = request.getParameter("name");  // "홍길동" | ""
		String gender = request.getParameter("gender");  // "M" | "F" | unll
		int age = Integer.parseInt(request.getParameter("age"));  // "47" => 47 | "" => NumberFormatException 오류
		String city = request.getParameter("city");  // "서울" | "경기" 등등 (무조건하나는옴 select)
		double height = Double.parseDouble(request.getParameter("height"));   // "170" => 170.00~ (무조건하나는옴)
		
		// 체크박스 같은 복수의 벨류값들을 추출하고자 할 때
		String[] foods = request.getParameterValues("food");  // ["한식","중식"] | unll
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if (foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join("/", foods));
		}
		
		// 추출한 값(요청시 전달된 값)들을 가지고 요청처리를 해야됨(db와 상호작용)
		// >> Service 메서드 호출 > Dao메서드 > DB sql문 실행 
		
		/**
		 * int result = new MemberService().insertMember(name, gender, age, city, height, foods);
		 * 
		 * if (result > 0){
		 * 		성공 -> 성공페이지 응답
		 * } else {
		 * 		실패 -> 실패페이지 응답
		 * }
		 */
		
		// 위에서의 요청처리 후 성공했다는 가정하에 사용자가 보게될 응답페이지 (html) 만들어서 전송
		// 즉, 여기 Java코드 내에 사용자가 보게될 응답 html 구문을 작성할 것
		
		// 장점 : Java 코드 내에서 작성하기 때문에 반복문이나 조건문, 유용한 메서드 등을 활용할 수 있다.
		// 단점 : 불편, 복잡, 혹시라도 차후에 html을 수정한다면 Java 코드를 수정하는 것이기 때문에
		//			수정된 내용된 반영하려면 서버를 재실행(restart) 해야됨
		
		
		// *response 객체를 통해 사용자에게 html(응답화면) 전달
		// (순수 Servlet방식)
		// 1) 이제부터 내가 출력할 내용은 문서형태의 html이고 문자셋은 UTF-8이다
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 응답하고자하는 사용자(요청했던 사용자)와의 스트림(클라이언트와의 통로)을 생성
		PrintWriter out = response.getWriter();
		
		// 3) 위에 만든 스트림을 통해 응답 html 구문을 한줄씩 출력 
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("h2{color:red}");
		out.println("#name{color:orange}");
		out.println("#age{color:yellow}");
		out.println("#city{color:green}");
		out.println("#height{color:purple}");		
		out.println("</style>");
		
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인정보응답화면</h2>");
		
//		out.println("<span id='name'>"+name+"</span>");  아래랑 같은 코드
		out.printf("<span id='name'>%s</span>님은 ", name);
		out.printf("<span id='age'>%d</span>살이며, ", age);
		out.printf("<span id='city'>%s</span>에 삽니다.", city);
		out.printf("키는 <span id='height'>%.1f</span>cm이고, ", height);
		
		out.print("성별은");
		if (gender == null) {
			out.println("선택하지 않았습니다. <br>");
		} else {
			if (gender.equals("M")) {
				out.println("<span id='gender'>남자</span>입니다.");
			} else {
				out.println("<span id='gender'>여자</span>입니다.");
			}
		}
		
		out.print("좋아하는 음식은");
		if (foods == null) {
			out.println("없습니다.");
		} else {
			out.println("<ul>");
			for (int i = 0; i < foods.length; i++) {
				out.println("<li>" + foods[i] + "</li>");
			}
			out.println("</ul>");
		}
		
		
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
