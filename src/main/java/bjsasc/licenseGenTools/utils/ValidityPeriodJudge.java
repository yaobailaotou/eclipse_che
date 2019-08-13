package bjsasc.licenseGenTools.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.Preferences;

public class ValidityPeriodJudge {

	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args) throws Exception {

//		writeSoftwareRegiserTime("");

	}

	/**
	 * 记录软件注册时间——输入License注册成功时的系统时间
	 */
	public static void writeSoftwareRegiserTime(String license){

		//写入注册表
		Preferences pre = Preferences.systemRoot().node("/1.0");
		pre.put("key", LicenseUtils.encodeData(LicenseUtils.encrypt(license)));

		//写入运行目录下
		File file = new File(System.getProperty("user.dir")+"\\注册信息-勿删改移动！\\license.cbs");
    	File dir = file.getParentFile();
    	//创建目录
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	//创建文件
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//写入时间
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(LicenseUtils.encodeData(LicenseUtils.encrypt(license)));
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 记录软件最后一次操作时间——每次启动软件时的系统时间
	 */
	public static void updateSoftewareLastOptTime(String license){
		//写入运行目录下
		File file = new File(System.getProperty("user.dir")+"\\注册信息-勿删改移动！\\license.cbs");
    	File dir = file.getParentFile();
    	//创建目录
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	//创建文件
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//写入时间
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(LicenseUtils.encodeData(LicenseUtils.encrypt(license)));
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断license是否处于有效期
	 * @param registrationDate 注册日期
	 * @param day 使用年限
	 * @return
	 * @throws Exception
	 */
	public static String getValidDate(String registrationDate,int days) throws Exception{
		System.out.println("注册日期"+':'+registrationDate);
		//定义日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date regDate = format.parse(registrationDate);

		//注册日期+使用年限 计算软件使用截止日期 deadlineDate
		Calendar date = Calendar.getInstance();
		date.setTime(regDate);
//		date.add(Calendar.YEAR, +year);
		date.add(Calendar.DATE, +days);
		Date deadDate = date.getTime();
		String deadlineDate = format.format(deadDate);
		System.out.println("截止日期"+':'+deadlineDate);


		//比较系统时间和截止日期确定license是否过期
		System.out.println((new Date()).after(deadDate));
		if(new Date().after(deadDate)){
			System.out.println("License已过期");
		}
		return "";
	}

	/**
	 * 判断试用版本license是否处于有效期
	 * @param beginDate 开始试用日期
	 * @return
	 * @throws Exception
	 */
	public static String getTrialValidDate(String beginDate) throws Exception{
		System.out.println("试用开始日期"+':'+beginDate);
		//定义日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date regDate = format.parse(beginDate);

		//开始试用日期+试用期15天 计算软件使用截止日期 deadlineDate
		Calendar date = Calendar.getInstance();
		date.setTime(regDate);
		date.add(Calendar.DATE, +15);
		Date deadDate = date.getTime();
		String deadlineDate = format.format(deadDate);
		System.out.println("试用截止日期"+':'+deadlineDate);


		//比较系统时间和截止日期确定license是否过期
		System.out.println((new Date()).after(deadDate));
		if(new Date().after(deadDate)){
			System.out.println("试用License已过期");
		}
		return "";
	}
}
