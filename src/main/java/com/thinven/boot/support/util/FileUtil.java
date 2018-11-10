package com.thinven.boot.support.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.support.config.Config;
import com.thinven.boot.support.domain.entity.model.FileEntityModel;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.log.Log;

public abstract class FileUtil {

	public static final Message<?> save(Message<?> msg, MultipartFile mf, String filePath) {
		if (msg.isOk()) {
			String fullPath = filePath + mf.getOriginalFilename();
			Log.info(FileUtil.class, fullPath);
			File f = new File(FileUtil.getOnlyPath(fullPath));
			if (!f.exists())
				f.mkdirs();
			try {
				FileCopyUtils.copy(mf.getBytes(), new FileOutputStream(fullPath));
			} catch (FileNotFoundException e) {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_FILE");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_FILE");
				e.printStackTrace();
			}
		}
		return msg;
	}

	public static final String getOnlyPath(String filePath) {
		int pos = filePath.lastIndexOf("/");
		if (pos > -1) {
			return filePath.substring(0, pos);
		} else {
			return "";
		}
	}

	public static final Message<?> save(Message<?> msg, MultipartFile mf, FileEntityModel fileentity) {

		if (fileentity.getSize() > FileEntityModel.FILE_LIMIT) {
			msg.setMsg("WAR_MSG_SIZE_OVER", FileEntityModel.FILE_LIMIT + "MB");
		}

		if (msg.isOk()) {
			String filePath = Config.get("basepathFile");

			File f = new File(filePath + fileentity.getOnlyPath());
			if (!f.exists()) {
				f.mkdirs();
			}

			try {
				FileCopyUtils.copy(mf.getBytes(), new FileOutputStream(filePath + fileentity.getPhypath()));
			} catch (FileNotFoundException e) {
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_FILE");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_FILE");
				e.printStackTrace();
			}
		}
		return msg;
	}

	public static final Message<?> remove(Message<?> msg, FileEntityModel info) {
		if (msg.isOk()) {
			String filePath = Config.get("basepathFile");
			File file = new File(filePath + info.getPhypath());
			if (file.exists()) {
				file.delete();
			}
		}
		return msg;
	}

	public static void deleteFile(String path) {
		File deleteFolder = new File(path);
		if (deleteFolder.exists()) {
			File[] deleteFolderList = deleteFolder.listFiles();
			for (int i = 0; i < deleteFolderList.length; i++) {
				if (deleteFolderList[i].isFile()) {
					deleteFolderList[i].delete();
				} else {
					deleteFile(deleteFolderList[i].getPath());
				}
				deleteFolderList[i].delete();
			}
			deleteFolder.delete();
		}
	}

	public static String readFile(String path) throws IOException {
		return readFile(path, Charset.forName("UTF-8"));
	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
