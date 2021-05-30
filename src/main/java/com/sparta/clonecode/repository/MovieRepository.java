package com.sparta.clonecode.repository;

import com.sparta.clonecode.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Content, Long> {

    List<Content> findAllByOrderByAverageDesc();

    List<Content> findAllByGenre(Long Genre);

}
