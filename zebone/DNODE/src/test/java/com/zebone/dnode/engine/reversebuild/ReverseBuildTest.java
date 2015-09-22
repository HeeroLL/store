package com.zebone.dnode.engine.reversebuild;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.dnode.engine.reversebuild.service.ReverseBuild;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/zebone/dnode/engine/reversebuild/config/ApplicationContext.xml"})
public class ReverseBuildTest {

	@Resource
	private ReverseBuild reverseBuild;
	
	@Test
	public void storeTest(){
		String data = "{\"data\":[{\"TABLENAME\":\"GXYHZDJBKB\",\"DOC_NO\":\"57a5f3ec0b5411e586d27357b9f805de\"}]}";
		reverseBuild.build(data);
	}

}
