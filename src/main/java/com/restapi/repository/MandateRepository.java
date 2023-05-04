package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.Mandate;

public interface MandateRepository extends JpaRepository<Mandate, Long> {

}
