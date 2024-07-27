package Book_My_Show.Controllers.Repositories;

import Book_My_Show.Controllers.Entities.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {
}
