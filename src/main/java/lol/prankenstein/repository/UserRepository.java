package lol.prankenstein.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import lol.prankenstein.Role;
import lol.prankenstein.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    User findById(Long id);
    
    List<User> findByFirstName(String firstName);
    
    List<User> findByLastName(String lastName);
    
    List<User> findByUserName(String userName);
    
    List<User> findByEmail(String email);
    
    List<User> findByRole(Role role);
    
    List<User> findAll();
    
    User deleteById(Long id);
    
    public long count();

}
