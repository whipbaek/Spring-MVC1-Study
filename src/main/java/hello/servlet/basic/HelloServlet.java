package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //해당 url로 가면 서블릿이 실행된다.
public class HelloServlet extends HttpServlet { //서블릿은 HttpServlet을 상속받는다.

    @Override //서블릿이 호출되면 service가 실행된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); //쿼리 파라미터를 조회할 수 있다.
        System.out.println("username = " + username);

        //http헤더에 들어감 (content type)
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); //Encoding 방법

        //http메시지 바디에 데이터가 들어간다.
        response.getWriter().write("hello " + username);

    }
}
