package com.chunjae.doctormath.common;

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

    //SHA256μνΈν
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
    //base64μΈμ½λ
    public String base64Encode(String str) throws UnsupportedEncodingException {
        String result = "";

        byte[] targetBytes = str.getBytes("UTF-8");

        // Base64 μΈμ½λ© ///////////////////////////////////////////////////
        Base64.Encoder encoder = Base64.getEncoder();

        // Encoder#encode(byte[] src) :: λ°μ΄νΈλ°°μ΄λ‘ λ°ν
//	        byte[] encodedBytes = encoder.encode(targetBytes);
//	        System.out.println(new String(encodedBytes));

        // Encoder#encodeToString(byte[] src) :: λ¬Έμμ΄λ‘ λ°ν
        String encodedString = encoder.encodeToString(targetBytes);

        result = encodedString;

        return result;
    }

    //base64λμ½λ
    public String base64Decode(String str) throws UnsupportedEncodingException{
        String result = "";

        // Base64 λμ½λ© ///////////////////////////////////////////////////
        Base64.Decoder decoder = Base64.getDecoder();

        // Decoder#decode(bytes[] src)
//	        byte[] decodedBytes1 = decoder.decode(str);
        // Decoder#decode(String src)
        byte[] decodedBytes2 = decoder.decode(str);

        // λμ½λ©ν λ¬Έμμ΄μ νμ
//	        String decodedString = new String(decodedBytes1, "UTF-8");
//	        System.out.println(decodedString);

        result = new String(decodedBytes2, "UTF-8");

        return result;
    }
    public static boolean byteCheck(String txt, int standardByte) {
        // λ°μ΄νΈ μ²΄ν¬ (μλ¬Έ 1, νκΈ 2, νΉλ¬Έ 1)
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
        SimpleDateFormat cDate = new SimpleDateFormat("YYYY-MM-dd");

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
     * response μ€μ 
     * @param response
     */
    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("euc-kr");
    }

    /**
     * character encoding μ§μ 
     * @param response
     * @param encoding encoding μλ ₯(utf-8,euc-kr..)
     */
    public static void init(HttpServletResponse response,String encoding) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding(encoding);
    }

    /**
     * alert νΈμΆ
     * @param response
     * @param alertText alert λ©μμ§
     * @throws IOException
     */
    public static void alert(HttpServletResponse response, String alertText) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "');</script> ");
        out.flush();
    }

    /**
     * alert νΈμΆ, μΈμ½λ© μ§μ 
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
     * alertλ°μ ν νμ΄μ§ μ΄λ
     * @param response HttpServletResponse κ°μ²΄
     * @param alertText alert λ©μμ§
     * @param nextPage μ΄λν  νμ΄μ§
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
     * alert λ°μ ν μ΄μ  νμ΄μ§λ‘ μ΄λ(μλ‘κ³ μΉ¨)
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
