package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    //순수한 자바코드로 테스트 작성하는 단위테스트
    //어떤 구현체를 조합해서 테스트를 수행할 것인가
    //실제 테스트시 가짜 객체를 사용하기도 함...생성자 주입방식 사용시 유연한 테스트 작성 가능하다.!

    @Test
    void createOrder(){

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,new RateDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}