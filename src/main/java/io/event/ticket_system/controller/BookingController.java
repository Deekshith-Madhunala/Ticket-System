package io.event.ticket_system.controller;

import io.event.ticket_system.modelDTO.BookingDTO;
import io.event.ticket_system.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("bookingId") String bookingId) {
        return ResponseEntity.ok(bookingService.get(bookingId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.create(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Void> updateBooking(@PathVariable("bookingId") String bookingId,
                                              @RequestBody @Valid BookingDTO bookingDTO) {
        bookingService.update(bookingId, bookingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBooking(@PathVariable("bookingId") String bookingId) {
        bookingService.delete(bookingId);
        return ResponseEntity.noContent().build();
    }
}
