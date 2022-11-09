package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import com.trilogyed.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/trackRecommendation")
public class TrackRecommendationController {
    @Autowired
    TrackRecommendationRepository repo;

    // create Track Recommendation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {
        return repo.save(trackRecommendation);
    }

    // get all Track Recommendations
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendations() {
        return repo.findAll();
    }

    // as shown by Dan's heroku-coffee example
    // get Track Recommendation by ID
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendationByID(@PathVariable("id") long trackRecommendationId) {
        Optional<TrackRecommendation> returnVal = repo.findById(trackRecommendationId);
        if (!returnVal.isPresent()) {
            throw new IllegalArgumentException("No track with id " + trackRecommendationId);
        }
        return returnVal.get();
    }

    // update Album
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {
        repo.save(trackRecommendation);
    }

    // delete Album
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable("id") long trackRecommendationId) {
        repo.deleteById(trackRecommendationId);
    }
}
