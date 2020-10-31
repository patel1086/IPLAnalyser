package com.bridgelabz.iplanalyser;

public class IPLException extends Exception {
	enum ExceptionType {
		IPL_RUN_FILE_PROBLEM, IPL_WICKET_FILE_PROBLEM, UNABLE_TO_PARSE, FILE_OR_HEADER_PROBLEM, NO_DATA;
	}

	ExceptionType type;

	public IPLException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public IPLException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
