package io.event.ticket_system.controller;

import io.event.ticket_system.modelDTO.VenueDTO;
import io.event.ticket_system.service.VenueService;
import io.event.ticket_system.util.ReferencedException;
import io.event.ticket_system.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/venues", produces = MediaType.APPLICATION_JSON_VALUE)
public class VenueController {

    private final VenueService venueService;

    public VenueController(final VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenues() {
        return ResponseEntity.ok(venueService.findAll());
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<VenueDTO> getVenue(@PathVariable(name = "venueId") final String venueId) {
        return ResponseEntity.ok(venueService.get(venueId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createVenue(@RequestBody @Valid final VenueDTO venueDTO) {
        final String createdVenueId = venueService.create(venueDTO);
        return new ResponseEntity<>(createdVenueId, HttpStatus.CREATED);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<String> updateVenue(@PathVariable(name = "venueId") final String venueId,
                                              @RequestBody @Valid final VenueDTO venueDTO) {
        venueService.update(venueId, venueDTO);
        return ResponseEntity.ok(venueId);
    }

    @DeleteMapping("/{venueId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVenue(@PathVariable(name = "venueId") final String venueId) {
        final ReferencedWarning referencedWarning = venueService.getReferencedWarning(venueId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        venueService.delete(venueId);
        return ResponseEntity.noContent().build();
    }
}