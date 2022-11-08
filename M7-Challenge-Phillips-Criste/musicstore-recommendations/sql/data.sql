use music_store_recommendations;

insert into label_recommendation(label_id, user_id, liked bool);
    values(1, 1, true);


insert into artist_recommendation(artist_id, user_id, liked bool);
    values(2, 1, true);


insert into album_recommendation(album_id, user_id, liked bool);
    values(3, 1, false);


insert into track_recommendation(track_id, user_id, liked bool);
    values(5, 1, true);

