# 📅 React + Spring Boot 캘린더 (Personal Calendar Service)

React(Frontend)와 Spring Boot(Backend)를 연동하여 구현한 **개인 일정 관리 웹 애플리케이션**입니다.  
사용자별 일정 관리, 카테고리 분류, 월별 캘린더 뷰 기능을 RESTful API 기반으로 제공합니다.

---

## 🛠️ 기술 스택 (Tech Stack)

### Backend
* **Framework**: Spring Boot 3.x
* **Language**: Java 17
* **Database**: H2 Database (TCP 모드)
* **ORM / SQL Mapper**: Spring Data JPA (EntityManager 직접 사용), MyBatis (레거시 코드 일부 포함)
* **Build Tool**: Gradle
* **Library**: Lombok

### Frontend
* **Library**: React, Vite
* **Styling**: Styled-components
* **Router**: React Router DOM
* **Calendar**: FullCalendar Library
* **HTTP Client**: Fetch API

---

## 📝 주요 도메인 및 DB 설계 (Domain)

이 프로젝트는 크게 3가지 핵심 엔티티로 구성되어 있습니다.

### 1. User (사용자)
* 서비스를 이용하는 회원 정보입니다.
* **주요 필드**: `userNo`(PK), `userId`, `userPwd`, `userName`, `userPhone`, `userEmail`
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

### 1. User API (`/api/users`, `/api/sessions`)

| 기능 | HTTP Method | URI | 설명 |
| :--- | :---: | :--- | :--- |
| **회원가입** | `POST` | `/api/users` | 새로운 사용자를 생성합니다. (아이디 중복 시 409 Conflict 반환) |
| **아이디 중복 체크** | `GET` | `/api/users/check` | 아이디 중복 여부를 확인합니다. (`?userId=...`) |
| **로그인** | `POST` | `/api/sessions` | 사용자 인증을 수행하고 세션을 생성합니다. |
| **정보 수정** | `PATCH` | `/api/users/{userNo}` | 회원 정보를 부분 수정합니다. |
| **회원 탈퇴** | `DELETE` | `/api/users/{userNo}` | 회원을 삭제합니다. |
| **아이디 찾기** | `POST` | `/api/users/id` | 이름과 전화번호로 아이디를 조회합니다. |
| **비번 찾기** | `POST` | `/api/users/pwd` | 아이디, 이름, 전화번호로 비밀번호를 조회합니다. |

### 2. Plan API (`/api/plans`)

| 기능 | HTTP Method | URI | 설명 |
| :--- | :---: | :--- | :--- |
| **일정 조회(검색)** | `GET` | `/api/plans/search` | 특정 날짜 및 검색어(옵션)로 일정을 조회합니다. |
| **일정 등록** | `POST` | `/api/plans/{userNo}` | 해당 사용자의 새 일정을 등록합니다. |
| **일정 수정** | `PATCH` | `/api/plans/{planNo}` | 일정을 수정합니다. |
| **일정 삭제** | `DELETE` | `/api/plans/{planNo}` | 일정을 삭제합니다. |

### 3. Category API (`/api/categories`)

| 기능 | HTTP Method | URI | 설명 |
| :--- | :---: | :--- | :--- |
| **목록 조회** | `GET` | `/api/categories` | 전체 카테고리 목록을 조회합니다. |
| **카테고리 추가** | `POST` | `/api/categories` | 새로운 카테고리를 추가합니다. (이름 중복 시 409 Conflict 반환) |

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
│   ├── controller      # API 요청 처리 (UserController, PlanController 등)
│   ├── service         # 비즈니스 로직 (UserService, PlanService 등)
│   ├── repository      # DB 접근 (JPA EntityManager 구현체)
│   ├── entity          # DB 테이블 매핑 객체
│   ├── dto             # 데이터 전송 객체 (Request/Response)
│   └── mapper          # MyBatis XML 매퍼 (UserMapper, PlanMapper)
│
└── src (React Frontend)
    ├── components      # 재사용 가능한 UI 컴포넌트 (Calendar, PlanList 등)
    ├── pages           # 페이지 단위 컴포넌트 (MainPage, DetailPage 등)
    ├── customHooks     # 로직 분리 (UserContext, usePlanManager)
    └── route           # 라우팅 설정
```

---

## ⚠️ 개발 환경 설정 (Proxy)
프론트엔드(`localhost:5173`)에서 백엔드(`localhost:8888`)로 
API 요청을 보내기 위해 `vite.config.js` 또는 `package.json`에 Proxy 설정이 필요합니다.
```javascript
// vite.config.js 예시
export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8888',
        changeOrigin: true,
      }
    }
  }
})
```
