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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;
    @FXML
    public TextField searchField;
    @FXML
    public Button resetBtn;
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
        genreComboBox.getItems().add("NO_FILTER");
        genreComboBox.getItems().addAll(Genre.values());


        //SEARCH BUTTON - ACTION EVENT
        searchBtn.setOnAction(actionEvent -> {
            genreFilter(allMovies, genreComboBox.getValue() != null ? genreComboBox.getValue().toString() : "NO_FILTER");
            searchQuery(moviesByGenre, searchField.getText().toLowerCase());
            sortMovies(moviesByGenre,sortBtn.getText());
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

        //CLEAR BUTTON - ACTION EVENT
        resetBtn.setOnAction(actionEvent -> {
            genreComboBox.getSelectionModel().clearSelection();
            initState();
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

    public void initState(){
        genreComboBox.setPromptText("Filter by Genre");
        observableMovies.clear();
        observableMovies.setAll(allMovies);
    }

    public List<Movie> genreFilter(List<Movie> allMovies, String genreSelection) {
        //clear list before adding the movies or else same movie will be added multiple times
        moviesByGenre.clear();

        if (!genreSelection.equals("NO_FILTER")) {
            for (Movie movies : allMovies) {
                List<Genre> genres = movies.getGenre();

                if (genres.toString().contains(genreSelection)) {
                    moviesByGenre.add(movies);
                }
            }
            observableMovies.setAll(moviesByGenre);
        } else {
            moviesByGenre.setAll(allMovies);// if "no filter" is selected, show all movies, moviesByGenre will be passed on to filterQuery
        }
        return moviesByGenre;
    }


    public List<Movie> searchQuery(List<Movie> moviesByGenre, String searchQuery) {
        ObservableList<Movie> movieList = FXCollections.observableArrayList();

        movieList.clear();

        if(!searchQuery.contains(" ")) {      //!searchQuery.isEmpty() replaced --> search field is never empty because of white space
            for (Movie movieLoop : moviesByGenre) {     // loops the already filtered movie list (by genre) and adds the movies it finds there to the new movieList
                String title = movieLoop.getTitle().toLowerCase();
                String description = movieLoop.getDescription().toLowerCase();

                // Check if the title or description of the movie  contains the search query
                if (title.contains(searchQuery) || description.contains(searchQuery)) {
                    movieList.add(movieLoop);
                }
                // Set the observableMovies list to the filtered movieList
                observableMovies.setAll(movieList);
            }
        }
        return movieList;
    }
}

