package com.example.terrainrental.controller;

import com.example.terrainrental.model.Terrain;
import com.example.terrainrental.repository.TerrainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
public class TerrainController {

    private final TerrainRepository terrainRepository;

    public TerrainController(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }

    @GetMapping
    public List<Terrain> getAll() {
        return terrainRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getById(@PathVariable Long id) {
        return terrainRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Terrain create(@RequestBody Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terrain> update(@PathVariable Long id, @RequestBody Terrain terrain) {
        if (!terrainRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        terrain.setId(id);
        return ResponseEntity.ok(terrainRepository.save(terrain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!terrainRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        terrainRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
