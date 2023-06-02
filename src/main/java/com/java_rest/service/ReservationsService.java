package com.java_rest.service;

import com.java_rest.domain.Reservations;
import com.java_rest.domain.User;
import com.java_rest.model.ReservationsDTO;
import com.java_rest.repos.ReservationsRepository;
import com.java_rest.repos.UserRepository;
import com.java_rest.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;

    public ReservationsService(final ReservationsRepository reservationsRepository,
            final UserRepository userRepository) {
        this.reservationsRepository = reservationsRepository;
        this.userRepository = userRepository;
    }

    public List<ReservationsDTO> findAll() {
        final List<Reservations> reservationss = reservationsRepository.findAll(Sort.by("id"));
        return reservationss.stream()
                .map(reservations -> mapToDTO(reservations, new ReservationsDTO()))
                .toList();
    }

    public ReservationsDTO get(final Long id) {
        return reservationsRepository.findById(id)
                .map(reservations -> mapToDTO(reservations, new ReservationsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ReservationsDTO reservationsDTO) {
        final Reservations reservations = new Reservations();
        mapToEntity(reservationsDTO, reservations);
        return reservationsRepository.save(reservations).getId();
    }

    public void update(final Long id, final ReservationsDTO reservationsDTO) {
        final Reservations reservations = reservationsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationsDTO, reservations);
        reservationsRepository.save(reservations);
    }

    public void delete(final Long id) {
        reservationsRepository.deleteById(id);
    }

    private ReservationsDTO mapToDTO(final Reservations reservations,
            final ReservationsDTO reservationsDTO) {
        reservationsDTO.setId(reservations.getId());
        reservationsDTO.setReservationDate(reservations.getReservationDate());
        reservationsDTO.setStartTime(reservations.getStartTime());
        reservationsDTO.setEndTime(reservations.getEndTime());
        reservationsDTO.setDateCreated(reservations.getDateCreated());
        reservationsDTO.setDateUpdated(reservations.getDateUpdated());
        reservationsDTO.setUser(reservations.getUser() == null ? null : reservations.getUser().getId());
        return reservationsDTO;
    }

    private Reservations mapToEntity(final ReservationsDTO reservationsDTO,
            final Reservations reservations) {
        reservations.setReservationDate(reservationsDTO.getReservationDate());
        reservations.setStartTime(reservationsDTO.getStartTime());
        reservations.setEndTime(reservationsDTO.getEndTime());
        reservations.setDateCreated(reservationsDTO.getDateCreated());
        reservations.setDateUpdated(reservationsDTO.getDateUpdated());
        final User user = reservationsDTO.getUser() == null ? null : userRepository.findById(reservationsDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        reservations.setUser(user);
        return reservations;
    }

}
