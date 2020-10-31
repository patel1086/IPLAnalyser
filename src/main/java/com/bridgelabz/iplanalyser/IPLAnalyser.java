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
			this.sortInDesendOrder(IPLComparator);
			String json = new Gson().toJson(IPLRunCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(IPLRunCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
		}
	}

	private void sortInDesendOrder(Comparator<IPLRuns> IPLComparator) {
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
