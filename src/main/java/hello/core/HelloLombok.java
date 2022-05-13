package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;
    private String nickname;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(7);
        System.out.println(helloLombok.toString());
    }
}
