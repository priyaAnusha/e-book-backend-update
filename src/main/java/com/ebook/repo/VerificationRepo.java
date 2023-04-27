package com.ebook.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebook.entity.Verification;

@Repository
public interface VerificationRepo extends JpaRepository<Verification,String> {
 
}


