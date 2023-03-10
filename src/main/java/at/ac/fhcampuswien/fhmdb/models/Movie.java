package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie{
    //  added properties here:
    private String title;
    private String description;
    public List<Genre> genre;

    public Movie(String title, String description, List<Genre> genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public List<Genre> getGenre() {
        return genre;
    }

    // dummy movies
    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar", "Blue guys and girls", List.of(Genre.ACTION))); //Array.asList(Genre.ACTION)
        movies.add(new Movie("Spiderman", "Man getting bit by a spider", List.of(Genre.ACTION)));
        movies.add(new Movie("Minions", "yellow creatures working for a villain", List.of(Genre.ANIMATION, Genre.COMEDY)));
        return movies;
    }
}
