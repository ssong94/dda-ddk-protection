package com.spring.boot.ddaddk.protection.exception;

public class DuplicateRequestException extends RuntimeException {

	public DuplicateRequestException() {
		super("이미 처리중인 요청입니다.");
	}

	public DuplicateRequestException(String message) {
		super(message);
	}

}
