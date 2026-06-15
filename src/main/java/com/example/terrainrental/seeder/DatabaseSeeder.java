package com.example.terrainrental.seeder;

import com.example.terrainrental.model.Booking;
import com.example.terrainrental.model.BookingStatus;
import com.example.terrainrental.model.Favorite;
import com.example.terrainrental.model.Payment;
import com.example.terrainrental.model.PaymentStatus;
import com.example.terrainrental.model.Review;
import com.example.terrainrental.model.Terrain;
import com.example.terrainrental.model.TerrainImage;
import com.example.terrainrental.repository.BookingRepository;
import com.example.terrainrental.repository.FavoriteRepository;
import com.example.terrainrental.repository.PaymentRepository;
import com.example.terrainrental.repository.ReviewRepository;
import com.example.terrainrental.repository.TerrainImageRepository;
import com.example.terrainrental.repository.TerrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final TerrainRepository terrainRepository;
    private final TerrainImageRepository terrainImageRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;

    public DatabaseSeeder(
            TerrainRepository terrainRepository,
            TerrainImageRepository terrainImageRepository,
            BookingRepository bookingRepository,
            PaymentRepository paymentRepository,
            ReviewRepository reviewRepository,
            FavoriteRepository favoriteRepository) {
        this.terrainRepository = terrainRepository;
        this.terrainImageRepository = terrainImageRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.reviewRepository = reviewRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void run(String... args) {
        if (terrainRepository.count() != 0) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        Terrain terrain = new Terrain();
        terrain.setOwnerId(1L);
        terrain.setTitle("Riverside Camping Terrain");
        terrain.setDescription("A spacious riverside terrain suitable for camping and events.");
        terrain.setLocation("Phnom Penh");
        terrain.setAreaSize(new BigDecimal("1500.00"));
        terrain.setPricePerDay(new BigDecimal("75.00"));
        terrain.setAvailableFrom(now);
        terrain.setAvailableTo(now.plusMonths(3));
        terrain.setIsAvailable(true);
        terrain.setCreatedAt(now);
        terrain.setUpdatedAt(now);
        terrain = terrainRepository.save(terrain);

        TerrainImage terrainImage = new TerrainImage();
        terrainImage.setTerrainId(terrain.getId());
        terrainImage.setImagePath("/images/terrains/riverside-camping.jpg");
        terrainImage.setUploadedAt(now);
        terrainImage = terrainImageRepository.save(terrainImage);

        terrain.setMainImageId(terrainImage.getId());
        terrainRepository.save(terrain);

        Booking booking = new Booking();
        booking.setTerrainId(terrain.getId());
        booking.setRenterId(2L);
        booking.setStartDate(now.plusDays(7));
        booking.setEndDate(now.plusDays(10));
        booking.setTotalPrice(new BigDecimal("225.00"));
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(now);
        booking.setUpdatedAt(now);
        booking = bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setBookingId(booking.getId());
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setAmountPaid(new BigDecimal("225.00"));
        payment.setPaymentDate(now);
        payment.setStatus(PaymentStatus.PAID);
        payment.setTransactionId("TXN-SAMPLE-001");
        paymentRepository.save(payment);

        Review review = new Review();
        review.setTerrainId(terrain.getId());
        review.setUserId(2L);
        review.setRating(5);
        review.setComment("Excellent terrain with plenty of space and a great location.");
        review.setCreatedAt(now);
        review.setUpdatedAt(now);
        reviewRepository.save(review);

        Favorite favorite = new Favorite();
        favorite.setUserId(2L);
        favorite.setTerrainId(terrain.getId());
        favorite.setCreatedAt(now);
        favorite.setUpdatedAt(now);
        favoriteRepository.save(favorite);
    }
}
