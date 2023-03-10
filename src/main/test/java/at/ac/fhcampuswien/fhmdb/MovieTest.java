package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MovieTest {
    @Test
    public void movie_list_is_not_empty_if_movies_are_added(){
        //given - set up the data to our test case (set up the data)
        //Movie movie = new Movie("test title", "test description"); //don't need it

        //when - call the unit/method we are going to test (call the method)
        List<Movie> list =  Movie.initializeMovies(); //because it is static we don't need to access via instance reference

        //then - assertions to verify if the expected behaviour is correct or not (evaluate the condition)
        assertFalse(list.isEmpty()); //list should not be empty
    }
}