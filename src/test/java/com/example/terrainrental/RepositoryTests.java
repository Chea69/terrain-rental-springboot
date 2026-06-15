package com.example.terrainrental;

import com.example.terrainrental.repository.BookingRepository;
import com.example.terrainrental.repository.TerrainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryTests {

    @Autowired
    private TerrainRepository terrainRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void terrainRepositoryShouldNotBeNull() {
        Assertions.assertNotNull(terrainRepository);
    }

    @Test
    void bookingRepositoryShouldNotBeNull() {
        Assertions.assertNotNull(bookingRepository);
    }
}
