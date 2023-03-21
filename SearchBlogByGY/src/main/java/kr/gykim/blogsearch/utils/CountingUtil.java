package kr.gykim.blogsearch.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.gykim.blogsearch.dto.response.KeywordRankResult;
import kr.gykim.blogsearch.entity.KeywordEntity;
import kr.gykim.blogsearch.repository.KeywordRepository;

@Component
public class CountingUtil {
	
	@Autowired
	private KeywordRepository keywordRepository;
	
	/**
	 * �˻��� ī����
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
	 * Ű���� ��ŷ 10������
	 * @return
	 */
	public List<KeywordRankResult> ranking() throws Exception{
		List<KeywordRankResult> rankResult = new ArrayList<KeywordRankResult>();
		List<KeywordEntity> ranking = keywordRepository.findOrderByCountDesc();
		int rankingSize = ranking.size();
		for(int i=0;i<rankingSize;i++) {
			KeywordRankResult keywordRank = new KeywordRankResult();
			keywordRank.setRank(i+1);
			keywordRank.setKeyword(ranking.get(i).getKeyword());
			keywordRank.setCount(ranking.get(i).getCount());
			rankResult.add(keywordRank);
		}
		
		return rankResult;
	}
	
}
