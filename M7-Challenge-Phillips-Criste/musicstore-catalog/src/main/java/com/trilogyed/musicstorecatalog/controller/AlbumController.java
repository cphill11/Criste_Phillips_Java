package com.trilogyed.musicstorecatalog.controller;


import com.trilogyed.musicstorecatalog.viewModel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/album")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AlbumController {
    @Autowired


    // create Album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumViewModel createAlbum(@RequestBody @Valid AlbumViewModel albumViewModel) {
       albumViewModel = service.createAlbum(albumViewModel);
        return albumViewModel;
    }


    // get all Albums

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumViewModel> getAllAlbums() {

        List<AlbumViewModel> avmByTitle  = service.getAllAlbums();
        if (avmByTitle == null || avmByTitle .isEmpty()) {
            throw new IllegalArgumentException("No albums were found");
        } else
            return avmByTitle;
    }


    // get Album by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumViewModel getAlbumByID(@PathVariable("id") long albumId) {
        AlbumViewModel albumViewModel = service.findById(albumId);
        if (albumViewModel == null) {
            throw new IllegalArgumentException("Album could not be retrieved for id " + albumId);
        } else {
            return albumViewModel;
        }
    }

    // update Album
    @PutMapping (value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody @Valid AlbumViewModel albumViewModel) {

        if (albumViewModel == null || albumViewModel.getId() < 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (albumViewModel.getId() > 0) {
            service.updateAlbum(albumViewModel);
        }
    }

    // delete Album

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteConsole(@PathVariable("id") long consoleId) {
//        service.deleteConsole(consoleId);
//    }

}
