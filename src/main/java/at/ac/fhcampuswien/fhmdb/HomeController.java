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
        genreComboBox.getItems().add("no filter");
        genreComboBox.getItems().addAll(Genre.values());


        //SEARCH BUTTON - ACTION EVENT
        searchBtn.setOnAction(actionEvent -> {
            filterGenre(allMovies, genreComboBox.getValue() != null ? genreComboBox.getValue().toString() : "no filter");      //if comboBox null -> turn into "no filter"
            filterQuery(moviesByGenre, searchField.getText().toLowerCase());

        });

        //SORT BUTTON - ACTION EVENT
        sortBtn.setOnAction(actionEvent -> {
            sortMovies(observableMovies, sortBtn.getText());
            if (Objects.equals(sortBtn.getText(), "Sort (asc)")) {
                sortBtn.setText("Sort (desc)");
            } else {
                sortBtn.setText("Sort (asc)");
            }

        });
}

    public ObservableList<Movie> sortMovies(ObservableList<Movie> observableMovies, String sortBtnText) {

        if (Objects.equals(sortBtnText, "Sort (asc)")) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));   //sort observableMovies ascending
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());  // sort observableMovies descending
            //change display on button
        }

        return observableMovies;
    }

    public List<Movie> filterGenre(List<Movie> allMovies, String genreSelection){
        //clear list before adding the movies or else same movie will be added multiple times
        moviesByGenre.clear();

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
            moviesByGenre.setAll(observableMovies);
            // if "no filter" is selected, show all movies, moviesByGenre will be passed on to filterQuery
        }
        return moviesByGenre;
    }

    //this method takes the movies filtered by filterGenre and performs the search on those selected movies
    public List<Movie> filterQuery(List<Movie> moviesByGenre, String searchQuery) {
        // store the filtered movies
        ObservableList<Movie> movieList = FXCollections.observableArrayList();

        movieList.clear();

        if(!searchQuery.contains(" ")) {      //!searchQuery.isEmpty() replaced --> search field is never empty because of white space
            for (Movie movieLoop : moviesByGenre) {     // loops the already filtered movie list (by genre) and adds the movies it finds there to the new movieList
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie  contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    movieList.add(movieLoop); // Remove the movie from the observableMovies list
                }
                // Set the observableMovies list to the filtered movieList
                observableMovies.setAll(movieList);
            }
        } //no need for else {show all movies}, since filterGenre takes care of that if "no filter" is selected
        return movieList;
    }
}
