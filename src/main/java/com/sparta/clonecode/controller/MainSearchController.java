package com.sparta.clonecode.controller;

import com.sparta.clonecode.dto.TrendDto;
import com.sparta.clonecode.model.*;
import com.sparta.clonecode.repository.DramaRepository;
import com.sparta.clonecode.repository.MovieRepository;
import com.sparta.clonecode.repository.TrendRepository;
import com.sparta.clonecode.utils.ApiSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class MainSearchController {

    private final ApiSearch apiSearch;
    private final MovieRepository movieRepository;
    private final DramaRepository dramaRepository;
    private final TrendRepository trendRepository;

    //메인 페이지 인기영화 데이터 조회
    @GetMapping("/api/main/database")
    public void getDB() {
        //10페이지까지 불러오기
        for (int i = 1; i <= 20; i++) {
            String resultString = apiSearch.moivePoppular(i);
            System.out.println(apiSearch.fromJSONtoItems(resultString));
        }
        //드라마
        for (int i = 1; i <= 19; i++) {
            String resultString = apiSearch.dramaPoppular(i);
            System.out.println(apiSearch.fromJSONtoDrama(resultString));
        }
        //트랜드 작품
        for (int i = 1; i <= 7; i++) {
            String resultString = apiSearch.trend(i);
            System.out.println(apiSearch.fromJSONtotrend(resultString));
            List<TrendDto> trendDtoList = apiSearch.fromJSONtotrend(resultString);
        }
    }


    @GetMapping("/api/main/movie")
    public List<Content> getContent() {
        return movieRepository.findAllByOrderByAverageDesc();
    }

    //영화 상세 검색
    @GetMapping("api/main/movie/{id}")
    public Detail getContentForId(@PathVariable Long id) {
        String resultString = apiSearch.moiveForId(id);
        return apiSearch.fromJSONtoDetail(resultString);
    }

    //영화 장르 검색
    @GetMapping("api/main/movie/genre/{id}")
    public List<Content> getContentForGenreId(@PathVariable Long id) {

        return movieRepository.findAllByGenre(id);
    }

    //영화 영상 검색
    @GetMapping("api/main/movie/video/{id}")
    public VideoUrl getContentVideoForId(@PathVariable Long id) {
        String resultString = apiSearch.ContentVideoForId(id);
        return apiSearch.fromJSONtoContentVideo(resultString);
    }


    @GetMapping("/api/main/trend")
    public List<Trend> getTrend() {
        return trendRepository.findAllByOrderByAverageDesc();
    }


    @GetMapping("/api/main/trend/{id}")
    public List<Trend> getTrendForId(@PathVariable Long id) {
        return trendRepository.findByContentId(id);
    }

    @GetMapping("/api/main/drama")
    public List<Drama> getDrama() {
        return dramaRepository.findAllByOrderByAverageDesc();
    }

    @GetMapping("api/main/drama/{id}")
    public DramaDetail getDramaForId(@PathVariable Long id) {
        String resultString = apiSearch.dramaForId(id);
        return apiSearch.fromJSONtoDramaDetail(resultString);
    }

    //드라마 장르 검색
    @GetMapping("api/main/drama/genre/{id}")
    public List<Drama> getDramaForGenreId(@PathVariable Long id) {
        return dramaRepository.findAllByGenre(id);
    }

    //드라마 영상 검색
    @GetMapping("api/main/drama/video/{id}")
    public VideoUrl getDramaVideoForId(@PathVariable Long id) {
        String resultString = apiSearch.DramaVideoForId(id);
        return apiSearch.fromJSONtoContentVideo(resultString);
    }
//    @GetMapping("api/main/videolink/")
//    public videourllist getContentId(@PathVariable Long id){
//        List videourllist = new ArrayList();
//        videourllist.add("key");
//    }


}
