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
			checkResult.setMessage("�˻��� ����");
			return checkResult;
		}else {
			if(StringUtils.trimWhitespace(blogSearchRequest.getKeyword()).equals("")) {
				checkResult.setCode(-1);
				checkResult.setMessage("�˻��� ����");
				return checkResult;
			}
		}
		
		if(blogSearchRequest.getPageNum() > 50 || blogSearchRequest.getPageNum() < 1) {
			checkResult.setCode(-2);
			checkResult.setMessage("������ ��ȣ�� 1 ~ 50������ �Է� �����մϴ�.");
			return checkResult;
		}
		
		if(blogSearchRequest.getPageSize() > 50 || blogSearchRequest.getPageSize() < 1) {
			checkResult.setCode(-3);
			checkResult.setMessage("�������� �Ǽ��� 1 ~ 50������ �Է� �����մϴ�.");
			return checkResult;
		}
		
		if(blogSearchRequest.getSortType() != 0 && blogSearchRequest.getSortType() != 1) {
			checkResult.setCode(-4);
			checkResult.setMessage("���Ĺ���� 0(��Ȯ��), 1(�ֽż�)�� �Է� �����մϴ�.");
			return checkResult;
		}
		
		return null;
		
	}

}
