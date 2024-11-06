package com.game_list.game_list.api;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GameApiResponse {

    private List<Game> results;

    private int count;

    public List<Game> getResults() {
        return results;
    }

    public void setResults(List<Game> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class Game {

        private long id;
        private String name;
        
        @JsonProperty("background_image")
        private String backgroundImage;
        
        private String released;
        private double rating;

        // Getters and setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBackgroundImage() {
            return backgroundImage;
        }

        public void setBackgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        public String getReleased() {
            return released;
        }

        public void setReleased(String released) {
            this.released = released;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}
