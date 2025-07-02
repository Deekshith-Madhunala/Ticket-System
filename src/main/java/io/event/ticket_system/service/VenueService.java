package io.event.ticket_system.service;

import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.User;
import io.event.ticket_system.entity.Venue;
import io.event.ticket_system.modelDTO.VenueDTO;
import io.event.ticket_system.repository.EventRepository;
import io.event.ticket_system.repository.UserRepository;
import io.event.ticket_system.repository.VenueRepository;
import io.event.ticket_system.util.NotFoundException;
import io.event.ticket_system.util.ReferencedWarning;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public VenueService(
            VenueRepository venueRepository,
            UserRepository userRepository,
            EventRepository eventRepository,
            ModelMapper modelMapper
    ) {
        this.venueRepository = venueRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public List<VenueDTO> findAll() {
        return venueRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public VenueDTO get(String id) {
        return convertToDTO(venueRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public String create(VenueDTO dto) {
        Venue venue = convertToEntity(dto);
        return venueRepository.save(venue).getId();
    }

    public void update(String id, VenueDTO dto) {
        Venue venue = venueRepository.findById(id).orElseThrow(NotFoundException::new);
        Venue updated = convertToEntity(dto);
        updated.setId(id); // preserve existing ID
        venueRepository.save(updated);
    }

    public void delete(String id) {
        venueRepository.deleteById(id);
    }

    public ReferencedWarning getReferencedWarning(String id) {
        Venue venue = venueRepository.findById(id).orElseThrow(NotFoundException::new);
        Event venueEvent = eventRepository.findFirstByVenue(venue);

        if (venueEvent != null) {
            ReferencedWarning warning = new ReferencedWarning();
            warning.setKey("venue.event.venue.referenced");
            warning.addParam(venueEvent.getId());
            return warning;
        }
        return null;
    }

    private VenueDTO convertToDTO(Venue venue) {
        VenueDTO dto = modelMapper.map(venue, VenueDTO.class);
        dto.setManager(venue.getManager() != null ? venue.getManager().getId() : null);
        return dto;
    }

    private Venue convertToEntity(VenueDTO dto) {
        Venue venue = modelMapper.map(dto, Venue.class);
        if (dto.getManager() != null) {
            User user = userRepository.findById(dto.getManager())
                    .orElseThrow(() -> new NotFoundException("Manager not found"));
            venue.setManager(user);
        } else {
            venue.setManager(null);
        }
        return venue;
    }
}
