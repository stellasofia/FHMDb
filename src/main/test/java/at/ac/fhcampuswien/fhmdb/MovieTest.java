package at.ac.fhcampuswien.fhmdb;


import static org.junit.jupiter.api.Assertions.*;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MovieTest {

    @Test
    public void movie_list_is_not_empty_if_movies_are_added(){
        //given - set up the data to our test case (set up the data)
        //Movie movie = new Movie("test title", "test description"); //why do we need this thing?

        //when - call the unit/method we are going to test (call the method)
        List<Movie> list =  Movie.initializeMovies(); //because it is static we don't need to access via instance reference

        //then - assertions to verify if the expected behaviour is correct or not (evaluate the condition)
        assertFalse(list.isEmpty()); //list should not be empty
    }



        //when:
        //List<Movie> actual =
        //List<Movie> expected =



       //assertEquals(actual, expected );
       //boolean sorted = Ordering.natural().isOrdered(actual);


         /*
        //given
        Movie movie1 = new Movie("a-title1", "description1", List.of(Genre.ANIMATION));
        Movie movie2 = new Movie("c-title2", "description2", List.of(Genre.ROMANCE));
        Movie movie3 = new Movie("b-title3", "description3", List.of(Genre.ROMANCE));
        List<Movie> list =  Movie.initializeMovies();
        list.add(movie1);
        list.add(movie2);
        list.add(movie3);

        //when
        List<Movie> arrangedAscList = Movie.arrangeAsc();

        //then
        assertEquals(arrangedAscList,arrangedAscList);
    */
}
