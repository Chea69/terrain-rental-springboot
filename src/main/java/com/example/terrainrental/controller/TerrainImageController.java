package com.example.terrainrental.controller;

import com.example.terrainrental.model.TerrainImage;
import com.example.terrainrental.repository.TerrainImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrain-images")
public class TerrainImageController {

    private final TerrainImageRepository terrainImageRepository;

    public TerrainImageController(TerrainImageRepository terrainImageRepository) {
        this.terrainImageRepository = terrainImageRepository;
    }

    @GetMapping
    public List<TerrainImage> getAll() {
        return terrainImageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerrainImage> getById(@PathVariable Long id) {
        return terrainImageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TerrainImage create(@RequestBody TerrainImage terrainImage) {
        return terrainImageRepository.save(terrainImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerrainImage> update(
            @PathVariable Long id,
            @RequestBody TerrainImage terrainImage) {
        if (!terrainImageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        terrainImage.setId(id);
        return ResponseEntity.ok(terrainImageRepository.save(terrainImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!terrainImageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        terrainImageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
