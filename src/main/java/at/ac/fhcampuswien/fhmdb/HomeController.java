package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;
    @FXML
    public TextField searchField;
    @FXML
    public JFXListView movieListView;
    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXButton sortBtn;
    public List<Movie> allMovies = Movie.initializeMovies();
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList<Movie> moviesByGenre = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //  added genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        // TODO: add the first field, called "NO FILTER" to undo filter
        genreComboBox.getItems().add("no filter");
        genreComboBox.getItems().addAll(Genre.values());

        //TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(actionEvent -> {
            filterGenre();
            filterQuery(moviesByGenre);

        });


        // sort button:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortMoviesAscending(observableMovies);   //sort observableMovies ascending
                sortBtn.setText("Sort (desc)");          // change display on button
            } else {
                sortMoviesDescending(observableMovies);  // sort observableMovies descending
                sortBtn.setText("Sort (asc)");           //change display on button
            }
        });


}

    // methods to sort ascending/descending
    public ObservableList<Movie> sortMoviesAscending(ObservableList<Movie> observableMovies) {
        observableMovies.sort(Comparator.comparing(Movie::getTitle));
        return observableMovies;
    }
    public ObservableList<Movie> sortMoviesDescending(ObservableList<Movie> observableMovies) {
        observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        return observableMovies;
    }

    public void filterGenre(){
        //clear list before adding the movies or else same movie will be added multiple times
        observableMovies.clear();
        moviesByGenre.clear();

        //get the genre from combo box as string
        String genreSelection = genreComboBox.getValue().toString();

        //if the genre is anything but "no filter", filter the movies with the selected genre
        if(!genreSelection.equals("no filter")) {
            for (Movie movies : allMovies) {
                List<Genre> genres = movies.getGenre();

                if (genres.toString().contains(genreSelection)) {
                    moviesByGenre.add(movies);
                }
            }
            observableMovies.setAll(moviesByGenre);
        } else {
            moviesByGenre.setAll(allMovies);            // if "no filter" is selected, show all movies, moviesByGenre will be passed on to filterQuery
        }



    }

    //this method takes the movies filtered by filterGenre and performs the search on those selected movies
    public void filterQuery(ObservableList<Movie> moviesByGenre) {


        // store the filtered movies
        ObservableList<Movie> movieList = FXCollections.observableArrayList();

        movieList.clear();

        // Get the search query entered by the user
        String searchQuery = searchField.getText().toLowerCase();
        // Check if the title or description of the movie contains the search query

        /*modified if condition --> if you just search multiple white spaces every movie will still pop up --> actually not that necessary
                         reason --> searching multiple white spaces results in no movies
        */
        if(!searchField.getText().contains(" ")) {      //!searchQuery.isEmpty() replaced --> search field is never empty because of white space
            for (Movie movieLoop : moviesByGenre) {
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie  contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    movieList.add(movieLoop); // Remove the movie from the observableMovies list
                }


                // Set the observableMovies list to the filtered movieList
                observableMovies.setAll(movieList);

                // Check if the sort button text contains "desc"
                if (sortBtn.getText().contains("desc")) {
                    // If the sort button text contains "desc", sort the observableMovies list in ascending order
                    sortMoviesAscending(observableMovies);
                } else {
                    // If the sort button text does not contain "desc", sort the observableMovies list in descending order
                    sortMoviesDescending(observableMovies);
                }
            }
        } //no need for else{show all movies}, since filterGenre takes care of that if "no filter" is selected

    }

    // TODO: add a method, that make it so that if you click on Filter that all the inputs(genre filter, search query are applied.


    }
