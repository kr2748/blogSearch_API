package kr.gykim.blogsearch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordRankResult {
	
	private int rank;
	private String keyword;
	private Long count;

}
