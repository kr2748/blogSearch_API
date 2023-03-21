# blogSearch_API

Jar 파일 다운로드 URL :: https://drive.google.com/file/d/11QaamD7iJeQDJrfu74aTR3EtM0xnbhng/view?usp=share_link

**API 명세**

1. 블로그 검색
   GET /blog/search
   Host: localhost:8081
   
   request parameter
   | | param | 설명 | 필수여부 |
   | :---: | :---: | :---: | :---: | 
   | 1 | keyword | 검색어 | Y |
   | 2 | sortType | 정렬방식 (0 : 정확도(Default), 1 : 최신순) | N |
   | 3 | pageNum | 페이지번호 (1 ~ 50) | N |
   | 4 | pageSize | 페이지당 조회 건 수 (1 ~ 50) | N |
   
   response
    | | data | 설명 |
   | :---: | :---: | :---: |
   | 1 | code | 결과코드 |
   | 2 | message | 결과메시지 |
   | 3 | page | 조회 페이지 번호 |
   | 4 | pageSize | 페이지당 조회 건 수 |
   | 5 | result | 결과 |
   | 5-1 | title | 제목 |
   | 5-2 | contents | 컨텐츠 |
   | 5-3 | url | 블로그url |
   | 5-4 | blogname | 블로그이름 |
   | 5-5 | datetime | 포스트시간 |
  
2. 검색 순위
   GET /blog/ranking
   Host: localhost:8081
   
   response
    | | data | 설명 |
   | :---: | :---: | :---: |
   | 1 | rank | 순위 |
   | 2 | keyword | 검색어 |
   | 3 | count | 검색횟수 |


**결과 코드**
 
   | code | 설명 |
   | :---: | :---: |
   | 0 | 정상 |
   | -1 | 검색어 누락 |
   | -2 | 페이지 번호는 1 ~ 50까지만 입력 가능합니다. |
   | -3 | 페이지당 건수는 1 ~ 50까지만 입력 가능합니다. |
   | -4 | count정렬방식은 0(정확도), 1(최신순)만 입력 가능합니다. |
   | -5 | Open API 에러 |
   | -6 | 알 수 없는 오류 |
