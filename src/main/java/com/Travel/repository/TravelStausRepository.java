package com.Travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Travel.entity.TravelStatus;

public interface TravelStausRepository extends JpaRepository<TravelStatus, Integer>{

}
