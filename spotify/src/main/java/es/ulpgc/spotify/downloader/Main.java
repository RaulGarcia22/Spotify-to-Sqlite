package es.ulpgc.spotify.downloader;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    private static final Map<String, String> artists = Map.of(
            "1EXjXQpDx2pROygh8zvHs4", "Melendi",
            "1ackd5XprZEkH3McKbQD51", "Juan Magán",
            "3vQ0GE3mI0dAaxIMYe5g7z", "Paulo Londra",
            "7dGJo4pcD2V6oG8kP0tJRR", "Eminen",
            "1Ts9of7VPZElwPQnqnDSfW", "Lyanno");


    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        List<Track.Artist> artistslist = new ArrayList<>();
        List<Track.Album> albums = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        for (String artistId : artists.keySet()) {
            artistslist.add(controller.GetArtist(artistId));
            albums.addAll(controller.GetAlbums(artistId));
            for (Track.Album album : albums) {
                tracks.addAll(controller.GetTracks(album.getAlbumid()));
            }
        }
        Sqlitedatabase connects =  new Sqlitedatabase();
        Statement statement;
        statement = connects.init();
        connects.insertArtists(statement, artistslist);
        connects.insertAlbums(statement, albums);
        connects.insertTracks(statement, tracks);
    }
}
