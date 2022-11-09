package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
import com.trilogyed.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/albumRecommendation")
public class AlbumRecommendationController {
    @Autowired
    AlbumRecommendationRepository repo;

    // create AlbumRecommendation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@RequestBody AlbumRecommendation albumRecommendation) {
        return repo.save(albumRecommendation);
    }

    // get all AlbumRecommendations
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() {
        return repo.findAll();
    }

    // as shown by Dan's heroku-coffee example
    // get AlbumRecommendation by ID
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendationByID(@PathVariable("id") long albumRecommendationId) {
        Optional<AlbumRecommendation> returnVal = repo.findById(albumRecommendationId);
        if (!returnVal.isPresent()) {
            throw new IllegalArgumentException("No album with id " + albumRecommendationId);
        }
        return returnVal.get();
    }

    // update Album
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@RequestBody AlbumRecommendation albumRecommendation) {
        repo.save(albumRecommendation);
    }

    // delete Album
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable("id") long albumRecommendationId) {
        repo.deleteById(albumRecommendationId);
    }
}

