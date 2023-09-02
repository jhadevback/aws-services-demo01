package com.joaquin;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.joaquin.config.DatabaseConnectionManager;
import com.joaquin.dao.impl.OrderDAOImpl;
import com.joaquin.model.Order;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.sql.Connection;

//com.joaquin.OrderHandler

public class OrderHandler implements RequestHandler<Order, String> {

    private final String queueUrl = "aws-sqs-update-order";
    private final SqsClient sqsClient;

    public OrderHandler() {
        this.sqsClient = SqsClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Override
    public String handleRequest(Order o, Context context) {

        try {

            Connection connection = DatabaseConnectionManager.getConnection();
            System.out.println("Connected to the database!");
            OrderDAOImpl orderDAO = new OrderDAOImpl(connection);
            Order orderFound = orderDAO.getOrderById(o.getCodOrder(),o.getIdCustomer());

            if (orderFound != null){
                orderFound.setStateOrder(o.getStateOrder());
                orderDAO.updateOrderStatus(orderFound);
                System.out.println(" UPDATE - " + orderFound.getCodOrder() + "- STATUS - " + orderFound.getStateOrder());
            }

            //DatabaseConnectionManager.closeConnection(connection);

            if (o.getStateOrder().equals("REFUSED")){
                System.out.println("SEND MESSAGE TO SQS");
                String jsonOrder = convertOrderToJson(o);
                SendMessageResponse response = sqsClient.sendMessage(SendMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .messageBody(jsonOrder)
                        .build());
                System.out.println("FINISH SEND MESSAGE TO SQS - " + response.messageId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return o.toString();
    }

    private String convertOrderToJson(Order order) {
        Gson gson = new Gson();
        return gson.toJson(order);
    }

}
