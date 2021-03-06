package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:global_config_app.properties")
@RequestMapping("product/")
public class ProductController {

    @Autowired
    Environment env;

    //ProductServiceImpl productService = new ProductServiceImpl();
    @Autowired
    IProductService productService;

    @RequestMapping(value = "list*",method = RequestMethod.GET)
    public ModelAndView listProducts(){

        List<Product> listProducts = productService.findAllHaveBusiness();

        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product",listProducts);

        return modelAndView;
    }

    @RequestMapping(value = "product-detail/{id}", method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/detail");
        modelAndView.addObject("product", product);
        return  modelAndView;
    }

    @RequestMapping(value = "search")
    public ModelAndView searchProduct(@RequestParam("txtSearch") String search){
        Product product = productService.findByName(search);
        ModelAndView modelAndView = new ModelAndView("/product/search");
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @RequestMapping(value = "create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productform", new ProductForm());
        return modelAndView;
    }

    @RequestMapping("save-product")
    public ModelAndView saveProduct(@ModelAttribute("productform") ProductForm productform, BindingResult result){

        // thong bao neu xay ra loi
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        // lay ten file
        MultipartFile multipartFile = productform.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(productform.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // tao doi tuong de luu vao db
        Product productObject = new Product(10,productform.getName(), productform.getPrice(), fileName);

        //add to database
        productService.save(productObject);

        List<Product> productList = productService.findAllHaveBusiness();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product", productList);
        return modelAndView;
    }

    @RequestMapping(value = "delete-product/{name}")
    public ModelAndView deleteProduct(@PathVariable String name){
        Product product = productService.findByName(name);
        productService.delete(product);
        List<Product> productList = productService.findAllHaveBusiness();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product",productList);
        return modelAndView;
    }

    @RequestMapping(value = "edit-product/{id}")
    public ModelAndView editProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product",product);
        return modelAndView;
    }



}