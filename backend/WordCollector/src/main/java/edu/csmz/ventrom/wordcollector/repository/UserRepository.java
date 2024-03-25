package edu.csmz.ventrom.wordcollector.repository;

import edu.csmz.ventrom.wordcollector.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsernameAndPassword(String username,String password);
}
