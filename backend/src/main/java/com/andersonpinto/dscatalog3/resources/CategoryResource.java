package com.andersonpinto.dscatalog3.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersonpinto.dscatalog3.entities.Category;
import com.andersonpinto.dscatalog3.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
