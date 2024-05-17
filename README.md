1.일정 관리 프로그램
 - 일정을 등록, 조회, 수정, 삭제하는 단순 CRUD 기능.

사용 기술 및 개발 환경 : JAVA, Spring Boot, Gradle, MySQL, IntelliJ

2.Usecase Diagram

![스크린샷 2024-05-16 오후 8 58 59](https://github.com/momoysm/schedule-management/assets/90163578/b0729b79-4cf6-4b33-85a1-611d36e8428c)

 - 일정 등록
 - 전체 일정 조회
 - 상세 일정 조회
 - 일정 수정
 - 일정 삭제


3.ERD Diagram

![schedule-management](https://github.com/momoysm/schedule-management/assets/90163578/e1dc9431-0c02-4e8a-8553-073200466e6a)

4.API 명세서

[https://documenter.getpostman.com/view/34877953/2sA3JRZzK2](https://web.postman.co/workspace/My-Workspace~773bd856-4a7c-4834-84a9-33ff686308a7/documentation/34877953-5d2c7949-3d2f-48f8-839c-057a35476fa1)

http://localhost:8080/swagger-ui/index.html
 - swagger를 활용한 api명세서

5.프로젝트를 진행하며 고민한 Technical Issue
 - 예외처리 : @ExceptionHandler를 활용하여 공통 예외 처리를 구현
   
