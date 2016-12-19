package com.jerry.sample.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Title: StringUtil
 * @Company: BOCO 
 * @author LFL
 * @date 2010-1-6
 * @Description: 字符串工具类
 */
public class DesEncStringUtil {

	private static final long serialVersionUID = 1L;
	public static final String KEY_MD5 = "MD5";   
	
	/**
	 * @Title: StringUtil
	 * @Description: 构造方法
	 */
	private DesEncStringUtil() {
	}
	 
	public static byte[] encryptMD5(byte[] data) throws Exception {

		// 此 MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA
		// 算法。信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
		// MessageDigest 对象开始被初始化。该对象通过使用 update 方法处理数据。任何时候都可以调用 reset 方法重置摘要。
		// 一旦所有需要更新的数据都已经被更新了，应该调用 digest 方法之一完成哈希计算。
		// 对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest
		// 对象被重新设置成其初始状态。

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}
	    
	/**
	 * @Title: decodeHex
	 * @Description: 字符串转换为byte[]
	 * @param hex
	 * @return byte[]
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * @Title: hexCharToByte
	 * @Description: char转换成byte
	 * @param ch
	 * @return byte
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		case 'A':
			return 0x0A;
		case 'B':
			return 0x0B;
		case 'C':
			return 0x0C;
		case 'D':
			return 0x0D;
		case 'E':
			return 0x0E;
		case 'F':
			return 0x0F;

		}
		return 0x00;
	}

	/**
	 * 貌似没什么用
	 * 
	 * @Title: convEntityToString
	 * @Description:
	 * @param strSrc
	 * @return
	 */
	public static final String convEntityToString(String strSrc) {
		String strDest = "";
		byte[] bysDest;

		ByteArrayOutputStream osByte = new ByteArrayOutputStream();
		java.io.DataOutputStream os = new java.io.DataOutputStream(osByte);

		int len = strSrc.length();
		for (int i = 0; i < len; i++) {
			if (i + 5 < len) {
				if (strSrc.charAt(i) == '&' && strSrc.charAt(i + 1) == '#'
						&& strSrc.charAt(i + 2) == 'x'
						&& strSrc.charAt(i + 5) == ';') {
					String strTemp = strSrc.substring(i + 3, i + 5);
					byte[] bysTemp = decodeHex(strTemp);
					try {
						os.write(bysTemp);
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					i += 5;
					continue;
				}
			}
			try {
				String str = "";
				str += strSrc.charAt(i);
				os.write(str.getBytes("GB2312"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		bysDest = osByte.toByteArray();
		try {
			strDest = new String(bysDest, "GB2312");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strDest;
	}

	/**
	 * @Title: toHexForLog
	 * @Description: 将ByteBuffer转换成字符串
	 * @param bb
	 * @return 字符串
	 */
	public static String toHexForLog(ByteBuffer bb) {
		int originPos = bb.position();
		bb.position(0);

		byte[] arr = new byte[bb.limit()];
		bb.get(arr);
		String str = toHexForLog(arr);

		bb.position(originPos);
		return str;
	}

	/**
	 * @Title: toHexForLog
	 * @Description: 把byte[]转换成字符串(大写字母)
	 * @param bt
	 * @return String
	 */
	public static String toHexForLog(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		String lSep = System.getProperty("line.separator");
		char[] hexChar = Hex.encodeHex(bytes);
		for (int i = 0; i < (hexChar.length / 2); i++) {
			sb.append(hexChar[2 * i]);
			sb.append(hexChar[2 * i + 1]);
			sb.append(' ');
			if ((i + 1) % 16 == 0) {
				sb.append(lSep);
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * @Title: intToByte
	 * @Description: 把整数转换到字节数组
	 * @param number
	 * @return byte[]
	 */
	public static byte[] intToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue(); // 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	/**
	 * @Title: byteToInt
	 * @Description: 把字节数组转换到整数
	 * @param b
	 * @return 
	 */
	public static int byteToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[i] >= 0)
				s = s + b[i];
			else
				s = s + 256 + b[i];
			s = s * 256;
		}
		if (b[3] >= 0) // 最后一个之所以不乘，是因为可能会溢出
			s = s + b[3];
		else
			s = s + 256 + b[3];
		return s;
	}

	/**
	 * @Title: charToByte
	 * @Description: 字符转换到字节数组
	 * @param ch
	 * @return byte[]
	 */
	public static byte[] charToByte(char ch) {
		int temp = (int) ch;
		byte[] b = new byte[2];
		for (int i = b.length - 1; i > -1; i--) {
			b[i] = new Integer(temp & 0xff).byteValue(); // 将最高位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	/**
	 * @Title: byteToChar
	 * @Description: 字节数组转换到字符
	 * @param b
	 * @return char
	 */
	public static char byteToChar(byte[] b) {
		int s = 0;
		if (b[0] > 0)
			s += b[0];
		else
			s += 256 + b[0];
		s *= 256;
		if (b[1] > 0)
			s += b[1];
		else
			s += 256 + b[1];
		char ch = (char) s;
		return ch;
	}

	/**
	 * @Title: byteToDouble
	 * @Description: 字节数组转换到浮点
	 * @param b
	 * @return double
	 */
	public static double byteToDouble(byte[] b) {
		long l;

		l = b[0];
		l &= 0xff;
		l = ((long) b[1] << 8);
		l &= 0xffff;
		l = ((long) b[2] << 16);
		l &= 0xffffff;
		l = ((long) b[3] << 24);
		l &= 0xffffffffl;
		l = ((long) b[4] << 32);
		l &= 0xffffffffffl;

		l = ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l = ((long) b[6] << 48);

		l = ((long) b[7] << 56);
		return Double.longBitsToDouble(l);
	}
	
	
	
	
	/**
	 * @Title: length
	 * @Description: 得到字符串长度。可以是中英文混合字符串。中文字符长度为2。
	 * @param source 字符串
	 * @return 字符串长度
	 */
	public static int length(String source) {
		if (isNull(source)) {
			source = "";
		}
		try {
			source = new String(getBytes(source), "ISO8859_1");
		} catch (IOException e) {
			source = "";
		}
		return source.length();
	}

	/**
	 * @Title: isNull
	 * @Description: 判断字符串是否为空
	 * @param source
	 * @return
	 */
	private static boolean isNull(String source) {
		return false;
	}

	/**
	 * @Title: getBytes
	 * @Description: 得到字符串的字节数组。默认编码方式为 GBK
	 * @param source 字符串
	 * @return 字符串的字节数组
	 */
	public static byte[] getBytes(String source) {
		return getBytes(source, "GBK");
	}

	/**
	 * @Title: getBytes
	 * @Description: 得到字符串的字节数组，如果charCoding为null默认使用GBK
	 * @param source 字符串
	 * @param charCoding 编码方式
	 * @return 字符串的字节数组
	 */
	public static byte[] getBytes(String source, String charCoding) {
		if (source == null) {
			return new byte[] {};
		}
		if (charCoding == null) {
			charCoding = "GBK";
		}
		try {
			return source.getBytes(charCoding);
		} catch (IOException e) {
			return new byte[] {};
		}
	}

	/**
	 * @Title: substring
	 * @Description: 截取指定长度的字符串
	 * @param source 字符串
	 * @param length 指定长度。每个中文字符按 2 计算。
	 * @return 截取后的字符串
	 */
	public static String substring(String source, int length) {
		return substring(source, 0, length, "");
	}

	/**
	 * @Title: substring
	 * @Description: 截取指定长度的字符串
	 * @param source 字符串
	 * @param length 指定长度。每个中文字符按 2 计算。
	 * @param postfix 截断后字符串添加的后缀。默认为“...”。
	 * @return 截取后的字符串
	 */
	public static String substring(String source, int length, String postfix) {
		return substring(source, 0, length, postfix);
	}

	/**
	 * @Title: substring
	 * @Description: 截取指定起始位置、指定长度的字符串
	 * @param source 字符串
	 * @param posStart 起始位置
	 * @param length 指定长度。每个中文字符按 2 计算。
	 * @return 截取后的字符串
	 */
	public static String substring(String source, int posStart, int length) {
		return substring(source, posStart, length, "");
	}

	/**
	 * @Title: substring
	 * @Description: 截取指定起始位置、指定长度的字符串
	 * @param source 字符串
	 * @param posStart 起始位置
	 * @param length 指定长度。每个中文字符按 2 计算。
	 * @param postfix 截断后字符串添加的后缀。默认为“...”。
	 * @return 截取后的字符串
	 */
	public static String substring(String source, int posStart, int length, String postfix) {
		if (posStart < 0) {
			posStart = 0;
		}
		if (length < 0) {
			length = 0;
		}
		if (postfix == null) {
			postfix = "...";
		}
		int strLength = length(source);
		if (strLength <= 0) {
			return "";
		}
		int posEnd = posStart + length;
		if (posEnd > strLength) {
			posEnd = strLength;
		}
		byte[] bs = getBytes(source);
		int i = 0;
		for (i = posStart - 1; i >= 0; i--) {
			if (bs[i] > 0) {
				break;
			}
		}
		if ((posStart - 1 - i) % 2 == 1) {
			posStart = posStart + 1;
		}
		for (i = posEnd - 1; i >= 0; i--) {
			if (bs[i] > 0) {
				break;
			}
		}
		if ((posEnd - 1 - i) % 2 == 1) {
			posEnd = posEnd + 1;
		}
		source = new String(bs, posStart, posEnd - posStart);
		length = length(source);
		if (length < strLength) {
			source = source + postfix;
		}
		return source;
	}

	/**
	 * @Title: hexStringToByte
	 * @Description: 将16进制数转化为Byte数组
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	
	/**
	 * @Title: toByte
	 * @Description: 把字符转换成字节
	 * @param c
	 * @return byte
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * @Title: bytesToHexString
	 * @Description: 把字节数组转换成16进制字符串
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2)
				sb.append(0);
//			sb.append(sTemp.toUpperCase());
			sb.append(sTemp.toLowerCase());
		}
		return sb.toString();
	}
	

	/**
	 * @Title: bytesToObject
	 * @Description: 把字节数组转换为对象
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}
	

	/**
	 * @Title: objectToBytes
	 * @Description: 把可序列化对象转换成字节数组
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static final byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}
	
	/**
	 * @Title: objectToHexString
	 * @Description: 把可序列化对象转换成16进制字符串
	 * @param s
	 * @return 16进制字符串
	 * @throws IOException
	 */

	public static final String objectToHexString(Serializable s) throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	/**
	 * @Title: hexStringToObject
	 * @Description: 把16进制字符串转换成对象
	 * @param hex
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToByte(hex));
	}

	/**
	 * @Title: bcd2Str
	 * @Description: BCD码转为10进制串(阿拉伯数据)
	 * @param bytes BCD码
	 * @return 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}

	/**
	 * @Title: str2Bcd
	 * @Description: 10进制串转为BCD码
	 * @param asc 10进制串
	 * @return BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * @Title: MD5EncodeToHex
	 * @Description: MD5加密字符串，返回加密后的16进制字符串
	 * @param origin MD5加密字符串
	 * @return 16进制字符串
	 */
	public static String MD5EncodeToHex(String origin) {
		return bytesToHexString(MD5Encode(origin));
	}

	/**
	 * @Title: MD5EncodeToHex
	 * @Description: MD5加密字符串，返回加密后的字节数组
	 * @param origin MD5加密字符串
	 * @return 加密后的字节数组
	 */
	public static byte[] MD5Encode(String origin) {
		return MD5Encode(origin.getBytes());
	}

	/**
	 * @Title: MD5EncodeToHex
	 * @Description: MD5加密字节数组，返回加密后的字节数组
	 * @param origin MD5加密字节数组
	 * @return 加密后的字节数组
	 */
	public static byte[] MD5Encode(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}

	}
	

	/** */
	/**
	 * @函数功能: BCD码转ASC码
	 * @输入参数: BCD串
	 * @输出结果: ASC码
	 */
	// public static String BCD2ASC(byte[] bytes) {
	// StringBuffer temp = new StringBuffer(bytes.length * 2);
	//
	// for (int i = 0; i < bytes.length; i++) {
	// int h = ((bytes[i] & 0xf0) >>> 4);
	// int l = (bytes[i] & 0x0f);
	// temp.append(BToA[h]).append( BToA[l]);
	// }
	// return temp.toString() ;
	// }
	
}
