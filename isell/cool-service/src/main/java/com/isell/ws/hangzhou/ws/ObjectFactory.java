package com.isell.ws.hangzhou.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.isell.ws.hangzhou.ws package.
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

	private final static QName _CheckReceived_QNAME = new QName(
			"http://ws.newyork.zjport.gov.cn/", "checkReceived");
	private final static QName _CheckReceivedResponse_QNAME = new QName(
			"http://ws.newyork.zjport.gov.cn/", "checkReceivedResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.isell.ws.hangzhou.ws
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CheckReceivedResponse }
	 * 
	 */
	public CheckReceivedResponse createCheckReceivedResponse() {
		return new CheckReceivedResponse();
	}

	/**
	 * Create an instance of {@link CheckReceived }
	 * 
	 */
	public CheckReceived createCheckReceived() {
		return new CheckReceived();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckReceived }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.newyork.zjport.gov.cn/", name = "checkReceived")
	public JAXBElement<CheckReceived> createCheckReceived(CheckReceived value) {
		return new JAXBElement<CheckReceived>(_CheckReceived_QNAME,
				CheckReceived.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CheckReceivedResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://ws.newyork.zjport.gov.cn/", name = "checkReceivedResponse")
	public JAXBElement<CheckReceivedResponse> createCheckReceivedResponse(
			CheckReceivedResponse value) {
		return new JAXBElement<CheckReceivedResponse>(
				_CheckReceivedResponse_QNAME, CheckReceivedResponse.class,
				null, value);
	}

}
