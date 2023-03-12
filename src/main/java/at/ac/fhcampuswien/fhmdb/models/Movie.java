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
        movies.add(new Movie("Everything Everywhere All At Once", "A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.", List.of(Genre.ACTION, Genre.DRAMA, Genre.COMEDY)));
        movies.add(new Movie("Parasite", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", List.of(Genre.DRAMA)));
        movies.add(new Movie("The Conjuring: The Devil Made Me Do It", "The Warrens investigate a murder that may be linked to a demonic possession.", List.of(Genre.HORROR)));
        movies.add(new Movie("Black Panther: Wakanda Forever", "The people of Wakanda fight to protect their home from intervening world powers as they mourn the death of King T'Challa.", List.of(Genre.ACTION, Genre.DRAMA)));
        return movies;
    }
}
