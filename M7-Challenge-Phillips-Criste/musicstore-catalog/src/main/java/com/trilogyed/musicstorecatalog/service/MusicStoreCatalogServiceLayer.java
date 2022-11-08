package com.trilogyed.musicstorecatalog.service;

import com.trilogyed.musicstorecatalog.model.Album;
import com.trilogyed.musicstorecatalog.model.Artist;
import com.trilogyed.musicstorecatalog.model.Label;
import com.trilogyed.musicstorecatalog.model.Track;
import com.trilogyed.musicstorecatalog.repository.AlbumRepository;
import com.trilogyed.musicstorecatalog.repository.ArtistRepository;
import com.trilogyed.musicstorecatalog.repository.LabelRepository;
import com.trilogyed.musicstorecatalog.repository.TrackRepository;
import com.trilogyed.musicstorecatalog.viewModel.AlbumViewModel;
import com.trilogyed.musicstorecatalog.viewModel.ArtistViewModel;
import com.trilogyed.musicstorecatalog.viewModel.LabelViewModel;
import com.trilogyed.musicstorecatalog.viewModel.TrackViewModel;
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
    public MusicStoreCatalogServiceLayer(AlbumRepository albumRepo, ArtistRepository artistRepo, LabelRepository labelRepo, TrackRepository trackRepo) {
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.labelRepo = labelRepo;
        this.trackRepo = trackRepo;
    }
    // Album service layer methods
    public AlbumViewModel getAlbum(long id) {
        Optional<Album> album = albumRepo.findById(id);
        if (album == null) {
            return null;
        } else {
            return buildAlbumViewModel(album.get());
      }
    }

    // create Album
    public AlbumViewModel createAlbum(AlbumViewModel albumViewModel) {

        // Validate incoming Album Data in the view model using JSR303
        if (albumViewModel == null) throw new IllegalArgumentException("No Album is passed; Album object is null.");

        Album album = new Album();
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId(albumViewModel.getArtistId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setLabelId(albumViewModel.getLabelId());
        album.setListPrice(albumViewModel.getListPrice());

        albumViewModel.setId(albumRepo.save(album).getId());
        return albumViewModel;
    }

    // update existing Album
    public void updateAlbum(AlbumViewModel albumViewModel) {
        if (albumViewModel == null)
            throw new IllegalArgumentException("No Album data is passed; Album object is null.");
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

    // delete existing Album
    public void deleteAlbum(long id) {
        albumRepo.deleteById(id);
    }

    // get All Albums
    public List<AlbumViewModel> getAllAlbums() {
        List<Album> albumList = albumRepo.findAll();
        List<AlbumViewModel> avmList = new ArrayList<>();

        if (albumList == null)
            return null;
        else
            albumList.stream().forEach(al -> avmList.add(buildAlbumViewModel(al)));
        return avmList;
    }

    // Artist service layer methods

    public ArtistViewModel getArtistById(long id) {
        Optional<Artist> artist = artistRepo.findById(id);
        if (artist == null)
            return null;
        else
            return buildArtistViewModel(artist.get());
    }

    // create Artist
    public ArtistViewModel createArtist(ArtistViewModel artistViewModel) {
        if (artistViewModel == null) throw new IllegalArgumentException("No Artist is passed; Artist object is null.");

        Artist artist = new Artist();
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        return buildArtistViewModel(artistRepo.save(artist));
    }

    // update Artist
    public void updateArtist(ArtistViewModel artistViewModel) {
        if (artistViewModel == null)
            throw new IllegalArgumentException("No Artist data is passed; Artist object is null.");
        if (this.getArtistById(artistViewModel.getId()) == null)
            throw new IllegalArgumentException("No such Artist to update.");

        Artist artist = new Artist();
        artist.setId(artistViewModel.getId());
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        artistRepo.save(artist);
    }

    // delete Artist by ID
    public void deleteArtist(long id) {
        artistRepo.deleteById(id);
    }

    // get All Artists
    public List<ArtistViewModel> getAllArtists() {
        List<Artist> artistList = artistRepo.findAll();
        List<ArtistViewModel> arvmList = new ArrayList<>();

        if (artistList == null)
            return null;
        else
            artistList.stream().forEach(ar -> arvmList.add(buildArtistViewModel(ar)));
        return arvmList;
    }



    // Label service layer methods
    public LabelViewModel getLabel(long id) {
        Optional<Label> label = labelRepo.findById(id);
        if (label == null)
            return null;
        else
            return buildLabelViewModel(label.get());
    }

    // create Label
    public LabelViewModel createLabel(LabelViewModel labelViewModel) {
        if (labelViewModel == null) throw new IllegalArgumentException("No Label is passed; Label object is null.");

        Label label = new Label();
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());

        label = labelRepo.save(label);

        return buildLabelViewModel(label);
    }

    // update Label
    public void updateLabel(LabelViewModel labelViewModel) {
        if (labelViewModel == null) throw new IllegalArgumentException("No Label is passed; Label object is null.");

        if (this.getLabel(labelViewModel.getId()) == null)
            throw new IllegalArgumentException("No such label to update.");

        Label label = new Label();
        label.setId(labelViewModel.getId());
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());

        labelRepo.save(label);
    }
    // delete Label By ID
    public void deleteLabel(long id) {
       labelRepo.deleteById(id);
    }

    // get all Labels
    public List<LabelViewModel> getAllLabels() {
        List<Label> labelList = labelRepo.findAll();
        List<LabelViewModel> lvmList = new ArrayList<>();

        if (labelList == null)
            return null;
        else
            labelList.stream().forEach(l -> lvmList.add(buildLabelViewModel(l)));
        return lvmList;
    }


    // Track Service Layer methods
    public TrackViewModel getTrack(long id) {
        Optional<Track> track = trackRepo.findById(id);
        if (track == null)
            return null;
        else
            return buildTrackViewModel(track.get());
    }

    // create Track
    public TrackViewModel createTrack(TrackViewModel trackViewModel) {
        if (trackViewModel == null) throw new IllegalArgumentException("No Track is passed; Track object is null.");

        Track track = new Track();
        track.setAlbumId(trackViewModel.getAlbumId());
        track.setTitle(trackViewModel.getTitle());
        track.setRunTime(trackViewModel.getRunTime());

        trackViewModel.setId(trackRepo.save(track).getId());
        return trackViewModel;
    }

    // update Track
    public void updateTrack(TrackViewModel trackViewModel) {
        if (trackViewModel == null)
            throw new IllegalArgumentException("No Track is passed; Track object is null.");

        if (this.getTrack(trackViewModel.getId()) == null)
            throw new IllegalArgumentException("No such track to update.");

        Track track = new Track();
       track.setId(trackViewModel.getId());
        track.setAlbumId(trackViewModel.getAlbumId());
        track.setTitle(trackViewModel.getTitle());
        track.setRunTime(trackViewModel.getRunTime());

        trackRepo.save(track);
    }

    // delete Track
    public void deleteTrack(long id) {
        trackRepo.deleteById(id);
    }

   // get all Tracks
    public List<TrackViewModel> getAllTracks() {
        List<Track> trackList = trackRepo.findAll();
        List<TrackViewModel> tvmList = new ArrayList<>();

        if (trackList == null)
            return null;
        else
            trackList.stream().forEach(t -> tvmList.add(buildTrackViewModel(t)));
        return tvmList;
    }


    // Helper Album Method
    public AlbumViewModel buildAlbumViewModel(Album album) {
        AlbumViewModel albumViewModel = new AlbumViewModel();
        albumViewModel.setId(album.getId());
        albumViewModel.setTitle(album.getTitle());
        albumViewModel.setArtistId(album.getArtistId());
        albumViewModel.setReleaseDate(album.getReleaseDate());
        albumViewModel.setLabelId(album.getLabelId());
        albumViewModel.setListPrice(album.getListPrice());

        return albumViewModel;
    }

    // Helper Artist Method
    public ArtistViewModel buildArtistViewModel(Artist artist) {
        ArtistViewModel artistViewModel = new ArtistViewModel();
        artistViewModel.setId(artist.getId());
        artistViewModel.setName(artist.getName());
        artistViewModel.setInstagram(artist.getInstagram());
        artistViewModel.setTwitter(artist.getTwitter());

        return artistViewModel;
    }

    // Helper Label Method
    public LabelViewModel buildLabelViewModel(Label label) {
        LabelViewModel labelViewModel = new LabelViewModel();
        labelViewModel.setId(label.getId());
        labelViewModel.setName(label.getName());
        labelViewModel.setWebsite(label.getWebsite());

        return labelViewModel;
    }

    // Helper Track Method
    public TrackViewModel buildTrackViewModel(Track track) {
        TrackViewModel trackViewModel = new TrackViewModel();
        trackViewModel.setId(track.getId());
        trackViewModel.setAlbumId(track.getAlbumId());
        trackViewModel.setTitle(track.getTitle());
        trackViewModel.setRunTime(track.getRunTime());

        return trackViewModel;
    }
}
