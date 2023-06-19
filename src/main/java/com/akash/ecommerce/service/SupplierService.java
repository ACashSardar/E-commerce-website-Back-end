package com.akash.ecommerce.service;

import java.util.List;

import com.akash.ecommerce.entity.Supplier;

public interface SupplierService {
	Supplier addSupplier(Supplier supplier);
	Supplier updateSupplier(int supplierId, Supplier supplier);
	boolean deleteSupplier(int supplierId);
	List<Supplier> getAllSuppliers();
	Supplier getSupplierById(int supplierId);
}
