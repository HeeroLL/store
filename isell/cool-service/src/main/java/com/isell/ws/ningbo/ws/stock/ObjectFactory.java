package com.isell.ws.ningbo.ws.stock;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.isell.ws.ningbo.ws.stock package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.isell.ws.ningbo.ws.stock
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetStock }
	 * 
	 */
	public GetStock createGetStock() {
		return new GetStock();
	}

	/**
	 * Create an instance of {@link GetAllStock }
	 * 
	 */
	public GetAllStock createGetAllStock() {
		return new GetAllStock();
	}

	/**
	 * Create an instance of {@link GetStockResponse }
	 * 
	 */
	public GetStockResponse createGetStockResponse() {
		return new GetStockResponse();
	}

	/**
	 * Create an instance of {@link GetAllStockResponse }
	 * 
	 */
	public GetAllStockResponse createGetAllStockResponse() {
		return new GetAllStockResponse();
	}

}
