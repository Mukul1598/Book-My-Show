package Book_My_Show.Repositories;

import Book_My_Show.Entities.Show;
import Book_My_Show.Entities.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat , Integer> {

//    public List<ShowSeat> findAllByShow(Show show); // Inbuilt method invoking

    @Query(value = "select * from show_seats where show_show_id = :showId" , nativeQuery = true) // Custom Method
    public List<ShowSeat> findShowSeats(Integer showId);




}
