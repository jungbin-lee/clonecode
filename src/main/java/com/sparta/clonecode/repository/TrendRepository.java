package com.sparta.clonecode.repository;

import com.sparta.clonecode.model.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrendRepository extends JpaRepository<Trend, Long> {

    List<Trend> findAllByOrderByAverageDesc();

    List<Trend> findByContentId(Long contentId);
}
