package Book_My_Show.Services;

import Book_My_Show.Entities.Movie;
import Book_My_Show.Repositories.MovieRepository;
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


}
