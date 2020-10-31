package com.bridgelabz.iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

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

}
