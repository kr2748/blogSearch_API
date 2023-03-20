package kr.gykim.blogsearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverSearchRequest {
	
	private String query;
	@Builder.Default private int display = 10;
	@Builder.Default private int start = 1;
	@Builder.Default private String sort = "sim";

}
