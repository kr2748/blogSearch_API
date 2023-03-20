package kr.gykim.blogsearch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class KeywordEntity {
	
	@Builder
	public KeywordEntity(Long id, String keyword, Long count) {
		this.id = id;
		this.keyword = keyword;
		this.count = count;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String keyword;
	
	@Column
	private Long count;

}
