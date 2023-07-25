package com.parabank.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.parabank.keyword.engine.KeywordDriven;

public class LoginTest {
	
	public KeywordDriven keyWordDriven;
	
	@Test
	public void loginTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		keyWordDriven = new KeywordDriven();
		Thread.sleep(3000);
		keyWordDriven.startExecution("Sheet1");
		Thread.sleep(3000);
	}

}
