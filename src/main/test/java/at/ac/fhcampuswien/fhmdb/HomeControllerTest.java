package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void movie_list_is_sorted_ascending_if_button_displays_desc(){
        // GIVEN
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (asc)"); //actual

        // THEN
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie1);
        expected.add(movie3);
        expected.add(movie2);
        //test
        assertEquals(expected, actual);
    }

    @Test
    void movie_list_is_sorted_descending_if_button_displays_asc(){
        // GIVEN
        HomeController controller = new HomeController();
        //create list that is going to be tested:
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        ObservableList<Movie> actual = controller.sortMovies(exampleMovies, "Sort (desc)"); //actual

        // THEN
        //create expected list:
        ObservableList<Movie> expected = FXCollections.observableArrayList();
        expected.add(movie2);
        expected.add(movie3);
        expected.add(movie1);
        //test
        assertEquals(expected, actual);
    }


    //won't work -> "because "this.genreComboBox" is null"
    /*
    @Test
    void if_resetBtn_is_pressed_the_original_state_of_movie_is_displayed(){
        // GIVEN
        //search query and movie list
        HomeController controller = new HomeController();
        ObservableList<Movie> exampleMovies = FXCollections.observableArrayList();

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.addAll(movie1, movie2, movie3);

        // WHEN
        controller.filterGenre(exampleMovies,"ACTION");
        controller.initState();

        // THEN
        assertEquals("c-title2", exampleMovies.get(1).getTitle());
    }
     */


    @Test
    void only_the_movies_that_match_the_genre_selected_get_displayed(){
        // GIVEN
        //search query and movie list
        HomeController controller = new HomeController();
        List <Movie> exampleMovies = new ArrayList<>();
        List<Movie> actualMovies;

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        actualMovies = controller.genreFilter(exampleMovies, "ROMANCE");


        // THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie3);

        assertEquals(expectedMovies, actualMovies);

    }

    @Test
    void only_the_movies_that_contain_searchQuery_in_title_or_description_are_displayed(){
        // GIVEN
        //search query and movie list
        HomeController controller = new HomeController();
        List <Movie> exampleMovies = new ArrayList<>();
        List<Movie> actualMovies;

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "dummy desc", List.of(Genre.ACTION));
        Movie movie3 = new Movie("b-title3", "dummy text", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        actualMovies = controller.searchQuery(exampleMovies, "dummy");


        // THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie2);
        expectedMovies.add(movie3);

        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void when_using_genreFilter_and_input_of_searchQuery_only_matching_movies_are_displayed(){
        // GIVEN
        HomeController controller = new HomeController();
        List <Movie> exampleMovies = new ArrayList<>();
        List<Movie> tempMovies;
        List<Movie> actualMovies;

        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "dummy desc", List.of(Genre.ACTION));
        Movie movie3 = new Movie("b-title3", "dummy text", List.of(Genre.ROMANCE));
        exampleMovies.add(movie1);
        exampleMovies.add(movie2);
        exampleMovies.add(movie3);

        // WHEN
        tempMovies = controller.searchQuery(exampleMovies, "dummy");
        actualMovies = controller.genreFilter(tempMovies, "ROMANCE");


        // THEN
        //List or ObservableList contain movies with the search query
        List<Movie> expectedMovies = new ArrayList<>();
        expectedMovies.add(movie3);

        assertEquals(expectedMovies, actualMovies);
    }



}