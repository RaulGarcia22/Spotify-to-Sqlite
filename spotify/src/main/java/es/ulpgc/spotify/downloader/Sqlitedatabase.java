package es.ulpgc.spotify.downloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Sqlitedatabase {

public Statement init() throws SQLException {
        String dbPath = "C:/Users/Raúl García Nuez/Desktop/spotify/spotify/src/main/java/es/ulpgc/spotify/downloader/database.db";
        Connection connection = connect(dbPath);
        Statement statement = connection.createStatement();
        createTable(statement);
        return statement;
        }

        private Connection connect(String dbPath) {
        Connection conn = null;
        try {
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return conn;
        }
private void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
        "name TEXT," +
        "id TEXT PRIMARY KEY," +
        "popularity NUMBER" +
        ");");
        statement.execute("CREATE TABLE IF NOT EXISTS album (" +
        "name TEXT," +
        "id TEXT PRIMARY KEY," +
        "release_date TEXT," +
        "total_tracks NUMBER" +
        ");");
        statement.execute("CREATE TABLE IF NOT EXISTS track (" +
        "name TEXT," +
        "id TEXT PRIMARY KEY," +
        "explicit BOOLEAN," +
        "duration_ms NUMBER," +
        "href TEXT" +
        ");");
}

public void insertArtists(Statement statement, List<Track.Artist> artistslist) throws SQLException {
        for (Track.Artist artist : artistslist) {
                statement.execute(Translator.InsertArtist(artist));
                }
        }

public void insertAlbums(Statement statement, List<Track.Album> albums) throws SQLException{
        for (Track.Album album : albums) {
                statement.execute(Translator.insertAlbum(album));
                }
        }

public void insertTracks(Statement statement, List<Track> tracks) throws SQLException{
        for (Track track : tracks) {
        statement.execute(Translator.insertTrack(track));
                }
        }
}