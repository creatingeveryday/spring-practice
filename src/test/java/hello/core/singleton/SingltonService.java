package hello.core.singleton;

public class SingltonService {

    //1. static 영역에 객체를 딱 1개만 생성
    private static final SingltonService instance = new SingltonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingltonService getInstance(){
        return instance;
    }

    //3. 생성자는 private으로 선언하여 외부에서 new 키워드로 객체 생성하지 못하게 막는다.
    private SingltonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출! ");
    }
}
