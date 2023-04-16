package lol.prankenstein.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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

	public UserRestController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}

	// TODO: Add also works as update. Create update route
	@PostMapping("/users")
	User addUser(@RequestBody User addUser) {

		addUser.setRole(Role.USER);

		// Add password hash
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(salt);

		byte[] hashedPassword = md.digest(addUser.getPassword().getBytes(StandardCharsets.UTF_8));

		addUser.setPassword(hashedPassword.toString());

		return repository.save(addUser);
	}

	@GetMapping("/users/{id}")
	User one(@PathVariable Long id) {
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

		if (searchUser.getUserName() != null) {
			results.addAll(repository.findByUserName(searchUser.getUserName()));
		}

		if (searchUser.getEmail() != null) {
			results.addAll(repository.findByEmail(searchUser.getEmail()));
		}

		if (searchUser.getRole() != null) {
			results.addAll(repository.findByRole(searchUser.getRole()));
		}

		return results;
	}

	@DeleteMapping("/users/{id}")
	void removeUser(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
