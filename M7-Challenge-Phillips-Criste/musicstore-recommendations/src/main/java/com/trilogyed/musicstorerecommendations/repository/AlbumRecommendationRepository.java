package com.trilogyed.musicstorerecommendations.repository;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Long> {
    List<AlbumRecommendation> findById(String albumRecommendation);
}
