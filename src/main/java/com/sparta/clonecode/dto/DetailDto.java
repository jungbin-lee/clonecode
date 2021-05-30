package com.sparta.clonecode.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class DetailDto {

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


    public DetailDto(JSONObject contentJson) {

        if (contentJson.isNull("backdrop_path")) {
            this.backdrop_path = null;
        } else {
            this.backdrop_path = contentJson.getString("backdrop_path");
        }

        if (contentJson.isNull("genres")) {
            this.genre = null;
        } else {

            JSONArray genre_ids_array = contentJson.getJSONArray("genres");
            JSONObject genre_object = genre_ids_array.getJSONObject(0);
            this.genre = genre_object.getString("name");

        }

        if (contentJson.isNull("title")) {
            this.title = null;
        } else {
            this.title = contentJson.getString("title");
        }

        if (contentJson.isNull("overview")) {
            this.overview = null;
        } else {
            this.overview = contentJson.getString("overview");
        }

        if (contentJson.isNull("release_date")) {
            this.releaseDate = null;
        } else {
            this.releaseDate = contentJson.getString("release_date");
        }


        if (contentJson.isNull("runtime")) {
            this.runtime = null;
        } else {
            this.runtime = contentJson.getLong("runtime");
        }


        if (contentJson.isNull("vote_average")) {
            this.vote_average = null;
        } else {
            this.vote_average = contentJson.getDouble("vote_average");
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


    }
}
