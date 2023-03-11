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
        // TODO add some dummy data here

        //instantiate genre lists per movie
        List<Genre> genres1 = new ArrayList<>();
        List<Genre> genres2 = new ArrayList<>();
        List<Genre> genres3 = new ArrayList<>();
        List<Genre> genres4 = new ArrayList<>();
        List<Genre> genres5 = new ArrayList<>();

        //add genres to genre list
        genres1.add(Genre.ACTION);
        genres1.add(Genre.DRAMA);
        genres1.add(Genre.COMEDY);

        genres2.add(Genre.DRAMA);

        genres3.add(Genre.HORROR);

        genres4.add(Genre.ACTION);
        genres4.add(Genre.DRAMA);

        genres5.add(Genre.ANIMATION);
        genres5.add(Genre.ADVENTURE);
        genres5.add(Genre.FANTASY);

        //dummy movie data, instantiate movies
        Movie movie1 = new Movie("Everything Everywhere All At Once", "A middle-aged Chinese immigrant is swept up into an insane adventure in which she alone can save existence by exploring other universes and connecting with the lives she could have led.", genres1);
        Movie movie2 = new Movie("Parasite", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", genres2);
        Movie movie3 = new Movie("The Conjuring: The Devil Made Me Do It", "The Warrens investigate a murder that may be linked to a demonic possession.", genres3);
        Movie movie4 = new Movie("Black Panther: Wakanda Forever", "The people of Wakanda fight to protect their home from intervening world powers as they mourn the death of King T'Challa.", genres4);
        Movie movie5 = new Movie("Pom Poko", "A community of magical shape-shifting raccoon dogs struggle to prevent their forest home from being destroyed by urban development.", genres5);

        //add movies to list
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        return movies;
    }
}
