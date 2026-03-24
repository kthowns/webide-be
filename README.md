# 🚀 WebIDE Goorm Backend

실시간 협업 코드 편집, 파일 시스템 관리, 그리고 격리된 환경에서의 코드 실행을 지원하는 클라우드 기반 Web IDE 백엔드 서비스입니다.

## ✨ 주요 기능

### 1. 실시간 협업 편집 (Real-time Collaboration)
- **WebSocket & Redis**: WebSocket과 Redis Pub/Sub을 활용한 저지연 실시간 텍스트 동기화.
- **편집기 상태 공유**: 커서 위치 추적 및 사용자 간 편집 상태 실시간 공유.
- **동시성 제어**: 다수의 사용자가 동시에 동일한 파일을 편집할 수 있는 환경 제공.

### 2. 코드 실행 엔진 (Code Execution Engine)
- **Docker 기반 격리**: `docker-java` 라이브러리를 사용하여 사용자 코드를 독립된 컨테이너 환경에서 실행.
- **프로그래밍 언어 지원**: 현재 Java 17 및 Python 3.11 환경 지원.
- **실시간 스트리밍**: 코드 실행 프로세스의 표준 출력(stdout) 및 표준 에러(stderr)를 WebSocket을 통해 실시간으로 클라이언트에 전달.
- **입력 상호작용**: 실행 중인 프로세스에 표준 입력(stdin) 전달 가능.

### 3. 파일 및 프로젝트 관리
- **계층형 구조**: 프로젝트, 폴더, 파일의 계층적 관리 기능 제공.
- **내용 영속화**: JPA를 통한 파일 내용 및 메타데이터 저장 및 관리.

### 4. 실시간 채팅
- **프로젝트별 채팅방**: 협업 중인 팀원들과 실시간으로 소통할 수 있는 채팅 기능.
- **메시지 브로커**: Redis를 메시지 브로커로 활용하여 다중 서버 환경에서도 원활한 채팅 지원.

### 5. 보안 및 인증
- **JWT 인증**: Spring Security와 JWT를 결합한 안전한 사용자 인증 및 권한 관리.

---

## 🛠 기술 스택

### Framework & Language
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Security**
- **Spring Data JPA**
- **Spring WebSocket**

### Database & Messaging
- **MySQL**: 데이터 영속성 관리
- **Redis**: 실시간 메시징(Pub/Sub) 및 캐싱
- **H2**: 개발 및 테스트용 인메모리 DB

### DevOps & Tools
- **Docker**: 코드 실행 환경 격리 및 컨테이너화
- **Swagger (SpringDoc)**: API 문서 자동화
- **Lombok**: 보일러플레이트 코드 제거

---

## ⚙️ 시작하기

### 요구 사항
- **Docker Engine** (코드 실행 컨테이너 구동을 위해 필수)
- **Java 17+**
- **MySQL & Redis**

### 환경 변수 설정
`src/main/resources/application.yml`에서 사용하는 주요 환경 변수를 설정해야 합니다.
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`
- `REDIS_HOST`, `REDIS_PORT`
- `JWT_SECRET`, `JWT_EXPIRATION_SECONDS`

### 실행 방법

#### Docker Compose 사용 (권장)
```bash
docker compose up --build
```

#### 로컬 실행
```bash
./gradlew bootRun
```

---

## 📖 API 문서 및 인터페이스

### Swagger UI
프로젝트 실행 후 다음 주소에서 API 명세를 확인할 수 있습니다.
- `http://localhost:8080/api/my-docs`

### WebSocket 엔드포인트
- **실시간 코드 실행**: `/ws/compile`
  - 요청: `start` 메시지 (코드 포함)
  - 응답: `output` (실시간 출력), `result` (최종 결과)
- **실시간 편집기**: `/ws/editor`
  - 커서 이동, 텍스트 변경 사항 동기화

---

## ⚠️ 주의 사항
- 코드 실행 시 호스트의 Docker 소켓을 사용하므로, 실행 환경에 Docker 권한이 설정되어 있어야 합니다.
- 실행 컨테이너는 `--network none` 옵션으로 외부 네트워크가 차단된 안전한 상태에서 구동됩니다.
