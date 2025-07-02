package io.event.ticket_system.service;

import io.event.ticket_system.entity.Booking;
import io.event.ticket_system.entity.User;
import io.event.ticket_system.entity.Venue;
import io.event.ticket_system.modelDTO.UserDTO;
import io.event.ticket_system.repository.BookingRepository;
import io.event.ticket_system.repository.UserRepository;
import io.event.ticket_system.repository.VenueRepository;
import io.event.ticket_system.util.JwtUtil;
import io.event.ticket_system.util.NotFoundException;
import io.event.ticket_system.util.ReferencedWarning;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VenueRepository venueRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public UserService(final UserRepository userRepository, final VenueRepository venueRepository,
                       final BookingRepository bookingRepository, final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.venueRepository = venueRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("userId"));
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    public UserDTO get(final String userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO create(final UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    public void update(final String userId, final UserDTO userDTO) {
        User existingUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        // Map fields from DTO to existing entity (excluding id)
        modelMapper.map(userDTO, existingUser);
        userRepository.save(existingUser);
    }

    public void delete(final String userId) {
        userRepository.deleteById((userId));
    }

    public ReferencedWarning getReferencedWarning(final String userId) {
        ReferencedWarning referencedWarning = new ReferencedWarning();
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Venue managerVenue = venueRepository.findFirstByManager(user);
        if (managerVenue != null) {
            referencedWarning.setKey("user.venue.manager.referenced");
            referencedWarning.addParam(managerVenue.getId());
            return referencedWarning;
        }
        Booking userBooking = bookingRepository.findFirstByUser(user);
        if (userBooking != null) {
            referencedWarning.setKey("user.booking.user.referenced");
            referencedWarning.addParam(userBooking.getId());
            return referencedWarning;
        }
        return null;
    }

    public String findByEmailAndPassword(String email, String password) {
        final User user = userRepository.findByEmail(email); // Find user by email
        if (user == null) {
            throw new NotFoundException("User not found");
        }
//        if (!PasswordEncryptionUtil.matchPassword(password, user.getPassword())) {
//            throw new IllegalArgumentException("Invalid password");
//        }
        return JwtUtil.generateToken(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }
}
