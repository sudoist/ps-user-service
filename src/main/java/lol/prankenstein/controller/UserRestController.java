package lol.prankenstein.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.FluentIterable;
import com.google.common.primitives.Ints;

import lol.prankenstein.Role;
import lol.prankenstein.model.User;
import lol.prankenstein.repository.UserRepository;

@RestController
public class UserRestController {

	private final UserRepository repository;
//	private User user;

//	public UserRestController(repository repository, User user) {
	public UserRestController(UserRepository repository) {
		this.repository = repository;
//		this.user = user;
	}

	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping("/users")
	User addUser(@RequestBody User addUser) {

		addUser.setRole(Role.USER);

		return repository.save(addUser);
	}

	@GetMapping("/users/{id}")
	User one(@PathVariable int id) {
		return repository.findById(id);
	}

	// TODO: Remove duplicates or in UI	
	@PostMapping("/users/search")
	List<User> searchUser(@RequestBody User searchUser) {

		List<User> results = new ArrayList<>();

		if (searchUser.getFirstName() != null) {
			results.addAll(repository.findByFirstName(searchUser.getFirstName()));
		}

		if (searchUser.getLastName() != null) {
			results.addAll(repository.findByLastName(searchUser.getLastName()));
		}
		System.out.println(searchUser.getUserName());
		System.out.println(searchUser.getUserName() != null);
		if (searchUser.getUserName() != null) {
			results.addAll(repository.findByUserName(searchUser.getUserName()));
		}

		if (searchUser.getEmail() != null) {
			results.addAll(repository.findByEmail(searchUser.getEmail()));
		}

		if (searchUser.getRole() != null) {
			System.out.println("role");
			results.addAll(repository.findByRole(searchUser.getRole()));
		}

		return results;
	}

//	@PutMapping("/Users/{id}")
//	User updateUser(@RequestBody User updateUser, @PathVariable int id) {
//
//		User user = this.repository.findById(id);
//
//		if (user != null) {
//
//		}
//
//		return repository.findById(id).map(User -> {
//			User.setName(newUser.getName());
//			User.setRole(newUser.getRole());
//			return repository.save(User);
//		}).orElseGet(() -> {
//			newUser.setId(id);
//			return repository.save(newUser);
//		});
//	}

	@DeleteMapping("/Users/{id}")
	void removeUser(@PathVariable int id) {
		repository.deleteById(id);
	}

}
