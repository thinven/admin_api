package com.thinven.boot.support.domain.entity.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thinven.boot.support.domain.entity.model.MemberModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.log.Log;
import com.thinven.boot.support.security.AesKeys;
import com.thinven.boot.support.security.AesUtil;
import com.thinven.boot.support.security.RsaUtil;
import com.thinven.boot.support.security.SHA512;
import com.thinven.boot.support.util.ParamUtil;

public class BindService<G> {

	public Message<G> bindP1(Message<G> msg, MemberInfoService memberService) {
		G entity = msg.getParams();
		// 기본암호키 할당.
		AesKeys aeskeys = new AesKeys();
		// p3 에서 rk 까기.
		String tmp = getP(3, entity);
		// Log.info(this, tmp);
		String rk = RsaUtil.decrypt(tmp);
		// Log.info(this, "1. bind rk : " + rk);
		MemberModel membermodel = memberService.infoByRk(rk);
		// p2 에서 aes key 까기.
		if ("guest".equals(rk)) {
			aeskeys = new AesKeys(RsaUtil.decrypt(getP(2, entity)));
			membermodel = new MemberModel(rk);
			// Log.info(this, "2-1. member.uid : " + membermodel.getUid() + ",
			// rk : " + rk);
		} else if (membermodel == null || ("".equals(membermodel.getUid()) && rk.length() == 32)) {
			// Log.info(this, "2-2. war_msg_clear_data : " + rk);
			msg.setMsg("WAR_MSG_CLEAR_DATA");
			return msg;
		} else if ("".equals(membermodel.getUid())) {
			aeskeys = new AesKeys(RsaUtil.decrypt(getP(2, entity)));
			// Log.info(this, "2-3. member.uid : " + membermodel.getUid() + ",
			// rk : " + rk);
		} else {
			aeskeys = new AesKeys(RsaUtil.decrypt(membermodel.getPk(), getP(2, entity)));
			// Log.info(this, "2-4. member.uid : " + membermodel.getUid() + ",
			// rk : " + rk);
		}
		// p1 에서 실자료 까기.
		String realdata = AesUtil.decrypt(aeskeys, getP(1, entity));
		Log.param(this, realdata);
		JsonObject json = new JsonParser().parse(realdata).getAsJsonObject();

		Set<Map.Entry<String, JsonElement>> entries = json.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {
			setValue(entity, entry.getKey(), entry.getValue());
		}
		msg.setParams(entity);
		return msg;
	}

	private void setValue(G entity, String key, JsonElement value) {
		if (key.indexOf(".") > -1) {
			String[] keys = key.split("\\.");
			switch (keys.length) {
			case 2:
				Object child = getPropertyInObject(entity, keys[0]);
				if (child != null) {
					doit(child, keys[1], value, entity, key);
					// Method m = getMethod(child, keys[1]);
					// callMethod(m, child, value, entity, key);
				}
				break;
			default:
				// 3depth deep....pass
			}
		} else {
			doit(entity, key, value, entity, key);
		}
	}

	private String getP(int num, G entity) {
		String result = "";
		try {
			Method m = entity.getClass().getMethod("getP" + num, new Class[] {});
			if (m != null) {
				result = (String) m.invoke(entity, new Object[] {});
			}
		} catch (NoSuchMethodException e) {
			Log.error(this, "BindService.getP() : " + e.toString());
		} catch (SecurityException e) {
			Log.error(this, "BindService.getP() : " + e.toString());
		} catch (IllegalAccessException e) {
			Log.error(this, "BindService.getP() : " + e.toString());
		} catch (IllegalArgumentException e) {
			Log.error(this, "BindService.getP() : " + e.toString());
		} catch (InvocationTargetException e) {
			Log.error(this, "BindService.getP() : " + e.toString());
		}
		return result;
	}

	private Object getPropertyInObject(G entity, String key) {
		Method m = null;
		try {
			m = entity.getClass().getMethod("get" + ParamUtil.toFirstUpper(key), new Class[] {});
			// Log.info(this, ParamUtil.toFirstUpper(key));
		} catch (NoSuchMethodException e) {
			Log.error(this, "BindService.getPropertyInObject() : " + e.toString());
		}
		if (m != null) {
			try {
				Object child = m.invoke(entity, new Object[] {});
				if (child == null) {
					String tmp = this.getClass().getPackage().getName().replace(".service", "");
					String prefix = tmp.substring(0, tmp.lastIndexOf(".") + 1);
					child = Class.forName(prefix + key.toLowerCase() + "." + ParamUtil.toFirstUpper(key)).newInstance();
					Method method = entity.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { child.getClass() });
					method.invoke(entity, child);
				}
				return child;
			} catch (Exception e) {
				Log.error(this, "BindService.getPropertyInObject() : " + e.toString());
			}
		}
		return null;
	}

	private void doit(Object obj, String key, JsonElement value, Object logobj, String logprop) {
		Method m = null;
		try {
			m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { String.class });
			m.invoke(obj, value.getAsString());
			if ("pw".equals(logprop) || "pw2".equals(logprop) || "npw".equals(logprop) || "npw2".equals(logprop)) {
				Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (String) " + SHA512.getDigest(value.getAsString()));
			} else {
				Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (String) " + value.getAsString());
			}
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			try {
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Long.class });
				m.invoke(obj, value.getAsLong());
				Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (long) " + value.getAsLong());
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e0) {
				try {
					m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Long.TYPE });
					m.invoke(obj, value.getAsLong());
					Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (long) " + value.getAsLong());
				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					try {
						m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Double.TYPE });
						m.invoke(obj, value.getAsDouble());
						Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (double) " + value.getAsDouble());
					} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
						try {
							m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Integer.TYPE });
							m.invoke(obj, value.getAsInt());
							Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (int) " + value.getAsInt());
						} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e2_1) {
							try {
								m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Long[].class });
								Long[] tmpArr = null;
								if (value.isJsonArray()) {
									int max = value.getAsJsonArray().size();
									tmpArr = new Long[max];
									for (int i = 0; i < max; i++) {
										JsonElement json = value.getAsJsonArray().get(i);
										tmpArr[i] = json.getAsLong();
										Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop.replace("[]", "") + "[" + i + "] : (Long) "
												+ json.getAsLong());
									}
								} else {
									tmpArr = new Long[1];
									tmpArr[0] = value.getAsLong();
									Log.binding(this,
											logobj.getClass().getSimpleName() + "." + logprop.replace("[]", "") + "[0] : (Long) " + value.getAsLong());
								}
								m.invoke(obj, new Object[] { tmpArr });
							} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
								try {
									m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { String[].class });
									String[] tmpArr = null;
									if (value.isJsonArray()) {
										int max = value.getAsJsonArray().size();
										tmpArr = new String[max];
										for (int i = 0; i < max; i++) {
											JsonElement json = value.getAsJsonArray().get(i);
											tmpArr[i] = json.getAsString();
											Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop.replace("[]", "") + "[" + i + "] : (String) "
													+ json.getAsString());
										}
									} else {
										tmpArr = new String[1];
										tmpArr[0] = value.getAsString();
										Log.binding(this,
												logobj.getClass().getSimpleName() + "." + logprop.replace("[]", "") + "[0] : (String) " + value.getAsString());
									}
									m.invoke(obj, new Object[] { tmpArr });
								} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e4) {
									// 위 6가지 타입을 제외한 타입은 처리하지 않음.
									Log.error(this, "BindService.doit() : " + e4.toString());
								}
							}
						}
					}
				}
			}
		}
	}

	@Deprecated
	protected Method getMethod(Object obj, String key, String type) {
		Method m = null;
		try {
			switch (type) {
			case "string":
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { String.class });
				break;
			case "integer":
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Integer.TYPE });
				break;
			case "long":
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Long.TYPE });
				break;
			case "double":
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { Double.TYPE });
				break;
			case "string[]":
				m = obj.getClass().getMethod("set" + ParamUtil.toFirstUpper(key), new Class[] { String[].class });
				break;
			}
		} catch (NoSuchMethodException e) {
			// 위 5가지 타입을 제외한 타입은 처리하지 않음.
			Log.info(this, "BindService.getMethod() : " + e.toString());
		}
		return m;
	}

	@Deprecated
	protected void callMethod(Method m, Object obj, String value, Object logobj, String logprop) {
		if (m != null) {
			try {
				m.invoke(obj, value);
				Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (String)" + value);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException eString) {
				try {
					m.invoke(obj, ParamUtil.parseInt(value));
					Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (int)" + value);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException eInteger) {
					try {
						m.invoke(obj, ParamUtil.parseLong(value));
						Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (long)" + value);
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException eLong) {
						try {
							m.invoke(obj, ParamUtil.parseDouble(value));
							Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop + " : (double)" + value);
						} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException eDouble) {
							try {
								String[] tmpArr = { value };
								m.invoke(obj, new Object[] { tmpArr });
								Log.binding(this, logobj.getClass().getSimpleName() + "." + logprop.replace("[]", "") + "[0]: " + value);
							} catch (Throwable e4) {
								Log.info(this, "BindService.callMethod() : " + eDouble.toString());
							}
						}
					}
				}
			}
		}
	}

}
