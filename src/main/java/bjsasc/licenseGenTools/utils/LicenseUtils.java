package bjsasc.licenseGenTools.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseUtils {

	private static String keygen = "srqrscbs";

    public static void setKey(String key){
        keygen = key;
    }

    public static void main(String[] args) {
//    	String str = "RDQwQTUwRkVGNTMwQjgxMERENUQ5RTYzNTM1RDM0RTJDMDY1OUZFNjY0NzJDN0E2MDIxMjZBRDJFOUFDRTVERUYzMTdCMTg0MEU1QjBBMDE1QTZEQzRDRjhEMTNFMEQ2RUJEMzcwQjczRjRFQUQ4N0NEQTlERjhCRDBFMUUwQzk=";
//    	System.out.println("初始值：" + str);
//    	System.out.println(str.length());
//    	System.out.println("base64后解密："+Base64Util.decodeData(str));
//    	System.out.println(Base64Util.decodeData(str).length());
//    	System.out.println("解密后：" + decrypt(Base64Util.decodeData(str)));
//    	System.out.println(decrypt(Base64Util.decodeData(str)).length());
    }

    //加密字符串，其中content为需要加密的内容
    public static String encrypt(String content){
        try{
        	KeyGenerator keyGen = KeyGenerator.getInstance("AES");

	        keyGen.init(128, new SecureRandom(keygen.getBytes()));

	        SecretKey secretKey = keyGen.generateKey();

	        byte[] enCodeFormat = secretKey.getEncoded();

	        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES"); // 创建密码器

	        Cipher cipher = Cipher.getInstance("AES");

	        byte[] byteContent = content.getBytes("utf-8");

	        cipher.init(Cipher.ENCRYPT_MODE, key); // 初始化

	        byte[] result = cipher.doFinal(byteContent);

	        String code = parseByteToHexStr(result);

//	        System.out.println("加密后：" + code);

	        return code;

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密字符串，其中content为需要解密的内容
    public static String decrypt(String content){

        byte[] code = parseHexStrToByte(content);

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");

            keyGen.init(128, new SecureRandom(keygen.getBytes()));

            SecretKey secretKey = keyGen.generateKey();

            byte[] enCodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            Cipher cipher = Cipher.getInstance("AES"); // 创建密码器

            cipher.init(Cipher.DECRYPT_MODE, key); // 初始化

            byte[] result = cipher.doFinal(code);

            try {
//            	System.out.println("解密后：" + new String(result, "UTF-8"));

                return new String(result, "UTF-8");

            } catch (UnsupportedEncodingException e) {
            	e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //流转字符串，辅助函数
    private static String parseByteToHexStr(byte buf[]){

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; i++){

            String hex = Integer.toHexString(buf[i] & 0xFF);

            if (hex.length() == 1){
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    //字符串转流，辅助函数
    private static byte[] parseHexStrToByte(String hexStr){

        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];

        for (int i = 0; i < hexStr.length() / 2; i++){

            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);

            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);

            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * BASE64对密钥进行二次处理
     */
    private static final Logger LOG = LoggerFactory.getLogger(Base64.class);
    // 字符串编码
    private static final String UTF_8 = "UTF-8";

    /**
     * 加密字符串
     * @param inputData
     * @return
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOG.error(inputData, e);
        }
        return null;
    }

    /**
     * 解密字符串
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOG.error(inputData, e);
        }
        return null;
    }


	/**
	 * 申请试用版license
	 * @return 机器信息MD5申请码
	 * @throws IOException
	 */
	public static String applyTrialLicense() throws IOException{
		return GetMachineInfo.getHardDiskSerical();
	}

	/**
	 * 购买正式版license
	 * @param functionMessage
	 * @return 三部分信息申请单
	 * @throws IOException
	 */
	public static String buyLicense(List functionMessage) throws IOException{
		String machineInfo = GetMachineInfo.getHardDiskSerical();
		String functionList = "";
		String year = "";
		int days = Integer.parseInt(year)*365+7;
		return "list";
	}

	/**
	 * 试用版升级到正式版，用户在软件页面上触发“升级到正式版”时的响应函数
	 * @param functionMessage
	 * @return 三部分信息申请单
	 * @throws IOException
	 */
	public static String upgrade(List functionMessage) throws IOException{
		String machineInfo = GetMachineInfo.getHardDiskSerical();
		List functionList;
		String year = "";
		int days = Integer.parseInt(year)*365+7;
		return "list";
	}

/*	*//**
	 * 生成license注册码
	 * @param machineInfo
	 * @param functionList
	 * @param days
	 * @return license
	 * @throws IOException
	 *//*
	public static String genLicense(String machineInfo) throws IOException{
		String license="";
		return license;
	}
	public static String genLicense(String machineInfo,List functionList,int days) throws IOException{
		String license="";
		return license;
	}*/

	/**
	 * 用户在页面触发“输入License注册”时的响应函数
	 * @param machineInfo
	 * @throws IOException
	 */
	public static void validateRegister(String codeNumber) throws IOException{

	}

	/**
	 * 扩展功能，用户在软件页面上触发“扩展功能”时的响应函数
	 * @param functionMessage
	 * @return 三部分信息申请单
	 * @throws IOException
	 */
	public static String addFunction(List functionMessage) throws IOException{
		String machineInfo = GetMachineInfo.getHardDiskSerical();
		String functionList = "";
		String year = "";
		int days = Integer.parseInt(year)*365+7;
		return "list";
	}

	/**
	 * license续期，用户在软件页面上触发“License续期”时的响应函数
	 * @param functionMessage
	 * @return 三部分信息申请单
	 * @throws IOException
	 */
	public static String LicenseRenewal(List functionMessage) throws IOException{
		String machineInfo = GetMachineInfo.getHardDiskSerical();
		String functionList = "";
		String year = "";
		int days = Integer.parseInt(year)*365+7;
		return "list";
	}

}



