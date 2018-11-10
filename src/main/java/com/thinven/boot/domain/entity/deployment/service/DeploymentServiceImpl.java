package com.thinven.boot.domain.entity.deployment.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.domain.entity.deployment.Deployment;
import com.thinven.boot.domain.entity.employeeset.employeeauth.service.EmployeeAuthService;
import com.thinven.boot.support.domain.entity.model.MemberModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.util.FileUtil;

@Service
public class DeploymentServiceImpl implements DeploymentService {

	@Value("${home.root.path}")
	private String homePath;
	@Value("${home.root.sample.path}")
	private String samplePath;

	@Autowired
	private EmployeeAuthService employeeAuthService;

	// ========================================================================

	@Override
	public Message<Deployment> info(Message<Deployment> msg) {
		if (msg.isOk()) {
			if (DeploymentService.GET_FILELIST.equals(msg.getParams().getWorkspace())) {
				this.filelist(msg);
			} else if (DeploymentService.GET_FILETEXT.equals(msg.getParams().getWorkspace())) {
				this.filetext(msg);
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
			}
		}
		return msg;
	}

	@Override
	public Message<Deployment> add(Message<Deployment> msg) {
		if (msg.isOk()) {
			if (DeploymentService.FILE_UPLOAD.equals(msg.getParams().getWorkspace())) {
				this.uploadFiles(msg);
			} else if (DeploymentService.NEW_FOLDER.equals(msg.getParams().getWorkspace())) {
				this.createFolder(msg);
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
			}
		}
		return msg;
	}

	@Override
	public Message<Deployment> delete(Message<Deployment> msg) {
		if (msg.isOk()) {
			MemberModel mmInfo = this.employeeAuthService.infoByRk(msg.getParams().getRk());
			JSONObject json = new JSONObject(msg.getParams().getSelected());
			File selectedFile = new File(this.homePath + "/" + mmInfo.getId() + "/" + json.getString("key"));
			if (selectedFile.exists()) {
				if (selectedFile.isFile())
					selectedFile.delete();
				else if (selectedFile.isDirectory())
					FileUtil.deleteFile(this.homePath + "/" + mmInfo.getId() + "/" + json.getString("key"));
			}
		}
		return msg;
	}

	// ========================================================================

	private void filelist(Message<Deployment> msg) {
		MemberModel mmInfo = this.employeeAuthService.infoByRk(msg.getParams().getRk());
		if (mmInfo != null) {
			String rootpath = this.homePath + "/" + mmInfo.getId();
			File rootFolder = new File(rootpath);
			if (!rootFolder.exists())
				this.copySampleCode(msg, rootFolder);
			if (msg.isOk())
				msg.add("fileList", subDirList(rootpath, ""));
		} else {
			msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "EMPLOYEEAUTH_ID");
		}
	}

	private void filetext(Message<Deployment> msg) {
		MemberModel mmInfo = this.employeeAuthService.infoByRk(msg.getParams().getRk());
		JSONObject json = new JSONObject(msg.getParams().getSelected());
		File selectedFile = new File(this.homePath + "/" + mmInfo.getId() + "/" + json.getString("key"));
		if (selectedFile.exists()) {
			if (selectedFile.isFile()) {
				try {
					msg.add("filetext", FileUtil.readFile(this.homePath + "/" + mmInfo.getId() + "/" + json.getString("key")));
				} catch (JSONException e) {
					msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
				} catch (IOException e) {
					msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
				}
			} else {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
			}
		} else {
			msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_REQUEST");
		}
	}

	private void copySampleCode(Message<Deployment> msg, File rootFolder) {
		try {
			FileUtils.copyDirectory(new File(this.samplePath), rootFolder);
		} catch (IOException e) {
			msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "EMPLOYEEAUTH_ID");
		}
	}

	private void createFolder(Message<Deployment> msg) {
		MemberModel mmInfo = this.employeeAuthService.infoByRk(msg.getParams().getRk());
		File newFolder = new File(this.homePath + "/" + mmInfo.getId() + "/" + msg.getParams().getParentPath() + msg.getParams().getFolderName());
		if (!newFolder.exists())
			newFolder.mkdirs();
	}

	private void uploadFiles(Message<Deployment> msg) {
		for (MultipartFile mf : msg.getParams().getFiles()) {
			FileUtil.save(msg, mf, this.homePath + msg.getParams().getParentPath());
		}
	}

	private List<Map<String, Object>> subDirList(String source, String parentPath) {
		File dir = new File(source);
		File[] list = dir.listFiles();
		List<Map<String, Object>> dirList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		try {
			for (File tempFile : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				String newParentPath = parentPath + "/" + tempFile.getName();
				map.put("key", newParentPath);
				map.put("title", tempFile.getName());
				if (tempFile.isFile()) {
					map.put("isLeaf", true);
					fileList.add(map);
				} else if (tempFile.isDirectory()) {
					map.put("isLeaf", false);
					map.put("children", subDirList(tempFile.getCanonicalPath(), newParentPath));
					dirList.add(map);
				}
			}
		} catch (IOException e) {
		}
		dirList.addAll(fileList);
		return dirList;
	}
}
