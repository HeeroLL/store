/**
 * ProductDeatilType.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.isell.ws.youxinda.order;

public class ProductDeatilType implements java.io.Serializable {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7650162960979334621L;
    
    private java.lang.String productSku;
    
    private float[] transactionPrice;
    
    private float[] dealPrice;
    
    private int opQuantity;
    
    public ProductDeatilType() {
    }
    
    public ProductDeatilType(java.lang.String productSku, float[] transactionPrice, float[] dealPrice, int opQuantity) {
        this.productSku = productSku;
        this.transactionPrice = transactionPrice;
        this.dealPrice = dealPrice;
        this.opQuantity = opQuantity;
    }
    
    /**
     * Gets the productSku value for this ProductDeatilType.
     * 
     * @return productSku
     */
    public java.lang.String getProductSku() {
        return productSku;
    }
    
    /**
     * Sets the productSku value for this ProductDeatilType.
     * 
     * @param productSku
     */
    public void setProductSku(java.lang.String productSku) {
        this.productSku = productSku;
    }
    
    /**
     * Gets the transactionPrice value for this ProductDeatilType.
     * 
     * @return transactionPrice
     */
    public float[] getTransactionPrice() {
        return transactionPrice;
    }
    
    /**
     * Sets the transactionPrice value for this ProductDeatilType.
     * 
     * @param transactionPrice
     */
    public void setTransactionPrice(float[] transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
    
    public float getTransactionPrice(int i) {
        return this.transactionPrice[i];
    }
    
    public void setTransactionPrice(int i, float _value) {
        this.transactionPrice[i] = _value;
    }
    
    /**
     * Gets the dealPrice value for this ProductDeatilType.
     * 
     * @return dealPrice
     */
    public float[] getDealPrice() {
        return dealPrice;
    }
    
    /**
     * Sets the dealPrice value for this ProductDeatilType.
     * 
     * @param dealPrice
     */
    public void setDealPrice(float[] dealPrice) {
        this.dealPrice = dealPrice;
    }
    
    public float getDealPrice(int i) {
        return this.dealPrice[i];
    }
    
    public void setDealPrice(int i, float _value) {
        this.dealPrice[i] = _value;
    }
    
    /**
     * Gets the opQuantity value for this ProductDeatilType.
     * 
     * @return opQuantity
     */
    public int getOpQuantity() {
        return opQuantity;
    }
    
    /**
     * Sets the opQuantity value for this ProductDeatilType.
     * 
     * @param opQuantity
     */
    public void setOpQuantity(int opQuantity) {
        this.opQuantity = opQuantity;
    }
    
    private java.lang.Object __equalsCalc = null;
    
    public synchronized boolean equals(java.lang.Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ProductDeatilType))
            return false;
        ProductDeatilType other = (ProductDeatilType)obj;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =
            true
                && ((this.productSku == null && other.getProductSku() == null) || (this.productSku != null && this.productSku.equals(other.getProductSku())))
                && ((this.transactionPrice == null && other.getTransactionPrice() == null) || (this.transactionPrice != null && java.util.Arrays.equals(this.transactionPrice,
                    other.getTransactionPrice())))
                && ((this.dealPrice == null && other.getDealPrice() == null) || (this.dealPrice != null && java.util.Arrays.equals(this.dealPrice,
                    other.getDealPrice()))) && this.opQuantity == other.getOpQuantity();
        __equalsCalc = null;
        return _equals;
    }
    
    private boolean __hashCodeCalc = false;
    
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getProductSku() != null) {
            _hashCode += getProductSku().hashCode();
        }
        if (getTransactionPrice() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getTransactionPrice()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransactionPrice(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDealPrice() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getDealPrice()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDealPrice(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getOpQuantity();
        __hashCodeCalc = false;
        return _hashCode;
    }
    
}
