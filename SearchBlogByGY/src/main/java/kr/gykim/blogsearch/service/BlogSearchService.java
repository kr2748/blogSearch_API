package kr.gykim.blogsearch.service;

import kr.gykim.blogsearch.dto.BaseResponse;
import kr.gykim.blogsearch.dto.request.BlogSearchRequest;

public interface BlogSearchService {
	
	public BaseResponse blogSearch(BlogSearchRequest blogSearchRequest) throws Exception;
}
