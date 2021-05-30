package com.sparta.clonecode.dto;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DramaDto {

    @Column
    private Long contentId;
    @Column
    private String name;
    @Column(length = 1000, nullable = false)
    private String overview;
    @Column
    private String poster_path;
    @Column
    private String firstDate;
    @Column
    private Double average;
    @Column(nullable = true)
    private String backdrop_path;
    @Column
    private List<Long> genre;
    @Column
    private String adult;


    public DramaDto(JSONObject contentJson) {

        if (contentJson.has("id")) {
            this.contentId = contentJson.getLong("id");
        }

        if (contentJson.isNull("name")) {
            this.name = "";
        } else {
            this.name = contentJson.getString("name");
        }

        if (contentJson.isNull("overview")) {
            this.overview = "";
        } else {
            this.overview = contentJson.getString("overview");
        }

        if (contentJson.isNull("poster_path")) {
            this.poster_path = "";
        } else {
            this.poster_path = contentJson.getString("poster_path");
        }


        if (contentJson.isNull("first_air_date")) {
            this.firstDate = "";
        } else {
            this.firstDate = contentJson.getString("first_air_date");
        }

        if (contentJson.has("vote_average")) {
            this.average = contentJson.getDouble("vote_average");
        }

        if (contentJson.isNull("backdrop_path")) {
            this.backdrop_path = "";
        } else {
            this.backdrop_path = contentJson.getString("backdrop_path");
        }

        if (contentJson.isNull("adult")) {
            this.adult = null;
        } else {
            if (contentJson.getBoolean("adult")) {
                this.adult = "True";
            } else {
                this.adult = "False";
            }

        }

        if (contentJson.isNull("genre_ids")) {
            this.genre = null;
        } else {
            this.genre = new ArrayList<>();
            JSONArray genre_ids_array = contentJson.getJSONArray("genre_ids");

            if (genre_ids_array.length() > 0) {
                this.genre.add(genre_ids_array.getLong(0));
            } else {
                this.genre = null;
            }
        }

    }
}
