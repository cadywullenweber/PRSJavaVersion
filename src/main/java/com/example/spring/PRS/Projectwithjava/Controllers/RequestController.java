package com.example.spring.PRS.Projectwithjava.Controllers;


import java.math.BigDecimal;
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


import com.example.spring.PRS.Projectwithjava.Request;
import com.example.spring.PRS.Projectwithjava.RequestRepository;


	@RestController
	@RequestMapping("/api/requests")
    public class RequestController {
		
		@Autowired 
		private RequestRepository requestRepo;

		@PersistenceContext
		private EntityManager em;
		
		@GetMapping
		public List<Request> GetRequests()
		{
			return requestRepo.findAll();
		}
		
		@GetMapping("{id}")
		public Optional<Request> GetRequestById(@PathVariable ("id") int id)
		{
			Optional<Request> r= requestRepo.findById(id);
			if (r.isPresent())
				return r;
			else
				throw new ResponseStatusException (HttpStatus.NOT_FOUND);
		}
		
		//GET: api/requests/review/userId
		@GetMapping("review/{id}")
	    public List<Request> getRequestsForReview(@PathVariable ("id") int id)
	    {    	
	    	String sqlstr = """
	    			SELECT r 
	    			FROM Request r 
	    			WHERE r.Status = 'REVIEW' AND r.Userid <> :userId
	    			""";
	    	Query query = em.createQuery(sqlstr);
	    	query.setParameter("userId", id);
	    	return query.getResultList();
	    }

		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Request createRequest(@RequestBody Request request) {
			try {
				return requestRepo.save(request);
			}
			catch (Exception e)
			{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		
		@PutMapping("{id}")
		public Request updateRequest(@PathVariable("id") int id,
				@RequestBody Request updatedRequest) {
			Optional<Request> r = requestRepo.findById(id);
			if (r.isPresent())
			{
				Request oldRequest = r.get();
				oldRequest.setJustification(updatedRequest.getJustification());
				try {
					requestRepo.save(oldRequest);
					return oldRequest;
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
		
		//PUT:/api/requests/5/review
		@PutMapping("/{id}/review")
	    public Request putRequestReview(@PathVariable int id)
	    {    
			Optional <Request> r = requestRepo.findById(id);
			if (r.isPresent())
			{
				var req = r.get();
				if(req.getTotal().compareTo(new BigDecimal(50)) <= 0)
				{
					req.setStatus("APPROVED");
				}
				else
				{
					req.setStatus("REVIEW");
				}
				requestRepo.save(req);
				return req;
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    	
	 
	    }
		
		//PUT: /api/requests/5/approve
		 @PutMapping("/{id}/approve")
		    public Request putRequestApprove(@PathVariable int id)
		    {  
		        Optional <Request> r = requestRepo.findById(id);
		        if (r.isPresent())
		        {
		            var req = r.get();
		            req.setStatus("APPROVED");

		        requestRepo.save(req);  
		        return req;
		        }

		        else throw new ResponseStatusException(HttpStatus.NOT_FOUND); //404 Error
		    }
		
		// PUT: api/Request/5/reject
		 @PutMapping("/{id}/reject")
		    public Request putRequestReject(@PathVariable int id)
		    {  
		        Optional <Request> r = requestRepo.findById(id);
		        if (r.isPresent())
		        {
		            var req = r.get();
		            req.setStatus("REJECTED");

		        requestRepo.save(req);  
		        return req;
		        }

		        else throw new ResponseStatusException(HttpStatus.NOT_FOUND); //404 Error
		    }
		
		@DeleteMapping("{id}")
		public void DeleteRequest(@PathVariable int id)
		{
			Optional<Request> r = requestRepo.findById(id);
			if (r.isPresent())
	        {
		      requestRepo.deleteById(id);
	        }
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}

	}



