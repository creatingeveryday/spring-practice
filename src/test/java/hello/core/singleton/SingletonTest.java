package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        // => 비효율적이므로 객체를 하나만 생성하고 공유하도록 설계해야한다. 싱글톤 패턴 사용
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonContainer() {
        SingltonService singltonService1 = SingltonService.getInstance();
        SingltonService singltonService2 = SingltonService.getInstance();

        System.out.println("singltonService1 = " + singltonService1);
        System.out.println("singltonService2 = " + singltonService2);

        assertThat(singltonService1).isSameAs(singltonService2);


    }

        // 개발자가 직접 싱글톤 패턴을 적용하면 테스트하기 어렵고, 자식 클래스를 만들기 어렵고 유연성이 떨어지는 여러가지 문제 발생.
        // 스프링 컨테이너는 이와 같은 문제를 해결하면서 기본적으로 객체를 싱글톤 패턴으로 관리 => 스프링 빈
        // 싱글톤 레지스트리 : 싱글톤 객체를 생성하고 관리하는 기능
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void SpringContainer(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);

    }


}


