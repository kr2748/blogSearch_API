package kr.gykim.blogsearch.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

	/**
	 * 결과 코드
	 */
	private Integer code;
	
	/**
	 * 결과 메시지
	 */
	private String message;
	
	/**
	 * 결과 건수
	 */
	private Integer count;
	
	/**
	 * 현재 페이지
	 */
	private Integer page;
	
	/**
	 * 페이지당 건 수
	 */
	private Integer pageSize;
	
	/**
	 * 결과
	 */
	private List<?> result;

}
