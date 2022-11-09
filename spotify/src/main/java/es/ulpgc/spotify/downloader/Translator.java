package es.ulpgc.spotify.downloader;

public class Translator {
    private static final String INSERT_ARTIST =
            "INSERT INTO artists VALUES ('%s', '%s', '%d') ON CONFLICT(id) DO UPDATE SET name = ?, popularity = ? WHERE id = ?";
    public static String InsertArtist(Track.Artist artist) {
        String format = String.format(INSERT_ARTIST,
                artist.getName(),
                artist.getArtistId(),
                artist.getPopularity()
                );
        return format;
    }

    private static final String INSERT_ALBUM =
            "INSERT INTO album VALUES ('%s', '%s', '%s', '%d') ON CONFLICT(id) DO UPDATE SET name = ?, release_date = ?, total_tracks = ? WHERE id = ?";
    public static String insertAlbum(Track.Album album) {
        String name = album.getName();
        name = name.replaceAll("'", "");
        name = name.replaceAll("\"", "");
        String format = String.format(INSERT_ALBUM,
                name,
                album.getAlbumid(),
                album.getRelease_date(),
                album.getTotal_tracks());
        return format;
    }

    private static final String INSERT_TRACK =
            "INSERT INTO track VALUES ('%s', '%s', '%b', '%d', '%s') ON CONFLICT(id) DO UPDATE SET name = ?, href = ?, explicit = ?, duration_ms = ? WHERE id = ?";

    public static String insertTrack(Track track) {
        String name = track.getName();
        name = name.replaceAll("'", "");
        name = name.replaceAll("\"", "");
        String format = String.format(INSERT_TRACK,
                name,
                track.getHref(),
                track.isExplicit(),
                track.getDuration_ms(),
                track.getId());
        return format;
    }
}