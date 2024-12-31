package com.salesreport.salesreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.service.SaleService;

// The @Controller annotation tells Spring that this class will handle HTTP requests and serve as the controller in the MVC architecture.
@Controller
public class SaleController {
    
    // The @Autowired annotation automatically injects the SaleService into this controller.
    // This allows the controller to access business logic methods from SaleService (such as fetching all sales).
    @Autowired
    private SaleService saleService;

    // The @GetMapping annotation maps HTTP GET requests to the specified URL ("/sales") to this method.
    // When a GET request is made to the "/sales" URL, this method will be invoked.
    @GetMapping("/sales")
    public String getSalesReport(Model model) {
        // Calling the SaleService to get a list of all sales from the database.
        // The SaleService handles the business logic and communicates with the repository to fetch data.
        List<Sale> sales = saleService.getAllSales();
        
        // Adding the sales data to the Model object with the attribute name "sales".
        // This makes the sales data available in the view for rendering (such as in a Thymeleaf template).
        model.addAttribute("sales", sales);
        
        // Returning the name of the view template (in this case, "sales").
        // Thymeleaf will use this name to locate the appropriate HTML file (e.g., sales.html) and render it.
        return "sales";  // The "sales" view will display the list of sales.
    }
}