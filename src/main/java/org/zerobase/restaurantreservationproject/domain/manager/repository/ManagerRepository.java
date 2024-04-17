package org.zerobase.restaurantreservationproject.domain.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, String> {

}
