package com.andersonpinto.dscatalog3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonpinto.dscatalog3.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
