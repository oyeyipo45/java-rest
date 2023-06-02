package com.java_rest.repos;

import com.java_rest.domain.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
}
