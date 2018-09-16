package com.thinven.boot.support.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinven.boot.support.config.Config;
import com.thinven.boot.support.domain.entity.model.ImageEntityModel;
import com.thinven.boot.support.domain.entity.model.Message;

public abstract class ImageUtil {

	public static final BufferedImage loadImage(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		return image;
	}

	public static final Message<?> save(Message<?> msg, MultipartFile mf, ImageEntityModel imageentity) {

		if (imageentity.getSize() > ImageEntityModel.IMAGE_LIMIT) {
			msg.setMsg("WAR_MSG_SIZE_OVER", ImageEntityModel.IMAGE_LIMIT + "MB");
		}

		if (msg.isOk()) {
			String filePath = Config.get("basepathImage");

			File f = new File(filePath + imageentity.getOnlyPath());
			if (!f.exists()) {
				f.mkdirs();
			}

			try {
				FileCopyUtils.copy(mf.getBytes(), new FileOutputStream(filePath + imageentity.getPhypath()));

				BufferedImage img = ImageUtil.loadImage(new File(filePath + imageentity.getPhypath()));
				imageentity.setWidth(img.getWidth());
				imageentity.setHeight(img.getHeight());

			} catch (IllegalArgumentException ex) {
				imageentity.setWidth(0);
				imageentity.setHeight(0);
			} catch (FileNotFoundException e) {
				msg.setMsg("WAR_MSG_DATA_NOT_FOUND", "PARAM_FILE");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				msg.setMsg("WAR_MSG_INPUT_ERROR", "PARAM_FILE");
				e.printStackTrace();
			}
		}
		return msg;
	}

	public static final Message<?> save(Message<?> msg, String imgurl, ImageEntityModel imageentity) {

		if (imageentity.getSize() > ImageEntityModel.IMAGE_LIMIT) {
			msg.setMsg("WAR_MSG_SIZE_OVER", ImageEntityModel.IMAGE_LIMIT + "MB");
		}

		if (msg.isOk()) {
			String filePath = Config.get("basepathImage");

			File f = new File(filePath + imageentity.getOnlyPath());
			if (!f.exists()) {
				f.mkdirs();
			}

			try {
				URL voImageURL = new URL(imgurl);

				// 이미지에 해당하는 url을 통하여 커넥션을 진행
				HttpURLConnection voImageReponse = (HttpURLConnection) voImageURL.openConnection();

				// 200_OK 응답에 대해서만 처리
				if (voImageReponse.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStream is = voImageReponse.getInputStream();

					// 응답 코드를 C:\1.jpg에 저장
					FileOutputStream fos = new FileOutputStream(filePath + imageentity.getPhypath());
					BufferedInputStream bis = new BufferedInputStream(is);
					BufferedOutputStream bos = new BufferedOutputStream(fos);

					// 응답 코드를 1024바이트 단위로 저장
					int len = 0;
					byte[] buf = new byte[1024];
					while ((len = bis.read(buf, 0, 1024)) != -1) {
						bos.write(buf, 0, len);
						bos.flush();
					}
					bos.close();
					File file = new File(filePath + imageentity.getPhypath());
					imageentity.setSize(file.length());
					imageentity.setMimetype(new MimetypesFileTypeMap().getContentType(file));

					ImageInputStream iis = ImageIO.createImageInputStream(file);
					Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
					if (iter.hasNext()) {
						ImageReader reader = (ImageReader) iter.next();
						ImageReadParam param = reader.getDefaultReadParam();
						reader.setInput(iis, true, true);
						try {
							BufferedImage bi = reader.read(0, param);
							imageentity.setWidth(bi.getWidth());
							imageentity.setHeight(bi.getHeight());
						} catch (IllegalArgumentException ex) {
							// 비정상적인 JPEG.
							imageentity.setWidth(0);
							imageentity.setHeight(0);
						} finally {
							reader.dispose();
							iis.close();
						}
					}
				} else {
					msg.setMsg("ERR_EXCEPTION", voImageReponse.getResponseMessage());
				}

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

	public static final Message<?> removeImage(ImageEntityModel info, Message<?> msg) {
		if (msg.isOk()) {
			String filePath = Config.get("basepathImage");
			File file = new File(filePath + info.getPhypath());
			if (file.exists()) {
				file.delete();
			}
		}
		return msg;
	}

	public static final Message<?> remove(Message<?> msg, String phypath) {
		if (msg.isOk()) {
			String filePath = Config.get("basepathImage");
			File file = new File(filePath + phypath);
			if (file.exists()) {
				file.delete();
			}
		}
		return msg;
	}
}
