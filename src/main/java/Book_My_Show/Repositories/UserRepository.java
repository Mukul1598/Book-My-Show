package Book_My_Show.Repositories;

import Book_My_Show.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

    public User findUserByMobileNo(String mobileNo);
}
