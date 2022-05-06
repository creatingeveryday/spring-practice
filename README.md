# spring-practice
강의들으며 실습 : 복습 및 놓친 부분 학습

- 객체지향 SOLID원칙
- 역할과 구현 분리, 책임을 명확하게 분리
- 단위테스트
- IoC(Inversion of Control) : 프로그램 제어의 흐름을 외부에서 통제하고 실행.
- DI(Dependency Injection) : 의존관계 주입 - 런타임에 외부에서 구현 객체 인스턴스 생성 후 클라이언트에 참조값을 전달해서 서버와 연결됨, DI 사용시 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능하다. 
- DI 컨테이너(IoC 컨테이너) : 객체를 생성하고 관리하면서 의존관계를 연결해주는 역할을 하는 것을 말한다. (object factory)
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
    