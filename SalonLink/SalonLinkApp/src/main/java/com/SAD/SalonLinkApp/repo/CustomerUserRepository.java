package com.SAD.SalonLinkApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerUserRepository extends JpaRepository<CustomerAppUser, Long>
{

    Optional<CustomerAppUser> findByEmail(String email);




}