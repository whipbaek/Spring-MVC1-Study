package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/requset-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName))); //paramName은 key고 value는 getParameter로 꺼낸다.

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");

        String username = request.getParameter("username");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("sex = " + sex);

        System.out.println("[단일 파라미터 조회] - end");

        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        //http://localhost:8080/request-param?username=BAEK&age=12&sex=man&username=KIM 이런식으로 username을 여러개 넣을 수 있다. 이럴때 사용
        String[] usernames = request.getParameterValues("username"); //배열로 username 쿼리값을 반환
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();


        response.getWriter().write("ok"); // 화면에 ok를 띄워줌

    }
}
