package com.thinven.boot.domain.entity.deployment.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thinven.boot.domain.entity.deployment.Deployment;
import com.thinven.boot.support.domain.entity.model.Message;

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
