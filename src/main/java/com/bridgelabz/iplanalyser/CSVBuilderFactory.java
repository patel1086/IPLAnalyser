package com.bridgelabz.iplanalyser;

public class CSVBuilderFactory {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}

}
