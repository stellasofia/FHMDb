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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        //  added genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        // TODO: add the first field, called "NO FILTER" to undo filter
        genreComboBox.getItems().addAll(Genre.values());

        //TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

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


    //filter for search query
    public void filterQuery(ActionEvent actionEvent) {
        // store the filtered movies
        List<Movie> movieList = new ArrayList<>();
        // Get the search query entered by the user
        String searchQuery = searchField.getText().toLowerCase();
        // Check if the title or description of the movie contains the search query
        if (!searchQuery.isEmpty()) {
            // If the search query is not empty, iterate through the observableMovies list and remove any movies that do not contain the search query in their title or description
            for (Movie movieLoop : allMovies) {
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    movieList.add(movieLoop); // Remove the movie from the observableMovies list
                }
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
        else {
            // If the search query is empty, set the observableMovies list to allMovies (i.e. display all movies)
            observableMovies.setAll(allMovies);
        }
    }

    // TODO: add a method, that make it so that if you click on Filter that all the inputs(genre filter, search query are applied.
}