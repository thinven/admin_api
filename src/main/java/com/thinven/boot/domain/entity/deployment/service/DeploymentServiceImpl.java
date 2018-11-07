package com.thinven.boot.domain.entity.deployment.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.domain.entity.deployment.Deployment;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.util.FileUtil;

@Service
public class DeploymentServiceImpl implements DeploymentService {

	@Value("${home.root.path}")
	private String homePath;

	@Override
	public Message<Deployment> info(Message<Deployment> msg) {
		if (msg.isOk()) {
			msg.add("fileList", subDirList(this.homePath, ""));
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
			}

		}
		return msg;
	}

	@Override
	public Message<Deployment> delete(Message<Deployment> msg) {
		if (msg.isOk()) {
			JSONObject json = new JSONObject(msg.getParams().getSelected());
			File selectedFile = new File(this.homePath + json.getString("key"));
			if (selectedFile.exists()) {
				if (selectedFile.isFile())
					selectedFile.delete();
				else if (selectedFile.isDirectory())
					FileUtil.deleteFile(this.homePath + json.getString("key"));
			}
		}
		return msg;
	}

	private void createFolder(Message<Deployment> msg) {
		File newFolder = new File(this.homePath + msg.getParams().getParentPath() + msg.getParams().getFolderName());
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
