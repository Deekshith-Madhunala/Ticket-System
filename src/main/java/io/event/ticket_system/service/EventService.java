//package io.event.ticket_system.service;
//
//import io.event.ticket_system.entity.Booking;
//import io.event.ticket_system.entity.Event;
//import io.event.ticket_system.entity.EventType;
//import io.event.ticket_system.entity.Venue;
//import io.event.ticket_system.modelDTO.EventDTO;
//import io.event.ticket_system.repository.BookingRepository;
//import io.event.ticket_system.repository.EventRepository;
//import io.event.ticket_system.repository.EventTypeRepository;
//import io.event.ticket_system.repository.VenueRepository;
//import io.event.ticket_system.util.NotFoundException;
//import io.event.ticket_system.util.ReferencedWarning;
//import java.util.List;
//import java.util.UUID;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class EventService {
//
//    private final EventRepository eventRepository;
//    private final EventTypeRepository eventTypeRepository;
//    private final VenueRepository venueRepository;
//    private final BookingRepository bookingRepository;
//
//    public EventService(final EventRepository eventRepository,
//            final EventTypeRepository eventTypeRepository, final VenueRepository venueRepository,
//            final BookingRepository bookingRepository) {
//        this.eventRepository = eventRepository;
//        this.eventTypeRepository = eventTypeRepository;
//        this.venueRepository = venueRepository;
//        this.bookingRepository = bookingRepository;
//    }
//
//    public List<EventDTO> findAll() {
//        final List<Event> events = eventRepository.findAll(Sort.by("eventId"));
//        return events.stream()
//                .map(event -> mapToDTO(event, new EventDTO()))
//                .toList();
//    }
//
//    public EventDTO get(final UUID eventId) {
//        return eventRepository.findById(eventId)
//                .map(event -> mapToDTO(event, new EventDTO()))
//                .orElseThrow(NotFoundException::new);
//    }
//
//    public UUID create(final EventDTO eventDTO) {
//        final Event event = new Event();
//        mapToEntity(eventDTO, event);
//        return eventRepository.save(event).getEventId();
//    }
//
//    public void update(final UUID eventId, final EventDTO eventDTO) {
//        final Event event = eventRepository.findById(eventId)
//                .orElseThrow(NotFoundException::new);
//        mapToEntity(eventDTO, event);
//        eventRepository.save(event);
//    }
//
//    public void delete(final UUID eventId) {
//        eventRepository.deleteById(eventId);
//    }
//
//    private EventDTO mapToDTO(final Event event, final EventDTO eventDTO) {
//        eventDTO.setEventId(event.getEventId());
//        eventDTO.setTitle(event.getTitle());
//        eventDTO.setDescription(event.getDescription());
//        eventDTO.setStartDatetime(event.getStartDatetime());
//        eventDTO.setEndDatetime(event.getEndDatetime());
//        eventDTO.setPrice(event.getPrice());
//        eventDTO.setImageUrl(event.getImageUrl());
//        eventDTO.setEventType(event.getEventType() == null ? null : event.getEventType().getEventTypeId());
//        eventDTO.setVenue(event.getVenue() == null ? null : event.getVenue().getVenueId());
//        return eventDTO;
//    }
//
//    private Event mapToEntity(final EventDTO eventDTO, final Event event) {
//        event.setTitle(eventDTO.getTitle());
//        event.setDescription(eventDTO.getDescription());
//        event.setStartDatetime(eventDTO.getStartDatetime());
//        event.setEndDatetime(eventDTO.getEndDatetime());
//        event.setPrice(eventDTO.getPrice());
//        event.setImageUrl(eventDTO.getImageUrl());
//        final EventType eventType = eventDTO.getEventType() == null ? null : eventTypeRepository.findById(eventDTO.getEventType())
//                .orElseThrow(() -> new NotFoundException("eventType not found"));
//        event.setEventType(eventType);
//        final Venue venue = eventDTO.getVenue() == null ? null : venueRepository.findById(eventDTO.getVenue())
//                .orElseThrow(() -> new NotFoundException("venue not found"));
//        event.setVenue(venue);
//        return event;
//    }
//
//    public ReferencedWarning getReferencedWarning(final UUID eventId) {
//        final ReferencedWarning referencedWarning = new ReferencedWarning();
//        final Event event = eventRepository.findById(eventId)
//                .orElseThrow(NotFoundException::new);
//        final Booking eventBooking = bookingRepository.findFirstByEvent(event);
//        if (eventBooking != null) {
//            referencedWarning.setKey("event.booking.event.referenced");
//            referencedWarning.addParam(eventBooking.getBookingId());
//            return referencedWarning;
//        }
//        return null;
//    }
//
//}
