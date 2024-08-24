package Book_My_Show.Repositories;

import Book_My_Show.Entities.Movie;
import Book_My_Show.Entities.Show;
import Book_My_Show.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Show,Integer > {

    public Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate,
                                                                LocalTime showTime,
                                                                Movie movie,
                                                                Theater theater);

}
