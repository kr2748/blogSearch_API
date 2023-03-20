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
public class BlogSearchResult {
	
	private int page;
	private int size;
	
	private List<SearchItem> searchItems;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SearchItem{
		private String title;
		private String contents;
		private String url;
		private String blogname;
		private String datetime;
	}

}
