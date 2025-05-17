package com.SpringBootProject.IMS.endpoint;

import com.SpringBootProject.IMS.entity.StockTable;
import com.SpringBootProject.IMS.service.StockService;
import com.SpringBootProject.IMS.valueobject.StockCreatePojo;
import com.SpringBootProject.IMS.valueobject.TemporaryStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//CRUD OPERATIONS FOR STOCKS

@RestController
@CrossOrigin(origins = "*")
public class StockEndPoint {

    //delete , create , update stocks are only accessed by ADMIN username and password only
    @Autowired
    StockService stockService;

    @PostMapping(value = "/stock/create")
    public StockTable stockCreation(@RequestBody StockCreatePojo stock)
    {

        return this.stockService.stockCreate(stock);
    }

   @GetMapping(value = "/search/stock/{stockName}")
    public String stockSearching(@PathVariable String stockName)
   {
        return this.stockService.stockSearch(stockName);
   }

   @PostMapping(value = "/update/stock")
    public StockTable stockUpdate(@RequestBody TemporaryStock temporaryStock)
   {
      return this.stockService.stockUpdate(temporaryStock);
   }

   @DeleteMapping(value = "stock/delete/{stockId}")
    public String stockDelete(@PathVariable Long stockId)
   {
       return this.stockService.stockDelete(stockId);
   }


   @GetMapping(value = "display/stock")
    public List<StockTable> stockDisplay()
   {
       return this.stockService.stockRead();
   }

   @PostMapping(value = "pagination/stock/{offset}/{pageSize}")
    public Page<StockTable> stocksPagination(@PathVariable int offset , @PathVariable int pageSize)
   {
    return this.stockService.stockPagination(offset, pageSize);
   }

    @PostMapping(value = "paginationAndSort/stock/{offset}/{pageSize}/{fieldName}")
    public Page<StockTable> stocksPaginationAndSort(@PathVariable int offset , @PathVariable int pageSize , @PathVariable String fieldName)
    {
        return this.stockService.stockPaginationAndSorting(offset, pageSize, fieldName);
    }
}
