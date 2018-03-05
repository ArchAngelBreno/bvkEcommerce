package com.bvk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URLUtils {

	
	public static List<Long> decodeLongList(String decode){
		return Arrays.asList(decode.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
	}
	
	
	public static String decodeParam(String uncode) {
		try {
			return URLDecoder.decode(uncode,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
