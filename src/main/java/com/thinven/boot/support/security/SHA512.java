package com.thinven.boot.support.security;

import java.security.MessageDigest;

public class SHA512 {
	
	/**
	 * 단방향용 암호회용
	 * @param pwd 암호화하고 싶은 문자열
	 * @return 암호화된 문자열
	 * 등록자	작업일자		등록 및 수정사유<br />
	 * 안승철	2012.05.28	단방향암호에 사용
	 */
	public static String getDigest(String pwd) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			byte[] pb = messageDigest.digest(pwd.getBytes());
			StringBuffer sb = new StringBuffer(pb.length << 1);
			for (int i = 0, iend = pb.length; i < iend; i++) {
				int val = (pb[i] + 256) & 0xff;
				sb.append(Integer.toHexString(val >> 4)).append(
						Integer.toHexString(val & 0xf));
			}

			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
