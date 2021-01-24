package com.example.trafficlight.repository;

import com.example.trafficlight.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
}
