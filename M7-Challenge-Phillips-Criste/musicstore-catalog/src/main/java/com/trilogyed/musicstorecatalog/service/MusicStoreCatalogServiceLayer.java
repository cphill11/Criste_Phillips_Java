package com.trilogyed.musicstorecatalog.service;

import com.trilogyed.musicstorecatalog.model.Album;
import com.trilogyed.musicstorecatalog.model.Label;
import com.trilogyed.musicstorecatalog.repository.AlbumRepository;
import com.trilogyed.musicstorecatalog.repository.ArtistRepository;
import com.trilogyed.musicstorecatalog.repository.LabelRepository;
import com.trilogyed.musicstorecatalog.repository.TrackRepository;
import com.trilogyed.musicstorecatalog.viewModel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    //Album service layer methods
//    public AlbumViewModel getAlbum(long id) {
//        Optional<Album> album = albumRepo.findById(id);
//        if (album == null)
//            return null;
//        else
//            return buildAlbumViewModel(album.get());
//    }
//
//    public AlbumViewModel createAlbum(AlbumViewModel albumViewModel) {
//
//        // Validate incoming Album Data in the view model using JSR303
////        if (album == null) throw new IllegalArgumentException("No Album is passed! Album object is null!");
//
//        Album album = new Album();
//        album.setTitle(albumViewModel.getTitle());
//        album.setArtistId(albumViewModel.getArtistId());
//        album.setReleaseDate(albumViewModel.getReleaseDate());
//        album.setLabelId(albumViewModel.getLabelId());
//        album.setListPrice(albumViewModel.getListPrice());
//
//        albumViewModel.setId(albumRepo.save(album).getId());
//        return albumViewModel;
//    }

//    public void updateGame(GameViewModel gameViewModel) {
//
//        //Validate incoming Game Data in the view model
//        if (gameViewModel == null)
//            throw new IllegalArgumentException("No Game data is passed! Game object is null!");
//
//        //ensure game exists; if not, throw exception
//        if (this.getGame(gameViewModel.getId()) == null)
//            throw new IllegalArgumentException("No such game to update.");
//
//        Game game = new Game();
//        game.setId(gameViewModel.getId());
//        game.setTitle(gameViewModel.getTitle());
//        game.setEsrbRating(gameViewModel.getEsrbRating());
//        game.setDescription(gameViewModel.getDescription());
//        game.setPrice(gameViewModel.getPrice());
//        game.setQuantity(gameViewModel.getQuantity());
//        game.setStudio(gameViewModel.getStudio());
//
//        gameRepo.save(game);
//    }
//
//    public void deleteGame(long id) {
//        gameRepo.deleteById(id);
//    }
//
//    public List<GameViewModel> getGameByEsrb(String esrb) {
//        List<Game> gameList = gameRepo.findAllByEsrbRating(esrb);
//        List<GameViewModel> gvmList = new ArrayList<>();
//        if (gameList == null)
//            return null;
//        else
//            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
//        return gvmList;
//    }
//
//    public List<GameViewModel> getGameByTitle(String title) {
//        List<Game> gameList = gameRepo.findAllByTitle(title);
//        List<GameViewModel> gvmList = new ArrayList<>();
//        List<GameViewModel> exceptionList = null;
//
//        if (gameList == null) {
//            return exceptionList;
//        } else {
//            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
//        }
//        return gvmList;
//    }
//
//    public List<GameViewModel> getGameByStudio(String studio) {
//        List<Game> gameList = gameRepo.findAllByStudio(studio);
//        List<GameViewModel> gvmList = new ArrayList<>();
//
//        if (gameList == null)
//            return null;
//        else
//            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
//        return gvmList;
//    }
//
//    public List<GameViewModel> getAllGames() {
//        List<Game> gameList = gameRepo.findAll();
//        List<GameViewModel> gvmList = new ArrayList<>();
//
//        if (gameList == null)
//            return null;
//        else
//            gameList.stream().forEach(g -> gvmList.add(buildGameViewModel(g)));
//        return gvmList;
//    }
//
//    //console service layer methods
//    public ConsoleViewModel createConsole(ConsoleViewModel consoleViewModel) {
//
//        // ViewModel data was validated using JSR 303 for incoming Console data
//        if (consoleViewModel == null) throw new IllegalArgumentException("No Console is passed! Game object is null!");
//
//        Console console = new Console();
//        console.setModel(consoleViewModel.getModel());
//        console.setManufacturer(consoleViewModel.getManufacturer());
//        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
//        console.setProcessor(consoleViewModel.getProcessor());
//        console.setPrice(consoleViewModel.getPrice());
//        console.setQuantity(consoleViewModel.getQuantity());
//
//        return buildConsoleViewModel(consoleRepo.save(console));
//    }
//
//    public ConsoleViewModel getConsoleById(long id) {
//        Optional<Console> console = consoleRepo.findById(id);
//        if (console == null)
//            return null;
//        else
//            return buildConsoleViewModel(console.get());
//    }
//
//    public void updateConsole(ConsoleViewModel consoleViewModel) {
//
//        //Validate incoming Console Data in the view model
//        if (consoleViewModel == null)
//            throw new IllegalArgumentException("No console data is passed! Console object is null!");
//
//        //ensure Console exists; if not, throw exception
//        if (this.getConsoleById(consoleViewModel.getId()) == null)
//            throw new IllegalArgumentException("No such console to update.");
//
//        Console console = new Console();
//        console.setId(consoleViewModel.getId());
//        console.setModel(consoleViewModel.getModel());
//        console.setManufacturer(consoleViewModel.getManufacturer());
//        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
//        console.setProcessor(consoleViewModel.getProcessor());
//        console.setPrice(consoleViewModel.getPrice());
//        console.setQuantity(consoleViewModel.getQuantity());
//
//        consoleRepo.save(console);
//    }
//
//    public void deleteConsole(long id) {
//        consoleRepo.deleteById(id);
//    }
//
//    public List<ConsoleViewModel> getConsoleByManufacturer(String manufacturer) {
//        List<Console> consoleList = consoleRepo.findAllByManufacturer(manufacturer);
//        List<ConsoleViewModel> cvmList = new ArrayList<>();
//
//        if (consoleList == null)
//            return null;
//        else
//            consoleList.stream().forEach(c -> cvmList.add(buildConsoleViewModel(c)));
//        return cvmList;
//    }
//
//    public List<ConsoleViewModel> getAllConsoles() {
//        List<Console> consoleList = consoleRepo.findAll();
//        List<ConsoleViewModel> cvmList = new ArrayList<>();
//
//        if (consoleList == null)
//            return null;
//        else
//            consoleList.stream().forEach(c -> cvmList.add(buildConsoleViewModel(c)));
//        return cvmList;
//    }
//
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
