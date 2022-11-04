package com.chunjae.doctormath.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	protected Logger logger = LoggerFactory.getLogger(StringUtil.class);

	@Autowired
	private static MessageSource messageSource;

	public static String stringNull(Object object){
		String result = "";
		if(object == null || String.valueOf(object).equals("null") || String.valueOf(object).equals("") ){
			result   =   "";
		}else {
			result = String.valueOf(object);
		}
		return result;
	}
	
	public static String stringNullChg(Object object, String res){
		String result = "";
		if(object == null || String.valueOf(object).equals("null") || String.valueOf(object).equals("") ){
			result   =   "";
			if(res != null) {
				result = res;
			}
		}else {
			result = String.valueOf(object);
		}
		return result;
	}
	
	//SHA256암호화
	public static String encryptionSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<byteData.length;i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
			sha = null;
		}
		return sha;
	}
	
	public static String encryptionSHA512(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-512");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<byteData.length;i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
			sha = null;
		}
		return sha;
	}
		//base64인코더
	public String base64Encode(String str) throws UnsupportedEncodingException{
		String result = "";
		
		byte[] targetBytes = str.getBytes("UTF-8");
	        
        // Base64 인코딩 ///////////////////////////////////////////////////
        Encoder encoder = Base64.getEncoder();
        
        // Encoder#encode(byte[] src) :: 바이트배열로 반환
//	        byte[] encodedBytes = encoder.encode(targetBytes);
//	        System.out.println(new String(encodedBytes));
        
        // Encoder#encodeToString(byte[] src) :: 문자열로 반환
        String encodedString = encoder.encodeToString(targetBytes);
        
        result = encodedString;
        
		return result;
	}

	//base64디코더
	public String base64Decode(String str) throws UnsupportedEncodingException{
		String result = "";
		
     // Base64 디코딩 ///////////////////////////////////////////////////
        Decoder decoder = Base64.getDecoder();
        
        // Decoder#decode(bytes[] src) 
//	        byte[] decodedBytes1 = decoder.decode(str);
        // Decoder#decode(String src)
        byte[] decodedBytes2 = decoder.decode(str);
        
        // 디코딩한 문자열을 표시
//	        String decodedString = new String(decodedBytes1, "UTF-8");
//	        System.out.println(decodedString);
        
        result = new String(decodedBytes2, "UTF-8");
        
		return result;
	}
    public static boolean byteCheck(String txt, int standardByte) {
        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;
 
        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
            } else {
                etc++;
            }
        }
 
        int txtByte = en + ko + etc;
        if (txtByte > standardByte) {
            return false;
        } else {
            return true;
        }
    }

	public static byte[] base64EncodeToBinary(byte[] buffer){
		return Base64.getEncoder().encode(buffer);
	}

	public static Map<String,Object> jsonToMap(String jsonString) throws Exception {
		if(null == jsonString || "".equals(jsonString)) return null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = new HashMap<String,Object>();
		map = mapper.readValue(jsonString, new TypeReference<Map<String,Object>>(){});
		return map;
	}

	public static Map<String,Object> currentDate(){
		Map<String,Object> map = new HashMap<String,Object>();

		Calendar nowCal = Calendar.getInstance();
		SimpleDateFormat cDate = new SimpleDateFormat("YYYY.MM.dd");

		int year = nowCal.get(Calendar.YEAR) ;
		int month = nowCal.get(Calendar.MONTH) ;
		int day  = nowCal.get(Calendar.DAY_OF_MONTH) ;
		int minute = nowCal.get(Calendar.MINUTE);
		int second = nowCal.get(Calendar.SECOND);
		map.put("year", stringNullChg(year, "0"));
		map.put("month", stringNullChg(month, "0"));
		map.put("day", stringNullChg(day, "0"));
		map.put("minute", stringNullChg(day, "0"));
		map.put("second", stringNullChg(day, "0"));
		map.put("cDate", cDate.format((nowCal.getTime())));
		return  map;
	}

	public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj ){
		Map<String, Object> map = null;

		try {
			map = new ObjectMapper().readValue(jsonObj.toString(), Map.class) ;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * response 설정
	 * @param response
	 */
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("euc-kr");
	}

	/**
	 * character encoding 지정
	 * @param response
	 * @param encoding encoding 입력(utf-8,euc-kr..)
	 */
	public static void init(HttpServletResponse response,String encoding) {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding(encoding);
	}

	/**
	 * alert 호출
	 * @param response
	 * @param alertText alert 메시지
	 * @throws IOException
	 */
	public static void alert(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "');</script> ");
		out.flush();
	}

	/**
	 * alert 호출, 인코딩 지정
	 * @param response
	 * @param alertText
	 * @param encoding
	 * @throws IOException
	 */
	public static void alert(HttpServletResponse response, String alertText,String encoding) throws IOException {
		init(response,encoding);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "');</script> ");
		out.flush();
	}

	/**
	 * alert발생 후 페이지 이동
	 * @param response HttpServletResponse 객체
	 * @param alertText alert 메시지
	 * @param nextPage 이동할 페이지
	 * @throws IOException
	 */
	public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage)
			throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
		out.flush();
	}

	/**
	 * alert 발생 후 이전 페이지로 이동(새로고침)
	 * @param response
	 * @param alertText
	 * @throws IOException
	 */
	public static void alertAndBackPage(HttpServletResponse response, String alertText) throws IOException {
		init(response);
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + alertText + "'); history.go(-1);</script>");
		out.flush();
	}

}
