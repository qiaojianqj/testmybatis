package com.test.mbg;


import com.test.mbg.util.MyBatisGeneratorTool;
import org.junit.Test;


public class GeneratorMainTest {
	@Test
	public void testMBGGenerator() {
		MyBatisGeneratorTool.generate("src/test/resources/generatorConfig.xml");
	}
}
