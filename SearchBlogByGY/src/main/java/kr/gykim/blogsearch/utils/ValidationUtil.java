package kr.gykim.blogsearch.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import kr.gykim.blogsearch.dto.BaseResponse;
import kr.gykim.blogsearch.dto.request.BlogSearchRequest;

@Component
public class ValidationUtil {
	
	public BaseResponse validaionCheck(BlogSearchRequest blogSearchRequest){
		BaseResponse checkResult = new BaseResponse();
		
		if(blogSearchRequest.getKeyword() == null) {
			checkResult.setCode(-1);
			checkResult.setMessage("검색어 누락");
			return checkResult;
		}else {
			if(StringUtils.trimWhitespace(blogSearchRequest.getKeyword()).equals("")) {
				checkResult.setCode(-1);
				checkResult.setMessage("검색어 누락");
				return checkResult;
			}
		}
		
		if(blogSearchRequest.getPageNum() > 50 || blogSearchRequest.getPageNum() < 1) {
			checkResult.setCode(-2);
			checkResult.setMessage("페이지 번호는 1 ~ 50까지만 입력 가능합니다.");
			return checkResult;
		}
		
		if(blogSearchRequest.getPageSize() > 50 || blogSearchRequest.getPageSize() < 1) {
			checkResult.setCode(-3);
			checkResult.setMessage("페이지당 건수는 1 ~ 50까지만 입력 가능합니다.");
			return checkResult;
		}
		
		if(blogSearchRequest.getSortType() != 0 && blogSearchRequest.getSortType() != 1) {
			checkResult.setCode(-4);
			checkResult.setMessage("정렬방식은 0(정확도), 1(최신순)만 입력 가능합니다.");
			return checkResult;
		}
		
		return null;
		
	}

}
