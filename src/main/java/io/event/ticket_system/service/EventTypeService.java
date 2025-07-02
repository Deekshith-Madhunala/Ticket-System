//package io.event.ticket_system.service;
//
//import io.event.ticket_system.entity.Event;
//import io.event.ticket_system.entity.EventType;
//import io.event.ticket_system.modelDTO.EventTypeDTO;
//import io.event.ticket_system.repository.EventRepository;
//import io.event.ticket_system.repository.EventTypeRepository;
//import io.event.ticket_system.util.NotFoundException;
//import io.event.ticket_system.util.ReferencedWarning;
//import java.util.List;
//import java.util.UUID;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class EventTypeService {
//
//    private final EventTypeRepository eventTypeRepository;
//    private final EventRepository eventRepository;
//
//    public EventTypeService(final EventTypeRepository eventTypeRepository,
//            final EventRepository eventRepository) {
//        this.eventTypeRepository = eventTypeRepository;
//        this.eventRepository = eventRepository;
//    }
//
//    public List<EventTypeDTO> findAll() {
//        final List<EventType> eventTypes = eventTypeRepository.findAll(Sort.by("eventTypeId"));
//        return eventTypes.stream()
//                .map(eventType -> mapToDTO(eventType, new EventTypeDTO()))
//                .toList();
//    }
//
//    public EventTypeDTO get(final UUID eventTypeId) {
//        return eventTypeRepository.findById(eventTypeId)
//                .map(eventType -> mapToDTO(eventType, new EventTypeDTO()))
//                .orElseThrow(NotFoundException::new);
//    }
//
//    public UUID create(final EventTypeDTO eventTypeDTO) {
//        final EventType eventType = new EventType();
//        mapToEntity(eventTypeDTO, eventType);
//        return eventTypeRepository.save(eventType).getEventTypeId();
//    }
//
//    public void update(final UUID eventTypeId, final EventTypeDTO eventTypeDTO) {
//        final EventType eventType = eventTypeRepository.findById(eventTypeId)
//                .orElseThrow(NotFoundException::new);
//        mapToEntity(eventTypeDTO, eventType);
//        eventTypeRepository.save(eventType);
//    }
//
//    public void delete(final UUID eventTypeId) {
//        eventTypeRepository.deleteById(eventTypeId);
//    }
//
//    private EventTypeDTO mapToDTO(final EventType eventType, final EventTypeDTO eventTypeDTO) {
//        eventTypeDTO.setEventTypeId(eventType.getEventTypeId());
//        eventTypeDTO.setTypeName(eventType.getTypeName());
//        eventTypeDTO.setDescription(eventType.getDescription());
//        return eventTypeDTO;
//    }
//
//    private EventType mapToEntity(final EventTypeDTO eventTypeDTO, final EventType eventType) {
//        eventType.setTypeName(eventTypeDTO.getTypeName());
//        eventType.setDescription(eventTypeDTO.getDescription());
//        return eventType;
//    }
//
//    public ReferencedWarning getReferencedWarning(final UUID eventTypeId) {
//        final ReferencedWarning referencedWarning = new ReferencedWarning();
//        final EventType eventType = eventTypeRepository.findById(eventTypeId)
//                .orElseThrow(NotFoundException::new);
//        final Event eventTypeEvent = eventRepository.findFirstByEventType(eventType);
//        if (eventTypeEvent != null) {
//            referencedWarning.setKey("eventType.event.eventType.referenced");
//            referencedWarning.addParam(eventTypeEvent.getEventId());
//            return referencedWarning;
//        }
//        return null;
//    }
//
//}
