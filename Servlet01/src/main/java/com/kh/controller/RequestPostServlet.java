package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 요청성공");
		
		// 요청시 전달된 값들은 request의 parameter영역에 담겨있음
		
		// POST방식 요청같은 경우에는
		// 데이터를 추출하기 *전!*에 인코딩 설정을 반드시 해야함
		request.setCharacterEncoding("UTF-8");
		
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
		
		
		// 추출한 값들을 가지고 요청처리를 해야 함 (db와 상호작용)
		// >> service메서드 호출 > dao메서드 호출 > db sql문
		
		// 요청처리가 다 되었다는 가정하에 사용자가 보게될 응답 html 작성
		
		// 순수 Servlet방식 : Java코드 내에 html을 기술
		// JSP(Java Server Page) 방식 : html내에 java코드를 쓸 수 있음
		
		// 응답페이지를 만드는 과정을 JSP에게 위임(넘긴다)
		// 단, 응답화면(JSP)에서 필요로하는 데이터들을 차곡차곡 담아서 전달해줘야 한다
		// 차곡차곡 담기위한 공간 == request의 attribute 영역
		//request.setAttribute("키", 벨류);
		
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		// 응답하고자하는 뷰(JSP) 선택하면서 RequestDispatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp");
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 요청성공");
		doGet(request, response);
	}

}
