package com.andersonpinto.dscatalog3.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersonpinto.dscatalog3.dto.CategoryDTO;
import com.andersonpinto.dscatalog3.entities.Category;
import com.andersonpinto.dscatalog3.repositories.CategoryRepository;
import com.andersonpinto.dscatalog3.services.exceptions.DatabaseException;
import com.andersonpinto.dscatalog3.services.exceptions.ResourceEntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
		Page<Category> list =  repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));
	}

	@Transactional
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceEntityNotFoundException("Entidade não encontrada!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
		catch (EntityNotFoundException e){
			throw new ResourceEntityNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceEntityNotFoundException("Id not found" + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
