package kr.gykim.blogsearch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.stereotype.Component;

import kr.gykim.blogsearch.dto.request.BlogSearchRequest;

@Component
public class ApiUtil {
	
	public String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body) throws UnsupportedEncodingException{
        InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
    
    /**
     * 
     * @param request
     * @param type - 0 : kakao, 1 : naver
     * @return
     * @throws UnsupportedEncodingException 
     */
    public String convertQueryString(BlogSearchRequest request, int type) throws UnsupportedEncodingException {
    	if(type == 0) {
    		return convertKaKaoQueryString(request);
    	}else if(type == 1) {
    		return convertNaverQueryString(request);
    	}else {
    		return convertKaKaoQueryString(request);
    	}
    }
    
    public String convertKaKaoQueryString(BlogSearchRequest request) throws UnsupportedEncodingException {
    	String query = "";
    	query += "query=" + URLEncoder.encode(request.getKeyword(), "UTF-8")
	    	+ "&sort=" + (request.getSortType() == 0 ? "accuracy" : "recency")
	    	+ "&page=" + request.getPageNum()
	    	+ "&size=" + request.getPageSize();
    	return query;
    }
    
    public String convertNaverQueryString(BlogSearchRequest request) throws UnsupportedEncodingException {
    	String query = "";
    	query += "query=" + URLEncoder.encode(request.getKeyword(), "UTF-8")
    		+ "&sort=" + (request.getSortType() == 0 ? "sim" : "date")
    		+ "&start=" + request.getPageNum()
    		+ "&display=" + request.getPageSize();
    	return query;
    }
    
}
