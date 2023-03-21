# blogSearch_API
blogSearch_API

Jar 파일 다운로드 URL :: https://drive.google.com/file/d/1dGMHwa4VilEtyGzKCJzbM8_QUH0lIcOD/view?usp=share_link

API 명세

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
   - keyword 검색어
   - count 검색 횟수

결과 코드
