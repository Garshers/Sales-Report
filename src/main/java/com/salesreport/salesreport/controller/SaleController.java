package com.salesreport.salesreport.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        model.addAttribute("saleStockList", fetchSaleStockData());
        model.addAttribute("salesPersonProfits", calculateSalesPersonProfits());
        model.addAttribute("saleList", mapSalesToDetails());
        return "sales";
    }

    private List<SaleStockDTO> fetchSaleStockData() {
        List<AccumulatedSale> accumulatedSales = accumulatedSaleService.getAllSales();
        List<Stock> stock = stockService.getAllStocks();
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
        return saleStockList;
    }

    private HashMap<String, BigDecimal> calculateSalesPersonProfits() {
        List<SalesPerson> salesPersons = salesPersonService.getAllSalesPersons();
        List<Sale> sales = saleService.getAllSales();
    
        return salesPersons.stream()
            .collect(Collectors.toMap(
                sp -> sp.getName() + " " + sp.getSurname(),    // Key: Full name as String
                sp -> sales.stream()
                    .filter(sale -> sale.getSalesPersonId() != null 
                                    && sale.getSalesPersonId().equals(sp.getSalesPersonId()) 
                                    && sale.getPrice() != null)
                    .map(Sale::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add), // Value: Total profit as BigDecimal
                    (existing, replacement) -> existing,       // Merge function for duplicate keys
                    HashMap<String, BigDecimal>::new
            ));
    }
    private List<HashMap<String, Object>> mapSalesToDetails() {
        List<Sale> sales = saleService.getAllSales();
        List<Stock> stock = stockService.getAllStocks();
        List<SalesPerson> salesPersons = salesPersonService.getAllSalesPersons();

        // List<SalePersonWithSaleDTO> salePersonsWithSaleDTOList = new ArrayList<>();
        List<HashMap<String, Object>> saleList = new ArrayList<>();

        for (Sale sale : sales) {
            // Find the corresponding salesperson based on salesPersonId
            SalesPerson correspondingSalesPerson = salesPersons.stream()
                    .filter(sp -> sp.getSalesPersonId().equals(sale.getSalesPersonId()))
                    .findFirst()
                    .orElse(null);

            // Find the corresponding stock based on productId
            Stock correspondingStock = stock.stream()
                    .filter(st -> st.getProductId().equals(sale.getProductId()))
                    .findFirst()
                    .orElse(null);

            // if (correspondingSalesPerson != null && correspondingStock != null) {
                Long saleId = sale.getSaleId(); 
                Long productId = sale.getProductId();  
                BigDecimal price = sale.getPrice(); 
                String productTypeName = correspondingStock.getProductType() + " " + correspondingStock.getProductName();
                Long salesPersonId = (correspondingSalesPerson != null) ? correspondingSalesPerson.getSalesPersonId() : -1L;
                String salesPersonNameSurname = (correspondingSalesPerson != null) ? 
                    correspondingSalesPerson.getName() + " " + correspondingSalesPerson.getSurname() : "N/A";

                HashMap<String, Object> saleDetails = new HashMap<>();
                saleDetails.put("saleId", saleId);
                saleDetails.put("productId", productId);
                saleDetails.put("price", price);
                saleDetails.put("productTypeName", productTypeName);
                saleDetails.put("salesPersonId", salesPersonId);
                saleDetails.put("salesPersonNameSurname", salesPersonNameSurname);
                saleList.add(saleDetails);
                
                /* 
                SalePersonWithSaleDTO salePersonWithSaleDTO = new SalePersonWithSaleDTO(
                        saleId, productId, price,
                        productTypeName,
                        salesPersonId, salesPersonNameSurname 
                );

                System.out.println("Check: " + salePersonWithSaleDTO);
                salePersonsWithSaleDTOList.add(salePersonWithSaleDTO);
                */
            //}
        }
        return saleList;
        // -------------------------------ERROR---------------------------------------------------
        // Error encountered during the transfer of "salePersonsWithSaleDTOList". 
        // It is not being read correctly, and the root cause of the issue could not be identified.
        // System.out.println("SalePersonsWithSaleDTOList: " + salePersonsWithSaleDTOList);
        // model.addAttribute("salePersonsWithSaleDTOList", salePersonsWithSaleDTOList);
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
