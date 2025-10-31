package com.example.ecommerce_example.controller;

import com.example.ecommerce_example.entity.Order;
import com.example.ecommerce_example.entity.Product;
import com.example.ecommerce_example.repository.OrderRepository;
import com.example.ecommerce_example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images";

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/product/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/product/save")
    public String saveProduct(
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            if (!imageFile.isEmpty()) {
                File uploadFolder = new File(UPLOAD_DIR);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs(); // create folder if it doesn't exist
                }

                // Generate unique filename
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

                // Save file
                Path filePath = Paths.get(uploadFolder.getAbsolutePath(), fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                product.setImageName(fileName);
            }
            productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/product/add?error=true";
        }

        return "redirect:/admin/product/add?success=true";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "add-product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/orders")
    public String viewAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin-orders";
    }
}
