package com.ebook.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebook.entity.AppUser;
@Repository
public interface UserRepo extends JpaRepository<AppUser, String>{

	Optional<AppUser> findByuserName(String authorId);

	

}
