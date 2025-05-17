package com.SpringBootProject.IMS.service;




import com.SpringBootProject.IMS.entity.OrdersTable;
import com.SpringBootProject.IMS.entity.StockTable;
import com.SpringBootProject.IMS.entity.UserProfileTable;
import com.SpringBootProject.IMS.repository.OrderRepository;
import com.SpringBootProject.IMS.repository.StockRepository;
import com.SpringBootProject.IMS.repository.UserRepository;

import com.SpringBootProject.IMS.valueobject.DisplayOrdersWithListPaginationConcept;
import com.SpringBootProject.IMS.valueobject.OrderCreatePojo;
import com.SpringBootProject.IMS.valueobject.OrdersResponseToStoreOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService2 implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String userEmail;

    public Long roleId;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//        username.concat("@ims.com");
        String u = username;
        if(!(u.contains("@ims.com")))
        {
             u = username+"@ims.com";
        }
        final UserProfileTable userProfileTable = this.userRepository.findByUserEmail(u);
        userEmail = u;
        roleId = userProfileTable.getRoleTable().getRoleId();

        if (userProfileTable == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        final GrantedAuthority[] grantedAuthorities = {new SimpleGrantedAuthority(userProfileTable.getRoleTable().getRoleName())};
        return new User(userProfileTable.getUserName(), userProfileTable.getUserPassword(), Arrays.asList(grantedAuthorities));
    }
    public String createOrder(OrderCreatePojo orderCreatePojo)
    {
        //UserProfileTable userProfileTable = userRepository.findByUserId(orderCreatePojo.getUserId());
        UserProfileTable userProfileTable = this.userRepository.findByUserEmail(userEmail);
        Optional<StockTable> optionalStockTable = stockRepository.findByStockId(orderCreatePojo.getStockId());
        double totalPrice;
        if(optionalStockTable.isPresent())
        {
            StockTable stockTable = optionalStockTable.get();
            totalPrice = orderCreatePojo.getStockQuantity() * stockTable.getStock_price_per_unit();
            OrdersTable ordersTable = new OrdersTable();

            Random r = new Random();
            int id = r.nextInt(100);

            ordersTable.setOrderId(Long.valueOf(id));
            ordersTable.setOrderedAt(LocalDateTime.now());
            ordersTable.setStockQuantity(orderCreatePojo.getStockQuantity());
            ordersTable.setOrderStatus("Active");
            ordersTable.setTotalPrice(totalPrice);
            ordersTable.setStockTable(stockTable);
            ordersTable.setUserProfileTable(userProfileTable);

            orderRepository.save(ordersTable);
            //this part of the updates the existing quantity in the stock table and minus the existing stock quantity with the user requested stock quantity and then updates the table
            int updateStockQuantityInStockTable = stockTable.getStock_quantity() - ordersTable.getStockQuantity();
            stockTable.setStock_quantity(updateStockQuantityInStockTable);
            stockRepository.save(stockTable);
            //stock table quantity is updated in the above last line , when the order is place successfully .


           // return "Order Created Successfully";
            //first we returned only this string , later in angular side , required the order id to do the payment process
            //, so im jus passing the order id as string to the angular page

            return String.valueOf(id);
        }

        return "Order Not Created";
    }

    public List<OrdersTable> displayOrders()
    {
        List<OrdersTable> ordersTables = orderRepository.findAll();
        return ordersTables;
    }

//   public List<OrdersTable> displayOrdersWithUserId(Long userId)
//   {
//       List<OrdersTable> ordersTables = orderRepository.findByUserProfileTableUserId(userId);
//       return ordersTables;
//   }

    public DisplayOrdersWithListPaginationConcept displayOrderOfUserId(Long userId , int offset , int pageSize)
    {
        Page<OrdersTable> ordersTablePage = orderRepository.findByUserProfileTableUserId(userId,PageRequest.of(offset , pageSize));
        DisplayOrdersWithListPaginationConcept displayOrdersWithListPaginationConcept = new DisplayOrdersWithListPaginationConcept();
        OrdersResponseToStoreOrders ordersResponse = new OrdersResponseToStoreOrders();
        List<OrdersResponseToStoreOrders> ordersResponseList = new ArrayList<>();
        ordersTablePage.forEach(order -> {
            ordersResponse.setOrderId(order.getOrderId());
            ordersResponse.setUserId(order.getUserProfileTable().getUserId());
            ordersResponse.setQuantity(order.getStockQuantity());
            ordersResponse.setStockId(order.getStockTable().getStockId());
            ordersResponse.setTotalPrice(order.getTotalPrice());
            ordersResponse.setStatus(order.getOrderStatus());
            ordersResponseList.add(ordersResponse);
        });

        displayOrdersWithListPaginationConcept.setOrderResponseList(ordersResponseList);
        displayOrdersWithListPaginationConcept.setOffset(offset);
        displayOrdersWithListPaginationConcept.setPageSize(pageSize);
        displayOrdersWithListPaginationConcept.setTotalElements(ordersTablePage.getTotalElements());
        displayOrdersWithListPaginationConcept.setTotalPages(ordersTablePage.getTotalPages());

        return displayOrdersWithListPaginationConcept;


    }
}
