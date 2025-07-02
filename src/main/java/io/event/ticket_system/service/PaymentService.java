//package io.event.ticket_system.service;
//
//import io.event.ticket_system.entity.Booking;
//import io.event.ticket_system.entity.Payment;
//import io.event.ticket_system.modelDTO.PaymentDTO;
//import io.event.ticket_system.repository.BookingRepository;
//import io.event.ticket_system.repository.PaymentRepository;
//import io.event.ticket_system.util.NotFoundException;
//import java.util.List;
//import java.util.UUID;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class PaymentService {
//
//    private final PaymentRepository paymentRepository;
//    private final BookingRepository bookingRepository;
//
//    public PaymentService(final PaymentRepository paymentRepository,
//            final BookingRepository bookingRepository) {
//        this.paymentRepository = paymentRepository;
//        this.bookingRepository = bookingRepository;
//    }
//
//    public List<PaymentDTO> findAll() {
//        final List<Payment> payments = paymentRepository.findAll(Sort.by("paymentId"));
//        return payments.stream()
//                .map(payment -> mapToDTO(payment, new PaymentDTO()))
//                .toList();
//    }
//
//    public PaymentDTO get(final UUID paymentId) {
//        return paymentRepository.findById(paymentId)
//                .map(payment -> mapToDTO(payment, new PaymentDTO()))
//                .orElseThrow(NotFoundException::new);
//    }
//
//    public UUID create(final PaymentDTO paymentDTO) {
//        final Payment payment = new Payment();
//        mapToEntity(paymentDTO, payment);
//        return paymentRepository.save(payment).getPaymentId();
//    }
//
//    public void update(final UUID paymentId, final PaymentDTO paymentDTO) {
//        final Payment payment = paymentRepository.findById(paymentId)
//                .orElseThrow(NotFoundException::new);
//        mapToEntity(paymentDTO, payment);
//        paymentRepository.save(payment);
//    }
//
//    public void delete(final UUID paymentId) {
//        paymentRepository.deleteById(paymentId);
//    }
//
//    private PaymentDTO mapToDTO(final Payment payment, final PaymentDTO paymentDTO) {
//        paymentDTO.setPaymentId(payment.getPaymentId());
//        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
//        paymentDTO.setPaymentDate(payment.getPaymentDate());
//        paymentDTO.setAmountPaid(payment.getAmountPaid());
//        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
//        paymentDTO.setBooking(payment.getBooking() == null ? null : payment.getBooking().getBookingId());
//        return paymentDTO;
//    }
//
//    private Payment mapToEntity(final PaymentDTO paymentDTO, final Payment payment) {
//        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
//        payment.setPaymentDate(paymentDTO.getPaymentDate());
//        payment.setAmountPaid(paymentDTO.getAmountPaid());
//        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
//        final Booking booking = paymentDTO.getBooking() == null ? null : bookingRepository.findById(paymentDTO.getBooking())
//                .orElseThrow(() -> new NotFoundException("booking not found"));
//        payment.setBooking(booking);
//        return payment;
//    }
//
//}
