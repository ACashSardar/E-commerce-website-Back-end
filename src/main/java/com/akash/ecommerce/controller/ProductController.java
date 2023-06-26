package com.akash.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akash.ecommerce.entity.Category;
import com.akash.ecommerce.entity.Product;
import com.akash.ecommerce.entity.Supplier;
import com.akash.ecommerce.service.CategoryService;
import com.akash.ecommerce.service.FileService;
import com.akash.ecommerce.service.ProductService;
import com.akash.ecommerce.service.SupplierService;
import com.akash.ecommerce.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/products")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;
	
	@Value("${project.image}")
	private String path;
	
	@GetMapping
	public List<Product> getAllProducts(){

		return productService.getAllProducts();
	}
	
	@GetMapping("/search/{catId}")
	public List<Product> getByCategory(@PathVariable("catId") Integer catId){
		if(catId==0) return productService.getAllProducts();
		return productService.getProductsByCategoryId(catId);
	}
	
	@PostMapping(value = "/category/{categoryId}/supplier/{supplierId}",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product createProduct (
			@RequestPart("file") MultipartFile file,
			@RequestPart("document") Product product,
			@PathVariable("categoryId") Integer categoryId,
			@PathVariable("supplierId") Integer supplierId
		) throws IOException{
		
		Category category=categoryService.getCategoryById(categoryId);
		Supplier supplier=supplierService.getSupplierById(supplierId);
		String uniqueName=fileService.uploadImage(path, file);
		
		product.setCategory(category);
		product.setSupplier(supplier);
		product.setCatId(categoryId);
		product.setSupId(supplierId);
		product.setImageURL(uniqueName);
		return productService.addProduct(product);
	}
	
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable("productId") int productId){
		return productService.getProductById(productId);
	}
	
	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable("productId") int productId){
		productService.deleteProduct(productId);
	}

	@PutMapping("/{productId}")
	public Product updateProduct(@PathVariable("productId") int productId,@RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}

	@GetMapping(value="/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
