package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.SaleDetails;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetails,Integer> {

}
