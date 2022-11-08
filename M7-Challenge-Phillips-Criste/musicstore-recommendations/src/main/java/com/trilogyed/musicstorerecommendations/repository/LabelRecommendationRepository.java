package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRecommendationRepository extends JpaRepository<LabelRecommendation, Long> {
    List<LabelRecommendation> findById(String labelRecommendation);
}
