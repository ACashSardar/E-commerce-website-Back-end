package com.akash.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.akash.ecommerce.entity.Product;
import com.akash.ecommerce.repository.CategoryRepository;
import com.akash.ecommerce.repository.ProductRepository;
import com.akash.ecommerce.repository.SupplierRepository;
import com.akash.ecommerce.service.FileService;
import com.akash.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int productId, Product product) {
		Product product2=productRepository.findById(productId).get();
		product2.setProductName(product.getProductName());
		product2.setProductDesc(product.getProductDesc());
		product2.setPrice(product.getPrice());
		product2.setStock(product.getStock());
		product2.setCatId(product.getCatId());
		product2.setSupId(product.getSupId());
		product2.setCategory(categoryRepository.findById(product.getCatId()).get());
		product2.setSupplier(supplierRepository.findById(product.getSupId()).get());
		return productRepository.save(product2);
	}

	@Override
	public boolean deleteProduct(int productId) {
		Product product=productRepository.findById(productId).get();
		String filename=product.getImageURL();
		System.out.println(filename+" is going to be deleted");
		try {
			if(fileService.deleteImage(path, filename))
				System.out.println("Image deleted successfully");;
		}catch (Exception e) {
			System.out.println("Failed to delete image");
		}
		productRepository.deleteById(productId);
		return true;
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int productId) {
		
		return productRepository.findById(productId).get();
	}

	@Override
	public List<Product> getProductsByCategoryId(int catId) {
		
		return productRepository.findProductByCatId(catId);
	}
}
