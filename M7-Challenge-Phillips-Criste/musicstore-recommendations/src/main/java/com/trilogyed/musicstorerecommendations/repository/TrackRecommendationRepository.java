package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Long> {
    List<TrackRecommendation> findById(String trackRecommendation);
}
