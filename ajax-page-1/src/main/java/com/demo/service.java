package com.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class service {
	@Autowired
	UserRepository repo;
	 public List<User> listAll() {
	        return repo.findAll();
	    }  
	 public Page<User> listAll(int pageNum, String sortField, String sortDir) {
		 	//Sort sort=Sort.by("name").ascending().and(Sort.by("address").descending());//sap xep
		 int pageSize = 5;
		    PageRequest pageable = PageRequest.of(pageNum - 1, pageSize,
		            sortDir.equals("asc") ? Sort.by(sortField).ascending()
		                                              : Sort.by(sortField).descending()
		    );
		     
		    return repo.findAll(pageable);
		}
}
