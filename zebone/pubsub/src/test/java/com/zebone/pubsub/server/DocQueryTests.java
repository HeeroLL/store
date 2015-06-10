package com.zebone.pubsub.server;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.webservice.cxf.log.DocQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext.xml")
public class DocQueryTests {
    
	@Resource
	private DocQuery docQuery;
	
	@Test
	public void testQueryDoc(){
		String para1 ="<param><flag>1</flag><code>jzxx122408</code><empiId>49f5591972a34766b1cd4e3e2734b0c1</empiId></param>";
		String para2 = "<param><flag>1</flag><code>yxjc12260101</code><empiId>49f5591972a34766b1cd4e3e2734b0c1</empiId></param>";
		String para3 = "<param><flag>1</flag><code>jzxx122407</code><empiId>49f5591972a34766b1cd4e3e2734b0c1</empiId></param>";
		String para4 = "<param><flag>1</flag><code>ybsys12260101</code><empiId>49f5591972a34766b1cd4e3e2734b0c1</empiId></param>";
		String resultXml1 = docQuery.DocumentRepository_RetrieveDocumentSet(para1);
		String resultXml2 = docQuery.DocumentRepository_RetrieveDocumentSet(para2);
		String resultXml3 = docQuery.DocumentRepository_RetrieveDocumentSet(para3);
		String resultXml4 = docQuery.DocumentRepository_RetrieveDocumentSet(para4);

		System.out.println(resultXml1);
		System.out.println(resultXml2);
		System.out.println(resultXml3);
		System.out.println(resultXml4);
		
		String para5 ="<param><flag>1</flag><code>zhaocb2014jiayouye</code><empiId>3ecd6e0a888e4ce3a14ac3f5a587389e</empiId></param>";
		String r5 = docQuery.DocumentRepository_RetrieveDocumentSet(para5);
		System.out.println(r5);
	}
}
