package com.example.terrainrental.controller;

import com.example.terrainrental.model.Favorite;
import com.example.terrainrental.repository.FavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping
    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getById(@PathVariable Long id) {
        return favoriteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Favorite create(@RequestBody Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorite> update(@PathVariable Long id, @RequestBody Favorite favorite) {
        if (!favoriteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        favorite.setId(id);
        return ResponseEntity.ok(favoriteRepository.save(favorite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!favoriteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        favoriteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
