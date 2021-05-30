package com.sparta.clonecode.dto;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TrendDto {

    private String title;
    private String overview;
    private String poster_path;
    private String releaseDate;
    private Double average;
    private Long contentId;
    private String backdrop_path;
    private List<Long> genre;
    private String name;
    private String firstDate;


    public TrendDto(JSONObject trendJson) {
        if (trendJson.has("id")) {
            this.contentId = trendJson.getLong("id");
        }
        if (trendJson.has("name")) {
            this.name = trendJson.getString("name");
        }
        if (trendJson.has("first_air_date")) {
            this.firstDate = trendJson.getString("first_air_date");
        }


        if (trendJson.isNull("title")) {
            this.title = "";
        } else {
            this.title = trendJson.getString("title");
        }

        if (trendJson.isNull("overview")) {
            this.overview = "";
        } else {
            this.overview = trendJson.getString("overview");
        }

        if (trendJson.isNull("poster_path")) {
            this.poster_path = "";
        } else {
            this.poster_path = trendJson.getString("poster_path");
        }


        if (trendJson.isNull("release_date")) {
            this.releaseDate = "";
        } else {
            this.releaseDate = trendJson.getString("release_date");
        }

        if (trendJson.has("vote_average")) {
            this.average = trendJson.getDouble("vote_average");
        }

        if (trendJson.isNull("backdrop_path")) {
            this.backdrop_path = "";
        } else {
            this.backdrop_path = trendJson.getString("backdrop_path");
        }
        if (trendJson.isNull("genre_ids")) {
            this.genre = null;
        } else {
            this.genre = new ArrayList<>();

            JSONArray genre_ids_array = trendJson.getJSONArray("genre_ids");
            for (int i = 0; i < genre_ids_array.length(); i++) {
                this.genre.add(genre_ids_array.getLong(i));
            }

        }
    }
}
