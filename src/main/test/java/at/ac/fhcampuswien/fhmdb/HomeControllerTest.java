package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void movie_list_is_sorted_ascending_if_button_is_pressed_when_it_displays_asc(){
        //  Given:
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // When:
        ObservableList<Movie> actual = controller.sortMoviesAscending(exampleMovies); //actual

        // Then:
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie1);
        expected.add(movie3);
        expected.add(movie2);
        //test
        assertEquals(expected, actual);
    }

    @Test
    void movie_list_is_sorted_descending_if_button_is_pressed_when_it_displays_desc(){
        //  Given:
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // When:
        ObservableList<Movie> actual = controller.sortMoviesDescending(exampleMovies); //actual

        // Then:
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie1);
        //test
        assertEquals(expected, actual);
    }

    //TODO: 2 tests that check if GenreFilter is applied properly (no filter - after filter was set & specific filter test)

    //TODO: test that checks if search query is applied properly

    //TODO: test that checks if the output is valid, if search query AND genre filter is set

}