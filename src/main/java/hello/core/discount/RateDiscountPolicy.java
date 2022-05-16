package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")   //문자는 컴파일시 타입체크 불가능
//@Primary
@MainDiscountPolicy //내가 직접 만든 어노테이션을 이용, 컴파일시 오류 체크 및 해당 소스 코드 위치 추적 가능
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }

        return 0;
    }
}
