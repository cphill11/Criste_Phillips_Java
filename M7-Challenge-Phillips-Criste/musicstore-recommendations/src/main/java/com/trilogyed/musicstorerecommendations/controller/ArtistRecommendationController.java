package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
import com.trilogyed.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artistRecommendation")
public class ArtistRecommendationController {
    @Autowired
    ArtistRecommendationRepository repo;

    // create ArtistRecommendation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRecommendation(@RequestBody ArtistRecommendation artistRecommendation) {
        return repo.save(artistRecommendation);
    }

    // get all ArtistRecommendations
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        return repo.findAll();
    }

    // as shown by Dan's heroku-coffee example
    // get ArtistRecommendation by ID
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRecommendationByID(@PathVariable("id") long artistRecommendationId) {
        Optional<ArtistRecommendation> returnVal = repo.findById(artistRecommendationId);
        if (!returnVal.isPresent()) {
            throw new IllegalArgumentException("No artist with id " + artistRecommendationId);
        }
        return returnVal.get();
    }

    // update Artist
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@RequestBody ArtistRecommendation artistRecommendation) {
        repo.save(artistRecommendation);
    }

    // delete Artist
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable("id") long artistRecommendationId) {
        repo.deleteById(artistRecommendationId);
    }
}
