package kr.gykim.blogsearch.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import kr.gykim.blogsearch.dto.response.BlogSearchResult;
import kr.gykim.blogsearch.dto.response.BlogSearchResult.SearchItem;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForKakao;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForKakao.Document;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForNaver;
import kr.gykim.blogsearch.dto.response.BlogSearchResultForNaver.Item;

@Component
public class MappingUtil {
	
	public BlogSearchResult kakaoMapping(BlogSearchResultForKakao kakaoResult) {
		
		BlogSearchResult result = new BlogSearchResult();
		
		List<SearchItem> searchItems = new ArrayList<SearchItem>();
		for(Document doc : kakaoResult.getDocuments()) {
			SearchItem item = new SearchItem();
			
			item.setTitle(doc.getTitle());
			item.setContents(doc.getContents());
			item.setUrl(doc.getUrl());
			item.setBlogname(doc.getBlogname());
			item.setDatetime(doc.getDatetime());
			
			searchItems.add(item);
		}
		
		result.setSearchItems(searchItems);
		
	    return result;
	}
	
public BlogSearchResult naverMapping(BlogSearchResultForNaver naverResult) {
		
		BlogSearchResult result = new BlogSearchResult();
		
		List<SearchItem> searchItems = new ArrayList<SearchItem>();
		for(Item doc : naverResult.getItems()) {
			SearchItem item = new SearchItem();
			
			item.setTitle(doc.getTitle());
			item.setContents(doc.getDescription());
			item.setUrl(doc.getLink());
			item.setBlogname(doc.getBloggername());
			item.setDatetime(doc.getPostdate());
			
			searchItems.add(item);
		}
		
		result.setSearchItems(searchItems);
		
	    return result;
	}

}
