package com.example.terrainrental.repository;

import com.example.terrainrental.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
