package com.akash.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akash.ecommerce.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
