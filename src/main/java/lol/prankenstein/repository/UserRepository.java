package lol.prankenstein.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import lol.prankenstein.Role;
import lol.prankenstein.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    User findById(Long id);
    
    User findByFirstName(String firstName);
    
    User findByLastName(String lastName);
    
    User findByUserName(String userName);
    
    User findByEmail(String email);
    
    User findByRole(Role role);
    
    List<User> findAll();
    
    User deleteById(Long id);
    
    public long count();

}
