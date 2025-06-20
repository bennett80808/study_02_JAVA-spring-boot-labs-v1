package com.example.ch4labs.labs03.repository;

import com.example.ch4labs.labs03.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryQueryDSL {

}
