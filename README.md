# spring-practice
강의 들으며 실습 : 복습 및 놓친 부분 학습

- 객체지향 SOLID원칙
- 역할과 구현 분리, 책임을 명확하게 분리
- 단위테스트
- IoC(Inversion of Control) : 프로그램 제어의 흐름을 외부에서 통제하고 실행.
- DI(Dependency Injection) : 의존관계 주입 - 런타임에 외부에서 구현 객체 인스턴스 생성 후 클라이언트에 참조값을 전달해서 서버와 연결됨, DI 사용시 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능하다. 
- DI 컨테이너(IoC 컨테이너) : 객체를 생성하고 관리하면서 의존관계를 연결해주는 역할을 함. (object factory)
- BeanFactory
- ApplicationContext : 추가로 개발시 수 많은 부가 기능 제공
  - 메시지소스를 활용한 국제화 기능 MessageSource
  - 환경변수 EnvironmentCapable : 로컬, 개발, 운영을 구분해서 처리
  - 애플리케이션 이벤트 ApplicationEventPublisher : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
  - 편리한 리소스 조회 ResourceLoader : 파일, 클래스, 패스 외부에서 리소스를 편리하게 조회
- BeanDefinition Meta Data : 스프링은 다양한 형태의 설정 정보를 BeanDefinition으로 추상화해서 사용
- Singleton
  - Singleton Container
  - 싱글톤 방식 사용시 주의할 점
    - stateless로 설계할 것 (지역변수, 파라미터, ThreadLocal)
- @Configuration은 스프링 빈이 싱글톤이 되도록 보장해준다. 기존 설정 클래스를 바이트코드 조작 라이브러리(CGLIB)를 이용해서 조작 후 조작된 클래스로 스프링 빈으로 등록한다.
  - Component Scan : @Component 붙은 클래스 모두 Scan하여 스프링 빈으로 등록(@Autowired로 의존관계 설정)
    - 탐색 기본 시작 위치 : @ComponentScan이 붙은 설정 정보 클래스의 패키지
    - @Controller 어노테이션에 @Component가 적용되어있다는 것을 어떻게 알 수 있나? : 스프링이 지원하는 기능 
    - @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 추상화된 스프링의 예외로 변환해주는 부가 기능이 있다.
    - includeFilters, excludeFilters
      - Filter Type Option
        - ANNOTATION: 기본 값
        - Assignable_TYPE : 지정한 타입과 자식 타입을 인식해서 동작함. 
        - ASPECTJ : AspectJ패턴 사용
        - REGEX : 정규 표현식
        - CUSTOM : TypeFilter라는 인터페이스를 구현해서 처리
    - 중복등록과 충돌
      - 수동 빈과 자동 빈 중복 등록시 수동 빈이 자동 빈을 오버라이딩 해버리는데 결과적으로 운영하면서 잡기 어려운 애매한 버그가 생길 가능성이 높아진다고한다.
      - 그래서 스프링부트 설정에서는 애초에 중복 등록하여 충돌 발생시 오류가 발생하도록 기본 값을 변경함.
    - 컨테이너 생성 -> 스프링 빈 등록 -> 의존관계 설정 준비 -> 의존 관계 설정
    - autowired 의존관계 주입 방법 비교
      - 생성자 주입 : 불변, 필수적인 의존 관계
      - 수정자 주입 : setter를 사용하여 선택, 변경 가능성 있는 의존 관계
      - 필드 주입 방식 : 코드가 간결해보이나 추천하지 않는 방법. 중복 코드 발생하고 테스트 하기 힘들다.
      - 일반 메서드 주입
    - 자동 주입 대상 옵션 처리 방법
      - requird=false : 자동주입할 대상이 없으면 수정자 메서드 자체가 호출이 되지 않는다.
      - @Nullable : 자동주입할 대상이 없으면 null이 입력. 생성자 주입시 특정 필드에서도 사용가능 
      - Optional<>: 자동 주입할 대상이 없으면 Optional.empty가 입력. 생성자 주입시 특정 필드에서도 사용가능
    - 생성자 주입 방식을 권장하는 이유!! 
      - 객체를 생성할 때 단 한 번만 호출되므로 객체를 불변하게 유지할 수 있다. 사실 의존 관계 주입 후 의존관계를 변경할 일이 적다. 변하면 안되는 경우가 대부분이다.
      - 의존 관계 주입 실수로 누락시 컴파일 오류 발생하여 실수를 방지할 수 있다.
      - final 키워드 사용가능 => 초기화 해야할 필드 누락 방지, 불변성 유지 
    - 수정자 주입 방식 사용시 단점
      - set메서드를 public으로 설정하게 되는데 변경하면 안되는 메서드까지 열어두는건 매우 위험하다.
      - 의존 관계 주입 실수로 누락시 컴파일 단계에서 체크되지 않고 오류 발생.
    - Lombok library
      - intellij의 경우 의존성 추가 후 Lombok 플러그인 설치 및 annotation processer 설정을 켜줘야 한다. 
      - Lombok은 java의 annotation processer 기능을 이용해서 컴파일 시점에 생성자 코드를 자동으로 생성해줌.
      - annotation processing? 어노테이션을 스캔하고 컴파일 타임에 코드를 생성, 수정, 검증한다. [intellij link](https://www.jetbrains.com/help/idea/annotation-processors-support.html)
    - 조회된 빈이 2개 이상일 때...?
      - @Autowired는 타입으로 조회! 그런데 같은 타입의 조회된 빈이 2개 이상일 때 
        - @Autowired 필드명 및 파라미터명으로 빈 이름 매칭
        - @Primary 붙은 빈이 우선권을 가진다.  깔끔하고 실무에서 많이 사용한다고 한다. 참고로 수동으로 직접 적용해야하는 @Quilifier가 우선 순위가 높다. 
        - @Quilifier 사용해 추가 구분자를 붙여주어 매칭(빈 이름이 변경되지는 않음), @Quilifier 끼리 매칭하는 방식으로 명확하게 사용할 것. 직접 빈 등록할때도 사용 가능
          - @Quilifier 이용시 추가 구분자를 이용하여 매칭시 문자를 적으면 컴파일시 타입체크가 안되므로 어노테이션을 직접 만들어서 문제해결 가능
        - 어노테이션에는 상속이라는 개념이 없고 여러 어노테이션을 조합하여 사용하는 기능은 스프링이 제공해주는 기능.
    - 조회된 빈이 모두 필요한 경우라면...? 스프링 빈을 List, Map 을 이용하여 관리해보자!
      - 서비스의 종류가 다르고 고객이 종류가 다른 서비스를 선택하는 상황에서 유연하게 해당하는 스프링 빈을 찾아서 실행할 수 있다.
      - 전략 패턴(Strategy Pattern)? 객체가 할 수 있는 행위를 각각의 전략으로 미리 만들어 놓고, 프로그램이 진행되면서 동적으로 전략을 바꾸어 행동을 유연하게 수정할 수 있다.
    - 빈을 자동으로 관리할 것인가? 수동으로 관리할 것인가? 
      - 업무로직과 기술지원로직부분을 분리하여 생각해보자.
      - 기술지원로직부분은 애플리케이션 전반에 광범위하게 영향을 끼치기 때문에 명확하게 수동빈으로 관리해주는게 좋다고 한다. 유지보수하기에도 유리함.
      - 비즈니스 로직 중 다형성을 적극활용한 케이스는 자동 빈 등록을하면 한눈에 코드 파악하기 쉽지 않다고함. 자동 빈 등록을 한다면 특정 패키지 안에서 같이 관리하거나 수동 빈 등록을 고려하는게 좋을수도 있다.
    - 빈 생명주기 콜백
      - 객체의 초기화 작업과 종료 작업
      - 스프링 빈 이벤트 라이프사이클 : 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
      - 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다. 
      - 스프링은 스프링 빈, 컨테이너가 종료되기 직전에 소멸 콜백을 줘서 안전하게 종료 작업을 진행할 수 있다. 
      - 객체의 생성과 초기화를 분리하자. (SRP) 객체 내부의 값만 바꾸는 단순한 경우에는 생성자에서 한번에 처리하는게 좋을 수 있지만 보통 초기화 작업이 훨씬 많은 리소스를 사용하므로 생성작업과 초기화 작업을 분리하는게 좋다. 생성 후 초기화를 지연시켜야할 때도 유용하게 사용할 수 있다. 
    - 스프링이 빈 생명주기 콜백을 지원하는 방법 3가지
      - 인터페이스 InitializingBean, DisposableBean : 초창기에 나온 방법으로 지금은 거의 사용하지 않는 방법이다. 스프링 전용 인터페이스고 초기화 소멸 메서드 이름도 변경할 수 없다. 외부 라이브러리에 적용할 수 없다. 
      - Bean Annotation 등록시 initMethod, destroyMethod 설정. 메서드 이름을 자유롭게 설정가능하고, 스프링 빈이 스프링 코드에 의존하지 않고, 외부라이브러리에도 초기화, 종료 메서드 적용 가능하다. 종료 메서드 속성은 '종료'를 의미하는 기본값으로 설정되어있어서 추론하여 호출한다.
      - PostConstruct, PreDestory Annotation : 어노테이션으로 간편하게 설정가능한 방법. 스프링에서 권장하는 방법으로 자바 표준이다.(JSR-250) 그러므로 다른 컨테이너에서도 사용가능. 빈 등록방식이 아니라 컴포넌트 스캔과 잘 어울리는 방식이다. 그러나 외부 라이브러리에는 적용이 할 수 없다..
    - 빈 스코프
      - 스프링 빈은 기본적으로 싱글톤 스코프로 생성된다. 빈이 존재할 수 있는 범위를 scope 라고 한다. 
        - 싱글톤 스코프 : 스프링 컨테이너의 시작~ 종료까지 유지되는 가장 넓은 범위의 스코프. 
        - 프로토타입 스코프: 스프링컨테이너는 프로토타입 빈의 생성, 의존관계주입, 초기화메서드 호출까지만 관여하고 더이상 관리 하지 않는다. 그래서 종료메서드는 호출되지 않음. 매우 짧은 범위의 스코프.
          - 빈 요청시 스프링 컨테이너는 매번 새로 생성하고 의존관계 주입 후 생성된 빈을 반환하고 더 이상 관리하지 않음
          - 반환 후 프로토타입 빈을 관리할 책임은 요청한 클라이언트에게로 넘어간다. 
        - request 스코프: 웹 요청이 들어오고 나갈때까지만 유지되는 스코프
        - session 스코프: 웹 세션이 생성되고 종료될때까지만 유지되는 스코프
        - application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다. 
      - 프로토타입스코프 빈을  싱글톤 빈과 함께 사용시 문제점? 
      - 싱글톤 빈이 의존 관계 주입을 통해서 프로토타입 빈을 주입 받아서 사용하는 경우:
        - 요청을 받아 사용할 때 마다 프로토타입 빈이 새로 생성되지 않는다!!! 싱글톤 빈은 주입 받은 프로토타입 빈의 참조 값을 내부 필드에 보관하고 싱글톤 빈을 호출해서 로직 수행시 싱글톤 빈 생성 시점에 주입된 프로토타입 빈을 이용하게 된다. 여기서 주의할 점은 의존관계 주입할때 스프링 컨테이너에 요청해서 프로토타입빈이 새로 생성이 된 것이라는 점이다. 사용할때마다 프로토타입 빈을 새로 생성해서 사용하는 것을 원하지만 의도와는 다르게, 사용할때마다가 새로 생성하는게 아니라 주입 시점에만 새로 생성하여 사용한다.
        - 그렇다면 어떻게 사용할때마다 프로토타입 빈을 생성할 수 있을까? 
          - 가장 간단한 방법은 싱글톤 빈이 프로토타입 빈을 사용할 때마다 스프링 컨테이너에 새로 요청하는 것이다.
          - Dependency LookUp(DL) 의존관계 조회(탐색)이란? 의존관계를 외부에서 주입받는게 아니라 직접 필요한 의존 관계를 찾는 것.
          - 스프링이 좋은 기능을 제공한다. 지정한 빈을 스프링 컨테이너에서 대신 찾아주는 DL 기능만 제공하는 ObjectProvider를 사용하면 좋다. (ObjectFactory에서 편의 기능(스트림 처리  등)이 더 추가됨), 단위테스트나 mock 코드를 훨씬 간단하게 만들 수 있다.
          - 자바 표준 기능도 존재한다. (JSR-330 Provider) 스프링이 아닌 다른 컨테이너에서 사용해야한다면 자바 표준을 사용하자.
      - 웹 관련 스코프
        - 웹 환경에서만 동작하고 스프링이 종료시점까지 관리한다. AnnotationConfigServletWebServerApplicationContext
          - request 스코프: HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프로 각 요청마다 별도의 빈 인스턴스가 생성.
          - session 스코프: HTTP 세션과 동일한 생명주기를 가짐
          - application 스코프: 서블릿 컨텍스트와 동일한 생명주기를 가짐.
          - web socket: 웹 소켓과 동일한 생명주기를 가지는 스코프
        - request 스코프 : 동시에 HTTP 요청이 올 때 어떤 요청이 남긴 로그인지 구별할 때 자주 사용. 
  
  

    