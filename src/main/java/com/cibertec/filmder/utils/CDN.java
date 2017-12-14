package com.cibertec.filmder.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CDN {

	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "alexandrecg", "api_key", "255463657955263",
			"api_secret", "yrG0k7BG9JFJ5oQI7Ci3lLK62A4"));

	public String subirArchivo(InputStream is) {
		File tempFile = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		try (OutputStream out = new FileOutputStream(tempFile = File.createTempFile("archivoTemporal", null));) {
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Map<?, ?> uploadResult = null;

		try {
			uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("No se pudo subir el archivo. (Class:CDN , Line: 24)");
		} finally {
			tempFile.deleteOnExit();
		}
		return uploadResult.get("secure_url").toString();
	}

}
