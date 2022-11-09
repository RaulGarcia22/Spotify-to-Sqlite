package es.ulpgc.spotify.downloader;

public class Track {
    private final int duration_ms;
    private String name;
    private boolean explicit;
    private String id;
    private String href;


    public int getDuration_ms() {return duration_ms;
    }

    public String getName() {
        return name;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public String getId() {
        return id;
    }

    public String getHref() {
        return href;
    }

    public Track(String name, String href, Boolean explicit, int duration_ms, String id) {
        this.duration_ms = duration_ms;
        this.name = name;
        this.explicit = explicit;
        this.id = id;
        this.href = href;

    }
    public static class Album {
        private String name;
        private String albumid;
        private String release_date;
        private int total_tracks;

        public String getName() {
            return name;
        }

        public String getAlbumid() {
            return albumid;
        }

        public String getRelease_date() {
            return release_date;
        }

        public int getTotal_tracks() {
            return total_tracks;
        }

        public Album(String name, String albumid, String release_date, int total_tracks) {
            this.name = name;
            this.albumid = albumid;
            this.release_date = release_date;
            this.total_tracks = total_tracks;
        }
    }

    public static class Artist {
        private String name;
        private String artistId;
        private int popularity;


        public String getName() {
            return name;
        }

        public String getArtistId() {
            return artistId;
        }

        public int getPopularity() {return popularity;
        }

        public Artist(String name, String artistId, int popularity) {
            this.name = name;
            this.artistId = artistId;
            this.popularity = popularity;
        }
    }
}

