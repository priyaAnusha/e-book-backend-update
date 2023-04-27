package com.ebook.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ebook.entity.BookEntity;
@Repository
public interface BookRepo extends JpaRepository<BookEntity,Long>{

	List<BookEntity> findByAuthorUserName(String name);
	@Query("SELECT b.title from BookEntity b WHERE b.title=:title")
		BookEntity findByTitle(String title);
	
}
