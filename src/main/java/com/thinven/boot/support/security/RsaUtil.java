package com.thinven.boot.support.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.thinven.boot.support.domain.entity.model.SecurityModel;

public abstract class RsaUtil {

	private static String privKeyStr = "308204bc020100300d06092a864886f70d0101010500048204a6308204a202010002820101008f96c4260e5623e6da46d8ddb9819fe6322b4ad647c5837071fa003d58fce4488f09dd0fe2e82a2b48c654c21dbca7e520df00fde5abc95896995a4bebc3aaa43c67a58975ef5d8a7740885cccee62cc8f48ebf104ec4cc1ce3b0f2ee4c26292a79b50bcf49c143b5cb3510a0e9277c38d0737c50cb73f3ca1f886f558c3c30ae401e6d39726e6313953ea9ba3de8e5582e0bcdcf679d153c5c8671500cd9da1417dcd67ab07fdb7e10b90f5059964989bd22b0586ea8ac8ddd76cd64392ff1735bb89b78dbccf081a366c48ef7f94b57253df63121ab9e23a0c9fb34a286591608248d4a5c4f286ad3250484a58283f9e9ad5c079f23579cd11946963907c810203010001028201003b1d41d1bd133fa299f892ededa26d17134e7977f082635396c8eaf256c17ea654a525ea956b29793a73d48f3a38e21255cf19946dcaa49121272f629444b0eafea7592257785d5555352f2ead7bf316ee6282286ef36dc2584a3e3cfb1700080a92c9d1910ef4d15f9fbf5c59957952fbab9e0d34e7f853d906fd60e2d36d675f6956414652c0dbbba257cd99b248279b7a01b88a1c325a82228cdc28876fa3e13667a63f7b012d602465e1bdaab40e80bd13526d09d12e5f60c838310b20758cf89595ec0205ba315607e4274170da9bcc7a5fca0f13acd91ba150aa4c61bbc6617458777b82ecb6faea4541281a752a19ec81bea4eca0e9b54a852bee8ea502818100c7cb15f717596b8a3e2a37f1f3dfa6112f400726769c2c8a9a2d612b962afc8a713e893e06bd69966a8ca70dde0bfec9a54c1ab40a60f7b22bd6c1e1681cd3ffa054af13d3b2f4a660bcff99bf1bb929e504c904484ee15916d3060bc73f4c63615be891b40e858dd3161bbf55dad2ae7628138c79e060535b42f0bbf08178b302818100b7fbe5c6e3a30493b72918936046ffee71800a4b6d5dcbbccd791580eb75b85ebc2fbc46cc3234f1208ddde62c093884fec84a5f2084d8eddf38c1912738b571283fac138c6b81f812c179b824facb4f777eada80acf769e5d5aba95be64301c6eda10bb85766072106a17542d876c7f530f0ab21ffb41f8ae12f3b23f04c7fb0281801c8b2c293001cb7665a93c72e9c792ff3383ef4194827d6e93d655e29742aeefcc7c05b981d6ffc5c8952dacb2837e342ad473a03337654be42d7b678bab4ab263a2959cbc8afd9ee3853cf327357bbfbb837280952007dbe581e2843e4411d2c4ef0f8bf97075ae2c712527b1c9607e224d5975a157e80b20a7b0a2f91a5637028180056f5b8a548f2c4370d5b5f42493f45b86ded16f08fc0c58b7792c3972bb378714e81417c350785fe72649b7d2f532c63b008fb1dfb40cf479f3a34436746667c158a02cb3a9395daab02c78541f54823475798bcd45f3ab19f6e42c2843ad68299e278062a0ec987203b04cbc4b93d8ef0b32b5b05c2bc6bcddb275418752a10281803a7018819c8822fb09e6cc219e77c4897e8c26959b8f52b933a0075d4620c432875d54fbf6ef53fa7dd8cafda5a6bf866e9f3ee6ab0011c1eb8bbafd6691b1d48208e88e734e675863f821497898641cb92de04e7e5bb9e14c808572c13c8077fad39fb0b5e8161b1d0c98547abcc0f59fc1016c95270163bbbb922154ed4b17";
	private static String pubKeyStr = "30820122300d06092a864886f70d01010105000382010f003082010a02820101008f96c4260e5623e6da46d8ddb9819fe6322b4ad647c5837071fa003d58fce4488f09dd0fe2e82a2b48c654c21dbca7e520df00fde5abc95896995a4bebc3aaa43c67a58975ef5d8a7740885cccee62cc8f48ebf104ec4cc1ce3b0f2ee4c26292a79b50bcf49c143b5cb3510a0e9277c38d0737c50cb73f3ca1f886f558c3c30ae401e6d39726e6313953ea9ba3de8e5582e0bcdcf679d153c5c8671500cd9da1417dcd67ab07fdb7e10b90f5059964989bd22b0586ea8ac8ddd76cd64392ff1735bb89b78dbccf081a366c48ef7f94b57253df63121ab9e23a0c9fb34a286591608248d4a5c4f286ad3250484a58283f9e9ad5c079f23579cd11946963907c810203010001";
	private static String pkm = "8f96c4260e5623e6da46d8ddb9819fe6322b4ad647c5837071fa003d58fce4488f09dd0fe2e82a2b48c654c21dbca7e520df00fde5abc95896995a4bebc3aaa43c67a58975ef5d8a7740885cccee62cc8f48ebf104ec4cc1ce3b0f2ee4c26292a79b50bcf49c143b5cb3510a0e9277c38d0737c50cb73f3ca1f886f558c3c30ae401e6d39726e6313953ea9ba3de8e5582e0bcdcf679d153c5c8671500cd9da1417dcd67ab07fdb7e10b90f5059964989bd22b0586ea8ac8ddd76cd64392ff1735bb89b78dbccf081a366c48ef7f94b57253df63121ab9e23a0c9fb34a286591608248d4a5c4f286ad3250484a58283f9e9ad5c079f23579cd11946963907c81";
	private static String pke = "10001";

	public static KeyPair generateKeyPair() {
		SecureRandom random = new SecureRandom();
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance("RSA", "SunJSSE");
			generator.initialize(2048, random);
			KeyPair pair = generator.generateKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			Key publicKey = pair.getPublic();
			Key privateKey = pair.getPrivate();

			pubKeyStr = byteArrayToHex(publicKey.getEncoded());
			privKeyStr = byteArrayToHex(privateKey.getEncoded());

			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			pkm = publicSpec.getModulus().toString(16);
			pke = publicSpec.getPublicExponent().toString(16);

			return pair;
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static SecurityModel generateKey() {
		SecurityModel model = new SecurityModel();
		SecureRandom random = new SecureRandom();
		KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance("RSA", "SunJSSE");
			generator.initialize(2048, random);
			KeyPair pair = generator.generateKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			Key publicKey = pair.getPublic();
			Key privateKey = pair.getPrivate();

			model.setPrivateKey(byteArrayToHex(privateKey.getEncoded()));

			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

			model.setPkm(publicSpec.getModulus().toString(16));
			model.setPke(publicSpec.getPublicExponent().toString(16));

			return model;
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String str) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "SunJCE");

			// Turn the encoded key into a real RSA public key.
			// Public keys are encoded in X.509.
			X509EncodedKeySpec ukeySpec = new X509EncodedKeySpec(hexToByteArray(pubKeyStr));
			KeyFactory ukeyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = null;
			try {
				publicKey = ukeyFactory.generatePublic(ukeySpec);
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}

			// 공개키를 전달하여 암호화
			byte[] input = str.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipherText = cipher.doFinal(input);
			return byteArrayToHex(cipherText);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String decrypt(String chipherHex) {
		return decrypt(privKeyStr, chipherHex);
	}

	public static String decrypt(String privateKeyStr, String chipherHex) {
		if (privateKeyStr == null || privateKeyStr.trim().length() == 0) {
			privateKeyStr = privKeyStr;
		}
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "SunJCE");
			// Turn the encoded key into a real RSA private key.
			// Private keys are encoded in PKCS#8.
			PKCS8EncodedKeySpec rkeySpec = new PKCS8EncodedKeySpec(hexToByteArray(privateKeyStr));
			KeyFactory rkeyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = null;
			try {
				privateKey = rkeyFactory.generatePrivate(rkeySpec);
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}

			// 개인키를 가지고있는쪽에서 복호화
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] plainText = cipher.doFinal(hexToByteArray(chipherHex));
			return new String(plainText);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}

	// hex string to byte[]
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	// byte[] to hex sting
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	public static String getPubKeyStr() {
		return pubKeyStr;
	}

	public static String getPrivKeyStr() {
		return privKeyStr;
	}

	public static String getPkm() {
		return pkm;
	}

	public static String getPke() {
		return pke;
	}

}
