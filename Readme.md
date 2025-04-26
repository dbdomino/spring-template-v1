# Spring Template v1
## 1. 설명
- 스프링 프로젝트 만들어 쓰기 위해, 프로젝트 템플릿 제작
- 멀티모듈 기반 프로젝트
## 2. 환경
- openjdk 17.0.2
- gradle 8.10
- spring boot 3.4.4
## 3. 프로젝트 구조 설명
- 멀티모듈 구조, build.gradle에서 관리
### 3.1. 애플리케이션 모듈 (appmod)
- 각각 별도 실행 가능한 Spring Boot 애플리케이션
- `userapp`: 사용자 서비스 제공
- `financeapp`: 금융 서비스 제공
### 3.2. 공통 모듈 (common-mod)
- 모든 모듈에서 공통으로 사용하는 라이브러리, Util, 상수 등을 관리
### 3.3. 핵심 비즈니스 로직 모듈 (core-mod)
- 도메인별 핵심 비즈니스 모델 작성
- ex) 로그인, 주문 처리, 금액 저장 등 비즈니스 규칙 관련 코드
### 3.4. 데이터 모듈 (data-mod)
- 데이터베이스 접근 모듈
- JPA 기반 Entity, Repository 관리
- MyBatis Mapper, SqlSession 관리 (추가 예정)
```
spring-template-v1/
├── .gradle/
├── .idea/
├── build.gradle (루트)
├── settings.gradle
├── appmod
│   ├── financeapp         # finance-app 애플리케이션 (SpringBoot 메인 실행)
│   │   ├── config         # 애플리케이션 설정 (Spring Config)
│   │   ├── controller     # API, Web Controller
│   │   └── service        # 앱 전용 비즈니스 로직 서비스
│   │
│   └── userapp            # user-app 애플리케이션 (SpringBoot 메인 실행)
│       ├── config         # 애플리케이션 설정 (Spring Config)
│       ├── controller     # API, Web Controller
│       └── service        # 앱 전용 비즈니스 로직 서비스
│
├── common-mod             # [공통 모듈] [공통 상수, Enum, 유틸성 클래스, 라이브러리]
│   └── common
│       └── config
│       └── constants
│       └── util
│       └── (공통 서비스, AOP 등)
│
├── core-mod/              # [핵심 비즈니스 모듈] [핵심 비즈니스 인터페이스, 스프링 핵심 기능 AOP/Filter/Interceptor]
│   └── core
│       └── user
│       └── account
│
├── data-mod/              # [DB 접근용 모듈] [JPA] [Mybatis 예정]
│   └── data
│       └── config
│       └── entity
│           └── (JPA Entity)
│       └── repository
│           └── (JPA repository)
└── test/
    └── (통합 테스트 예정)
```