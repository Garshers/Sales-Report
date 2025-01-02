package com.salesreport.salesreport.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesreport.salesreport.dto.SaleStockDTO;
import com.salesreport.salesreport.model.AccumulatedSale;
import com.salesreport.salesreport.model.Sale;
import com.salesreport.salesreport.model.SalesPerson;
import com.salesreport.salesreport.model.Stock;
import com.salesreport.salesreport.service.AccumulatedSaleService;
import com.salesreport.salesreport.service.SaleService;
import com.salesreport.salesreport.service.SalesPersonService;
import com.salesreport.salesreport.service.StockService;

import jakarta.annotation.PostConstruct;

// The @Controller annotation indicates that this class is a Spring MVC controller,
// which will handle HTTP requests and serve as the controller in the MVC architecture.
@Controller
public class SaleController {
    
    // The @Autowired annotation automatically injects the SaleService into this controller.
    // This allows the controller to access the business logic methods of SaleService (e.g., fetching all sales).
    @Autowired
    private AccumulatedSaleService accumulatedSaleService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private StockService stockService;
    @Autowired
    private SalesPersonService salesPersonService;

    @PostConstruct
    public void init() {
        System.out.println("SaleController has been initialized");  // For debbugging sake
    }

    // The @GetMapping annotation maps HTTP GET requests to the specified URL ("/sales") to this method.
    // When a GET request is made to the "/sales" URL, this method will be invoked.
    @GetMapping("/sales")
    public String getSalesReport(Model model) {
        // Fetch sales data and stock data
        List<AccumulatedSale> accumulatedSales = accumulatedSaleService.getAllSales();
        List<Stock> stock = stockService.getAllStocks();

        // List to hold combined data
        List<SaleStockDTO> saleStockList = new ArrayList<>();

        // Combine data from Sale and Stock based on productId
        for (AccumulatedSale AccSale : accumulatedSales) {
            // Find the corresponding product in stock based on productId
            Stock correspondingStock = stock.stream()
                    .filter(s -> s.getProductId().equals(AccSale.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (correspondingStock != null) {
                // Create a DTO with combined data
                SaleStockDTO saleStockDTO = new SaleStockDTO(
                        correspondingStock.getProductType() + " " + correspondingStock.getProductName(), 
                        AccSale.getTotalQuantitySold(), 
                        AccSale.getTotalProfit(), 
                        correspondingStock.getProductId()
                );
                saleStockList.add(saleStockDTO);
            }
        }
        
        // Add the combined data to the model
        model.addAttribute("saleStockList", saleStockList);

        //------------------------------------------------------------
        
        List<Sale> sales = saleService.getAllSales();
        List<SalesPerson> salesPersons = salesPersonService.getAllSalesPersons();
        
        System.out.println("Sales size: " + sales.size());
        System.out.println("SalesPersons size: " + salesPersons.size());

        // HashMap to store total profit for each salesperson
        HashMap<String, BigDecimal> salesPersonProfits = new HashMap<>();

        for (SalesPerson salesPerson : salesPersons) {
            BigDecimal totalProfit = BigDecimal.ZERO;
            
            // Iterate over each sale and match it with the current salesperson
            for (Sale sale : sales) {
                if (sale.getSalesPersonId() != null && sale.getSalesPersonId().equals(salesPerson.getSalesPersonId())) {
                    System.out.println("Sale for " + salesPerson.getSalesPersonName() + ": " + sale.getPrice()); // Debug log
                    if (sale.getPrice() != null) {
                        totalProfit = totalProfit.add(sale.getPrice());
                    }
                }
            }
            System.out.println("Total profit for " + salesPerson.getSalesPersonName() + ": " + totalProfit); // Debug log
            salesPersonProfits.put(salesPerson.getSalesPersonName() + " " + salesPerson.getSalesPersonSurname(), totalProfit);
        }

        // Add the combined data to the model
        model.addAttribute("salesPersonProfits", salesPersonProfits);

        // Return the view name that will display the combined data
        return "sales";  // The "sales" view will display the combined data
    }

    @GetMapping("/charts")
    public String getSalesCharts(Model model) {
        // Assuming the service can provide chart data (e.g., sales data for visualization)
        List<AccumulatedSale> accSales = accumulatedSaleService.getAllSales();
        List<Stock> stock = stockService.getAllStocks();

        // Preparing data for the chart
        List<String> productNames = new ArrayList<>();
        List<BigDecimal> totalProfit = new ArrayList<>();

        // Aggregating data for the chart
        for (AccumulatedSale sale : accSales) {
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

        // Create ChartData object with declaration of data type for second variable
        ChartData<BigDecimal> chartData = new ChartData<>(productNames, totalProfit);

        // Adding chart data to the model
        model.addAttribute("chartData", chartData);

        // Returning the view name "charts" for rendering the chart page
        return "charts";  // The "charts" view will display the sales charts.
    }
}
