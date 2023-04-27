package com.ebook.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebook.entity.AppUser;

public interface EmailRepo extends JpaRepository<AppUser, String> {

}
