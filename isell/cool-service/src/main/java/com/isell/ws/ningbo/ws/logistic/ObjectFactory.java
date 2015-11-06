
package com.isell.ws.ningbo.ws.logistic;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.isell.ws.ningbo.ws.logistic package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.isell.ws.ningbo.ws.logistic
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLogisticsInfo }
     * 
     */
    public GetLogisticsInfo createGetLogisticsInfo() {
        return new GetLogisticsInfo();
    }

    /**
     * Create an instance of {@link GetLogisticsInfoResponse }
     * 
     */
    public GetLogisticsInfoResponse createGetLogisticsInfoResponse() {
        return new GetLogisticsInfoResponse();
    }

    /**
     * Create an instance of {@link GetKJB2CLogisticsInfo }
     * 
     */
    public GetKJB2CLogisticsInfo createGetKJB2CLogisticsInfo() {
        return new GetKJB2CLogisticsInfo();
    }

    /**
     * Create an instance of {@link GetKJB2CLogisticsInfoResponse }
     * 
     */
    public GetKJB2CLogisticsInfoResponse createGetKJB2CLogisticsInfoResponse() {
        return new GetKJB2CLogisticsInfoResponse();
    }

    /**
     * Create an instance of {@link GetLogisticsMain }
     * 
     */
    public GetLogisticsMain createGetLogisticsMain() {
        return new GetLogisticsMain();
    }

    /**
     * Create an instance of {@link GetLogisticsMainResponse }
     * 
     */
    public GetLogisticsMainResponse createGetLogisticsMainResponse() {
        return new GetLogisticsMainResponse();
    }

}
