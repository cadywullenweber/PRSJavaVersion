package com.example.spring.PRS.Projectwithjava.Controllers;


import java.util.List;
import java.util.Optional;

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


import com.example.spring.PRS.Projectwithjava.Vendor;
import com.example.spring.PRS.Projectwithjava.VendorRepository;


	@RestController
	@RequestMapping("/api/vendors")
    public class VendorController {
		
		@Autowired private VendorRepository vendorRepo;
		
		@GetMapping
		public List<Vendor> GetProduct()
		{
			return vendorRepo.findAll();
		}
		
		@GetMapping("{id}")
		public Optional<Vendor> GetVendorById(@PathVariable int id)
		{
			Optional<Vendor> v= vendorRepo.findById(id);
			if (v.isPresent())
				return v;
			else
				throw new ResponseStatusException (HttpStatus.NOT_FOUND);
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Vendor createVendor(@RequestBody Vendor vendor) {
			try {
				return vendorRepo.save(vendor);
			}
			catch (Exception e)
			{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		
		@PutMapping("/{id}")
		public Vendor updateVendor(@PathVariable("id") int id,
				@RequestBody Vendor updatedVendor) {
			Optional<Vendor> v = vendorRepo.findById(id);
			if (v.isPresent())
			{
				Vendor oldVendor = v.get();
				oldVendor.setName(updatedVendor.getName());
				try {
					vendorRepo.save(oldVendor);
					return oldVendor;
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
		public void DeleteVendor(@PathVariable int id)
		{
			Optional<Vendor> v = vendorRepo.findById(id);
			if (v.isPresent())
	        {
		      vendorRepo.deleteById(id);
	        }
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}

	}


