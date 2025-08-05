package io.event.ticket_system.service;

import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.User;
import io.event.ticket_system.entity.Venue;
import io.event.ticket_system.modelDTO.BookingDTO;
import io.event.ticket_system.modelDTO.EventDTO;
import io.event.ticket_system.repository.EventRepository;
import io.event.ticket_system.repository.UserRepository;
import io.event.ticket_system.repository.VenueRepository;
import io.event.ticket_system.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    public EventDTO get(String eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        return modelMapper.map(event, EventDTO.class);
    }

    public EventDTO create(EventDTO eventDTO) {

//        // Map eventDTO to Event
        Event event = modelMapper.map(eventDTO, Event.class);
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventDTO.class);
//        return savedEvent.modelmapper;
    }

    public void update(String eventId, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        Event updatedEvent = mapToEntity(eventDTO);
        updatedEvent.setId(eventId); // Preserve ID
        eventRepository.save(updatedEvent);
    }

    public void delete(String eventId) {
        eventRepository.deleteById(eventId);
    }

    // 🔁 Helper to map DTO to Entity
    private Event mapToEntity(EventDTO dto) {
        Event event = modelMapper.map(dto, Event.class);

        // Set Venue from venueId
        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new NotFoundException("Venue not found"));
        event.setVenue(venue);

        return event;
    }
}
