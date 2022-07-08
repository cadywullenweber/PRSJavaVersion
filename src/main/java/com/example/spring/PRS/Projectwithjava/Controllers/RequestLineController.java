package com.example.spring.PRS.Projectwithjava.Controllers;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
import com.example.spring.PRS.Projectwithjava.RequestLine;
import com.example.spring.PRS.Projectwithjava.RequestLineRepository;
import com.example.spring.PRS.Projectwithjava.RequestRepository;


	@RestController
	@RequestMapping("/api/requestlines")
    public class RequestLineController {
		
		@Autowired 
		private RequestLineRepository requestlineRepo;
		
		@Autowired
		private RequestRepository requestRepo;
		
		@PersistenceContext
		private EntityManager em;

		
		
		@GetMapping
		public List<RequestLine> GetRequestLine()
		{
			return requestlineRepo.findAll();
		}
		
		@GetMapping("{id}")
		public Optional<RequestLine> GetRequestLineById(@PathVariable int id)
		{
			Optional<RequestLine> rl= requestlineRepo.findById(id);
			if (rl.isPresent())
				return rl;
			else
				throw new ResponseStatusException (HttpStatus.NOT_FOUND);
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public RequestLine createRequestLine(@RequestBody RequestLine requestline) {
			try {
				var newrequestline = requestlineRepo.save(requestline);
				RecalculateRequestTotal (newrequestline.getRequestId());
				return newrequestline;
			}
			catch (Exception e)
			{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@PutMapping("{id}")
		public RequestLine updateRequestLine(@PathVariable int id,
				@RequestBody RequestLine updatedRequestLine) {
			Optional<RequestLine> rl = requestlineRepo.findById(id);
			if (rl.isPresent())
			{
				RequestLine oldRequestLine = rl.get();
				oldRequestLine.setQuantity(updatedRequestLine.getQuantity());
				try {
					requestlineRepo.save(oldRequestLine);
					RecalculateRequestTotal (oldRequestLine.getRequestId());
					return oldRequestLine;
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
		public void DeleteRequestLine(@PathVariable int id)
		{
			Optional<RequestLine> rl = requestlineRepo.findById(id);
			if (rl.isPresent())
	        {
		      requestlineRepo.deleteById(id);
		      var oldrl = rl.get();
		      RecalculateRequestTotal(oldrl.getRequestId());
	        }
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}
		private void RecalculateRequestTotal(@PathVariable int requestId)
		{   
		    	String sqlstr = """
		    			SELECT sum(rl.Quantity * p.Price) as Total
		    			FROM RequestLine rl
		    			JOIN Product p ON rl.ProductId = p.Id
		    			WHERE rl.RequestId = :requestId
		    			""";
		    	Query query = em.createQuery(sqlstr);  // EntityManager
		    	query.setParameter("requestId", requestId);
		    	BigDecimal total = (BigDecimal) query.getSingleResult();
			
		    	Optional<Request> req = requestRepo.findById(requestId);
		        if (req.isPresent())
		        {
			    	Request r = req.get();
			    	r.setTotal(total);
			    	// TODO try catch
			    	requestRepo.save(r);
		        }
	
			

		}
		

	}


