package com.thinven.boot.support.util;

public abstract class NumUtil {

	/**
	 * 진법변환.(36,62진법까지)
	 * 
	 * @param n
	 *            숫자
	 * @param base
	 *            진법
	 * @return
	 */
	public static String convertBase(long n, int base) {
		String[] T = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		int r = (int) (n % base);
		long q = n / base;
		if (q == 0) {
			return T[r];
		} else {
			return convertBase(q, base) + T[r];
		}
	}

}
