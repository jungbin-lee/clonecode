package com.sparta.clonecode.dto;

import lombok.Getter;
import org.json.JSONObject;

import javax.persistence.Column;

@Getter
public class VideoUrlDto {


    @Column
    private String key;


    public VideoUrlDto(JSONObject contentJson) {

        if (contentJson.isNull("key")) {
            this.key = "";
        } else {
            this.key = contentJson.getString("key");
        }


    }
}
