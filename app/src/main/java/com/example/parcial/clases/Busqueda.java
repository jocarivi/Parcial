package com.example.parcial.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Busqueda {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public static class Results{

        @SerializedName("trackmatches")
        @Expose
        private Trackmatches trackmatches;

        public Trackmatches getTrackmatches() {
            return trackmatches;
        }

        public void setTrackmatches(Trackmatches trackmatches) {
            this.trackmatches = trackmatches;
        }

    }

    public static class Trackmatches{
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
        private String name;
        @SerializedName("artist")
        @Expose
        private String artist;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

    }

}