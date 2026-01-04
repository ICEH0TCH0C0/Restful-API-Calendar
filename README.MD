# 📅 React + Spring Boot 캘린더 (Personal Calendar Service)

React(Frontend)와 Spring Boot(Backend)를 연동하여 구현한 **개인 일정 관리 웹 애플리케이션**입니다.  
사용자별 일정 관리, 카테고리 분류, 월별 캘린더 뷰 기능을 RESTful API 기반으로 제공합니다.
Spring Security와 JWT를 적용하여 안전한 인증/인가 시스템을 구축했습니다.

---

## 🛠️ 기술 스택 (Tech Stack)

### Backend
* **Framework**: Spring Boot 3.x
* **Language**: Java 17
* **Database**: H2 Database (TCP 모드)
* **ORM / SQL Mapper**: Spring Data JPA (EntityManager 직접 사용), MyBatis (레거시 코드 일부 포함)
* **Security**: Spring Security, JWT (JSON Web Token)
* **Build Tool**: Gradle
* **Library**: Lombok, Validation

### Frontend
* **Library**: React, Vite
* **Styling**: Styled-components
* **Router**: React Router DOM
* **Calendar**: FullCalendar Library
* **HTTP Client**: Axios

---

## 📝 주요 도메인 및 DB 설계 (Domain)

이 프로젝트는 크게 3가지 핵심 엔티티로 구성되어 있습니다.

### 1. User (사용자)
* 서비스를 이용하는 회원 정보입니다.
* **주요 필드**: `userNo`(PK), `userId`, `userPwd`(암호화), `userName`, `userPhone`, `userEmail`
* **관계**: `User` (1) : (N) `Plan`

### 2. Plan (일정)
* 캘린더에 등록되는 개별 일정 정보입니다.
* **주요 필드**: `planNo`(PK), `planTitle`, `planContent`, `date`
* **관계**:
    * `Plan` (N) : (1) `User` (일정 소유자)
    * `Plan` (N) : (1) `Category` (일정 분류)

### 3. Category (카테고리)
* 일정을 분류하는 카테고리 정보입니다. (예: 업무, 개인, 기념일 등)
* **주요 필드**: `categoryNo`(PK), `categoryName`
* **관계**: `Category` (1) : (N) `Plan`

---

## 📡 API 명세서 (API Specification)

RESTful 설계 원칙에 따라 리소스(명사) 중심으로 URI를 구성하고, HTTP Method로 행위를 정의했습니다.
모든 API는 `/api` 프리픽스를 가집니다.

### 1. User API (`/api/users`, `/api/tokens`)

| 기능 | HTTP Method | URI | 설명 | 인증 필요 |
| :--- | :---: | :--- | :--- | :---: |
| **회원가입** | `POST` | `/api/users` | 새로운 사용자를 생성합니다. (비밀번호 암호화 저장) | X |
| **아이디 중복 체크** | `GET` | `/api/users/availability` | 아이디 중복 여부를 확인합니다. (`?userId=...`) | X |
| **로그인 (토큰 발급)** | `POST` | `/api/tokens` | 사용자 인증 후 JWT 토큰을 발급합니다. | X |
| **정보 수정** | `PATCH` | `/api/users/{userNo}` | 회원 정보를 부분 수정합니다. | O |
| **회원 탈퇴** | `DELETE` | `/api/users/{userNo}` | 회원을 삭제합니다. | O |
| **아이디 찾기** | `POST` | `/api/users/id-recovery` | 이름과 전화번호로 아이디를 조회합니다. | X |
| **비밀번호 재설정** | `POST` | `/api/users/password-reset` | 본인 확인 후 비밀번호를 재설정합니다. | X |

### 2. Plan API (`/api/plans`)

| 기능 | HTTP Method | URI | 설명 | 인증 필요 |
| :--- | :---: | :--- | :--- | :---: |
| **일정 조회(검색)** | `GET` | `/api/plans` | 특정 날짜 및 검색어(옵션)로 일정을 조회합니다. | O |
| **일정 등록** | `POST` | `/api/plans/{userNo}` | 해당 사용자의 새 일정을 등록합니다. | O |
| **일정 수정** | `PATCH` | `/api/plans/{planNo}` | 일정을 수정합니다. | O |
| **일정 삭제** | `DELETE` | `/api/plans/{planNo}` | 일정을 삭제합니다. | O |

### 3. Category API (`/api/categories`)

| 기능 | HTTP Method | URI | 설명 | 인증 필요 |
| :--- | :---: | :--- | :--- | :---: |
| **목록 조회** | `GET` | `/api/categories` | 전체 카테고리 목록을 조회합니다. | O |
| **카테고리 추가** | `POST` | `/api/categories` | 새로운 카테고리를 추가합니다. | O |

---

## 🔐 보안 (Security)

* **인증 방식**: JWT (JSON Web Token) 기반 인증
* **비밀번호**: BCrypt 해시 알고리즘을 사용하여 암호화 저장
* **비밀번호 재설정**: 기존 비밀번호 찾기 대신, 본인 인증 후 비밀번호를 덮어쓰는(Reset) 방식으로 구현하여 보안 강화
* **유효성 검사**: `@Valid` 및 Bean Validation을 사용하여 입력 데이터 검증

---

## 💻 실행 방법 (How to Run)

### 1. H2 Database 설정
`src/main/resources/application.yaml` 파일의 DB 경로를 본인의 환경에 맞게 수정해야 할 수 있습니다.

```yaml
spring:
  datasource:
    hikari:
      # 아래 경로는 예시이며, 실제 H2 DB 파일 경로로 변경 필요
      jdbc-url: jdbc:h2:tcp://localhost/~/calendar 
      username: calendar
      password: 1234
jwt:
  secret: (64바이트 이상의 긴 비밀키)
  expiration: 86400000
```
* H2 Console 접속: http://localhost:8888/h2-console 

### 2. Backend (Spring Boot) 실행
1. 프로젝트 루트에서 터미널을 엽니다.
2. (Windows) `./gradlew bootRun` 또는 IDE(IntelliJ 등)에서 `CalendarApplication.java` 실행.
3. 서버 포트: **8888**

### 3. Frontend (React) 실행
1. `src` 폴더가 있는 프론트엔드 디렉토리로 이동합니다.
2. 의존성 설치:
    ```
    npm install
    ```
3. 개발 서버 실행:
    ```
   npm run dev
   ```
4. 브라우저 접속: `http://localhost:5173` (Vite 기본 포트)

---

## 📂 프로젝트 구조 (Folder Structure)
```aiexclude
src
├── main/java/com/kh/Calendar
│   ├── config          # SecurityConfig 등 설정 파일
│   ├── controller      # API 요청 처리 (UserController, PlanController 등)
│   ├── service         # 비즈니스 로직 (UserService, PlanService 등)
│   ├── repository      # DB 접근 (JPA EntityManager 구현체)
│   ├── entity          # DB 테이블 매핑 객체
│   ├── dto             # 데이터 전송 객체 (Request/Response)
│   ├── security        # JWT Provider, Filter 등 보안 관련 클래스
│   └── exception       # 전역 예외 처리 (GlobalExceptionHandler)
│
└── src (React Frontend)
    ├── components      # 재사용 가능한 UI 컴포넌트 (Calendar, PlanList 등)
    ├── pages           # 페이지 단위 컴포넌트 (MainPage, DetailPage 등)
    ├── customHooks     # 로직 분리 (UserContext, usePlanManager)
    └── route           # 라우팅 설정
```
