package kr.gykim.blogsearch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.gykim.blogsearch.constant.ApiInfo;
import kr.gykim.blogsearch.dto.BaseResponse;
import kr.gykim.blogsearch.dto.request.BlogSearchRequest;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForKakao;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForNaver;
import kr.gykim.blogsearch.utils.ApiUtil;
import kr.gykim.blogsearch.utils.MappingUtil;

@Service
public class BlogSearchServiceImpl implements BlogSearchService {
	
	@Autowired
	private ApiUtil apiUtil;
	
	@Autowired
	private MappingUtil mappingUtil;
	
	@Override
	public BaseResponse blogSearch(BlogSearchRequest blogSearchRequest) throws Exception {
		
		try {
			BaseResponse baseResponse = blogSearchFromKakao(blogSearchRequest);
			if(baseResponse.getCount() == 0) {
				return blogSearchFromNaver(blogSearchRequest);
			}else {
				return baseResponse;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return blogSearchFromNaver(blogSearchRequest);
		}
		
	}
	
	private BaseResponse blogSearchFromKakao(BlogSearchRequest blogSearchRequest) throws Exception {
		
		String url = ApiInfo.KAKAO_URL + "?";
		url += apiUtil.convertQueryString(blogSearchRequest, 0);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/json;utf-8");
		requestHeaders.put("Authorization", "KakaoAK " + ApiInfo.KAKAO_API_KEY);
				
		String response = apiUtil.get(url, requestHeaders);
		
		ObjectMapper mapper = new ObjectMapper();
		
		BlogSearchResultForKakao kakaoResult = mapper.readValue(response, BlogSearchResultForKakao.class);
		
		if(kakaoResult.getErrorType() != null) {
			return new BaseResponse().builder()
					.code(-5)
					.count(0)
					.page(blogSearchRequest.getPageNum())
					.pageSize(blogSearchRequest.getPageSize())
					.message(kakaoResult.getMessage())
					.result(null)
					.build();
		}

		return new BaseResponse().builder()
				.code(0)
				.count(kakaoResult.getMeta().getTotal_count())
				.page(blogSearchRequest.getPageNum())
				.pageSize(blogSearchRequest.getPageSize())
				.message("성공")
				.result(mappingUtil.kakaoMapping(kakaoResult).getSearchItems())
				.build();
		
	}
	
	private BaseResponse blogSearchFromNaver(BlogSearchRequest blogSearchRequest) throws Exception {
		
		String url = ApiInfo.NAVER_URL + "?";
		url += apiUtil.convertQueryString(blogSearchRequest, 1);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/json;utf-8");
		requestHeaders.put("X-Naver-Client-Id", ApiInfo.NAVER_CLIENT_ID);
		requestHeaders.put("X-Naver-Client-Secret", ApiInfo.NAVER_SECRET_KEY);
				
		String response = apiUtil.get(url, requestHeaders);
		
		ObjectMapper mapper = new ObjectMapper();
		
		BlogSearchResultForNaver naverResult = mapper.readValue(response, BlogSearchResultForNaver.class);
		
		if(naverResult.getErrorCode() != null) {
			return new BaseResponse().builder()
					.code(-5)
					.count(0)
					.page(blogSearchRequest.getPageNum())
					.pageSize(blogSearchRequest.getPageSize())
					.message(naverResult.getErrorMessage())
					.result(null)
					.build();
		}
		
		return new BaseResponse().builder()
				.code(0)
				.count(naverResult.getTotal())
				.page(blogSearchRequest.getPageNum())
				.pageSize(blogSearchRequest.getPageSize())
				.message("성공")
				.result(mappingUtil.naverMapping(naverResult).getSearchItems())
				.build();
	}

}
