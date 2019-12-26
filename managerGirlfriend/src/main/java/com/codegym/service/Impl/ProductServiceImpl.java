package com.codegym.service.Impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAllHaveBusiness() {
        return productRepository.findAll();
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);

    }

    @Override
    public void edit(int id, String name, float price, String image) {
        productRepository.edit(id,name,price,image);
    }

}
