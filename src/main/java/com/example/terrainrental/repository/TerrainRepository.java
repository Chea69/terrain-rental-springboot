package com.example.terrainrental.repository;

import com.example.terrainrental.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {
}