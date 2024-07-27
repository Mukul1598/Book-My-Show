package Book_My_Show.Controllers.Repositories;


import Book_My_Show.Controllers.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
