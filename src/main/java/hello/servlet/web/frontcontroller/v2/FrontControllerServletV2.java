package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*") //v1 하위의 어떤 url이 들어와도 servlet이 호출된다,
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI(); //URI를 꺼낸다. (localhost 뒷부분이 쭉 꺼내짐, ex> "/front-controller/v1/members/new-form")

        ControllerV2 controller = controllerMap.get(requestURI); //URI를 기준으로 기존에 정의한 해쉬맵에서 원하는 객체가 반환될것이다.
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //컨트롤러가 없으면!
            return;
        }


        //front controller 에서 공통로직 처리!
        MyView view = controller.process(request, response); //process는 new MyView("/WEB-INF/views/new-form.jsp") 와 같은 뷰 객체를 반환해준다.
        view.render(request, response);

    }
}
