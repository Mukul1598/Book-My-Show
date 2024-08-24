package Book_My_Show.Services;

import Book_My_Show.Entities.User;
import Book_My_Show.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user){
        user = userRepository.save(user);
        return "The user has been saved to the Database with userId : " + user.getUserId() ;
    }


}
