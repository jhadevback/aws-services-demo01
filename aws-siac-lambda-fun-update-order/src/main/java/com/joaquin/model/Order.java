package com.joaquin.model;

public class Order {
    private Integer id;
    private String codOrder;
    private Integer idCustomer;
    private String stateOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodOrder() {
        return codOrder;
    }

    public void setCodOrder(String codOrder) {
        this.codOrder = codOrder;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(String stateOrder) {
        this.stateOrder = stateOrder;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", codOrder=" + codOrder + ", idCustomer=" + idCustomer + ", stateOrder=" + stateOrder
                + "]";
    }
}
