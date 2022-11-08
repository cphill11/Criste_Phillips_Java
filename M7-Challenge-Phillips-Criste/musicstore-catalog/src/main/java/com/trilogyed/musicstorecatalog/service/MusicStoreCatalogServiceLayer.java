package com.trilogyed.musicstorecatalog.service;

import com.trilogyed.musicstorecatalog.model.Album;
import com.trilogyed.musicstorecatalog.model.Artist;
import com.trilogyed.musicstorecatalog.model.Label;
import com.trilogyed.musicstorecatalog.repository.AlbumRepository;
import com.trilogyed.musicstorecatalog.repository.ArtistRepository;
import com.trilogyed.musicstorecatalog.repository.LabelRepository;
import com.trilogyed.musicstorecatalog.repository.TrackRepository;
import com.trilogyed.musicstorecatalog.viewModel.AlbumViewModel;
import com.trilogyed.musicstorecatalog.viewModel.ArtistViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// as worked on in Challenge 5
@Component
public class MusicStoreCatalogServiceLayer {
    private final String ALBUM_ITEM_TYPE = "Album";
    private final String ARTIST_ITEM_TYPE = "Artist";
    private final String LABEL_ITEM_TYPE = "Label";
    private final String TRACK_ITEM_TYPE = "Track";

    AlbumRepository albumRepo;
    ArtistRepository artistRepo;
    LabelRepository labelRepo;
    TrackRepository trackRepo;



    @Autowired
    public MusicStoreCatalogServiceLayer( AlbumRepository albumRepo, ArtistRepository artistRepo, LabelRepository labelRepo, TrackRepository trackRepo) {
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.labelRepo = labelRepo;
        this.trackRepo = trackRepo;
    }
    // Album service layer methods
    public AlbumViewModel getGame(long id) {
        Optional<Album> album = albumRepo.findById(id);
        if (album == null) {
            return null;
        } else {
            return buildAlbumViewModel(album.get());
      }
    }

    public AlbumViewModel createGame(AlbumViewModel albumViewModel) {

        // Validate incoming Album Data in the view model using JSR303
        if (albumViewModel == null) throw new IllegalArgumentException("No Album is passed! Album object is null!");

        Album album = new Album();
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId(albumViewModel.getArtistId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setLabelId(albumViewModel.getLabelId());
        album.setListPrice(albumViewModel.getListPrice());

        albumViewModel.setId(albumRepo.save(album).getId());
        return albumViewModel;
    }

    public void updateAlbum(AlbumViewModel albumViewModel) {

        //Validate incoming Album Data in the view model
        if (albumViewModel == null)
            throw new IllegalArgumentException("No Album data is passed! Album object is null!");

        //ensure album exists; if not, throw exception
        if (this.getAlbum(albumViewModel.getId()) == null)
            throw new IllegalArgumentException("No such album to update.");

        Album album = new Album();
        album.setId(albumViewModel.getId());
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId(albumViewModel.getArtistId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setLabelId(albumViewModel.getLabelId());
        album.setListPrice(albumViewModel.getListPrice());

        albumRepo.save(album);
    }

    public void deleteAlbum(long id) {
        albumRepo.deleteById(id);
    }

    public List<AlbumViewModel> getAllAlbums() {
        List<Album> gameList = albumRepo.findAll();
        List<AlbumViewModel> gvmList = new ArrayList<>();

        if (albumList == null)
            return null;
        else
            albumList.stream().forEach(g -> avmList.add(buildAlbumViewModel(g)));
        return avmList;
    }

    // Artist service layer methods
    public ArtistViewModel createArtist(ArtistViewModel artistViewModel) {
        // ViewModel data was validated using JSR 303 for incoming Artist data
        if (artistViewModel == null) throw new IllegalArgumentException("No Artist is passed! Artist object is null!");

        Artist artist = new Artist();
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        return buildArtistViewModel(artistRepo.save(artist));
    }

    public ArtistViewModel getArtistById(long id) {
        Optional<Artist> artist = artistRepo.findById(id);
        if (artist == null)
            return null;
        else
            return buildArtistViewModel(artist.get());
    }

    public void updateConsole(ArtistViewModel consoleViewModel) {

        //Validate incoming Console Data in the view model
        if (consoleViewModel == null)
            throw new IllegalArgumentException("No console data is passed! Console object is null!");

        //ensure Console exists; if not, throw exception
        if (this.getConsoleById(consoleViewModel.getId()) == null)
            throw new IllegalArgumentException("No such console to update.");

        Console console = new Console();
        console.setId(consoleViewModel.getId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleRepo.save(console);
    }

    public void deleteConsole(long id) {
        consoleRepo.deleteById(id);
    }

    public List<ArtistViewModel> getAllConsoles() {
        List<Console> consoleList = consoleRepo.findAll();
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        if (consoleList == null)
            return null;
        else
            consoleList.stream().forEach(c -> cvmList.add(buildConsoleViewModel(c)));
        return cvmList;
    }

//    //T-Shirt service layer
//    public TShirtViewModel createTShirt(TShirtViewModel tShirtViewModel) {
//
//        // Model view validated through JSR 303 for incoming TShirt Data in the view model
//        if (tShirtViewModel == null) throw new IllegalArgumentException("No TShirt is passed! TShirt object is null!");
//
//        TShirt tShirt = new TShirt();
//        tShirt.setSize(tShirtViewModel.getSize());
//        tShirt.setColor(tShirtViewModel.getColor());
//        tShirt.setDescription(tShirtViewModel.getDescription());
//        tShirt.setPrice(tShirtViewModel.getPrice());
//        tShirt.setQuantity(tShirtViewModel.getQuantity());
//
//        tShirt = tShirtRepo.save(tShirt);
//
//        return buildTShirtViewModel(tShirt);
//    }
//
//    public TShirtViewModel getTShirt(long id) {
//        Optional<TShirt> tShirt = tShirtRepo.findById(id);
//        if (tShirt == null)
//            return null;
//        else
//            return buildTShirtViewModel(tShirt.get());
//    }
//
//    public void updateTShirt(TShirtViewModel tShirtViewModel) {
//        if (tShirtViewModel == null) throw new IllegalArgumentException("No TShirt is passed! TShirt object is null!");
//
//        //ensure T-Shirt exists; if not, throw exception
//        if (this.getTShirt(tShirtViewModel.getId()) == null)
//            throw new IllegalArgumentException("No such TShirt to update.");
//
//        TShirt tShirt = new TShirt();
//        tShirt.setId(tShirtViewModel.getId());
//        tShirt.setSize(tShirtViewModel.getSize());
//        tShirt.setColor(tShirtViewModel.getColor());
//        tShirt.setDescription(tShirtViewModel.getDescription());
//        tShirt.setPrice(tShirtViewModel.getPrice());
//        tShirt.setQuantity(tShirtViewModel.getQuantity());
//
//        tShirtRepo.save(tShirt);
//    }
//
//    public void deleteTShirt(long id) {
//        tShirtRepo.deleteById(id);
//    }
//
//    public List<TShirtViewModel> getTShirtByColor(String color) {
//        List<TShirt> tShirtList = tShirtRepo.findAllByColor(color);
//        List<TShirtViewModel> tvmList = new ArrayList<>();
//
//        if (tShirtList == null)
//            return null;
//        else
//            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
//        return tvmList;
//    }
//
//    public List<TShirtViewModel> getTShirtBySize(String size) {
//        List<TShirt> tShirtList = tShirtRepo.findAllBySize(size);
//        List<TShirtViewModel> tvmList = new ArrayList<>();
//
//        if (tShirtList == null)
//            return null;
//        else
//            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
//        return tvmList;
//    }
//
//    public List<TShirtViewModel> getAllTShirts() {
//        List<TShirt> tShirtList = tShirtRepo.findAll();
//        List<TShirtViewModel> tvmList = new ArrayList<>();
//
//        if (tShirtList == null)
//            return null;
//        else
//            tShirtList.stream().forEach(t -> tvmList.add(buildTShirtViewModel(t)));
//        return tvmList;
//    }
//
//    //Helper Methods...
//    public ConsoleViewModel buildConsoleViewModel(Console console) {
//        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
//        consoleViewModel.setId(console.getId());
//        consoleViewModel.setModel(console.getModel());
//        consoleViewModel.setManufacturer(console.getManufacturer());
//        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
//        consoleViewModel.setProcessor(console.getProcessor());
//        consoleViewModel.setPrice(console.getPrice());
//        consoleViewModel.setQuantity(console.getQuantity());
//
//        return consoleViewModel;
//    }
//
//    public GameViewModel buildGameViewModel(Game game) {
//        GameViewModel gameViewModel = new GameViewModel();
//        gameViewModel.setId(game.getId());
//        gameViewModel.setTitle(game.getTitle());
//        gameViewModel.setEsrbRating(game.getEsrbRating());
//        gameViewModel.setDescription(game.getDescription());
//        gameViewModel.setPrice(game.getPrice());
//        gameViewModel.setStudio(game.getStudio());
//        gameViewModel.setQuantity(game.getQuantity());
//
//        return gameViewModel;
//    }
//
//    public TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
//        TShirtViewModel tShirtViewModel = new TShirtViewModel();
//        tShirtViewModel.setId(tShirt.getId());
//        tShirtViewModel.setSize(tShirt.getSize());
//        tShirtViewModel.setColor(tShirt.getColor());
//        tShirtViewModel.setDescription(tShirt.getDescription());
//        tShirtViewModel.setPrice(tShirt.getPrice());
//        tShirtViewModel.setQuantity(tShirt.getQuantity());
//
//        return tShirtViewModel;
//    }
}
