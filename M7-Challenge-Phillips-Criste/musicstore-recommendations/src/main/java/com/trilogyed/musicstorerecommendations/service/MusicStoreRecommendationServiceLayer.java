package com.trilogyed.musicstorerecommendations.service;

import com.trilogyed.musicstorerecommendations.model.AlbumRecommendation;
import com.trilogyed.musicstorerecommendations.model.ArtistRecommendation;
import com.trilogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import com.trilogyed.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.trilogyed.musicstorerecommendations.repository.ArtistRecommendationRepository;
import com.trilogyed.musicstorerecommendations.repository.LabelRecommendationRepository;
import com.trilogyed.musicstorerecommendations.repository.TrackRecommendationRepository;
import com.trilogyed.musicstorerecommendations.viewModel.AlbumRecommendationViewModel;
import com.trilogyed.musicstorerecommendations.viewModel.ArtistRecommendationViewModel;
import com.trilogyed.musicstorerecommendations.viewModel.LabelRecommendationViewModel;
import com.trilogyed.musicstorerecommendations.viewModel.TrackRecommendationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MusicStoreRecommendationServiceLayer {
    // as worked on in Challenge 5

    private final String ALBUM_RECOMMENDATION_ITEM_TYPE = "AlbumRecommendation";
    private final String ARTIST_RECOMMENDATION_ITEM_TYPE = "ArtistRecommendation";
    private final String LABEL_RECOMMENDATION_ITEM_TYPE = "LabelRecommendation";
    private final String TRACK_RECOMMENDATION_ITEM_TYPE = "TrackRecommendation";

    AlbumRecommendationRepository albumRecommendationRepo;
    ArtistRecommendationRepository artistRecommendationRepo;
    LabelRecommendationRepository labelRecommendationRepo;
    TrackRecommendationRepository trackRecommendationRepo;


    @Autowired
    public MusicStoreRecommendationServiceLayer(AlbumRecommendationRepository albumRecommendationRepo, ArtistRecommendationRepository artistRecommendationRepo, LabelRecommendationRepository labelRecommendationRepo,
                                                TrackRecommendationRepository trackRecommendationRepo) {
        this.albumRecommendationRepo = albumRecommendationRepo;
        this.artistRecommendationRepo = artistRecommendationRepo;
        this.labelRecommendationRepo = labelRecommendationRepo;
        this.trackRecommendationRepo = trackRecommendationRepo;
    }

    // Album Recommendation service layer methods
    public AlbumRecommendationViewModel getAlbumRecommendation(long id) {
        Optional<AlbumRecommendation> albumRecommendation = albumRecommendationRepo.findById(id);
        if (albumRecommendation == null) {
            return null;
        } else {
            return buildAlbumRecommendationViewModel(albumRecommendation.get());
        }
    }

    // create Album Recommendation
    public AlbumRecommendationViewModel createAlbumRecommendation(AlbumRecommendationViewModel albumRecommendationViewModel) {
        if (albumRecommendationViewModel == null)
            throw new IllegalArgumentException("No Album Recommendation is passed; Album Recommendation object is null.");

        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(albumRecommendationViewModel.getAlbumId());
        albumRecommendation.setUserId(albumRecommendationViewModel.getUserId());
        albumRecommendation.setLiked(albumRecommendationViewModel.getLiked());

        albumRecommendationViewModel.setId(albumRecommendationRepo.save(albumRecommendation).getId());
        return albumRecommendationViewModel;
    }

    // update existing Album Recommendation
    public void updateAlbumRecommendation(AlbumRecommendationViewModel albumRecommendationViewModel) {
        if (albumRecommendationViewModel == null)
            throw new IllegalArgumentException("No Album Recommendation data is passed; Album Recommendation object is null.");
        if (this.getAlbumRecommendation(albumRecommendationViewModel.getId()) == null)
            throw new IllegalArgumentException("No such album recommendation to update.");

        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setId(albumRecommendationViewModel.getId());
        albumRecommendation.setAlbumId(albumRecommendationViewModel.getAlbumId());
        albumRecommendation.setUserId(albumRecommendationViewModel.getUserId());
        albumRecommendation.setLiked(albumRecommendationViewModel.getLiked());

        albumRecommendationRepo.save(albumRecommendation);
    }

    // delete existing Album Recommendation
    public void deleteAlbumRecommendation(long id) {
        albumRecommendationRepo.deleteById(id);
    }

    // get All Album Recommendations
    public List<AlbumRecommendationViewModel> getAllAlbumRecommendations() {
        List<AlbumRecommendation> albumRecommendationList = albumRecommendationRepo.findAll();
        List<AlbumRecommendationViewModel> arvmList = new ArrayList<>();

        if (albumRecommendationList == null)
            return null;
        else
            albumRecommendationList.stream().forEach(arl -> arvmList.add(buildAlbumRecommendationViewModel(arl)));
        return arvmList;
    }

    // Artist Recommendation service layer methods
    public ArtistRecommendationViewModel getArtistRecommendationById(long id) {
        Optional<ArtistRecommendation> artistRecommendation = artistRecommendationRepo.findById(id);
        if (artistRecommendation == null)
            return null;
        else
            return buildArtistRecommendationViewModel(artistRecommendation.get());
    }

    // create Artist Recommendation
    public ArtistRecommendationViewModel createArtistRecommendation(ArtistRecommendationViewModel artistRecommendationViewModel) {
        if (artistRecommendationViewModel == null)
            throw new IllegalArgumentException("No Artist Recommendation is passed; Artist Recommendation object is null.");

        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(artistRecommendationViewModel.getArtistId());
        artistRecommendation.setUserId(artistRecommendationViewModel.getUserId());
        artistRecommendation.setLiked(artistRecommendationViewModel.getLiked());

        return buildArtistRecommendationViewModel(artistRecommendationRepo.save(artistRecommendation));
    }

    // update Artist Recommendation
    public void updateArtistRecommendation(ArtistRecommendationViewModel artistRecommendationViewModel) {
        if (artistRecommendationViewModel == null)
            throw new IllegalArgumentException("No Artist Recommendation data is passed; Artist Recommendation object is null.");
        if (this.getArtistRecommendationById(artistRecommendationViewModel.getId()) == null)
            throw new IllegalArgumentException("No such Artist Recommendation to update.");

        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setId(artistRecommendationViewModel.getId());
        artistRecommendation.setArtistId(artistRecommendationViewModel.getArtistId());
        artistRecommendation.setUserId(artistRecommendationViewModel.getUserId());
        artistRecommendation.setLiked(artistRecommendationViewModel.getLiked());

        artistRecommendationRepo.save(artistRecommendation);
    }

    // delete Artist Recommendation by ID
    public void deleteArtistRecommendation(long id) {
        artistRecommendationRepo.deleteById(id);
    }

    // get All Artist Recommendations
    public List<ArtistRecommendationViewModel> getAllArtistRecommendations() {
        List<ArtistRecommendation> artistRecommendationList = artistRecommendationRepo.findAll();
        List<ArtistRecommendationViewModel> arrvmList = new ArrayList<>();

        if (artistRecommendationList == null)
            return null;
        else
            artistRecommendationList.stream().forEach(arr -> arrvmList.add(buildArtistRecommendationViewModel(arr)));
        return arrvmList;
    }


    // Label Recommendation service layer methods
    public LabelRecommendationViewModel getLabelRecommendation(long id) {
        Optional<LabelRecommendation> labelRecommendation = labelRecommendationRepo.findById(id);
        if (labelRecommendation == null)
            return null;
        else
            return buildLabelRecommendationViewModel(labelRecommendation.get());
    }

    // create Label Recommendation
    public LabelRecommendationViewModel createLabelRecommendation(LabelRecommendationViewModel labelRecommendationViewModel) {
        if (labelRecommendationViewModel == null)
            throw new IllegalArgumentException("No Label Recommendation is passed; Label Recommendation object is null.");

        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(labelRecommendationViewModel.getLabelId());
        labelRecommendation.setUserId(labelRecommendationViewModel.getUserId());
        labelRecommendation.setLiked(labelRecommendationViewModel.getLiked());

        labelRecommendation = labelRecommendationRepo.save(labelRecommendation);

        return buildLabelRecommendationViewModel(labelRecommendation);
    }

    // update Label Recommendation
    public void updateLabelRecommendation(LabelRecommendationViewModel labelRecommendationViewModel) {
        if (labelRecommendationViewModel == null)
            throw new IllegalArgumentException("No Label Recommendation is passed; Label Recommendation object is null.");

        if (this.getLabelRecommendation(labelRecommendationViewModel.getId()) == null)
            throw new IllegalArgumentException("No such label recommendation to update.");

        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setId(labelRecommendationViewModel.getId());
        labelRecommendation.setLabelId(labelRecommendationViewModel.getLabelId());
        labelRecommendation.setUserId(labelRecommendationViewModel.getUserId());
        labelRecommendation.setLiked(labelRecommendationViewModel.getLiked());

        labelRecommendationRepo.save(labelRecommendation);
    }

    // delete Label Recommendation By ID
    public void deleteLabelRecommendation(long id) {
        labelRecommendationRepo.deleteById(id);
    }

    // get all Label Recommendations
    public List<LabelRecommendationViewModel> getAllLabelRecommendations() {
        List<LabelRecommendation> labelRecommendationList = labelRecommendationRepo.findAll();
        List<LabelRecommendationViewModel> lrvmList = new ArrayList<>();

        if (labelRecommendationList == null)
            return null;
        else
            labelRecommendationList.stream().forEach(lr -> lrvmList.add(buildLabelRecommendationViewModel(lr)));
        return lrvmList;
    }


    // Track Recommendation Service Layer methods
    public TrackRecommendationViewModel getTrackRecommendation(long id) {
        Optional<TrackRecommendation> trackRecommendation = trackRecommendationRepo.findById(id);
        if (trackRecommendation == null)
            return null;
        else
            return buildTrackRecommendationViewModel(trackRecommendation.get());
    }

    // create Track Recommendation
    public TrackRecommendationViewModel createTrackRecommendation(TrackRecommendationViewModel trackRecommendationViewModel) {
        if (trackRecommendationViewModel == null)
            throw new IllegalArgumentException("No Track Recommendation is passed; Track Recommendation object is null.");

        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(trackRecommendationViewModel.getTrackId());
        trackRecommendation.setAlbumId(trackRecommendationViewModel.getAlbumId());
        trackRecommendation.setLiked(trackRecommendationViewModel.getLiked());

        trackRecommendationViewModel.setId(trackRecommendationRepo.save(trackRecommendation).getId());
        return trackRecommendationViewModel;
    }

    // update Track Recommendation
    public void updateTrackRecommendation(TrackRecommendationViewModel trackRecommendationViewModel) {
        if (trackRecommendationViewModel == null)
            throw new IllegalArgumentException("No Track Recommendation is passed; Track Recommendation object is null.");

        if (this.getTrackRecommendation(trackRecommendationViewModel.getId()) == null)
            throw new IllegalArgumentException("No such track recommendation to update.");

        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setId(trackRecommendationViewModel.getId());
        trackRecommendation.setTrackId(trackRecommendationViewModel.getTrackId());
        trackRecommendation.setAlbumId(trackRecommendationViewModel.getAlbumId());
        trackRecommendation.setLiked(trackRecommendationViewModel.getLiked());

        trackRecommendationRepo.save(trackRecommendation);
    }

    // delete Track Recommendation
    public void deleteTrackRecommendation(long id) {
        trackRecommendationRepo.deleteById(id);
    }

    // get all Track Recommendations
    public List<TrackRecommendationViewModel> getAllTrackRecommendations() {
        List<TrackRecommendation> trackRecommendationList = trackRecommendationRepo.findAll();
        List<TrackRecommendationViewModel> trvmList = new ArrayList<>();

        if (trackRecommendationList == null)
            return null;
        else
            trackRecommendationList.stream().forEach(t -> trvmList.add(buildTrackRecommendationViewModel(t)));
        return trvmList;
    }


    // Helper Album Recommendation Method
    public AlbumRecommendationViewModel buildAlbumRecommendationViewModel(AlbumRecommendation albumRecommendation) {
        AlbumRecommendationViewModel albumRecommendationViewModel = new AlbumRecommendationViewModel();
        albumRecommendation.setId(albumRecommendationViewModel.getId());
        albumRecommendation.setAlbumId(albumRecommendationViewModel.getAlbumId());
        albumRecommendation.setUserId(albumRecommendationViewModel.getUserId());
        albumRecommendation.setLiked(albumRecommendationViewModel.getLiked());

        return albumRecommendationViewModel;
    }

    // Helper Artist Recommendation Method
    public ArtistRecommendationViewModel buildArtistRecommendationViewModel(ArtistRecommendation artistRecommendation) {
        ArtistRecommendationViewModel artistRecommendationViewModel = new ArtistRecommendationViewModel();
        artistRecommendation.setId(artistRecommendationViewModel.getId());
        artistRecommendation.setArtistId(artistRecommendationViewModel.getArtistId());
        artistRecommendation.setUserId(artistRecommendationViewModel.getUserId());
        artistRecommendation.setLiked(artistRecommendationViewModel.getLiked());

        return artistRecommendationViewModel;
    }

    // Helper Label Recommendation Method
    public LabelRecommendationViewModel buildLabelRecommendationViewModel(LabelRecommendation labelRecommendation) {
        LabelRecommendationViewModel labelRecommendationViewModel = new LabelRecommendationViewModel();
        labelRecommendation.setId(labelRecommendationViewModel.getId());
        labelRecommendation.setLabelId(labelRecommendationViewModel.getLabelId());
        labelRecommendation.setUserId(labelRecommendationViewModel.getUserId());
        labelRecommendation.setLiked(labelRecommendationViewModel.getLiked());

        return labelRecommendationViewModel;
    }

    // Helper Track Recommendation Method
    public TrackRecommendationViewModel buildTrackRecommendationViewModel(TrackRecommendation trackRecommendation) {
        TrackRecommendationViewModel trackRecommendationViewModel = new TrackRecommendationViewModel();
        trackRecommendation.setId(trackRecommendationViewModel.getId());
        trackRecommendation.setTrackId(trackRecommendationViewModel.getTrackId());
        trackRecommendation.setAlbumId(trackRecommendationViewModel.getAlbumId());
        trackRecommendation.setLiked(trackRecommendationViewModel.getLiked());

        return trackRecommendationViewModel;
    }
}
