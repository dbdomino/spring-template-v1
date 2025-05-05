# front-app 트리
1. 기본구조
```
front-app/
├── src/
│   └── main/
│       ├── java/com/example/frontapp/
│       │   └── FrontAppApplication.java
│       ├── resources/
│       │   ├── templates/          ← Thymeleaf HTML 페이지
│       │   │   ├── login.html
│       │   │   ├── signup.html
│       │   │   ├── mypage.html
│       │   │   └── home.html
│       │   ├── static/             ← 정적 리소스(css, js 등)
│       │   └── application.yml
├── build.gradle
└── settings.gradle
```