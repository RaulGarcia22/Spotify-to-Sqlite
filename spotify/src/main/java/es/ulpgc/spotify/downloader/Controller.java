package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Controller {
    public Track.Artist GetArtist(String artistId) throws Exception{
        SpotifyAccessor accessor = new SpotifyAccessor();
        String json = accessor.get("/artists/" + artistId, Map.of());
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(json, JsonObject.class);
        String id = object.getAsJsonObject().get("id").getAsString();
        String name = object.getAsJsonObject().get("name").getAsString();
        int popularity = object.getAsJsonObject().get("popularity").getAsInt();
        return new Track.Artist(name, id, popularity);
    }
    public List<Track.Album> GetAlbums(String artistId) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        String response = accessor.get("/artists/" + artistId + "/albums", Map.of());
        JsonObject object = new Gson().fromJson(response, JsonObject.class);
        JsonArray items = object.getAsJsonArray("items");
        Track.Album album;
        List<Track.Album> albums = new ArrayList<>();
        for (JsonElement item : items) {
            String name = item.getAsJsonObject().get("name").getAsString();
            String albumid = item.getAsJsonObject().get("id").getAsString();
            String release_date = item.getAsJsonObject().get("release_date").getAsString();
            int total_tracks = item.getAsJsonObject().get("total_tracks").getAsInt();
            album = new Track.Album(name, albumid, release_date, total_tracks);
            albums.add(album);
        }
        return albums;
    }

    public List<Track> GetTracks(String albumId) throws Exception {
        List<Track> tracks = new ArrayList<>();
        SpotifyAccessor accessor = new SpotifyAccessor();
        String json = accessor.get("/albums/" + albumId + "/tracks", Map.of());
        JsonObject object = new Gson().fromJson(json, JsonObject.class);
        JsonArray items = object.getAsJsonArray("items");
        for (JsonElement item : items) {
            String name = item.getAsJsonObject().get("name").getAsString();
            String id = item.getAsJsonObject().get("id").getAsString();
            Boolean explicit = item.getAsJsonObject().get("explicit").getAsBoolean();
            int duration_ms = item.getAsJsonObject().get("duration_ms").getAsInt();
            String href = item.getAsJsonObject().get("href").getAsString();
            Track track = new Track(name, id, explicit, duration_ms, href);
            tracks.add(track);
        }
        return tracks;
    }
}