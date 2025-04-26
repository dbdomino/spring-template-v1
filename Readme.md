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
```
spring-template-v1/
├── .gradle/
├── .idea/
├── build.gradle (루트)
├── settings.gradle
├── app-mod/
│   └── user-app/         # [실행 모듈]
│       ├── build.gradle
│       └── src/
│           ├── main/java/com/jhspring/controller/
│           ├── main/java/com/jhspring/service/
│           ├── main/java/com/jhspring/UserAppMain.java
│           └── resources/
│               └── application.yml 등
├── common-lib/            # [공통 라이브러리 모듈] [공통 상수, Enum, 유틸성 클래스]
│   ├── build.gradle
│   └── src/
│       └── main/java/com/jhspring/common/constants/
│           ├── ErrorMessages.java
│           └── UserRole.java
├── core-mod/              # [핵심 비즈니스 모듈] [핵심 비즈니스 인터페이스, 스프링 핵심 기능 AOP/Filter/Interceptor]
│   ├── build.gradle
│   └── src/
│       └── main/java/
│           └── (주요 서비스, AOP 등)
├── data-mod/              # [DB 접근용 모듈] [JPA] [Mybatis]
│   ├── build.gradle
│   └── src/
│       └── main/java/
│           └── (JPA Entity, Repository)
└── test/
    └── (통합 테스트 예정)
```