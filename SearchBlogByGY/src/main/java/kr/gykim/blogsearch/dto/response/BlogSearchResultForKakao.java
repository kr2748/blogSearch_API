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
		 * ��α� ����
		 */
		private String title;
		
		/**
		 * ��α� �� ���
		 */
		private String contents;
		
		/**
		 * ��α� �� URL
		 */
		private String url;
		
		/**
		 * ��α� �̸�
		 */
		private String blogname;
		
		/**
		 * ��ǥ �̸����� �̹��� URL
		 */
		private String thumbnail;
		
		/**
		 * ��α� �� �ۼ��ð�
		 */
		private String datetime;
	};
}
