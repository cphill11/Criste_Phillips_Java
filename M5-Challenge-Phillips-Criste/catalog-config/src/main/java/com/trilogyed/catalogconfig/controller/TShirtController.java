package com.trilogyed.catalogconfig.controller;

import com.trilogyed.catalogconfig.service.CatalogServiceLayer;
import com.trilogyed.catalogconfig.viewModel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value = "/tshirt")
@CrossOrigin(origins = {"http://localhost:3000"})
public class TShirtController {

    @Autowired
    CatalogServiceLayer service;

    // create TShirt
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel) {
        tShirtViewModel = service.createTShirt(tShirtViewModel);
        return tShirtViewModel;
    }

    // get TShirt by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int tShirtId) {
        TShirtViewModel tShirtViewModel = service.getTShirt(tShirtId);
        if (tShirtViewModel == null) {
            throw new IllegalArgumentException("T-Shirt could not be retrieved for id " + tShirtId);
        } else {
            return tShirtViewModel;
        }
    }

    // get all TShirts
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts() {
        List<TShirtViewModel> tvmByColor = service.getAllTShirts();
        if (tvmByColor == null || tvmByColor.isEmpty()) {
            throw new IllegalArgumentException("No t-shirts were found.");
        }
        return tvmByColor;
    }

    // get TShirt by Size
    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size) {
        List<TShirtViewModel> tvmBySize = service.getTShirtBySize(size);
        if (tvmBySize == null || tvmBySize.isEmpty()) {
            throw new IllegalArgumentException("No t-shirts were found in size " + size);
        }
        return tvmBySize;
    }

    // get TShirt by Color
    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color) {
        List<TShirtViewModel> tvmByColor = service.getTShirtByColor(color);
        if (tvmByColor == null || tvmByColor.isEmpty()) {
            throw new IllegalArgumentException("No t-shirts were found in " + color);
        }
        return tvmByColor;
    }

    // update TShirt
    @PutMapping (value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel) {
        if (tShirtViewModel == null || tShirtViewModel.getId() < 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (tShirtViewModel.getId() > 0) {
            service.updateTShirt(tShirtViewModel);
        }
    }

    // delete TShirt by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable("id") int tShirtId) {
        service.deleteTShirt(tShirtId);
    }
}
