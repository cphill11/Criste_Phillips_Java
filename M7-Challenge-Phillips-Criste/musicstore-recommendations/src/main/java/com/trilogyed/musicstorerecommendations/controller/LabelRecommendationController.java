package com.trilogyed.musicstorerecommendations.controller;

import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trilogyed.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/labelRecommendation")
public class LabelRecommendationController {

    @Autowired
    LabelRecommendationRepository repo;

    // create Label Recommendation
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@RequestBody LabelRecommendation labelRecommendation) {
        return repo.save(labelRecommendation);
    }

    // get all Label Recommendations
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendations() {
        return repo.findAll();
    }
    // as shown by Dan's heroku-coffee example
    // get Label Recommendation by ID
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendationByID(@PathVariable("id") long labelRecommendationId) {
        Optional<LabelRecommendation> returnVal = repo.findById(labelRecommendationId);
        if (!returnVal.isPresent()) {
            throw new IllegalArgumentException("No label with id " + labelRecommendationId);
        }
        return returnVal.get();
    }

    // update Label
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@RequestBody LabelRecommendation labelRecommendation) {
        repo.save(labelRecommendation);
    }

    // delete Label
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable("id") long labelRecommendationId) {
        repo.deleteById(labelRecommendationId);
    }

}
