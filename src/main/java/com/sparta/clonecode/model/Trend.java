package com.sparta.clonecode.model;

import com.sparta.clonecode.dto.TrendDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Trend {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String title;
    @Column(length = 1000, nullable = false)
    private String overview;
    @Column
    private String poster_path;
    @Column
    private String releaseDate;
    @Column
    private Double average;
    @Column(nullable = true)
    private String backdrop_path;
    @Column
    private Long contentId;
    @Column
    private Long genre;
    @Column
    private String name;


    public Trend(TrendDto trendDto) {
        this.title = trendDto.getTitle();
        this.overview = trendDto.getOverview();
        this.poster_path = trendDto.getPoster_path();
        this.releaseDate = trendDto.getReleaseDate();
        this.average = trendDto.getAverage();
        this.backdrop_path = trendDto.getBackdrop_path();
        this.contentId = trendDto.getContentId();
        this.genre = trendDto.getGenre().get(0);
        this.name = trendDto.getName();


    }
}
