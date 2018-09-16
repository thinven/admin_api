package com.thinven.boot.support.util;

import java.io.BufferedReader;
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.thinven.boot.support.security.SHA512;

public abstract class ParamUtil {

	/**
	 * 서버 아이피 얻기
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getServerIp() {
		String ip = "";
		try {
			InetAddress Address = InetAddress.getLocalHost();
			ip = Address.getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		return ip;
	}

	/**
	 * 순수 자바로 서버IP 얻어오기.
	 * 
	 * @return
	 */
	public static String getLenuxServerIp() {
		String ip = "";

		try {
			Enumeration<?> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) en.nextElement();
				Enumeration<?> inetAddresses = ni.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress ia = (InetAddress) inetAddresses.nextElement();
					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						byte[] address = ia.getAddress();
						if (address[0] == 127)
							continue;
						ip = ia.getHostAddress();
						break;
					}
				}
				if (ip.length() > 0) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * 날짜 변환
	 * 
	 * @param pattern
	 * @return
	 */
	public static String currentFormatDate(String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(cal.getTime());
	}

	/**
	 * 태그노트의 미리보기 링크스크립트 생성
	 * 
	 * @param str
	 * @return
	 */
	public static final String createLink(String str) {
		String[] astr = str.split("(\r\n)");
		String ext = "";
		String result = "";
		for (int i = 0; i < astr.length; i++) {
			ext = astr[i].substring(astr[i].lastIndexOf(".")).toLowerCase();
			if (".js".equals(ext)) {
				result += "<script type='text/javascript' src='" + astr[i] + "'></script>\r\n";
			} else if (".css".equals(ext)) {
				result += "<link rel='stylesheet' type='text/css' href='" + astr[i] + "' />\r\n";
			}
		}
		return result;
	}

	/**
	 * 알파벳, 숫자, 하이픈 만 참 나머지 거짓
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isAlphaNumericHyphen(String str) {
		return Pattern.matches("^[_a-zA-Z0-9-]*$", str);
	}

	/**
	 * 문자열에 한글이 포함되었는지 확인
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean containsHangul(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock) || UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock))
				return true;
		}
		return false;
	}

	/**
	 * 문자열을 받아서 null이면 false로 리턴
	 * 
	 * @param str
	 * @return boolean
	 */
	public static final boolean isNull(String str) {
		if (str == null || str.trim().equals("") || str.trim().equals("null"))
			return true;
		else
			return false;
	}

	/**
	 * 문자열이 널이면 공백 문자열로 리턴
	 * 
	 * @param str
	 * @return String
	 */
	public static final String NVL(String str) {
		if (isNull(str))
			return "";
		else
			return str;
	}

	/**
	 * 문자열이 널이면 공백 문자열로 리턴
	 * 
	 * @param str
	 * @return String
	 */
	public static final String NVL(String str, String dft) {
		if (isNull(str))
			return dft;
		else
			return str;
	}

	/**
	 * 문자열이 널이면 대체할 정수를 리턴
	 * 
	 * @param str
	 * @param NVLInt
	 * @return int
	 */
	public static final int NVL(String str, int NVLInt) {
		if (isNull(str))
			return NVLInt;
		else
			return Integer.parseInt(str);
	}

	/**
	 * 문자열이 널이면 대체할 long형정수를 리턴
	 * 
	 * @param str
	 * @param NVLInt
	 * @return int
	 */
	public static final long NVL(String str, long NVLLong) {
		if (isNull(str))
			return NVLLong;
		else
			return Long.parseLong(str);
	}

	/**
	 * 문자열을 정수로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static final int parseInt(String str) {
		if (isNull(str))
			return 0;
		else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}

	/**
	 * 문자열을 long형으로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static final long parseLong(String str) {
		if (isNull(str))
			return 0L;
		else {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {
				return 0L;
			}
		}
	}

	/**
	 * 문자열을 long형으로 변환
	 * 
	 * @param str
	 * @param dft
	 *            기본값
	 * @return
	 */
	public static final long parseLong(String str, long dft) {
		if (isNull(str))
			return dft;
		else {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {
				return dft;
			}
		}
	}

	/**
	 * 문자열을 float로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static final float parseFloat(String str) {
		if (isNull(str))
			return 0f;
		else {
			try {
				return Float.parseFloat(str);
			} catch (NumberFormatException e) {
				return 0f;
			}
		}
	}

	/**
	 * 문자열을 double로 변환
	 * 
	 * @param str
	 * @return
	 */
	public static final double parseDouble(String str) {
		if (isNull(str))
			return 0;
		else {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}

	/**
	 * 오른쪽 문자열 기준으로 자르기
	 * 
	 * @param text
	 * @param length
	 * @return
	 */
	public static String Right(String text, int length) {
		if (text.length() >= length)
			return text.substring(text.length() - length, text.length());
		else {
			String temp = "";
			for (int i = 0; i < length; i++)
				temp += " ";
			return temp;
		}
	}

	/**
	 * 용도 : 첫글자만 대문자로 변환
	 * 
	 * @param src
	 *            : 대상문자열
	 * @return : 완성된 문자열
	 */
	public static final String toFirstUpper(String src) {
		return src.substring(0, 1).toUpperCase() + src.substring(1, src.length());
	}

	/**
	 * 용도 : common.js의 encode된 내역을 decode 함
	 * 
	 * @param param
	 * @return
	 */
	public static String DeCode(String param) {
		StringBuffer sb = new StringBuffer();
		int pos = 0;
		boolean flg = true;

		if (param != null) {
			if (param.length() > 1) {
				while (flg) {
					String sLen = param.substring(pos, ++pos);
					int nLen = 0;
					try {
						nLen = Integer.parseInt(sLen);
					} catch (Exception e) {
						nLen = 0;
					}
					String code = "";
					if ((pos + nLen) > param.length())
						code = param.substring(pos);
					else
						code = param.substring(pos, (pos + nLen));

					pos += nLen;
					sb.append(((char) Integer.parseInt(code)));
					if (pos >= param.length())
						flg = false;
				}
			}
		} else {
			param = "";
		}

		return sb.toString();
	}

	/**
	 * 용도 : 파라미터로 넘어온 값들을 ModelAndView를 통해 클라로 리턴.
	 * 
	 * @param mav
	 * @param request
	 */
	public static final void reAssign(ModelAndView mav, HttpServletRequest request) {
		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String s = (String) en.nextElement();
			mav.addObject(s, request.getParameter(s));
		}
	}

	/**
	 * 용도 : 숫자 난수 발생 (첫수는 1~9사이)
	 * 
	 * @param l
	 *            숫자 난수 길이
	 * @return
	 */
	public static long randomNum(int l) {
		String result = "0";
		result = ((Math.random() * (1000000)) + 1) + "";
		result = result.replace(".", "");
		result = result.substring(0, l);
		return ParamUtil.parseLong(result);
	}

	/**
	 * 폰형식 체크.
	 * 
	 * @param phonenum
	 *            체크할 전화번호.
	 * @return
	 */
	public static boolean isPhone(String phonenum) {
		return Pattern.matches("^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", ParamUtil.NVL(phonenum));
	}

	/**
	 * 이메일형식의 문자열중 SHOWCOUNT만큼 보여주고 나머지는 *를 표시.
	 * 
	 * @param srcText
	 * @return
	 */
	public static String clockingText(String srcText) {
		if (srcText != null && srcText.length() > 1) {
			String result = srcText;
			int CLOCKINGCOUNT = 1;
			int max = result.length();
			String suffix = "";

			for (int i = 0; i < CLOCKINGCOUNT; i++) {
				suffix += "*";
			}
			result = result.substring(0, max - CLOCKINGCOUNT) + suffix;
			return result;
		} else {
			return "";
		}
	}

	/**
	 * 이메일형식의 문자열중 SHOWCOUNT만큼 보여주고 나머지는 *를 표시.
	 * 
	 * @param srcEmail
	 * @return
	 */
	public static String clockingEmail(String srcEmail) {
		String result = srcEmail;
		int SHOWCOUNT = 2;
		int maxID = result.indexOf("@");
		int maxEMail = result.length() - maxID;
		String suffixID = "";
		String suffixEMail = "";

		for (int i = 0; i < (maxID - SHOWCOUNT); i++) {
			suffixID += "*";
		}
		for (int i = 0; i < (maxEMail - SHOWCOUNT); i++) {
			suffixEMail += "*";
		}
		result = result.substring(0, SHOWCOUNT) + suffixID + result.substring(maxID, maxID + SHOWCOUNT + 1) + suffixEMail;
		return result;
	}

	/**
	 * 시퀀스와 길이를 받아 랜덤키를 생성.
	 * 
	 * @param seq
	 * @param size
	 * @return
	 */
	public static String getRandomKey(long seq, int size) {
		return SHA512.getDigest(seq + "_" + DateUtil.getDateTime()).substring(0, size);
	}

	/**
	 * double 형 연산 오류 방지용.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDoubleAdd(double a, double b) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(a));
		BigDecimal bd2 = new BigDecimal(String.valueOf(b));
		return bd1.add(bd2).doubleValue();
	}

	public static double getDoubleSub(double a, double b) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(a));
		BigDecimal bd2 = new BigDecimal(String.valueOf(b));
		return bd1.subtract(bd2).doubleValue();
	}

	public static double getDoubleMul(double a, double b) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(a));
		BigDecimal bd2 = new BigDecimal(String.valueOf(b));
		return bd1.multiply(bd2).doubleValue();
	}

	public static double getDoubleDiv(double a, double b) {
		BigDecimal bd1 = new BigDecimal(String.valueOf(a));
		BigDecimal bd2 = new BigDecimal(String.valueOf(b));
		return bd1.divide(bd2, 2, BigDecimal.ROUND_CEILING).doubleValue();
	}

	public static String getRequestJson(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			/* report an error */
		}
		return jb.toString();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
