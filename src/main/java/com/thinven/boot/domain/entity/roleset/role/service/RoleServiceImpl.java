package com.thinven.boot.domain.entity.roleset.role.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinven.boot.domain.entity.employeeset.employee.Employee;
import com.thinven.boot.domain.entity.roleset.role.Role;
import com.thinven.boot.domain.entity.roleset.role.dao.RoleDao;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.domain.entity.service.BindService;
import com.thinven.boot.support.log.Log;

@Service
@Transactional
public class RoleServiceImpl extends BindService<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Message<Role> list(Message<Role> msg) {
		if (msg.isOk()) {
			msg.add("roleList", this.roleDao.list(msg.getParams()));
		}
		return msg;
	}

	@Override
	public Message<Role> add(Message<Role> msg) {
		if (msg.isOk()) {
			Log.info(this, "fire~~~~");
		}
		return msg;
	}

	@Override
	public Message<Employee> makeJson(Message<Employee> msg) {
		try {
			JSONArray out = new JSONArray();
			JSONArray arr = new JSONArray(msg.getParams().getRolejson());
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				Role info = this.roleDao.infoByName(obj.getString("label"));
				if (info == null)
					info = this.roleDao.add(obj.getString("label"));
				obj.put("value", info.getUid());
				out.put(obj);
			}
			Log.info(this, out.toString());
			Log.info(this, msg.getParams().getRolejson());
			msg.getParams().setRolejson(out.toString());
		} catch (JSONException e) {
			msg.setMsg("WAR_MSG_CANNOT_PROCESS", "EMPLOYEE_ROLEJSON");
		}
		return msg;
	}

}
