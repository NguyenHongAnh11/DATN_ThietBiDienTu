package com.PS29729.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PS29729.entity.Brands;
import com.PS29729.entity.Product;
import com.PS29729.service.BrandsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bands")
public class BandsRestController {
	@Autowired
	BrandsService bandservice;

	@GetMapping
	public List<Brands> getAll() {
		return bandservice.findAll();
	}

}
