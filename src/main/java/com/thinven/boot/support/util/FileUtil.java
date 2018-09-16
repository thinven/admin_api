package com.thinven.boot.support.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.support.config.Config;
import com.thinven.boot.support.domain.entity.model.FileEntityModel;
import com.thinven.boot.support.domain.entity.model.Message;

public abstract class FileUtil {

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
}
