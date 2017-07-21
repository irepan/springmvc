package com.alcatrazstudios.springmvc.controllers;

import com.alcatrazstudios.springmvc.domain.Product;
import com.alcatrazstudios.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by irepan on 10/07/17.
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "/product/list";
    }

    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "/product/show";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "/product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrDelete(Product product){
        Product savedProduct = productService.saveOrUpdate(product);
        return "redirect:/product/" + savedProduct.getId();
    }

    @RequestMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product" , productService.getById(id));
        return "/product/productform";
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/products";
    }
}
