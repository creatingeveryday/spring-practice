package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request") //
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {  //requestUrl은 빈이 생성되는 시점에는 알 수가 없어서 외부에서 setter로 입력받는다.
        this.requestUrl = requestUrl;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestUrl+"] "+ message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create:"+ this);
    }

    @PreDestroy
    public void close() {
        System.out.println("");
        System.out.println("["+uuid+"] request scope bean close:"+ this);
    }
}
