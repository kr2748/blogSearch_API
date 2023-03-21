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
	 * ��� �ڵ�
	 */
	private Integer code;
	
	/**
	 * ��� �޽���
	 */
	private String message;
	
	/**
	 * ��� �Ǽ�
	 */
	private Integer count;
	
	/**
	 * ���� ������
	 */
	private Integer page;
	
	/**
	 * �������� �� ��
	 */
	private Integer pageSize;
	
	/**
	 * ���
	 */
	private List<?> result;

}
