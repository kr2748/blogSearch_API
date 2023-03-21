package kr.gykim.blogsearch.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import kr.gykim.blogsearch.dto.BaseResponse;
import kr.gykim.blogsearch.dto.request.BlogSearchRequest;
import kr.gykim.blogsearch.service.BlogSearchService;
import kr.gykim.blogsearch.utils.CountingUtil;
import kr.gykim.blogsearch.utils.ValidationUtil;

@RestController
@RequestMapping("/blog")
public class BlogSearchController {
	
	@Autowired
	private BlogSearchService blogSearchService;
	
	@Autowired
	private CountingUtil countingUtil;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	/**
	 * 블로그 검색 컨트롤러
	 * 
	 * @param keyword  : 검색 키워드
	 * @param sortType : Sort타입(0 : 정확도순, 1: 최신순)
	 * @param pageNum  : 페이지 번호(1 ~ 50)
	 * @param pageSize : 페이지당 검색 수(1 ~ 50)
	 * @return         : 검색결과
	 */
	@GetMapping("/search")
	public ResponseEntity<BaseResponse> blogSearch(BlogSearchRequest blogSearchRequest) throws Exception {
		BaseResponse checkResult = validationUtil.validaionCheck(blogSearchRequest);
		if(checkResult != null) {
			return new ResponseEntity<BaseResponse>(checkResult, HttpStatus.BAD_REQUEST);
		}
		
		CountingThread countingThread = new CountingThread(blogSearchRequest.getKeyword());
		countingThread.start();
		
		BaseResponse searchResult = blogSearchService.blogSearch(blogSearchRequest);
		return new ResponseEntity<BaseResponse>(searchResult,searchResult.getCode() != 0 ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK);
	}

	
	@GetMapping("/ranking")
	public ResponseEntity<BaseResponse> ranking() {
		BaseResponse ranking = null;
		try {
			ranking = new BaseResponse().builder()
					.code(0)
					.message("성공")
					.result(countingUtil.ranking())
					.build();
		} catch(Exception e) {
			ranking = new BaseResponse().builder()
					.code(-6)
					.message("알 수 없는 오류")
					.result(null)
					.build();
		}
		return new ResponseEntity<BaseResponse>(ranking, ranking.getCode() != 0 ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * 키워드 카운팅 쓰레드
	 *
	 */
	public class CountingThread extends Thread {
		
		private String keyword;
		
		public CountingThread(String keyword) {
			this.keyword = keyword;
		}
		
		@Override
		public void run() {
			try {
				countingUtil.keywordCounting(keyword);
			} catch (Exception e) {
			}
		}
	}
}
