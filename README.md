# spring-practice
강의들으며 실습 : 복습 및 놓친 부분 학습

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
    - 
  

    