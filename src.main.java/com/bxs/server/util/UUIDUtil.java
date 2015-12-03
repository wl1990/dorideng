package com.bxs.server.util;

import java.util.UUID;



public class UUIDUtil {
	public static String createUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		if (uuid.length() > 32)
			uuid = uuid.substring(0, 32);
		return uuid;
	}
	
}
