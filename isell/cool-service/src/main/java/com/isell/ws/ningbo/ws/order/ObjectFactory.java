
package com.isell.ws.ningbo.ws.order;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.isell.ws.ningbo.ws.order package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.isell.ws.ningbo.ws.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestHelloWorld }
     * 
     */
    public TestHelloWorld createTestHelloWorld() {
        return new TestHelloWorld();
    }

    /**
     * Create an instance of {@link TestHelloWorldResponse }
     * 
     */
    public TestHelloWorldResponse createTestHelloWorldResponse() {
        return new TestHelloWorldResponse();
    }

    /**
     * Create an instance of {@link AddOrderKJB2C }
     * 
     */
    public AddOrderKJB2C createAddOrderKJB2C() {
        return new AddOrderKJB2C();
    }

    /**
     * Create an instance of {@link AddOrderKJB2CResponse }
     * 
     */
    public AddOrderKJB2CResponse createAddOrderKJB2CResponse() {
        return new AddOrderKJB2CResponse();
    }

}
