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
			Assert.assertEquals(101, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnCorrectRecords() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			int numOfRecords = iplAnalyser.loadIPLRunsData(IPL_WICKET_CSV_FILE_PATH);
			Assert.assertEquals(99, numOfRecords);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestAverageRun() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortIPLData = iplAnalyser.getBatsmanWithTopAverages();
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
			String sortIPLData = iplAnalyser.getBatsmanWithTopStrikeRate();
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
			String sortedIPLData = iplAnalyser.getBatsmanWithTopBoundaries();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("Andre Russell", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestStrikeRateAndBoundaries() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBatsmansWithTopStrikeRateandBoundaryBoth();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("Andre Russell", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestStrikeRateAndAverage() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBatsmanWithTopStrikeRateandAverage();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("MS Dhoni", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestRunsAndAvearge() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBatsmanWithHighestRunsAndAverage();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("David Warner", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnBowler_WithHighestAvearge() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBowlersWithTopBowlingAverages();
			IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
			Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnBowler_WithToptStrikeRate() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBowlersWithTopBowlingStrikeRate();
			IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
			Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnBowler_WithToptEconomyRate() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBowlersWithTopBowlingEconomyRate();
			IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
			Assert.assertEquals("Ben Cutting", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnBowler_WithToptStrikeRateAndMost5Wor4W() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBowlersWithBestStrikeRateWith5Wor4W();
			IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
			Assert.assertEquals("Krishnappa Gowtham", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLWicketsCSVFile_ShouldReturnBowler_WithHighestWktsAndBestAvg() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBowlersWithHighestWktsandBestAvg();
			IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
			Assert.assertEquals("Imran Tahir", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunAndWicketsCSVFile_ShouldReturnPlayer_WithBestBowlingAndBattingAvg() throws IPLException {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedBat = iplAnalyser.getBatsmanWithTopAverages();
			IPLRuns[] average = new Gson().fromJson(sortedBat, IPLRuns[].class);
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sorted = iplAnalyser.getBowlersWithTopBowlingAverages();
			IPLWickets[] wickets = new Gson().fromJson(sorted, IPLWickets[].class);
			String bestAvg = iplAnalyser.getPlayersWithBestAvgInBoth(average, wickets);
			Assert.assertEquals("Marcus Stoinis", bestAvg);
		} catch (IPLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenIPLRunAndWicketsCSVFile_ShouldReturnPlayer_WithHighestRunAndWkts() throws IPLException {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedBat = iplAnalyser.getBatsmanWithHighestRun();
			IPLRuns[] run = new Gson().fromJson(sortedBat, IPLRuns[].class);
			iplAnalyser.loadIPLWicketsData(IPL_WICKET_CSV_FILE_PATH);
			String sorted = iplAnalyser.getBowlersWithHighestWkts();
			IPLWickets[] wickets = new Gson().fromJson(sorted, IPLWickets[].class);
			String bestAvg = iplAnalyser.getBestAllRounder(run, wickets);
			Assert.assertEquals("Ben Cutting", bestAvg);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnBatsMan_WithHighestCenturiesAndAvg() {
		try {
			IPLAnalyser iplAnalyser = new IPLAnalyser();
			iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
			String sortedIPLData = iplAnalyser.getBatsmanWithHighestCenturiesAndBestAvg();
			IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
			Assert.assertEquals("David Warner", iplRuns[0].player);
		} catch (IPLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLRunCSVFile_ShouldReturnPlayer_BestAvgWithZeroCenturyAndFifty() throws IPLException {
		IPLAnalyser iplAnalyser = new IPLAnalyser();
		iplAnalyser.loadIPLRunsData(IPL_RUN_CSV_FILE_PATH);
		String sorted = iplAnalyser.getBatsmanWithHighestCenturiesAndFifties();
		IPLRuns[] runs = new Gson().fromJson(sorted, IPLRuns[].class);
		String sortedBat = iplAnalyser.getBatsmanWithTopAverages();
		IPLRuns[] average = new Gson().fromJson(sortedBat, IPLRuns[].class);
		for (int i = 0; i < runs.length; i++) {
			if (runs[runs.length - 1 - i].player.equals(average[i].player)) {
				Assert.assertEquals("Robin Uthappa", average[i].player);
				break;
			}
		}
	}

}
