# blogSearch_API
blogSearch_API

API 명세

1. 블로그 검색
   GET /blog/search
   Host: localhost:8081
   
   request parameter
   - keyword 검색어(필수)
   - sortType 정렬방식(선택)
   - pageNum 페이지 번호(선택)
   - pageSize 페이지당 조회 건 수(선택)
   
   response
   - code 결과코드
   - message 결과메시지
   - count 결과 건 수
   - page 조회 페이지 번호
   - pageSize 페이지당 조회 건 수
   - result 결과
        > title 제목
        > contents 컨텐츠
        > url 블로그url
        > blogname 블로그이름
        > datetime 포스트시간
   
2. 검색 순위
   GET /blog/ranking
   Host: localhost:8081
   
   response
   - keyword 검색어
   - count 검색 횟수

결과 코드
