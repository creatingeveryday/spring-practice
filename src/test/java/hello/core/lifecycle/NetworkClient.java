package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//인터페이스 IntializingBean, DisposableBean
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자호출 url="+url);

//        생성 작업과 초기화 작업 분리
//        connect();
//        call("초기화 연결 메시지!!");

    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect="+url);
    }

    public void call(String message){
        System.out.println("call: "+ url + " message = "+message );
    }

    public void disconnect(){
        System.out.println("close :"+ url);
    }




    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지!!");
    }


    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
