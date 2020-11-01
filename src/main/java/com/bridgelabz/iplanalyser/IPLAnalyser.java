package com.bridgelabz.iplanalyser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IPLAnalyser {
	List<IPLRuns> IPLRunCSVList = null;
	List<IPLWickets> IPLWicketsCSVList = null;

	// Method to load IPL Run CSV file
	public int loadIPLRunsData(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			IPLRunCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLRuns.class);
			return IPLRunCSVList.size();

		} catch (IOException | CSVBuilderException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.IPL_RUN_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to load IPL Wickets CSV file
	public int loadIPLWicketsData(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			IPLWicketsCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLWickets.class);
			return IPLWicketsCSVList.size();

		} catch (IOException | CSVBuilderException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.IPL_WICKET_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with highest run in descending order
	public String getBatsmanWithHighestRun() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingRun.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> IPLComparator = Comparator.comparing(census -> census.runs);
			this.sortInDescendOrder(IPLComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with top average in run in descending order
	public String getBatsmanWithTopAverages() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingAvg.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> IPLComparator = Comparator.comparing(census -> census.avg);
			this.sortInDescendOrder(IPLComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with top strike rate in descending order
	public String getBatsmanWithTopStrikeRate() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingStrikeRate.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.strikeRate);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with highest boundaries in descending order
	public String getBatsmanWithTopBoundaries() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.fours + census.sixes);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get batsman with top strike rate or highest boundaries in
	// descending order
	public String getBatsmansWithTopStrikeRateandBoundaryBoth() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandBoundary.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getSixes).thenComparing(ipl -> ipl.fours)
					.thenComparing(census -> census.strikeRate);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get batsman with top strike rate and best average in descending
	// order
	public String getBatsmanWithTopStrikeRateandAverage() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getAvg)
					.thenComparing(ipl -> ipl.strikeRate);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with Highest run and best average in descending order
	public String getBatsmanWithHighestRunsAndAverage() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMaxRunsAndAvg.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getRuns).thenComparing(ipl -> ipl.avg);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with highest centuries and best average in descending
	// order
	public String getBatsmanWithHighestCenturiesAndBestAvg() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingCenturiesAndAvg.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getHundreds)
					.thenComparing(ipl -> ipl.avg);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get player with Highest centuries and fifties in descending order
	public String getBatsmanWithHighestCenturiesAndFifties() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMax100sAnd50s.json")) {
			if (IPLRunCSVList == null || IPLRunCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getHundreds)
					.thenComparing(ipl -> ipl.fifties);
			this.sortInDescendOrder(iplComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with Highest Wickets in descending order
	public String getBowlersWithHighestWkts() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingWkts.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(ipl -> ipl.wickets);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with Top Bowling Average in descending order
	public String getBowlersWithTopBowlingAverages() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingAvg.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(ipl -> ipl.avg);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with top bowling strike rate in descending order
	public String getBowlersWithTopBowlingStrikeRate() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingStrikeRate.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(ipl -> ipl.strikeRate);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with top bowling economy rate in descending order
	public String getBowlersWithTopBowlingEconomyRate() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingEconomyRate.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(ipl -> ipl.economy);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with best bowling strike rate and highest 5w or 4w in
	// descending order
	public String getBowlersWithBestStrikeRateWith5Wor4W() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingSRandWkts.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(IPLWickets::getStrikeRate).thenComparing(ipl -> ipl.fiveWicket).thenComparing(ipl -> ipl.fourWicket);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with best bowling average and best strike rate in
	// descending order
	public String getBowlersWithBestBowlingAvgAndSR() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingAverageAndSR.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(IPLWickets::getAvg).thenComparing(ipl -> ipl.strikeRate);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get bowlers with Highest Wickets and best bowling average in
	// descending order
	public String getBowlersWithHighestWktsandBestAvg() throws IPLException {
		try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingWktsAndAvg.json")) {
			if (IPLWicketsCSVList == null || IPLWicketsCSVList.size() == 0) {
				throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
			}
			Comparator<IPLWickets> IPLComparator = Comparator.comparing(IPLWickets::getWickets).thenComparing(ipl -> ipl.avg);
			this.sortInDescendOrderWkts(IPLComparator);
			String json = new Gson().toJson(IPLWicketsCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLWicketsCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	// Method to get players those whose average is best in both run and wickets
	public String getPlayersWithBestAvgInBoth(IPLRuns[] average, IPLWickets[] wickets) {
		for (int i = 0; i < average.length; i++) {
			if (average[i].player.equals(wickets[i].player)) {
				return average[i].player;
			}
		}
		return null;
	}

	// Method to get best all rounder player
	public String getBestAllRounder(IPLRuns[] run, IPLWickets[] wickets) {
		for (int i = 0; i < run.length; i++) {
			if (run[i].player.equals(wickets[i].player)) {
				return run[i].player;
			}
		}
		return null;
	}

	// Method to get IPL wickets csv data in descending order
	private void sortInDescendOrderWkts(Comparator<IPLWickets> IPLComparator) {
		for (int i = 0; i < IPLWicketsCSVList.size() - 1; i++) {
			for (int j = 0; j < IPLWicketsCSVList.size() - i - 1; j++) {
				IPLWickets census1 = IPLWicketsCSVList.get(j);
				IPLWickets census2 = IPLWicketsCSVList.get(j + 1);
				if (IPLComparator.compare(census1, census2) < 0) {
					IPLWicketsCSVList.set(j, census2);
					IPLWicketsCSVList.set(j + 1, census1);
				}
			}
		}
	}

	// Method to get IPL run csv data in descending order
	private void sortInDescendOrder(Comparator<IPLRuns> IPLComparator) {
		for (int i = 0; i < IPLRunCSVList.size() - 1; i++) {
			for (int j = 0; j < IPLRunCSVList.size() - i - 1; j++) {
				IPLRuns census1 = IPLRunCSVList.get(j);
				IPLRuns census2 = IPLRunCSVList.get(j + 1);
				if (IPLComparator.compare(census1, census2) < 0) {
					IPLRunCSVList.set(j, census2);
					IPLRunCSVList.set(j + 1, census1);
				}
			}
		}
	}
}
