# 📅 Personal Calendar Service

React 기반의 개인 일정 관리 웹 애플리케이션입니다.
**인증(Authentication)**은 RESTful API를 통해 서버와 통신하며, **일정 관리(Calendar Management)**는 React 상태 관리를 통해 빠르고 유연하게 처리하도록 설계되었습니다.

## 🏗 System Architecture

이 프로젝트는 기능별로 역할이 명확히 구분되어 있습니다.

| 영역 (Layer) | 역할 (Role) | 주요 기능 |
| :-- | :-- | :-- |
| **RESTful API** | **User Authentication** | 회원가입, 로그인 (서버 DB 통신) |
| **React Client** | **State & UI Management** | 캘린더 렌더링, 일정 CRUD, 라우팅, 세션 유지 |

---

## 🔌 1. RESTful API Specifications
서버와 비동기 통신(`fetch`)을 수행하여 보안이 필요한 사용자 인증 프로세스를 처리합니다.

### 🔐 Auth (User)
| HTTP Method | Endpoint | Description | Request Body |
| :-- | :-- | :-- | :-- |
| **POST** | `/api/user/signup` | **회원가입** <br> 사용자의 정보를 서버 DB에 등록합니다. | `{ userId, userPwd, userName, ... }` |
| **POST** | `/api/user/login` | **로그인** <br> ID/PW 검증 후 사용자 정보를 반환합니다. | `{ userId, userPwd }` |

---

## ⚛️ 2. React Client Processing
서버 부하를 줄이고 빠른 사용자 경험(UX)을 제공하기 위해, 일정 관리 및 화면 로직은 React 내부에서 처리합니다.

### 🗓️ Calendar & Schedule (Client-Side Logic)
FullCalendar 라이브러리와 Context API를 결합하여 일정을 관리합니다.

* **일정 조회 (Read):** 로그인한 사용자의 상태(`currentUser`)에 저장된 일정 배열을 `FullCalendar` 이벤트로 매핑하여 렌더링합니다.
* **일정 추가 (Create):** `UserContext`의 `addPlan` 함수를 통해 상태를 업데이트하고 화면에 즉시 반영합니다.
* **일정 수정/삭제 (Update/Delete):**
    * **인라인 수정:** 별도의 페이지 이동 없이 상세 페이지에서 즉시 수정 모드로 전환됩니다.
    * **동기화:** 수정된 데이터는 즉시 `SessionStorage`에 반영되어 새로고침 시에도 유지됩니다.

### 🧭 Routing & Security
* **Protected Routes:** `MainPage` 진입 시 `id` 파라미터와 현재 로그인된 사용자의 `userNo`를 대조하여, 불일치 시 자동으로 로그아웃 및 리다이렉트 처리합니다.
* **Dynamic Routing:** `/main/:id/:date` 형식의 동적 라우팅을 사용하여 날짜별 상세 페이지를 생성합니다.

---

## 🛠 Tech Stack

### Frontend
* **Core:** React, Vite
* **Styling:** Styled-components (CSS-in-JS)
* **Routing:** React Router DOM v6
* **Calendar:** FullCalendar (@fullcalendar/react)
* **State Management:** Context API + SessionStorage

### Backend Interface
* **Protocol:** REST API (Fetch API)
* **Data Format:** JSON
