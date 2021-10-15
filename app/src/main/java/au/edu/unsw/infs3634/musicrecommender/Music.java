package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;

public class Music {
    private String name;
    private String artist;
    private String genre;
    private int rating;

    public Music(String name, String artist, String genre, int rating, String description) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public static ArrayList<Music> getMusic() {
        ArrayList<Music> music = new ArrayList<>();

        music.add(new Music("WITHOUT YOU", "The Kid LAROI", "Pop", 3, ""));
        music.add(new Music("Photograph", "Ed Sheeran", "Pop", 4, ""));
        music.add(new Music("Heather", "Conan Gray", "Pop", 5, ""));
        music.add(new Music("Trip", "Ella Mai", "R&B", 3, ""));
        music.add(new Music("Sativa", "Jhene Aiko", "R&B", 2, ""));
        music.add(new Music("Dear Name", "IU", "K-pop", 4, ""));
        music.add(new Music("Instagram", "DEAN", "Pop", 5, ""));
        music.add(new Music("Bye bye my blue", "Yerin Baek", "K-pop", 2, ""));
        music.add(new Music("Wonderland", "iri", "J-Pop", 2, ""));
        music.add(new Music("Lights", "warbear", "J-pop", 3, ""));

        return music;
    }

}
