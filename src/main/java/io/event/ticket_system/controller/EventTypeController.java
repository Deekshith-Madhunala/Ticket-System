//package io.event.ticket_system.controller;
//
//import io.event.ticket_system.modelDTO.EventTypeDTO;
//import io.event.ticket_system.service.EventTypeService;
//import io.event.ticket_system.util.ReferencedException;
//import io.event.ticket_system.util.ReferencedWarning;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.UUID;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping(value = "/api/eventTypes", produces = MediaType.APPLICATION_JSON_VALUE)
//public class EventTypeController {
//
//    private final EventTypeService eventTypeService;
//
//    public EventTypeController(final EventTypeService eventTypeService) {
//        this.eventTypeService = eventTypeService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EventTypeDTO>> getAllEventTypes() {
//        return ResponseEntity.ok(eventTypeService.findAll());
//    }
//
//    @GetMapping("/{eventTypeId}")
//    public ResponseEntity<EventTypeDTO> getEventType(
//            @PathVariable(name = "eventTypeId") final UUID eventTypeId) {
//        return ResponseEntity.ok(eventTypeService.get(eventTypeId));
//    }
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<UUID> createEventType(
//            @RequestBody @Valid final EventTypeDTO eventTypeDTO) {
//        final UUID createdEventTypeId = eventTypeService.create(eventTypeDTO);
//        return new ResponseEntity<>(createdEventTypeId, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{eventTypeId}")
//    public ResponseEntity<UUID> updateEventType(
//            @PathVariable(name = "eventTypeId") final UUID eventTypeId,
//            @RequestBody @Valid final EventTypeDTO eventTypeDTO) {
//        eventTypeService.update(eventTypeId, eventTypeDTO);
//        return ResponseEntity.ok(eventTypeId);
//    }
//
//    @DeleteMapping("/{eventTypeId}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteEventType(
//            @PathVariable(name = "eventTypeId") final UUID eventTypeId) {
//        final ReferencedWarning referencedWarning = eventTypeService.getReferencedWarning(eventTypeId);
//        if (referencedWarning != null) {
//            throw new ReferencedException(referencedWarning);
//        }
//        eventTypeService.delete(eventTypeId);
//        return ResponseEntity.noContent().build();
//    }
//
//}
