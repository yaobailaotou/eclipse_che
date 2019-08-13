package bjsasc.licenseGenTools.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.codec.digest.DigestUtils;

import bjsasc.licenseGenTools.entity.FunctionInfo;
import bjsasc.licenseGenTools.ui.RegisterPrompt;
import bjsasc.licenseGenTools.utils.LicenseUtils;

public class CommonUtils {

	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	static String product = "";

	static String funcList = "";

	static String[] yearInfo = {};

	//功能信息列表
	static List<FunctionInfo> funcInfo = new ArrayList<FunctionInfo>();

	//传入参数
	static List<FunctionInfo> infoList = new ArrayList<FunctionInfo>();

	/**
	 * 判断是否注册
	 * @throws IOException
	 * @throws BackingStoreException
	 */
	@SuppressWarnings("resource")
	public static void registeredOrNot(List<FunctionInfo> infoList,String[] yearList,String productName) throws IOException, BackingStoreException{
//	public void registeredOrNot(String[] yearList) throws IOException{
		/**
		 * 从磁盘上获取license并解析，得到时间信息、机器信息、功能信息
		 * 获取机器信息判断是否匹配，获取系统时间判断是否过期
		 * 成功则进入软件，同时保存信息
		 */
		//将参数product赋给product
		product = productName;

		//将参数yearList赋给yearInfo
		yearInfo = yearList;

		//将参数infoList赋给funcInfo
		funcInfo.addAll(infoList);

		//获取软件运行目录下的license文本信息
		String encoding = "UTF-8";
		File file = new File(System.getProperty("user.dir")+"\\注册信息-勿删改移动！\\license.cbs");

		//license信息文本是否存在
		if(file.isFile() && file.exists()){
			InputStreamReader inputStreamReader = new InputStreamReader(
					new FileInputStream(file), encoding);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = bufferedReader.readLine();
			bufferedReader.close();

			//license解密
			String license = "";
			try{
	    		license = LicenseUtils.decrypt(LicenseUtils.decodeData(str));
	    	}catch(Exception e){
	    		System.out.println("注册信息不合法，请重新注册");
				RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearList, product);
				RegisterPrompt.setVisible(true);
    			return;
	    	}
			//license解析，获取机器信息、生成时间、注册时间、最后操作时间、到期时间、功能列表
			String machineCode = license.split("-")[0];
			String genTime = license.split("-")[1];
			String regTime = license.split("-")[2];
			String loTime = license.split("-")[3];
			String dlTime = license.split("-")[4];
			if(license.split("-").length>5){
				funcList = license.split("-")[5];
			}else{
				funcList = "";
			}

			//判断机器是否匹配
			//截取license中标识机器信息的md5部分
	    	if(!machineCode.equals(DigestUtils.md5Hex(GetMachineInfo.getCPUSerical()
					+ GetMachineInfo.getHardDiskSerical()).toUpperCase())){
				System.out.println("非法机器，请购买后注册");
				RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearList, product);
				RegisterPrompt.setVisible(true);
    			return;
			}
	    	/////////////////////////////////////////////////
			System.out.println("每次运行前初始license："+license);
	    	/**
			 * license生成时间早于注册成功时间
			 * 注册成功时间早于等于最后一次操作时间
			 * 最后一次操作时间早于当前系统时间
			 * 当前系统时间早于有效截止日期
			 */
	    	String sysTime = format.format(new Date());
			if(genTime.compareTo(regTime)<=0 && (regTime.compareTo(loTime)<=0) &&
				(loTime.compareTo(sysTime))<0){
				if(sysTime.compareTo(dlTime)<0){
					System.out.println("注册成功！");
					//更新注册信息中最后一次操作时间
					String newLicense = machineCode+"-"+genTime+"-"+regTime+"-"+sysTime
							+"-"+dlTime+"-"+funcList;
					//////////////////////////////////////////////////////
					System.out.println("每次运行更新最后一次操作时间："+newLicense);
					ValidityPeriodJudge.updateSoftewareLastOptTime(newLicense);
				}else{
					System.out.println("软件已过期，请重新购买");
					RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearList, product);
					RegisterPrompt.setVisible(true);
					return;
				}
			}else{
				System.out.println("非法时间，请购买后注册");
				RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearList, product);
				RegisterPrompt.setVisible(true);
				return;
			}
		}else{
			System.out.println("没有注册信息，请注册");
			RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearList, product);
			RegisterPrompt.setVisible(true);
			return;
		}

		/**
		 * 没有注册或验证失败，弹出申请试用版或者购买正式版license按钮
		 * applyTrialLicense() / buyLicense()
		 */

		/**
		 * 如果有效期不足一个月，提示用户续期
		 * LicenseRenewal()
		 */
	}

	/**
	 * 判断软件功能是否被授权
	 * @param functionId
	 */
	public static void validatePermission(int functionId){
		/**
		 * 判断functionId是否包含在functionList中
		 * 如未被授权，则弹出扩展功能按钮
		 */
		String func = Integer.toString(functionId);
		if(funcList.contains(func)){
			System.out.println("该功能已购买，可以使用");
		}else{
			System.out.println("该功能未购买，请购买后使用");
			RegisterPrompt RegisterPrompt = new RegisterPrompt(new JFrame(), true, yearInfo, product);
			RegisterPrompt.setVisible(true);
			return;
		}
	}

	public static void main(String[] args) throws IOException, BackingStoreException {

		entrance("D:\\eclipse-jee-neon-2\\ws_license\\license\\初始化信息列表.txt");

		//传入购买年限数据
//		String[] yearList = {"永久" , "1年", "2年", "5年", "10年"};

		//传入购买功能列表
//		FunctionInfo func1 = new FunctionInfo();
//		func1.setId(1);
//		func1.setName("登录");
//		func1.setStatus("系统登录");

//		CommonUtils CommonUtils = new CommonUtils();
//		CommonUtils.registeredOrNot(infoList,yearList);
//		CommonUtils.validatePermission(4);
	}

	/**
	 * 入口函数
	 * 读取给定路径下文本文档，初始化年限列表和功能列表
	 * 进而调用license工具
	 * @throws IOException
	 * @throws BackingStoreException
	 */
	public static void entrance(String getInfoPath) throws IOException, BackingStoreException{
		//读取指定路径下文本
		String charseName = "GBK";
		File file = new File(getInfoPath);
		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(file), charseName);
		BufferedReader br = new BufferedReader(inputStreamReader);

		//product
		String product = "";

		//yearList
		String[] yearList = {};

		//infoList
		String[] funcInfoLine = {};

		String line = "";
		int row = 0;
		int yearRow = 0;
		int funcStartRow = 88888;
		while((line=br.readLine())!=null){
			row++;
			if(line.contains("产品")){
				product = line.split("：")[1];
			}
			if(line.contains("年限列表")){
				yearRow = row+1;
			}if(row==yearRow){
				yearList = line.split("/");
			}if(line.contains("功能列表")){
				funcStartRow = row+1;
			}if(row>=funcStartRow){
				funcInfoLine = line.split("-");
				FunctionInfo func = new FunctionInfo();
				func.setId(Integer.parseInt(funcInfoLine[0]));
				func.setName(funcInfoLine[1]);
				func.setStatus(funcInfoLine[2]);
				infoList.add(func);
			}
		}

		CommonUtils.registeredOrNot(infoList,yearList,product);

		CommonUtils.validatePermission(3);
	}

	/**
	 * 初始化功能列表
	 */
	public static class FuncListTable extends AbstractTableModel {

		Object[][] p = new Object[funcInfo.size()][4];
		{
			for(int i=0; i<funcInfo.size(); i++){
				p[i][0] = true;
				p[i][1] = funcInfo.get(i).getId();
				p[i][2] = funcInfo.get(i).getName();
				p[i][3] = funcInfo.get(i).getStatus();
			}
		}

//	    Object[][] p = {
//	            { true, new Integer(1), "登录", "系统登录" },
//	            { true, new Integer(2), "注册", "用户注册" },
//	            { true, new Integer(3), "增", "增" },
//	            { true, new Integer(4), "删", "删" },
//	            { true, new Integer(5), "改", "改" },
//	            { true, new Integer(6), "查", "查" }
//        };

	    String[] n = { "","序号", "功能名称", "功能描述" };

	    public int getRowCount() {
			return p.length;
	    }

	    public int getColumnCount() {
	        return n.length;
	    }

	    public Object getValueAt(int rowIndex, int columnIndex) {
	        return p[rowIndex][columnIndex];
	    }

	    @Override
	    public String getColumnName(int column) {
	        return n[column];
	    }

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        return getValueAt(0, columnIndex).getClass();
	    }

	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return true;
	    }

	    @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	        p[rowIndex][columnIndex] = aValue;
	        fireTableCellUpdated(rowIndex, columnIndex);
	    }
	}
}
