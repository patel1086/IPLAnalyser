package com.bridgelabz.iplanalyser;

public class CSVBuilderException extends Exception {
	enum ExceptionType {
		IPL_RUN_FILE_PROBLEM, IPL_WICKET_FILE_PROBLEM, UNABLE_TO_PARSE
	}

	ExceptionType type;

	public CSVBuilderException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}
