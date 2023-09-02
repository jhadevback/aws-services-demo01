package com.joaquin.dao.impl;

import com.joaquin.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements IOrderDAO{

    private final Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Order getOrderById(String codOrder,int idCustomer) {

        String selectQuery = "SELECT * FROM tb_order WHERE cod_order = ? and id_customer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, codOrder);
            preparedStatement.setInt(2, idCustomer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setIdCustomer(resultSet.getInt("id_customer"));
                    order.setCodOrder(resultSet.getString("cod_order"));
                    order.setStateOrder(resultSet.getString("state_order"));
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void updateOrderStatus(Order order) {

        String updateQuery = "UPDATE tb_order SET state_order = ? WHERE cod_order = ? and id_customer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, order.getStateOrder());
            preparedStatement.setString(2, order.getCodOrder());
            preparedStatement.setInt(3, order.getIdCustomer());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated. - " + order.getCodOrder());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
