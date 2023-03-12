package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (asc)"); //actual

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
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (desc)"); //actual

        // Then:
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie1);
        //test
        assertEquals(expected, actual);
    }

    @Test
    void movies_shown_when_genre_contains_genreString (){

        //GIVEN
        //search query and movie list
        HomeController controller = new HomeController();
        List <Movie> Movies = new ArrayList<>();
        List<Movie> actualMovies;

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        Movies.add(movie1);
        Movies.add(movie2);
        Movies.add(movie3);

        //WHEN
        actualMovies = controller.filterGenre(Movies, "ROMANCE");


        //THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie3);

        assertEquals(expectedMovies, actualMovies);

    }

    @Test
    void movies_shown_when_title_or_description_contain_searchQuery (){

        //GIVEN
        //search query and movie list
        HomeController controller = new HomeController();
        List <Movie> movies = new ArrayList<>();
        List<Movie> actualMovies;

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "dummy desc", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "dummy text", List.of(Genre.ROMANCE));
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        //WHEN
        actualMovies = controller.filterQuery(movies, "dummy");


        //THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie3);

        assertEquals(expectedMovies, actualMovies);

    }

}