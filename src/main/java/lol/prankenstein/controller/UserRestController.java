package lol.prankenstein.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	User one(@PathVariable Long id) {
		return repository.findById(id);
	}

	@SuppressWarnings("null")
	@PostMapping("/users/search")
	List<User> searchUser(@RequestBody User searchUser) {

		List<User> results = null;

		if (searchUser.getFirstName() != null && searchUser.getFirstName().isEmpty()) {
			results.add(repository.findByFirstName(searchUser.getFirstName()));
		}

		if (searchUser.getLastName() != null && searchUser.getLastName().isEmpty()) {
			results.add(repository.findByLastName(searchUser.getLastName()));
		}

		if (searchUser.getUserName() != null && searchUser.getUserName().isEmpty()) {
			results.add(repository.findByUserName(searchUser.getUserName()));
		}

		if (searchUser.getEmail() != null && searchUser.getEmail().isEmpty()) {
			results.add(repository.findByEmail(searchUser.getEmail()));
		}

		if (searchUser.getRole() != null) {
			results.add(repository.findByRole(searchUser.getRole()));
		}

		if (results != null) {
			List<User> distinctResults = results.stream().distinct().collect(Collectors.toList());

			return distinctResults;
		}

		return results;
	}

//	@PutMapping("/Users/{id}")
//	User updateUser(@RequestBody User updateUser, @PathVariable Long id) {
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
	void removeUser(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
