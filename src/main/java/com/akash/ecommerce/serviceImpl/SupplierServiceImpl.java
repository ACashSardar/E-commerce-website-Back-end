package com.akash.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.Supplier;
import com.akash.ecommerce.repository.SupplierRepository;
import com.akash.ecommerce.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	SupplierRepository supplierRepository;
	
	@Override
	public Supplier addSupplier(Supplier supplier) {
		
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier updateSupplier(int supplierId, Supplier supplier) {
		Supplier supplier2=supplierRepository.findById(supplierId).get();
		supplier2.setSupplierName(supplier.getSupplierName());
		supplier2.setSupplierAddress(supplier.getSupplierAddress());
		supplier2.setDescription(supplier.getDescription());
		supplier2.setPhoneNo(supplier.getPhoneNo());
		return supplierRepository.save(supplier2);
	}

	@Override
	public boolean deleteSupplier(int supplierId) {
		supplierRepository.deleteById(supplierId);
		return true;
	}

	@Override
	public List<Supplier> getAllSuppliers() {

		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplierById(int supplierId) {
		
		return supplierRepository.findById(supplierId).get();
	}

}
