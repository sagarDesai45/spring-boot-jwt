package com.example.assignment.controller;

import com.example.assignment.dao.entity.Product;
import com.example.assignment.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() throws Exception {
    	
    	List<String> tags=new ArrayList<>();
    	tags.add("red");
    	tags.add("shirt");
    	tags.add("slim fit");
        Product product = new Product();
        product.setName("Red Shirt");
        product.setDescription("Red Hugo Boss shirt");
        product.setBrand("Hugo Boss");
        product.setTags(tags);
        product.setCategory("apparel");

        mockMvc.perform(post("/v1/products/insert-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Red Shirt"))
                .andExpect(jsonPath("$.category").value("apparel"));
    }

    @Test
    public void testSearchProducts() throws Exception {
    	
    	List<String> tags=new ArrayList<>();
    	tags.add("red");
    	tags.add("shirt");
    	tags.add("slim fit");
        Product product = new Product();
        product.setName("Red Shirt");
        product.setDescription("Red Hugo Boss shirt");
        product.setBrand("Hugo Boss");
        product.setTags(tags);
        product.setCategory("apparel");
        productRepository.save(product);

        mockMvc.perform(get("/v1/products/product")
                .param("category", "apparel")
                .param("page", "0")
                .param("maxEntries", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Red Shirt"));
    }
}