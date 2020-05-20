package com.example.parcial.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Canciones {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public static class Tracks{
        @SerializedName("track")
        @Expose
        private List<Track> track = null;

        public List<Track> getTrack() {
            return track;
        }

        public void setTrack(List<Track> track) {
            this.track = track;
        }
    }

    public static class Track{
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("duration")
        @Expose
        public String duration;
        @SerializedName("artist")
        @Expose
        public Artist artist;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

    }

    public static class Artist{

        @SerializedName("name")
        @Expose
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}