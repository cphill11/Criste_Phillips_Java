package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Long> {
    List<ArtistRecommendation> findById(String artistRecommendation);
}
