package Book_My_Show.Repositories;


import Book_My_Show.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findMovieByMovieName(String movieName); // Inbuilt query


//    @Query(value = "select * from movies where movie_name = :movieName" , nativeQuery = true)// Custom query
//    Movie findMovieByMovieName(String movieName);

}
