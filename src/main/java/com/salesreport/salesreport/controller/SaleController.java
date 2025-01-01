package com.salesreport.salesreport.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.model.Stock;
import com.salesreport.salesreport.service.SaleService;
import com.salesreport.salesreport.service.StockService;

import jakarta.annotation.PostConstruct;

// The @Controller annotation indicates that this class is a Spring MVC controller,
// which will handle HTTP requests and serve as the controller in the MVC architecture.
@Controller
public class SaleController {
    
    // The @Autowired annotation automatically injects the SaleService into this controller.
    // This allows the controller to access the business logic methods of SaleService (e.g., fetching all sales).
    @Autowired
    private SaleService saleService;
    @Autowired
    private StockService stockService;

    @PostConstruct
    public void init() {
        System.out.println("SaleController has been initialized");  // For debbugging sake
    }

    // The @GetMapping annotation maps HTTP GET requests to the specified URL ("/sales") to this method.
    // When a GET request is made to the "/sales" URL, this method will be invoked.
    @GetMapping("/sales")
    public String getSalesReport(Model model) {
        // Calling the SaleService to get a list of all sales from the database.
        // SaleService handles the business logic and fetches the data from the repository.
        List<Sale> sales = saleService.getAllSales();
        
        // Adding the sales data to the Model object with the attribute name "sales".
        // This allows the sales data to be available for rendering in the view (e.g., Thymeleaf template).
        model.addAttribute("sales", sales);
        
        // Returning the name of the view template (in this case, "sales").
        // Thymeleaf will use this to locate the corresponding HTML file (e.g., sales.html) and render it.
        return "sales";  // The "sales" view will display the list of sales.
    }

    @GetMapping("/charts")
    public String getSalesCharts(Model model) {
        System.out.println("Inside getSalesCharts method");
        // Assuming the service can provide chart data (e.g., sales data for visualization)
        List<Sale> sales = saleService.getAllSales();
        List<Stock> stock = stockService.getAllStocks();

        // Preparing data for the chart
        List<String> productNames = new ArrayList<>();
        List<BigDecimal> totalProfit = new ArrayList<>();

        // Aggregating data for the chart
        for (Sale sale : sales) {
            // Find corresponding product from stock by productId
            Stock correspondingStock = stock.stream()
                .filter(s -> s.getProductId().equals(sale.getProductId()))
                .findFirst()
                .orElse(null);  // Handle case where productId from sale does not match any in stock

            if (correspondingStock != null) {
                productNames.add(correspondingStock.getProductType() + " " + correspondingStock.getProductName());  // Add product name from stock
                totalProfit.add(sale.getTotalProfit());  // Add total profit from sale
            }
        }
        System.out.println("--------------------------- After for loop ---------------------------------");

        // Create ChartData object
        ChartData chartData = new ChartData(productNames, totalProfit);

        // Print chart data to console (this will now use the toString method)
        System.out.println(chartData);  // This will call chartData.toString() and print the data

        // Adding chart data to the model
        model.addAttribute("chartData", chartData);

        // Returning the view name "charts" for rendering the chart page
        return "charts";  // The "charts" view will display the sales charts.
    }
}
