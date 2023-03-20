package kr.gykim.blogsearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSearchRequest {

	private String query;
	@Builder.Default private String sort = "accuracy";
	@Builder.Default private int page = 1;
	@Builder.Default private int size = 10;
	
}
