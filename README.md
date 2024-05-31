1.일정 관리 프로그램

 - 회원가입 로그인 기능.
 - 일정을 등록, 조회, 수정, 삭제하는 기능.
 - 일정에 댓글을 등록, 수정, 삭제하는 기능.

사용 기술 및 개발 환경 : JAVA, Spring Boot, Gradle, MySQL, IntelliJ, JWT, Spring Security, Swagger

2.ERD Diagram

<img width="965" alt="스크린샷 2024-05-31 오전 11 33 45" src="https://github.com/momoysm/schedule-management/assets/90163578/123831c8-8811-4205-8813-8eeb771ebd01">

3.API 명세서

https://documenter.getpostman.com/view/34877953/2sA3JRZzK2

4.프로젝트를 진행하며 고민한 Technical Issue
 - 예외처리 : @ExceptionHandler를 활용하여 공통 예외 처리를 구현
 - 파일 업로드 기능 : 경로가 아닌 파일을 직접 DB에 업로드하는 부분이 처음 접해보고 예시도 적어서 어려웠다.
