package com.akash.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.ecommerce.configuration.AppConstants;
import com.akash.ecommerce.entity.Supplier;
import com.akash.ecommerce.service.SupplierService;

@RestController
@CrossOrigin(origins = AppConstants.client_base_url)
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	
	@GetMapping
	public List<Supplier> getAllSuppliers(){
		return supplierService.getAllSuppliers();
	}
	
	@PostMapping
	public Supplier createSupplier(@RequestBody Supplier supplier){
		return supplierService.addSupplier(supplier);
	}
	
	@GetMapping("/{supplierId}")
	public Supplier getSupplierById(@PathVariable("supplierId") int supplierId){
		return supplierService.getSupplierById(supplierId);
	}
	
	@DeleteMapping("/{supplierId}")
	public void deleteSupplier(@PathVariable("supplierId") int supplierId){
		supplierService.deleteSupplier(supplierId);
	}

	@PutMapping("/{supplierId}")
	public Supplier updateSupplier(@PathVariable("supplierId") int supplierId,@RequestBody Supplier supplier) {
		return supplierService.updateSupplier(supplierId, supplier);
	}
}
