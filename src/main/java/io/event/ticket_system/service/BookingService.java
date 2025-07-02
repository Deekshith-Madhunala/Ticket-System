//package io.event.ticket_system.service;
//
//import io.event.ticket_system.entity.Booking;
//import io.event.ticket_system.entity.Event;
//import io.event.ticket_system.entity.Payment;
//import io.event.ticket_system.entity.User;
//import io.event.ticket_system.modelDTO.BookingDTO;
//import io.event.ticket_system.repository.BookingRepository;
//import io.event.ticket_system.repository.EventRepository;
//import io.event.ticket_system.repository.PaymentRepository;
//import io.event.ticket_system.repository.UserRepository;
//import io.event.ticket_system.util.NotFoundException;
//import io.event.ticket_system.util.ReferencedWarning;
//import java.util.List;
//import java.util.UUID;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class BookingService {
//
//    private final BookingRepository bookingRepository;
//    private final UserRepository userRepository;
//    private final EventRepository eventRepository;
//    private final PaymentRepository paymentRepository;
//
//    public BookingService(final BookingRepository bookingRepository,
//            final UserRepository userRepository, final EventRepository eventRepository,
//            final PaymentRepository paymentRepository) {
//        this.bookingRepository = bookingRepository;
//        this.userRepository = userRepository;
//        this.eventRepository = eventRepository;
//        this.paymentRepository = paymentRepository;
//    }
//
//    public List<BookingDTO> findAll() {
//        final List<Booking> bookings = bookingRepository.findAll(Sort.by("bookingId"));
//        return bookings.stream()
//                .map(booking -> mapToDTO(booking, new BookingDTO()))
//                .toList();
//    }
//
//    public BookingDTO get(final UUID bookingId) {
//        return bookingRepository.findById(bookingId)
//                .map(booking -> mapToDTO(booking, new BookingDTO()))
//                .orElseThrow(NotFoundException::new);
//    }
//
//    public Integer create(final BookingDTO bookingDTO) {
//        final Booking booking = new Booking();
//        mapToEntity(bookingDTO, booking);
//        return bookingRepository.save(booking).getBookingId();
//    }
//
//    public void update(final UUID bookingId, final BookingDTO bookingDTO) {
//        final Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(NotFoundException::new);
//        mapToEntity(bookingDTO, booking);
//        bookingRepository.save(booking);
//    }
//
//    public void delete(final UUID bookingId) {
//        bookingRepository.deleteById(bookingId);
//    }
//
//    private BookingDTO mapToDTO(final Booking booking, final BookingDTO bookingDTO) {
//        bookingDTO.setBookingId(booking.getBookingId());
//        bookingDTO.setTotalAmount(booking.getTotalAmount());
//        bookingDTO.setNumberOfTickets(booking.getNumberOfTickets());
//        bookingDTO.setBookingStatus(booking.getBookingStatus());
//        bookingDTO.setPaymentStatus(booking.getPaymentStatus());
//        bookingDTO.setBookingDate(booking.getBookingDate());
//        bookingDTO.setCancellationDeadline(booking.getCancellationDeadline());
//        bookingDTO.setUser(booking.getUser() == null ? null : booking.getUser().getUserId());
//        bookingDTO.setEvent(booking.getEvent() == null ? null : booking.getEvent().getEventId());
//        return bookingDTO;
//    }
//
//    private Booking mapToEntity(final BookingDTO bookingDTO, final Booking booking) {
//        booking.setTotalAmount(bookingDTO.getTotalAmount());
//        booking.setNumberOfTickets(bookingDTO.getNumberOfTickets());
//        booking.setBookingStatus(bookingDTO.getBookingStatus());
//        booking.setPaymentStatus(bookingDTO.getPaymentStatus());
//        booking.setBookingDate(bookingDTO.getBookingDate());
//        booking.setCancellationDeadline(bookingDTO.getCancellationDeadline());
//        final User user = bookingDTO.getUser() == null ? null : userRepository.findById(bookingDTO.getUser())
//                .orElseThrow(() -> new NotFoundException("user not found"));
//        booking.setUser(user);
//        final Event event = bookingDTO.getEvent() == null ? null : eventRepository.findById(bookingDTO.getEvent())
//                .orElseThrow(() -> new NotFoundException("event not found"));
//        booking.setEvent(event);
//        return booking;
//    }
//
//    public ReferencedWarning getReferencedWarning(final UUID bookingId) {
//        final ReferencedWarning referencedWarning = new ReferencedWarning();
//        final Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(NotFoundException::new);
//        final Payment bookingPayment = paymentRepository.findFirstByBooking(booking);
//        if (bookingPayment != null) {
//            referencedWarning.setKey("booking.payment.booking.referenced");
//            referencedWarning.addParam(bookingPayment.getPaymentId());
//            return referencedWarning;
//        }
//        return null;
//    }
//
//}
