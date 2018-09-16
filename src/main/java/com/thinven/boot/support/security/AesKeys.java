package com.thinven.boot.support.security;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesKeys {

	private SecretKeySpec keyspec;
	private IvParameterSpec ivspec;

	public AesKeys() {
		this.keyspec = new SecretKeySpec(RsaUtil.hexToByteArray("60c7645b6cb62a30c0ec16dac68dbf1b"), "AES");
		this.ivspec = new IvParameterSpec(RsaUtil.hexToByteArray("ca3c555603d360086a0ec763e7fc2091"));
	}

	public AesKeys(String keyniv) {
		String[] keys = keyniv.split(",");
		this.keyspec = new SecretKeySpec(RsaUtil.hexToByteArray(keys[0]), "AES");
		this.ivspec = new IvParameterSpec(RsaUtil.hexToByteArray(keys[1]));
		// System.out.println("keyspec : " + keys[0]);
		// System.out.println("ivspec : " + keys[1]);
	}

	public SecretKeySpec getKeyspec() {
		return keyspec;
	}

	public IvParameterSpec getIvspec() {
		return ivspec;
	}

}
