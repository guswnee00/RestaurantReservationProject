package org.zerobase.restaurantreservationproject.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerobase.restaurantreservationproject.domain.reservation.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity findByUser_Username(String username);
    ReservationEntity findByManager_Username(String managerName);
    boolean existsByUser_Username(String username);

}
