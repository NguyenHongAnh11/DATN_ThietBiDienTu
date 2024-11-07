package com.PS29729.rest.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PS29729.entity.Brands;
import com.PS29729.entity.Product;
import com.PS29729.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	
	@GetMapping
	public List<Product> getAll() {
		return productService.findAll();
	}
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	@PostMapping
	public Product post(@RequestBody Product product) {
		productService.create(product);
		return product;
	}
	@PutMapping("{id}")
	public Product put(@PathVariable("id") Integer id, @RequestBody Product product) {
		return productService.update(product);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
	@GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam("categoryId") Optional<String> categoryId,
            @RequestParam("priceRange") String priceRange) {
        
        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;

        switch (priceRange) {
            case "under100":
                maxPrice = 100000;
                break;
            case "100-300":
                minPrice = 100000;
                maxPrice = 300000;
                break;
            case "300-900":
                minPrice = 300000;
                maxPrice = 900000;
                break;
            case "over900":
                minPrice = 900000;
                break;
        }

        List<Product> list;
        if (categoryId.isPresent()) {
            list = productService.findByCategoryIdAndPriceRange(categoryId.get(), minPrice, maxPrice);
        } else {
            list = productService.findByPriceRange(minPrice, maxPrice);
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list(
            @RequestParam("cid") Optional<String> cid,
            @RequestParam("minPrice") Optional<Double> minPrice,
            @RequestParam("maxPrice") Optional<Double> maxPrice) {
        
        List<Product> list;

        if (cid.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty()) {
            list = productService.findAll();
        } else if (cid.isPresent()) {
            list = productService.findByCategoryIdAndPriceRange(cid.get(), minPrice.orElse(0.0), maxPrice.orElse(Double.MAX_VALUE));
        } else {
            list = productService.findByPriceRange(minPrice.orElse(0.0), maxPrice.orElse(Double.MAX_VALUE));
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> detail(@PathVariable("id") Integer id) {
        Product item = productService.findById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Product>> sortProducts(@RequestParam("sortBy") Optional<String> sortBy) {
        List<Product> list = productService.findAll();

        sortBy.ifPresent(order -> {
            switch (order) {
                case "A-Z":
                    list.sort(Comparator.comparing(Product::getName));
                    break;
                case "Z-A":
                    list.sort(Comparator.comparing(Product::getName).reversed());
                    break;
                case "Giá, thấp đến cao":
                    list.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "Giá, cao đến thấp":
                    list.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                default:
                    break;
            }
        });

        return ResponseEntity.ok(list);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Product>> listhome(@RequestParam("cid") Optional<String> cid) {
        List<Product> list;
        if (cid.orElse("").isEmpty()) {
            list = productService.findAll();
        } else {
            list = productService.findByCategoryId(cid.get());
        }
        return ResponseEntity.ok(list);
    }
	
}
