package com.sparta.clonecode.utils;

import com.sparta.clonecode.dto.*;
import com.sparta.clonecode.model.*;
import com.sparta.clonecode.repository.DramaRepository;
import com.sparta.clonecode.repository.MovieRepository;
import com.sparta.clonecode.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ApiSearch {

    private final MovieRepository movieRepository;
    private final DramaRepository dramaRepository;
    private final TrendRepository trendRepository;

    //영화 검색
    public String moivePoppular(int page) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/movie/popular?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR&page=" + page, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }


    //Drama 검색
    public String dramaPoppular(int page) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/tv/popular?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR&page=" + page, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    //Drama DB
    public List<DramaDto> fromJSONtoDrama(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("results");

        List<DramaDto> dramaDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {

            JSONObject itemJson = items.getJSONObject(i);
            DramaDto itemDto = new DramaDto(itemJson);
            Drama content = new Drama(itemDto);
            dramaDtoList.add(itemDto);
            dramaRepository.save(content);
        }
        return dramaDtoList;
    }

    //트렌드 검색
    public String trend(int page) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/trending/all/day?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR&page=" + page, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    //트렌드 DB
    public List<TrendDto> fromJSONtotrend(String result) {
        JSONObject tjson = new JSONObject(result);
        JSONArray trends = tjson.getJSONArray("results");
        List<TrendDto> trendDtoList = new ArrayList<>();

        for (int i = 0; i < trends.length(); i++) {

            JSONObject trendJson = trends.getJSONObject(i);
            TrendDto trenddto = new TrendDto(trendJson);
            Trend trend = new Trend(trenddto);
            trendDtoList.add(trenddto);
            trendRepository.save(trend);
        }
        return trendDtoList;
    }


    //영화 DB
    public List<ContentDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("results");

        List<ContentDto> contentDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ContentDto itemDto = new ContentDto(itemJson);
            Content content = new Content(itemDto);
            contentDtoList.add(itemDto);
            movieRepository.save(content);
        }
        return contentDtoList;
    }

    //영화 상세검색
    public String moiveForId(Long id) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/movie/" + id + "?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public Detail fromJSONtoDetail(String result) {
        JSONObject rjson = new JSONObject(result);
        System.out.println(rjson);
        DetailDto itemDto = new DetailDto(rjson);
        System.out.println(itemDto);
        Detail detail = new Detail(itemDto);


        return detail;
    }

    //트렌트 상세 검색
    public String dramaForId(Long id) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/tv/" + id + "?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public DramaDetail fromJSONtoDramaDetail(String result) {
        JSONObject rjson = new JSONObject(result);
        System.out.println(rjson);
        DramaDetailDto itemDto = new DramaDetailDto(rjson);
        System.out.println(itemDto);
        DramaDetail dramaDetail = new DramaDetail(itemDto);
        return dramaDetail;
    }

    //영화 비디오 검색
    public String ContentVideoForId(Long id) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=ko-KR", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public VideoUrl fromJSONtoContentVideo(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("results");
        JSONObject itemJson = items.getJSONObject(0);
        VideoUrlDto videoUrlDto = new VideoUrlDto(itemJson);
        VideoUrl videoUrl = new VideoUrl(videoUrlDto);
        return videoUrl;
    }


    public String DramaVideoForId(Long id) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("cookie", "JSESSIONID=D639E44D96F4C8B7CCDD48F8F1CB2480");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.themoviedb.org/3/tv/" + id + "/videos?api_key=127d1ec8dfd28bfe9f6b8d15f689cdd4&language=en-US", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }


}
