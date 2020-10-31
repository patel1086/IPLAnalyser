package com.bridgelabz.iplanalyser;

import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
	private static final String IPL_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_WICKET_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

	@Test
	public void givenIPLRunCSVFile_ShouldReturnCorrectRecords() {
		try {
			IPLAnalyser censusAnalyser = new IPLAnalyser();
			int numOfRecords = censusAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			Assert.assertEquals(100, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnCorrectRecords() {
		try {
			IPLAnalyser censusAnalyser = new IPLAnalyser();
			int numOfRecords = censusAnalyser.loadIPLRunsData(IPL_WICKET_CSV_FILE_PATH);
			Assert.assertEquals(100, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

}
