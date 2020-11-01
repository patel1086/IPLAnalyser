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

	public String getPlayersWithTopAverages() throws IPLException {
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

	public String getPlayersWithTopStrikeRate() throws IPLException {
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

	public String getPlayersWithTopBoundaries() throws IPLException {
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

	public String getPlayersWithTopStrikeRateandBoundaryBoth() throws IPLException {
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

	public String getPlayersWithTopStrikeRateandAverage() throws IPLException {
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

	public String getPlayersWithHighestRunsAndAverage() throws IPLException {
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

	public String getPlayersWithTopBowlingAverages() throws IPLException {
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

	public String getPlayersWithTopBowlingStrikeRate() throws IPLException {
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

	public String getPlayersWithTopBowlingEconomyRate() throws IPLException {
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

	public String getPlayersWithBestStrikeRateWith5Wor4W() throws IPLException {
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

	public String getPlayersWithBestBowlingAvgAndSR() throws IPLException {
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
