package com.joaquin.dao.impl;

import com.joaquin.model.Order;

import java.util.List;

public interface IOrderDAO {
    Order getOrderById(String codOrder,int idCustomer);
    List<Order> getAllOrders();
    void updateOrderStatus(Order order);
}
