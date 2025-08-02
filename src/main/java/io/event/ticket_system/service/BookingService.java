package io.event.ticket_system.service;

import io.event.ticket_system.entity.Booking;
import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.User;
import io.event.ticket_system.modelDTO.BookingDTO;
import io.event.ticket_system.modelDTO.EventDTO;
import io.event.ticket_system.repository.BookingRepository;
import io.event.ticket_system.repository.EventRepository;
import io.event.ticket_system.repository.UserRepository;
import io.event.ticket_system.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public BookingDTO get(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        return modelMapper.map(booking, BookingDTO.class);
    }

    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = mapToEntity(bookingDTO);

        Event event = eventRepository.findById(bookingDTO.getEventId()).get();
        event.getTicketDetails().getFirst().setTicketQuantity(event.getTicketDetails().get(0).getTicketQuantity() -
                bookingDTO.getNumberOfTickets());
        log.info("found booking, updated tickets: ", event.getTicketDetails().getFirst().getTicketQuantity());
        eventRepository.save(event);

        Booking savedbooking = bookingRepository.save(booking);
        return modelMapper.map(savedbooking, BookingDTO.class);

    }

    public void update(String bookingId, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        Booking updatedBooking = mapToEntity(bookingDTO);
        updatedBooking.setId(existingBooking.getId()); // keep the original ID
        bookingRepository.save(updatedBooking);
    }

    public void delete(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    private Booking mapToEntity(BookingDTO dto) {
        Booking booking = modelMapper.map(dto, Booking.class);

        // Set user reference
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            booking.setUser(user);
        }

        // Set event reference
        if (dto.getEventId() != null) {
            Event event = eventRepository.findById(dto.getEventId())
                    .orElseThrow(() -> new NotFoundException("Event not found"));
            booking.setEvent(event);
        }

        return booking;
    }
}
