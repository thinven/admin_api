package com.thinven.boot.support.domain.entity.model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class Message<G> implements MessageSourceAware {

	private static MessageSource accessor = null;
	private String key;
	private String param;// 클라이언트용 메시지.
	// version2
	private Map<String, Object> modelMap = new HashMap<String, Object>();
	private G params;// 클라이언트에서 받은 정보.
	private G info;// 디비에서 로드한 정보.

	public Message() {
		this.key = "SUCCESS";
		this.param = "";
	}

	// Behavior
	/**
	 * KEY에 해당하는 메세지 반환
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		return accessor.getMessage(key, null, Locale.getDefault());
	}

	/**
	 * KEY에 해당하는 메세지 반환 (외부인터페이스 단순화용)
	 * 
	 * @param key
	 * @param param
	 * @return
	 */
	public static String getMessage(String key, String param) {
		param = getMessage(param);
		return getMessage(key, new String[] { param });
	}

	/**
	 * KEY에 해당하는 메세지 반환 (외부인터페이스 단순화용)
	 * 
	 * @param key
	 * @param param
	 * @param param2
	 * @return
	 */
	public static String getMessage(String key, String param, String param2) {
		return getMessage(key, new String[] { getMessage(param), getMessage(param2) });
	}

	/**
	 * KEY에 해당하는 메세지 반환
	 * 
	 * @param key
	 * @param objs
	 * @return
	 */
	public static String getMessage(String key, Object[] objs) {
		return accessor.getMessage(key, objs, Locale.getDefault());
	}

	/**
	 * 실제 표시될 메시지 반환
	 * 
	 * @return
	 */
	public String getDesc() {
		String msg = "";
		if ("".equals(this.param)) {
			msg = getMessage(this.key);
		} else {
			msg = getMessage(this.key, this.param);
		}
		return msg;
	}

	/**
	 * 현재 프로세스 상태 반환.
	 * 
	 * @return
	 */
	public boolean isOk() {
		if (this.getKey().equals("SUCCESS"))
			return true;
		else
			return false;
	}

	/**
	 * 클라이언트로 전송할 객체 버퍼에 담기.
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		modelMap.put(key, value);
	}

	/**
	 * 클라이언트로 전송할 모델뷰 담기.
	 * 
	 * @return
	 */
	public ModelAndView toMAV() {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addAllObjects(modelMap);
		mav.addObject("key", this.getKey());
		mav.addObject("desc", this.getDesc());
		return mav;
	}

	public ModelAndView toMAV(ModelMap mm) {
		mm.clear();
		return this.toMAV();
	}

	@SuppressWarnings("unchecked")
	public ModelAndView toMAVPDF(String pdfView) {
		ModelAndView mav = new ModelAndView(pdfView, (Map<String, ?>) modelMap.get("params"));
		return mav;
	}

	// Get & Set
	public String getKey() {
		return this.key;
	}

	public void setMsg(String key) {
		this.key = key;
	}

	public void setMsg(String key, String param) {
		this.setMsg(key);
		this.param = param;
	}

	@JsonIgnore
	public G getParams() {
		return params;
	}

	public void setParams(G params) {
		this.params = params;
	}

	@JsonIgnore
	public G getInfo() {
		return info;
	}

	public void setInfo(G info) {
		this.info = info;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		Message.accessor = messageSource;
	}

}
