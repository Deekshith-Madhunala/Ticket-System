package io.event.ticket_system.controller;

import io.event.ticket_system.modelDTO.EventDTO;
import io.event.ticket_system.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(eventService.get(eventId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createEvent(@RequestBody @Valid EventDTO eventDTO) {
        String createdEventId = eventService.create(eventDTO);
        return new ResponseEntity<>(createdEventId, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable String eventId, @RequestBody @Valid EventDTO eventDTO) {
        eventService.update(eventId, eventDTO);
        return ResponseEntity.ok(eventId);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable String eventId) {
        eventService.delete(eventId);
    }
}
