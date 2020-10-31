package com.bridgelabz.iplanalyser;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

public class IPLAnalyserTest {
	private static final String IPL_RUN_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
	private static final String IPL_WICKET_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

	@Test
	public void givenIPLRunCSVFile_ShouldReturnCorrectRecords() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			int numOfRecords = iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			Assert.assertEquals(100, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnCorrectRecords() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			int numOfRecords = iplAnalyser.loadIPLRunsData(IPL_WICKET_CSV_FILE_PATH);
			Assert.assertEquals(100, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestAverageRun() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortIPLData = iplAnalyser.getPlayersWithTopAverages();
			IPLRuns[] iplRuns = new Gson().fromJson(sortIPLData, IPLRuns[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestStrikeRate() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortIPLData = iplAnalyser.getPlayersWithTopStrikeRate();
			IPLRuns[] iplRuns = new Gson().fromJson(sortIPLData, IPLRuns[].class);
			Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestBoundaries() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getPlayersWithTopBoundaries();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("Andre Russell", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}
}
