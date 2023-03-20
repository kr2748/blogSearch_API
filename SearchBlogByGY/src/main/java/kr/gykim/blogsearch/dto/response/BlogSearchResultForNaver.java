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
public class BlogSearchResultForNaver {
	
	private String errorCode;
	private String errorMessage;
	
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	
	private List<Item> items;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Item{
		
		private String title;
		private String link;
		private String description;
		private String bloggername;
		private String bloggerlink;
		private String postdate;
	}

}
