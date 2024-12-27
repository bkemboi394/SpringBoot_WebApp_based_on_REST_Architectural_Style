package com.SAD.SalonLinkApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalonRepository extends JpaRepository<Salon,Long> {


    Optional<Salon> findByName(String name);
}
