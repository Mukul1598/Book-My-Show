package Book_My_Show.Controllers;

import Book_My_Show.Entities.Movie;
import Book_My_Show.Repositories.MovieRepository;
import Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie){
    String result = movieService.addMovie(movie);
    return result;
    }

}
