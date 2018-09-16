package com.thinven.boot.support.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyJsonMapper extends ObjectMapper {

	private static final long serialVersionUID = -2112279649029760837L;

	public MyJsonMapper() {
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

}
