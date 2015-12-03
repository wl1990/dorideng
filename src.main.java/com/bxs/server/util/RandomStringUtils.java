package com.bxs.server.util;

import java.util.Random;

public class RandomStringUtils {
	private final static String numberTable = "0123456789";
	private final static String upperTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String lowerTable = upperTable.toLowerCase();
	private final static String letterTable = upperTable + lowerTable;
	private final static String allTable = numberTable + letterTable;

	public enum StringType {
		NUMBER, LETTER, UPPER, LOWER, ALL
	}

	public static String random(int length, StringType type) {
		if (length < 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();

		while (length-- > 0) {
			switch (type) {
			case NUMBER:
				result.append(numberTable.charAt(new Random().nextInt(10)));
				break;
			case LETTER:
				result.append(letterTable.charAt(new Random().nextInt(52)));
				break;
			case UPPER:
				result.append(upperTable.charAt(new Random().nextInt(26)));
				break;
			case LOWER:
				result.append(lowerTable.charAt(new Random().nextInt(26)));
				break;
			case ALL:
				result.append(allTable.charAt(new Random().nextInt(62)));
				break;
			default:
				result.append(numberTable.charAt(new Random().nextInt(10)));
			}
		}

		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(RandomStringUtils.random(6, StringType.NUMBER));
		System.out.println(RandomStringUtils.random(6, StringType.UPPER));
		System.out.println(RandomStringUtils.random(6, StringType.LOWER));
		System.out.println(RandomStringUtils.random(6, StringType.ALL));
	}
}