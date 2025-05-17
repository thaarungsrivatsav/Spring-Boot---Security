package com.SpringBootProject.IMS.service;

import com.SpringBootProject.IMS.entity.StockTable;
import com.SpringBootProject.IMS.repository.StockRepository;
import com.SpringBootProject.IMS.valueobject.StockCreatePojo;
import com.SpringBootProject.IMS.valueobject.TemporaryStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    public StockTable stockCreate(StockCreatePojo stock)
    {
        StockTable stockTable = new StockTable();
        stockTable.setStock_quantity(stock.getStockQuantity());
        stockTable.setStockName(stock.getStockName());
        stockTable.setStock_price_per_unit(stock.getPricePerUnit());
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter datetimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = datetime.format(datetimeFormatPattern);
        String modifyTime = createTime;
        stockTable.setCreatedAt(createTime);
        stockTable.setModifiedAt(modifyTime);
        stockRepository.save(stockTable);
        return stockTable;
    }
    public String stockSearch(String stockName)
    {
        Optional<StockTable> optionalStockTable = stockRepository.findByStockName(stockName);
        if(optionalStockTable.isPresent())
        {
            return "Yes , Stock is Present";
        }
        else
        {
            return "No such Stock is Present in DB";
        }
    }

    public StockTable stockUpdate(TemporaryStock temporaryStock)
    {
       Optional<StockTable> optionalStockTable = stockRepository.findByStockId(temporaryStock.getStockId());
       if(optionalStockTable.isPresent())
       {
            StockTable stockTable = optionalStockTable.get();
            stockTable.setStockName(temporaryStock.getStockName());
            stockTable.setStock_quantity(temporaryStock.getStockQuantity());
            stockTable.setStock_price_per_unit(temporaryStock.getPricePerUnit());
           LocalDateTime datetime = LocalDateTime.now();
           DateTimeFormatter datetimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
           String modifyTime = datetime.format(datetimeFormatPattern);
            stockTable.setModifiedAt(modifyTime);
            stockRepository.save(stockTable);
            return stockTable;
       }
       return null;

    }

    public String stockDelete(Long stockId) {
      Optional<StockTable> optionalStockTable =  stockRepository.findByStockId(stockId);
        if(optionalStockTable.isPresent())
        {
            stockRepository.deleteById(stockId);
            return "Yes , Stock is Deleted";
        }
        else
        {
            return "No such Stock is Present in DB";
        }

    }
    public List<StockTable> stockRead()
    {
        List<StockTable> stockTables = new ArrayList<>();
        stockRepository.findAll().forEach(singleStock ->{
            stockTables.add(singleStock);
        });
        return stockTables;
    }

    public Page<StockTable> stockPagination(int offset , int pageSize)
    {
       Page<StockTable> stockTablePages = stockRepository.findAll(PageRequest.of(offset,pageSize));
       return stockTablePages;

    }

    public Page<StockTable> stockPaginationAndSorting(int offset , int pageSize , String fieldName)
    {
        Page<StockTable> stockTablePages = stockRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(fieldName)));
        return stockTablePages;
    }

}


