package com.sparta.clonecode.model;

import com.sparta.clonecode.dto.VideoUrlDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class VideoUrl {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String key;

    public VideoUrl(VideoUrlDto VideoUrlDto) {
        this.key = VideoUrlDto.getKey();
    }


}
