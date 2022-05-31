package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;   //스프링 시작 단계에서는 request가 들어오지 않았기 때문에 빈 생성되지 않았음. 오류 발생. 프로바이더가 필요하다!

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){

        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestUrl(requestUrl);
        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "okay";


    }
}
