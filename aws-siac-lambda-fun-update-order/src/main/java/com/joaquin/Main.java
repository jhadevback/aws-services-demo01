/*
package com.joaquin;

import com.joaquin.config.DatabaseConnectionManager;
import com.joaquin.dao.impl.OrderDAOImpl;
import com.joaquin.model.Order;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnectionManager.getConnection()) {

            System.out.println("Connected to the database!");
            OrderDAOImpl orderDAO = new OrderDAOImpl(connection);
            Order orderFound = orderDAO.getOrderById("ORDER00003",1);

            if (orderFound != null){
                orderFound.setStateOrder("IN_PROCESS");
                orderDAO.updateOrderStatus(orderFound);
                System.out.println(" UPDATE - " + orderFound.getCodOrder() + "- STATUS - " + orderFound.getStateOrder());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}*/
