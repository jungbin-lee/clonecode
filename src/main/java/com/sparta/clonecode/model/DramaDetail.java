package com.sparta.clonecode.model;

import com.sparta.clonecode.dto.DramaDetailDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class DramaDetail {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column
    private String backdrop_path;
    @Column
    private String genre;
    @Column
    private String title;
    @Column(length = 1000, nullable = false)
    private String overview;
    @Column
    private String releaseDate;
    @Column
    private Long runtime;
    @Column
    private Double vote_average;
    @Column
    private String adult;


    public DramaDetail(DramaDetailDto DramaDto) {
        this.backdrop_path = DramaDto.getBackdrop_path();
        this.genre = DramaDto.getGenre();
        this.title = DramaDto.getTitle();
        this.overview = DramaDto.getOverview();
        this.releaseDate = DramaDto.getReleaseDate();
        this.runtime = DramaDto.getRuntime().get(0);
        this.vote_average = DramaDto.getVote_average();
        this.adult = DramaDto.getAdult();
    }
}
