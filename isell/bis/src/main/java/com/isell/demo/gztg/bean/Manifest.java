package com.isell.demo.gztg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Manifest")
public class Manifest {
	
	@XmlElement(name="Head")
	private Head head;
	
	@XmlElement(name="Declaration")
	private Declaration declaration;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}


	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}
	
	
}
