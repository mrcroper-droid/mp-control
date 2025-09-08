package com.wcsoft.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.alibaba.fastjson2.JSONObject;
import com.wcsoft.exception.RoperRuntimeException;

public class Utils {
  
  private static SecureRandom a = new SecureRandom();
  
  private static final String[] b = new String[] { 
      "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", 
      "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13", 
      "14", "15", 
      "16", "17", "18", "19", "1a", "1b", "1c", "1d", 
      "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", 
      "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30", "31", 
      "32", 
      "33", "34", "35", "36", "37", "38", "39", "3a", "3b", 
      "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", 
      "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", 
      "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", 
      "5a", "5b", "5c", "5d", "5e", "5f", "60", "61", "62", "63", 
      "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", 
      "6d", 
      "6e", "6f", "70", "71", "72", "73", "74", "75", "76", "77", 
      "78", "79", "7a", "7b", "7c", "7d", "7e", "7f", "80", "81", 
      "82", "83", "84", "85", "86", "87", "88", "89", 
      "8a", "8b", 
      "8c", "8d", "8e", "8f", "90", "91", "92", "93", "94", "95", 
      "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f", 
      "a0", "a1", "a2", "a3", "a4", "a5", "a6", 
      "a7", "a8", "a9", 
      "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", 
      "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", 
      "be", "bf", "c0", "c1", "c2", "c3", 
      "c4", "c5", "c6", "c7", 
      "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", 
      "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "da", "db", 
      "dc", "dd", "de", "df", "e0", 
      "e1", "e2", "e3", "e4", "e5", 
      "e6", "e7", "e8", "e9", "ea", "eb", "ec", "ed", "ee", "ef", 
      "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", 
      "fa", "fb", "fc", "fd", 
      "fe", "ff" };
  
  public static String byte2Hex(byte[] inbuf) {
    return byte2Hex(inbuf, 0, inbuf.length);
  }
  
  public static void checkParams(Map<String, Object> params, String... strs) {
	  for(String str: strs) {
		 if(params.get(str) == null) {
			 throw new RoperRuntimeException("参数"+ str +"缺失");
		 }
	  }
	  if(params.keySet().size()!= strs.length ) {
		  throw new RoperRuntimeException("参数不合法");
	  }
  }
  
  
  public static String byte2Hex(byte[] inbuf, int offset, int length) {
    StringBuffer stringBuffer = new StringBuffer();
    length += offset;
    for (int i = offset; i < length; i++) {
      String str = b[inbuf[i] & 0xFF];
      stringBuffer.append(str);
    } 
    return stringBuffer.toString();
  }
  
  public static byte[] hex2Byte(String inbuf) {
    int i;
    byte[] arrayOfByte = new byte[i = inbuf.length() / 2];
    for (byte b = 0; b < i; b++) {
      int j = a(inbuf.charAt(b << 1));
      int k = a(inbuf.charAt((b << 1) + 1));
      arrayOfByte[b] = (byte)(j << 4 | k);
    } 
    return arrayOfByte;
  }
  
  private static int a(char paramChar) {
    if (paramChar >= '0' && paramChar <= '9')
      return paramChar - 48; 
    if (paramChar >= 'a' && paramChar <= 'f')
      return paramChar - 97 + 10; 
    if (paramChar >= 'A' && paramChar <= 'F')
      return paramChar - 65 + 10; 
    throw new IllegalArgumentException();
  }
  
  public static byte[] generateSalt(int numBytes) {
    byte[] arrayOfByte = new byte[numBytes];
    a.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
  
  public static String getCN(Object cert) {
    String str;
    int i;
    if ((i = (str = getSubjectDN(cert)).indexOf("CN=")) == -1)
      return ""; 
    int j;
    if ((j = str.indexOf(",", i + 1)) == -1)
      return str.substring(i); 
    return str.substring(i, j);
  }
  
  public static String getCNwithSN(X509Certificate cert) {
    String str1, str2 = prefixZero(Integer.valueOf(((str1 = getCN(cert)).getBytes()).length), 2);
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer()).append(str2);
    stringBuffer.append(str1);
    BigInteger bigInteger;
    String str3;
    if ((str3 = (bigInteger = cert.getSerialNumber()).toString(16)).length() % 2 != 0)
      str3 = "0" + str3; 
    String str4 = prefixZero(Integer.valueOf(str3.length()), 2);
    stringBuffer.append(str4);
    stringBuffer.append(str3);
    return stringBuffer.toString();
  }
  
  public static String getSN(Object cert) {
    BigInteger bigInteger;
    if (cert instanceof X509Certificate) {
      bigInteger = ((X509Certificate)cert).getSerialNumber();
    } else {
      bigInteger = ((X509Certificate)cert).getSerialNumber();
    } 
    String str;
    if ((str = bigInteger.toString(16)).length() % 2 != 0)
      str = "0" + str; 
    return str;
  }
  
  public static String getIssuerDN(Object cert) {
    if (cert instanceof X509Certificate)
      return ((X509Certificate)cert).getIssuerDN().getName(); 
    return ((X509Certificate)cert).getIssuerDN().getName();
  }
  
  public static String getSubjectDN(Object cert) {
    if (cert instanceof X509Certificate)
      return ((X509Certificate)cert).getSubjectDN().getName(); 
    return ((X509Certificate)cert).getSubjectDN().getName();
  }
  
  public static PublicKey getPublicKey(Object cert) {
    if (cert instanceof X509Certificate)
      return ((X509Certificate)cert).getPublicKey(); 
    return ((X509Certificate)cert).getPublicKey();
  }
  
  public static boolean isAscii(String sToTest) {
    for (byte b = 0; b < sToTest.length(); b++) {
      if (sToTest.charAt(b) > '')
        return false; 
    } 
    return true;
  }
  
  public static boolean isNumeric(String sNum) {
    try {
      Double.valueOf(sNum);
      return true;
    } catch (Exception exception) {
      return false;
    } 
  }
  
  public static Integer conventInteger2Z(Integer num) {
		if(num == null) {
			return num = 0;
		}
		return num;
	}
  
  
  public static String trim(Object s) {
    return (s == null) ? "" : s.toString().trim();
  }
  
  public static String null2Empty(Object s) {
    return (s == null) ? "" : s.toString();
  }
  
  public static String prefixZero(Object obj, int iLength) {
    StringBuffer stringBuffer = new StringBuffer(iLength);
    String str = null2Empty(obj);
    int i = iLength - str.length();
    for (byte b = 0; b < i; b++)
      stringBuffer.append('0'); 
    stringBuffer.append(str);
    return stringBuffer.toString();
  }
  
  
  public static String scale(String strdecimal, String delchar, int maxlen) {
    StringBuffer stringBuffer = new StringBuffer();
    int i = strdecimal.length();
    byte b;
    for (b = 0; b < i; b++) {
      if (delchar.indexOf(strdecimal.charAt(b)) == -1)
        stringBuffer.append(strdecimal.charAt(b)); 
    } 
    if ((i = stringBuffer.length()) < maxlen)
      for (b = 0; b < maxlen - i; b++)
        stringBuffer.insert(0, '0');  
    return stringBuffer.toString();
  }
  
  public static String[] getLocalHostAddresses() {
    InetAddress[] arrayOfInetAddress;
    String[] arrayOfString = new String[(arrayOfInetAddress = getLocalAddresses()).length];
    for (byte b = 0; b < arrayOfInetAddress.length; b++)
      arrayOfString[b] = arrayOfInetAddress[b].getHostAddress(); 
    return arrayOfString;
  }
  
  public static InetAddress[] getLocalAddresses() {
    try {
      return InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException unknownHostException) {
      throw new RuntimeException(unknownHostException);
    } 
  }
  
  public static String getLocalName() {
    String str;
    try {
      InetAddress inetAddress;
      if ((str = (inetAddress = InetAddress.getLocalHost()).getHostName()) == null)
        str = inetAddress.getHostAddress(); 
    } catch (UnknownHostException unknownHostException) {
      str = "";
    } 
    return str;
  }
  
  public static boolean isLocalAddress(String ip) {
    try {
      return isLocalAddress(InetAddress.getByName(ip));
    } catch (UnknownHostException unknownHostException) {
      throw new RuntimeException(unknownHostException);
    } 
  }
  
  public static boolean isLocalAddress(InetAddress address) {
    if (address == null)
      return false; 
    if (address.isAnyLocalAddress() || address.isLoopbackAddress())
      return true; 
    InetAddress[] arrayOfInetAddress = getLocalAddresses();
    for (byte b = 0; b < arrayOfInetAddress.length; b++) {
      if (arrayOfInetAddress[b].equals(address))
        return true; 
    } 
    return false;
  }
  
  public static Set<String> str2Set(String str) {
    HashSet<String> hashSet = new HashSet();
    if (str != null) {
      String[] arrayOfString = str.split(",");
      for (byte b = 0; b < arrayOfString.length; b++)
        hashSet.add(arrayOfString[b]); 
    } 
    return hashSet;
  }
  
  public static Integer getInteger(Object object) {
    if (object == null)
      return null; 
    if (object instanceof Integer)
      return (Integer)object; 
    if (object instanceof String) {
      if (((String)object).equals(""))
        return null; 
      return new Integer((String)object);
    } 
    if (object instanceof Number)
      return new Integer(((Number)object).intValue()); 
    throw new IllegalArgumentException();
  }
  
  
  public static Double getDouble(Object object) {
	    if (object == null)
	      return null; 
	    if (object instanceof Double)
	      return (Double)object; 
	    if (object instanceof String) {
	      if (((String)object).equals(""))
	        return null; 
	      return new Double((String)object);
	    } 
	    if (object instanceof Number)
	      return new Double(((Number)object).doubleValue()); 
	    throw new IllegalArgumentException();
	  }
  
  public static Long getLong(Object object) {
    if (object == null)
      return null; 
    if (object instanceof Long)
      return (Long)object; 
    if (object instanceof String) {
      if (((String)object).equals(""))
        return null; 
      return new Long((String)object);
    } 
    if (object instanceof Number)
      return new Long(((Number)object).longValue()); 
    throw new IllegalArgumentException();
  }
  
  public static String getString(Object object) {
    if (object == null)
      return null; 
    if (object instanceof String)
      return (String)object; 
    return object.toString();
  }
  
  public static BigDecimal getBigDecimal(Object object) {
    if (object == null)
      return null; 
    if (object instanceof BigDecimal)
      return (BigDecimal)object; 
    if (object instanceof String)
      return new BigDecimal((String)object); 
    if (object instanceof Number)
      return new BigDecimal(((Number)object).doubleValue()); 
    throw new IllegalArgumentException();
  }
  
  public static int corral(int value, int low, int high) {
    if (value < low)
      return low; 
    if (value > high)
      return high; 
    return value;
  }
  
  public static boolean isEmpty(String str) {
    return !(str != null && str.length() != 0);
  }
  
  public static boolean isNotEmpty(String str) {
    return (str != null && str.length() != 0);
  }
  
  public static boolean isBlank(Object obj) {
    String str = (String)obj;
    return !(str != null && str.trim().length() != 0);
  }
  
  public static boolean isNotBlank(Object obj) {
	String str = (String)obj;
    return (str != null && str.trim().length() != 0);
  }
  
  public static boolean isIn(String ranges, String str2test) {
    StringTokenizer stringTokenizer = new StringTokenizer(ranges, ", ");
    while (stringTokenizer.hasMoreTokens()) {
      if (stringTokenizer.nextToken().equals(str2test))
        return true; 
    } 
    return false;
  }
  
  public static boolean isInRange(String ranges, String str2test) {
    if (ranges.indexOf(",") == -1)
      return isIn(ranges, str2test); 
    StringTokenizer stringTokenizer = new StringTokenizer(ranges, ", ");
    while (stringTokenizer.hasMoreTokens()) {
      String str;
      int i;
      if ((i = (str = stringTokenizer.nextToken()).indexOf("-")) == -1) {
        if (str.equals(str2test))
          return true; 
        continue;
      } 
      try {
        String str1 = str.substring(0, i);
        String str2 = str.substring(i + 1);
        int j;
        if ((j = str1.length()) != str2.length() || j != str2test.length())
          continue; 
        if (str1.compareTo(str2test) <= 0 && str2.compareTo(str2test) >= 0)
          return true; 
      } catch (Exception exception) {}
    } 
    return false;
  }
  
  public static String rp(String str, char ch, char rep) {
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer(str.length())).setLength(str.length());
    for (byte b = 0; b < str.length(); b++) {
      char c;
      if ((c = str.charAt(b)) == ch) {
        stringBuffer.setCharAt(b, rep);
      } else {
        stringBuffer.setCharAt(b, c);
      } 
    } 
    return stringBuffer.toString().intern();
  }
  
  public static String rm(String str, char... ch) {
    StringBuffer stringBuffer = new StringBuffer(str.length());
    for (byte b = 0; b < str.length(); b++) {
      char c = str.charAt(b);
      char[] arrayOfChar;
      int i = (arrayOfChar = ch).length;
      byte b1 = 0;
      while (true) {
        if (b1 >= i) {
          stringBuffer.append(c);
          break;
        } 
        char c1 = arrayOfChar[b1];
        if (c != c1) {
          b1++;
          continue;
        } 
        break;
      } 
    } 
    return stringBuffer.toString().intern();
  }
  
  public static String rep(char ch, int count) {
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer(count)).setLength(count);
    for (byte b = 0; b < count; b++)
      stringBuffer.setCharAt(b, ch); 
    return stringBuffer.toString().intern();
  }
  
  public static String rmDecimalPoint(String str, int hiddenDecimal) {
    StringTokenizer stringTokenizer;
    String str1 = (stringTokenizer = new StringTokenizer(str, ".")).nextToken();
    String str2;
    int i = (str2 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "").length();
    StringBuffer stringBuffer = new StringBuffer(str1);
    int j;
    if ((j = i - hiddenDecimal) == 0) {
      stringBuffer.append(str2);
    } else if (j > 0) {
      stringBuffer.append(str2.substring(0, hiddenDecimal));
    } else {
      j = -j;
      stringBuffer.append(str2);
      for (byte b = 0; b < j; b++)
        stringBuffer.append('0'); 
    } 
    return stringBuffer.toString();
  }
  
  public static String delChar(String str, char ch) {
    if (str == null || str.equals(""))
      return str; 
    byte[] arrayOfByte1;
    int i;
    byte[] arrayOfByte2 = new byte[i = (arrayOfByte1 = str.getBytes()).length];
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      if (arrayOfByte1[b2] != ch)
        arrayOfByte2[b1++] = arrayOfByte1[b2]; 
    } 
    return new String(arrayOfByte2, 0, b1);
  }
  
  public static boolean allNoneZeros(int[] intArray) {
    for (byte b = 0; b < intArray.length; b++) {
      if (intArray[b] == 0)
        return false; 
    } 
    return true;
  }
  
  public static String getCharsetFromContentType(String contentType, String defaultValue) {
    int i;
    String str;
    if (contentType != null && (
      
      i = contentType.indexOf('=')) != -1 && (
      
      str = trim(contentType.substring(i + 1))).length() > 0)
      try {
        "".getBytes(str);
        return str;
      } catch (Exception exception) {} 
    return defaultValue;
  }
  
  public static String full2half(String str) {
    if (str == null || str.length() == 0)
      return str; 
    StringBuilder stringBuilder = new StringBuilder(str.length());
    for (byte b = 0; b < str.length(); b++) {
      String str1 = str.substring(b, b + 1);
      try {
        byte[] arrayOfByte;
        if (((arrayOfByte = str1.getBytes("UnicodeBigUnmarked"))[0] == 48 && arrayOfByte[1] == 0) || (arrayOfByte[0] == -1 && arrayOfByte[1] > 0 && arrayOfByte[1] < 95)) {
          stringBuilder.append((char)(arrayOfByte[1] + 32));
        } else {
          stringBuilder.append(str1);
        } 
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        stringBuilder.append(str1);
      } 
    } 
    return stringBuilder.toString();
  }
  
  
  private static String c = "afa;j324n0xdfdsnfDDfasn334304@##@$";
  
  public static final String CLIENT_CERTIFICATES_ATTRIBUTE_NAME = "javax.net.ssl.peer_certificates";
  
  public static final String digest(String s) {
    String str2, str1 = c;
    try {
      MessageDigest messageDigest;
      (messageDigest = MessageDigest.getInstance("SHA1")).update(s.getBytes("UTF-8"));
      messageDigest.update(str1.getBytes("UTF-8"));
      messageDigest.update("Copyright 2014 Beijing CloudCore Network Technology Inc.".getBytes("UTF-8"));
      byte[] arrayOfByte;
      str2 = byte2Hex(arrayOfByte = messageDigest.digest());
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      str2 = null;
    } catch (Exception exception) {
      str2 = null;
    } 
    return str2;
  }
  
  public static final byte[] digest(byte[] bytes) {
    try {
      MessageDigest messageDigest;
      (messageDigest = MessageDigest.getInstance("SHA1")).update(bytes);
      byte[] arrayOfByte;
      return arrayOfByte = messageDigest.digest();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  
  public static String getLocalHostName() {
    String str;
    try {
      InetAddress inetAddress;
      if ((str = (inetAddress = InetAddress.getLocalHost()).getHostName()) == null)
        str = inetAddress.getHostAddress(); 
    } catch (UnknownHostException unknownHostException) {
      str = "";
    } 
    return str;
  }
  
  public static String getLocalAddress() {
    try {
      InetAddress inetAddress;
      return (inetAddress = InetAddress.getLocalHost()).getHostAddress();
    } catch (UnknownHostException unknownHostException) {
      return "";
    } 
  }
  
  public static NetworkInterface[] getHardwareInterface(String... ips) {
    Enumeration<NetworkInterface> enumeration;
    try {
      enumeration = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException socketException) {
      return new NetworkInterface[0];
    } 
    ArrayList<NetworkInterface> arrayList = new ArrayList();
    List<String> list = Arrays.asList(ips);
    while (enumeration.hasMoreElements()) {
      NetworkInterface networkInterface = enumeration.nextElement();
      try {
        if (!networkInterface.isUp() || networkInterface.isLoopback() || networkInterface.isPointToPoint() || networkInterface.isVirtual())
          continue; 
      } catch (SocketException socketException) {}
      if (list.isEmpty()) {
        arrayList.add(networkInterface);
        continue;
      } 
      for (Enumeration<InetAddress> enumeration1 = networkInterface.getInetAddresses(); enumeration1.hasMoreElements(); ) {
        InetAddress inetAddress = enumeration1.nextElement();
        if (list.contains(inetAddress.getHostAddress())) {
          arrayList.add(networkInterface);
          break;
        } 
      } 
    } 
    return arrayList.<NetworkInterface>toArray(new NetworkInterface[arrayList.size()]);
  }
  
  public static List<Map<String, Object>> map2Arr(Map<String, Object> params){
	ArrayList<Map<String, Object>> resultArr = new ArrayList<>();
	for(String key :params.keySet()) {
		Map<String, Object> map  = new HashMap<>();
		map.put("key", key);
		map.put("value", params.get(key));
		resultArr.add(map);
	}
	return resultArr;
  }
  
  public static BigDecimal obj2BigDecimal(Object obj) {	  
	  return new BigDecimal(Utils.null2Empty(obj));
  }
  
  public static String reWiriteRemark(String planText, String newText) {
	  if(Utils.isNotBlank(planText)) {
		  if(planText.contains(newText)) {
			  return planText;
		  }else {
			  return planText+"、"+ newText;
		  }
	  }else {
		  return newText;
	  }
  }
  /**
   * 不满足2位前面填充0至2位
   * @param str
   * @return
   */
	public static String chargeZero(Object obj){
		String str = null2Empty(obj);
		if(str.length()<2) {
			return 0+str;
		}
		return str;
	}
	
	public static String middleStringAdd(String originStr ,int index, String charge) {
		  StringBuilder sb = new StringBuilder();  
          int count = 0;  
          for (int i = 0; i < originStr.length(); i++) {  
              char c = originStr.charAt(i);  
              sb.append(c);  
              count++;  
              if (count % index == 0) {  
                  sb.append(charge);  
              }  
          }  
          return sb.toString();
	}
	
	/**
	 * 计算同等级的下一个等级
	 * 1->return2; 1.1->return1.2
	 * @param level
	 * @return
	 */
	public static String getNextLevel(String level) {
		int pointIndex = level.lastIndexOf(".");
		if(pointIndex <0) {
			return "" + Integer.valueOf(level)+1;
		}
		String tailLevelString = level.substring(pointIndex+1);
		String headLevelString = level.substring(0, pointIndex);
		int nextTailLevelNum = Integer.valueOf(tailLevelString)+1;
		return headLevelString+"."+ nextTailLevelNum;
	}
	
	public static List<String> getAllUpLevel(String level) {
		List<String> result = new ArrayList<>();
		result.add(level);
		String[] parts = level.split("\\.");  
		for(int i = 0; i < parts.length-1; i++) {  
			level = level.substring(0, level.lastIndexOf("."));
			result.add(level);
		}  
		return result;
	}
	
	public static List<String> getAllUpLevelWithOutSelfLevel(String level) {
		List<String> result = new ArrayList<>();
		String[] parts = level.split("\\.");  
		for(int i = 0; i < parts.length-1; i++) {  
			level = level.substring(0, level.lastIndexOf("."));
			result.add(level);
		}  
		return result;
	}
	
    public static boolean isInteger(BigDecimal number) {  
        if (number == null) {  
            return false;  
        }  
        return number.compareTo(number.setScale(0, BigDecimal.ROUND_HALF_UP)) == 0;  
    }  
    
    public static JSONObject buildMsgValue(Object value) {
    	JSONObject job = new JSONObject();
    	job.put("value", value);
    	return job;
    }
    public static String generateMD5(String input) {
	    try {
		    MessageDigest md = MessageDigest.getInstance("MD5");byte[] messageDigest = md.digest(input .getBytes());
		    StringBuilder hexString = new StringBuilder();
		    for (byte b : messageDigest) 
		    {
		    	String hex = Integer.toHexString(0xFF & b);
			    if (hex.length() == 1) {
			    	hexString.append('0');
			    }
			    hexString.append(hex);
		    }
		    	return hexString.toString();
		    } catch (NoSuchAlgorithmException e) {
		    	e.printStackTrace();
		    	return null;
		    }
    }
	class Area{
		private String provinceCode;
		private String province;
		private String prefectureCode;
		private String prefecture;
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public void setPrefectureCode(String prefectureCode) {
			this.prefectureCode = prefectureCode;
		}
		public void setPrefecture(String prefecture) {
			this.prefecture = prefecture;
		}
	}
	
		
	
	
}
