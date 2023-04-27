package com.ebook.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ebook.entity.EbookManagement;
import com.ebook.enums.RequestStatus;
@Repository
public interface EbookManagementRepo extends JpaRepository<EbookManagement, Long>{
	List<EbookManagement> findByRequestStatus(RequestStatus requestStatus);
	@Query("SELECT em.requestStatus FROM EbookManagement em WHERE em.boook.bookId = :bookId")
    RequestStatus findRequestStatusByBookId(@Param("bookId") Long bookId);

	
}
