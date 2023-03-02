package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void movies_sorted_asc_when_sortButtonTest_says_sortAsc(){
        //given
        HomeController controller = new HomeController();
        List<Movie> someMovies = new ArrayList<>();

        Movie movie1 = new Movie("Parasite", "Desc1", Collections.singletonList(Genre.DRAMA));
        Movie movie2 = new Movie("Black Panther: Wakanda Forever", "Desc2", Collections.singletonList(Genre.DRAMA));
        Movie movie3 = new Movie("Everything Everywhere All At Once", "Desc3", Collections.singletonList(Genre.DRAMA));

        someMovies.add(0, movie1);
        someMovies.add(1, movie2);
        someMovies.add(2, movie3);


        ObservableList<Movie> unsortedMovies = FXCollections.observableArrayList();
        unsortedMovies.addAll(someMovies);

        //when
        controller.sortMoviesAscending(unsortedMovies);
        ObservableList<Movie> actualMovies = unsortedMovies;

        //then
        ObservableList<Movie> expectedMovies = FXCollections.observableArrayList();

        expectedMovies.add(0, movie2);
        expectedMovies.add(1, movie3);
        expectedMovies.add(2, movie1);


        Assertions.assertEquals(expectedMovies, actualMovies);




    }
    @Test
    void movies_sorted_desc_when_sortButtonTest_says_sortDesc(){
        //given
        HomeController controller = new HomeController();
        List<Movie> someMovies = new ArrayList<>();

        Movie movie1 = new Movie("Parasite", "Desc1", Collections.singletonList(Genre.DRAMA));
        Movie movie2 = new Movie("Black Panther: Wakanda Forever", "Desc2", Collections.singletonList(Genre.DRAMA));
        Movie movie3 = new Movie("Everything Everywhere All At Once", "Desc3", Collections.singletonList(Genre.DRAMA));

        someMovies.add(0, movie1);
        someMovies.add(1, movie2);
        someMovies.add(2, movie3);


        ObservableList<Movie> unsortedMovies = FXCollections.observableArrayList();
        unsortedMovies.addAll(someMovies);

        //when
        controller.sortMoviesDescending(unsortedMovies);
        ObservableList<Movie> actualMovies = unsortedMovies;

        //then
        ObservableList<Movie> expectedMovies = FXCollections.observableArrayList();

        expectedMovies.add(0, movie1);
        expectedMovies.add(1, movie3);
        expectedMovies.add(2, movie2);




        Assertions.assertEquals(expectedMovies, actualMovies);

    }

}