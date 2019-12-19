package com.codegym.repository.Impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {
    List<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1, "Samsung", 300,"img1"));
        productList.add(new Product(2, "Iphone", 500, "img2"));
        productList.add(new Product(3, "Nokia", 700,"img3"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public Product findByName(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(name)) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public void delete(Product product) {
        productList.remove(product);
    }

}
