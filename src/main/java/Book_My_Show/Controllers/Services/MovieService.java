package Book_My_Show.Controllers.Services;

import Book_My_Show.Controllers.Entities.Movie;
import Book_My_Show.Controllers.Repositories.MovieRepository;
import Book_My_Show.Controllers.RequestDTO.UpdateMovieAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie){

        movie = movieRepository.save(movie);

        return "The movie has been added to the Database with movieID : " + movie.getMovieId();
    }

    public String updateMovieAttributes(UpdateMovieAttributes movieAttributes){
       Movie movie = movieRepository.findById(movieAttributes.getMovieId()).get();

       double rating = movieAttributes.getMovieRating();
       double duration = movieAttributes.getMovieDuration();

       movie.setRating(rating);
       movie.setMovieDuration(duration);

       movieRepository.save(movie);

       return "Attributes are modified";

    }


}