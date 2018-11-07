package test;

import java.security.MessageDigest;

public class MD5Security {
	public static void main(String[] args) throws Exception{ 
		String pass = "123456";
		
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] result = md.digest(pass.getBytes());

		StringBuilder sb = new StringBuilder();
		
		for (byte b : result) {
			//如果时负数那么取低八位，然后将其转换为16进制。
			String hexStr = Integer.toHexString(b&0xff);
			if(hexStr.length() ==1) hexStr = 0+hexStr; 
			sb.append(hexStr);
		}
		
		System.out.println(sb);
	}
}
