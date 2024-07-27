package Book_My_Show.Controllers.Controllers;

import Book_My_Show.Controllers.Entities.Movie;
import Book_My_Show.Controllers.RequestDTO.UpdateMovieAttributes;
import Book_My_Show.Controllers.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateMovieAttributes")
    public String updateMovieAttribute(@RequestBody UpdateMovieAttributes movieAttributes){
        // Only relevant attributes are exposed to the client

        String result = movieService.updateMovieAttributes(movieAttributes);
        return result;
    }



}
