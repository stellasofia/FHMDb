package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

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
        genreComboBox.getItems().addAll(
                Genre.ACTION,Genre.ANIMATION,Genre.ADVENTURE,Genre.BIOGRAPHY,Genre.COMEDY, Genre.CRIME,
                Genre.DOCUMENTARY, Genre.DRAMA, Genre.DOCUMENTARY, Genre.FAMILY, Genre.FANTASY, Genre.HISTORY,
                Genre.HORROR, Genre.MUSICAL, Genre.MYSTERY, Genre.ROMANCE, Genre.SCIENCE_FICTION, Genre.SPORT,
                Genre.THRILLER, Genre.WAR, Genre.WESTERN);

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

    // TODO: add method, that uses the input of the search field (-> target: title OR description, set all ".toLowerCase()")
    // TODO: add a method, that make it so that if you click on Filter that all the inputs(genre filter, search query are applied.
}