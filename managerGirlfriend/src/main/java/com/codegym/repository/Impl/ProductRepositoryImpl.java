package com.codegym.repository.Impl;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {
    List<Product> productList = new ArrayList<>();
    {
        productList.add(new Product(1,"demo",1000,"img1"));
        productList.add(new Product(2,"demo2",1100,"img2"));
    }

    @Override
    public List<Product> findAll(){
        return productList;
    }

    @Override
    public Product findByName(String name) {
        for (int i = 0; i <productList.size() ; i++) {
            if(productList.get(i).getName().equals(name)) {
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

    @Override
    public void edit(int id, String name, float price, String image) {
        for (int i = 0; i <productList.size() ; i++) {
            if(productList.get(i).getId()==id){
                productList.get(i).setId(id);
                productList.get(i).setName(name);
                productList.get(i).setPrice(price);
                productList.get(i).setImage(image);
            }
        }
    }
}
