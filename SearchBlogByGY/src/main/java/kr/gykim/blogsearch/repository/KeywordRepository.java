package kr.gykim.blogsearch.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import kr.gykim.blogsearch.entity.KeywordEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KeywordRepository {
	
	private final EntityManager em;
	
	public void save(KeywordEntity keywordEntity) throws Exception{
		em.persist(keywordEntity);
	}
	
	public KeywordEntity findByKeword(String keyword) throws NoResultException{
		return em.createQuery("select k from KeywordEntity k where k.keyword = :keyword", KeywordEntity.class)
				.setParameter("keyword", keyword).getSingleResult();
	}
	
	public List<KeywordEntity> findOrderByCountDesc() throws NoResultException{
		return em.createQuery("select k from KeywordEntity k order by k.count desc", KeywordEntity.class)
				.setMaxResults(10).getResultList();
	}
	
}
