package com.thinven.boot.support.log;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private static Logger logger = null;

	/**
	 * msg를 콘솔에 표시
	 * 
	 * @param obj
	 *            this 객체
	 * @param msg
	 *            콘솔에 표시하고 싶은 문자열 등록자 작업일자 등록 및 수정사유<br />
	 *            안승철 2018.06.17 디버깅용 로그 작성용
	 */
	public static void debug(Object obj, String msg) {
		logger = LoggerFactory.getLogger(obj.getClass());
		// if (logger.isDebugEnabled()) {
		logger.debug(" * @Message ==>> " + msg);
		// }
	}

	public static void debug(Class<?> cls, String msg) {
		logger = LoggerFactory.getLogger(cls);
		// if (logger.isDebugEnabled()) {
		logger.debug(" * @Message ==>> " + msg);
		// }
	}

	/**
	 * msg를 콘솔에 표시
	 * 
	 * @param obj
	 *            this 객체
	 * @param msg
	 *            콘솔에 표시하고 싶은 문자열 등록자 작업일자 등록 및 수정사유<br />
	 *            안승철 2012.05.28 디버깅용 로그 작성용
	 */
	public static void info(Object obj, String msg) {
		logger = LoggerFactory.getLogger(obj.getClass());
		// if (logger.isDebugEnabled()) {
		logger.info(" * @info ==>> " + msg);
		// }
	}

	public static void info(Class<?> cls, String msg) {
		logger = LoggerFactory.getLogger(cls);
		// if (logger.isDebugEnabled()) {
		logger.info(" * @Message ==>> " + msg);
		// }
	}

	/**
	 * msg를 콘솔에 표시
	 * 
	 * @param obj
	 *            this 객체
	 * @param msg
	 *            콘솔에 표시하고 싶은 문자열 등록자 작업일자 등록 및 수정사유<br />
	 *            안승철 2012.05.28 디버깅용 로그 작성용
	 */
	public static void error(Object obj, String msg) {
		logger = LoggerFactory.getLogger(obj.getClass());
		// if (logger.isDebugEnabled()) {
		logger.error(" * @error ==>> " + msg);
		// }
	}

	public static void error(Class<?> cls, String msg) {
		logger = LoggerFactory.getLogger(cls);
		// if (logger.isDebugEnabled()) {
		logger.error(" * @error ==>> " + msg);
		// }
	}

	/**
	 * 파라미터 정보를 콘솔에 표시
	 * 
	 * @param obj
	 *            this 객체
	 * @param msg
	 *            콘솔에 표시하고 싶은 문자열 등록자 작업일자 등록 및 수정사유<br />
	 *            안승철 2013.10.30 디버깅용 로그 작성용
	 */
	public static void param(Object obj, String msg) {
		logger = LoggerFactory.getLogger(obj.getClass());
		// if (logger.isDebugEnabled()) {
		logger.info(" * @Param ==>> " + msg);
		// }
	}

	/**
	 * 바인딩 정보를 콘솔에 표시
	 * 
	 * @param obj
	 *            this 객체
	 * @param msg
	 *            콘솔에 표시하고 싶은 문자열 등록자 작업일자 등록 및 수정사유<br />
	 *            안승철 2013.10.30 디버깅용 로그 작성용
	 */
	public static void binding(Object obj, String msg) {
		logger = LoggerFactory.getLogger(obj.getClass());
		// if (logger.isDebugEnabled()) {
		logger.info(" * @Bind  ==>> " + msg);
		// }
	}

	/**
	 * request객체의 모든 파라미터를 출력한다.
	 * 
	 * @param obj
	 *            : this
	 * @param request
	 */
	public static void showParameter(Object obj, HttpServletRequest request) {
		Enumeration<?> paramNames = request.getParameterNames();
		String sessionid = request.getSession().getId();
		logger = LoggerFactory.getLogger(obj.getClass());
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					logger.info(" * @Param ==>> " + paramName + ": No Value {" + sessionid + "}");
				else
					logger.info(" * @Param ==>> " + paramName + ": " + paramValue + " {" + sessionid + "}");
			} else {
				logger.info(" * @Param ==>> " + "paramName: " + paramName + " {" + sessionid + "}");
				for (int i = 0; i < paramValues.length; i++) {
					if (paramValues[i].length() > 0) {
						logger.info(" * @Param ==>> " + "Value[" + i + "]: " + paramValues[i] + " {" + sessionid + "}");
					}
				}
			}
		}
	}
}
