package com.thinven.boot.support.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class DateUtil {

	/**
	 * 현재 년월일 구하기
	 * 
	 * @return String "yyyyMMdd"형식
	 */
	public static final String getDateString() {
		return getDateString("yyyyMMdd");
	}

	/**
	 * 현재 년월일 구하기
	 * 
	 * @return String "yyyy-MM-dd"형식
	 */
	public static final String getDashDate() {
		return getDateString("yyyy-MM-dd");
	}

	/**
	 * 현재 년월일시분초 구하기
	 * 
	 * @return String "yyyyMMdd HH:mm:ss"형식
	 */
	public static final String getDateTime() {
		return getDateString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 현재 년월일 구하기
	 * 
	 * @return String "yyyyMMdd"형식
	 */
	public static final Date now() {
		return new Date();
	}

	/**
	 * 현재 일자를 지정된 형식으로 구한다.
	 * 
	 * @param format
	 *            날짜 포맷(예:"yyyy-MM-dd HH:mm:ss")
	 * @return String 지정된 형식의 현재 일자(예:"yyyy-MM-dd HH:mm:ss")
	 */
	public static final String getDateString(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		return formatter.format(new Date());
	}

	/**
	 * 현재 일자를 지정된 형식으로 구한다.
	 * 
	 * @param format
	 *            날짜 포맷(예:"yyyy-MM-dd HH:mm:ss")
	 * @return String 지정된 형식의 현재 일자(예:"yyyy-MM-dd HH:mm:ss")
	 */
	public static final String getDateString(Timestamp date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		return formatter.format(date);
	}

	/**
	 * 지정일을 지정된 형식으로 변환
	 * 
	 * @param s
	 * @param format
	 *            날짜 포맷 (예: "yyyy-MM-dd")
	 * @return String
	 */
	public static final String getDisDate(String s, String format) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		Date targetDate = null;
		targetDate = check(s);
		// return targetDate.toString();
		return formatter.format(targetDate);
	}

	/**
	 * 지정일을 지정된 형식으로 변환
	 * 
	 * @param s
	 * @param format
	 *            날짜 포맷 (예: "yyyy-MM-dd")
	 * @return String
	 */
	public static final String getDisDate(Date d, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		return formatter.format(d);
	}

	/**
	 * 년월일 사이에 '-'를 첨가한다. "yyyymmdd" -> "yyyy-mm-dd"
	 * 
	 * @param str
	 *            날짜(yyyymmdd)
	 * @return java.lang.String
	 */
	public static final String dashDate(String str) throws Exception {
		return getDisDate(str, "yyyy-MM-dd");
	}

	/**
	 * 년월 사이에 '-'를 첨가한다. "yyyymm" -> "yyyy-mm"
	 * 
	 * @param str
	 *            날짜(yyyymm)
	 * @return java.lang.String
	 */
	public static final String dashYM(String str) throws Exception {
		String temp = null;
		if (str == null)
			return "";
		int len = str.length();
		if (len != 6)
			return str;
		if (str.equals("000000") || str.equals("     0")) {
			return "";
		} else {
			temp = str.substring(0, 4) + "-" + str.substring(4, 6);
			return temp;
		}
	}

	/**
	 * "yyyyMMdd"형식의 지정일을 "yyyy.MM.dd"형식으로 변환
	 * 
	 * @param s
	 *            "yyyyMMdd"형식
	 * @return String "yyyy.MM.dd"형식
	 */
	public static final String dotDate(String s) throws Exception {
		return getDisDate(s, "yyyy.MM.dd");
	}

	/**
	 * 일자 변환 <br>
	 * "yyyyMMdd"형식의 문자열을 Date형으로 변환하여 리턴한다.
	 * 
	 * @param s
	 *            "yyyyMMdd".
	 * @return date java.util.Date
	 */
	public static final Date check(String s) {
		return check(s, "yyyyMMdd");
	}

	/**
	 * 현재 일자 구하기
	 * 
	 * @return int
	 */
	public static final int getIntDate() {
		return getNumberByPattern("yyyyMMdd");
	}

	/**
	 * 현재 년도 구하기
	 * 
	 * @return int
	 */
	public static final int getIntYear() {
		return getNumberByPattern("yyyy");
	}

	/**
	 * 현재 월(month) 구하기
	 * 
	 * @return int 현재 월(MM)
	 */
	public static final int getIntMonth() {
		return getNumberByPattern("MM");
	}

	/**
	 * 현재 일(day) 구하기
	 * 
	 * @return int 현재 일(dd)
	 */
	public static final int getIntDay() {
		return getNumberByPattern("dd");
	}

	/**
	 * 현재 시간(hour) 구하기
	 * 
	 * @return int 현재 시간(HH)
	 */
	public static final int getIntHour() {
		return getNumberByPattern("HH");
	}

	/**
	 * 현재 분(minute) 구하기
	 * 
	 * @return int 현재 분(mm)
	 */
	public static final int getIntMinute() {
		return getNumberByPattern("mm");
	}

	/**
	 * 현재 일자를 지정된 형식으로 int형으로 변환 <br>
	 * DateUtil 내부에서만 사용
	 * 
	 * @param format
	 *            날짜 포맷(예:"MM")
	 * @return int
	 */
	private static final int getNumberByPattern(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
		String dateString = formatter.format(new Date());
		return Integer.parseInt(dateString);
	}

	/**
	 * 일자 변환 <br>
	 * 주어진 포맷 형식의 문자열을 Date형으로 변환하여 리턴한다.
	 * 
	 * @param s
	 * @param format
	 *            날짜 포맷 (예: "yyyy-MM-dd")
	 * @return date java.util.Date
	 */
	public static final Date check(String s, String format) {
		if (s == null)
			s = DateUtil.getDashDate();
		if (format == null)
			format = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	/**
	 * Timestamp 형식으로 현재일자를 반환
	 * 
	 * @return
	 */
	public static final Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * "yyyy-MM-dd" 포맷으로 온 문자열을 timestamp로 변환하여 반환
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Timestamp getTimestamp(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTemp = null;
		try {
			dateTemp = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(dateTemp.getTime());
	}

	/**
	 * "yyyyMMdd" 포맷으로 온 문자열을 timestamp로 변환하여 반환
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static final Timestamp getTimestamp8(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date dateTemp = null;
		try {
			dateTemp = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(dateTemp.getTime());
	}

	/**
	 * 파일 경로 및 이름을 날짜를 기준으로 산출한 문자열반환. 목적은 파일 및 이미지 경로를 일관되게 사용하기 위함.
	 * 
	 * @return
	 */
	public static final String getUniquePath() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getDateString("/yyyy/MM/dd/HH/") + "u" + getDateString("mmss") + "n" + System.currentTimeMillis();
	}

	/**
	 * 날짜를 기준으로 중복되지 않는 이름을 만듬. 태그노트나 스킬노트등의 url항목 기본값 용으로 사용.
	 * 
	 * @return
	 */
	public static final String getUniqueName() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "u" + getDateString("yyyyMMddHHmmss") + System.currentTimeMillis();
	}

	/**
	 * 선택한 날짜에 특정일수를 더한다.
	 * 
	 * @param dt
	 *            변화할 대상
	 * @param days
	 *            추가일수
	 * @return
	 */
	public static final Date add(Date dt, long days) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, (int) days);
		return c.getTime();
	}

	/**
	 * 선택한 날짜에 특정시간을 더한다.
	 * 
	 * @param dt
	 * @param hours
	 * @return
	 */
	public static final Date addTime(Date dt, long hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.HOUR_OF_DAY, (int) hours);
		return cal.getTime();
	}

	public static Long sub(Date dt) {
		Date now = new Date();
		return (now.getTime() - dt.getTime()) / 1000;
	}
}
