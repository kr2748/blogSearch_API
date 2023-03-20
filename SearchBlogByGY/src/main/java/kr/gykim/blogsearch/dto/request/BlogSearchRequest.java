package kr.gykim.blogsearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearchRequest {
	
	private String keyword;
	@Builder.Default private int sortType = 0;
	@Builder.Default private int pageNum = 1;
	@Builder.Default private int pageSize = 10;

}
