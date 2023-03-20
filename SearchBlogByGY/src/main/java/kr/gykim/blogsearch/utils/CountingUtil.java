package kr.gykim.blogsearch.utils;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.gykim.blogsearch.entity.KeywordEntity;
import kr.gykim.blogsearch.repository.KeywordRepository;

@Component
public class CountingUtil {
	
	@Autowired
	private KeywordRepository keywordRepository;
	
	/**
	 * 검색어 카운팅
	 */
	@Transactional
	public synchronized void keywordCounting(String keyword) throws Exception{
		KeywordEntity keywordEntity = null;
		try {
			keywordEntity = keywordRepository.findByKeword(keyword);
			keywordEntity.setCount(keywordEntity.getCount() + 1L);
			keywordRepository.save(keywordEntity);
		} catch (NoResultException e) {
			keywordEntity = new KeywordEntity().builder()
					.id(null)
					.keyword(keyword)
					.count(1L)
					.build();
			keywordRepository.save(keywordEntity);
		}

		
	}
	
	/**
	 * 키워드 랭킹 10위까지
	 * @return
	 */
	public List<KeywordEntity> ranking() throws Exception{
		return keywordRepository.findOrderByCountDesc();
	}
	
}
