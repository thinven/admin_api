package com.thinven.boot.support.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.thinven.boot.support.util.ParamUtil;

public abstract class AesUtil {

	private static final String algorithm = "AES/CBC/NoPadding";

	private static SecretKeySpec keyspec = new SecretKeySpec(RsaUtil.hexToByteArray("60c7645b6cb62a30c0ec16dac68dbf1b"), "AES");
	private static IvParameterSpec ivspec = new IvParameterSpec(RsaUtil.hexToByteArray("ca3c555603d360086a0ec763e7fc2091"));

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String encrypt(String Data) {
		Cipher c;
		String encryptedValue = "";
		if (!"".equals(ParamUtil.NVL(Data))) {
			Data = padString(Data);
			try {
				c = Cipher.getInstance(algorithm);
				c.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
				byte[] encVal = c.doFinal(Data.getBytes());
				// encryptedValue = new BASE64Encoder().encode(encVal);
				encryptedValue = DatatypeConverter.printBase64Binary(encVal);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return encryptedValue;
	}

	public static String decrypt(AesKeys aeskeys, String encryptedData) {
		Cipher c;
		String decryptedValue = "";
		try {
			c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, aeskeys.getKeyspec(), aeskeys.getIvspec());
			// byte[] decordedValue = new
			// BASE64Decoder().decodeBuffer(ParamUtil.NVL(encryptedData));
			byte[] decordedValue = DatatypeConverter.parseBase64Binary(ParamUtil.NVL(encryptedData));
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryptedValue.trim();
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static String padString(String source) {
		char paddingChar = ' ';
		int size = 16;
		int x = source.getBytes().length % size;
		int padLength = size - x;

		for (int i = 0; i < padLength; i++) {
			source += paddingChar;
		}
		return source;
	}

	public static void main(String[] args) throws Exception {

		String password = "soldier";
		String passwordEnc = AesUtil.encrypt(password);
		//String passwordDec = AesUtil.decrypt(passwordEnc);

		System.out.println("Plain Text : " + password);
		System.out.println("Encrypted Text : " + passwordEnc);
		//System.out.println("Decrypted Text : " + passwordDec);
	}

	public static void setKeys(String keyniv) {
		String[] keys = keyniv.split(",");
		keyspec = new SecretKeySpec(RsaUtil.hexToByteArray(keys[0]), "AES");
		ivspec = new IvParameterSpec(RsaUtil.hexToByteArray(keys[1]));
		System.out.println("keyspec : " + keys[0]);
		System.out.println("ivspec : " + keys[1]);
	}
}
