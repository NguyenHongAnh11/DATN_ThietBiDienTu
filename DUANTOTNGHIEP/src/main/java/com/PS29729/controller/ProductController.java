package com.PS29729.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PathVariable;

import com.PS29729.entity.Product;
import com.PS29729.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/products/filter")
	public String filterProducts(Model model, 
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

	    model.addAttribute("items", list);
	    return "product/list";
	}

	@RequestMapping("/product/list")
	public String list(Model model, 
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

	    model.addAttribute("items", list);
	    return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
	@RequestMapping("/sort")
	public String sortProducts(Model model, @RequestParam("sortBy") Optional<String> sortBy) {
	    List<Product> list = productService.findAll();

	    if (sortBy.isPresent()) {
	        switch (sortBy.get()) {
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
	    }

	    model.addAttribute("items", list);
	    return "product/list";
	}

	@RequestMapping("/product/home")
	public String listhome(Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.orElse("").isEmpty()) {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		else {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		}
		return "layout/home";
	}
}