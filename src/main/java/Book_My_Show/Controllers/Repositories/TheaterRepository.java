package Book_My_Show.Controllers.Repositories;

import Book_My_Show.Controllers.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
