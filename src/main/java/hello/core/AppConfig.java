package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 역할과 책임 분리: 실제 동작에 필요한 객체를 생성하고 연결하는 역할. OOP SOLID 원칙 중  DIP 원칙 위반 해소
 * 구현체의 변경은 구성 영역에서만 변경하면 되는 구조로 변경되었음.
 * 중복 해소 및 애플리케이션에서 역할에 따른 구현이 잘보이게 리팩터링(큰 그림이 한눈에 보이게)
 *
 * ApplicationContext : 스프링 컨테이너로 객체를 생성하고 DI 관리
 * @Bean 메서드를 모두 호출해서 반환된 객체를 팩토리 메서드를 통해서 스프링 컨테이너에 등록. = 스프링 빈
 * 스프링 컨테이너 사용시 장점?
 */

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
