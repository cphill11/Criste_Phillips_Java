package com.trilogyed.musicstorecatalog.controller;


import com.trilogyed.musicstorecatalog.service.MusicStoreCatalogServiceLayer;
import com.trilogyed.musicstorecatalog.viewModel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/album")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AlbumController {
    @Autowired
    MusicStoreCatalogServiceLayer service;

    // create Album
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public AlbumViewModel createAlbum(@RequestBody @Valid AlbumViewModel albumViewModel) {
//       albumViewModel = service.createAlbum(albumViewModel);
//        return albumViewModel;
//    }


    // get all Albums

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<ConsoleViewModel> getAllConsoles() {
//        List<ConsoleViewModel> cvmByManufacturer = service.getAllConsoles();
//        if (cvmByManufacturer == null || cvmByManufacturer.isEmpty()) {
//            throw new IllegalArgumentException("No consoles were found");
//        } else
//            return cvmByManufacturer;
//    }


    // get Album by ID
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ConsoleViewModel getConsole(@PathVariable("id") long consoleId) {
//        ConsoleViewModel consoleViewModel = service.getConsoleById(consoleId);
//        if (consoleViewModel == null) {
//            throw new IllegalArgumentException("Console could not be retrieved for id " + consoleId);
//        } else {
//            return consoleViewModel;
//        }
//    }

    // update Album
//    @PutMapping (value = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel) {
//
//        if (consoleViewModel == null || consoleViewModel.getId() < 1) {
//            throw new IllegalArgumentException("Id in path must match id in view model");
//        } else if (consoleViewModel.getId() > 0) {
//            service.updateConsole(consoleViewModel);
//        }
//    }

    // delete Album

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteConsole(@PathVariable("id") long consoleId) {
//        service.deleteConsole(consoleId);
//    }

}
