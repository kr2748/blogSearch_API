package kr.gykim.blogsearch.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearchResultForKakao {
	
	private String errorType;
	
	private String message;
	
	private Meta meta;
	
	private List<Document> Documents;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Meta{
		private int total_count;
		private int pageable_count;
		private String is_end;
	}
	
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Document{
		
		/**
		 * 블로그 제목
		 */
		private String title;
		
		/**
		 * 블로그 글 요약
		 */
		private String contents;
		
		/**
		 * 블로그 글 URL
		 */
		private String url;
		
		/**
		 * 블로그 이름
		 */
		private String blogname;
		
		/**
		 * 대표 미리보기 이미지 URL
		 */
		private String thumbnail;
		
		/**
		 * 블로그 글 작성시간
		 */
		private String datetime;
	};
}
