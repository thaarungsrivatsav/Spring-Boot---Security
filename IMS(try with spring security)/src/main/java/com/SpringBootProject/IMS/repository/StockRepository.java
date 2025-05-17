package com.SpringBootProject.IMS.repository;

import com.SpringBootProject.IMS.entity.StockTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StockRepository extends JpaRepository<StockTable , Long> {

        public Optional<StockTable> findByStockName(String stockName);

        public Optional<StockTable> findByStockId(Long stockId);

}
