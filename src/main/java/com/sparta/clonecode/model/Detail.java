package com.sparta.clonecode.model;

import com.sparta.clonecode.dto.DetailDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Detail {

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

    public Detail(DetailDto DetailDto) {
        this.backdrop_path = DetailDto.getBackdrop_path();
        this.genre = DetailDto.getGenre();
        this.title = DetailDto.getTitle();
        this.overview = DetailDto.getOverview();
        this.releaseDate = DetailDto.getReleaseDate();
        this.runtime = DetailDto.getRuntime();
        this.vote_average = DetailDto.getVote_average();
        this.adult = DetailDto.getAdult();


    }

}
