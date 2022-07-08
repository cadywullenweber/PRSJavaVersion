package com.example.spring.PRS.Projectwithjava.Controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.PRS.Projectwithjava.User;
import com.example.spring.PRS.Projectwithjava.UserRepository;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired 
	private UserRepository userRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@GetMapping
	public List<User> GetUser()
	{
		return userRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<User> GetUserById(@PathVariable int id)
	{
		Optional<User> u= userRepo.findById(id);
		if (u.isPresent())
			return u;
		else
			throw new ResponseStatusException (HttpStatus.NOT_FOUND);
	}
	
	//authenticate user by username and password combo
	@GetMapping("{username}/{password}")
	public User GetUserLogin(@PathVariable String username,@PathVariable String password)
	{
		Query query = em.createQuery("SELECT u FROM User u WHERE u.Username = :username AND u.Password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		
		if (users.size()==1)
			return users.get(0);
		else
			throw new ResponseStatusException (HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		try {
			return userRepo.save(user);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") int id,
			@RequestBody User updatedUser) {
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent())
		{
			User oldUser = u.get();
			oldUser.setLastname(updatedUser.getLastname());
			try {
				userRepo.save(oldUser);
				return oldUser;
			}
			catch (Exception e)
			{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}")
	public void DeleteUser(@PathVariable int id)
	{
		Optional<User> u = userRepo.findById(id);
		if (u.isPresent())
        {
	      userRepo.deleteById(id);
        }
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
