package bjsasc.licenseGenTools.utils;

import java.io.IOException;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

import java.util.Scanner;
import java.util.Enumeration;

import org.apache.commons.codec.digest.DigestUtils;

public class GetMachineInfo {
	/**
	 * 获取CPU序列号
	 * 查看方式：wmic cpu get processorid
	 * @return
	 * @throws IOException
	 */
	public static String getCPUSerical() throws IOException{
		Process process = Runtime.getRuntime().exec(
				new String[] {"wmic","cpu","get","ProcessorId"});
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		String property = sc.next();
		String serial = sc.next();
		sc.close();
//		System.out.println("1、cpu序列号 " + property + ":" + serial);
		return serial;
		/*String result = "";
		try{
			File file = File.createTempFile("tmp", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n"
					+ "(\"Select * from Win32_Processor\") \n"
					+ "For Each objItem in colItems \n"
					+ "Wscript.Echo.objItem.ProcessorId \n"
					+ "exit for 'do the first cpu only!' \n"
					+ "Next \n";
//					+ "Exit for \r\n" + "Next";
			fw.write(vbs);
			fw.close();
//			String path = file.getPath().replace("%20", " ");
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			System.out.println(input);
			String line;
			while((line = input.readLine()) != null){
				result += line;
			}
			input.close();
//			file.delete();

		}catch(Exception e){
			e.fillInStackTrace();
		}
		if(result.trim().length() < 1 || result ==null){
			result = "无CPU_ID被读取";
		}
//		System.out.println(result.trim());
		return result.trim();*/
	}

	/**
	 * 获取磁盘序列号
	 * 硬盘的物理序列号，硬盘的唯一id ，除非是刷硬盘，一般不会改变。
	 * 查看方式：wmic diskdrive get serialnumber
	 * 对于win7系统硬盘ID是HEX格式的文本(40位)，如：202020202020325a4b415244544；
	 * 对于win10系统硬盘ID是字符串格式，如：XYZ1234567890；
	 * @return
	 * @throws IOException
	 */
	public static String getHardDiskSerical() throws IOException{
		Process process = Runtime.getRuntime().exec(
				new String[] {"wmic","diskdrive","get","serialnumber"});
		/*Process process = Runtime.getRuntime().exec(
				new String[] {"wmic","path","win32_physicalmedia","get","serialnumber"});*/
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		String property = sc.next();
		String serial = sc.next();
		sc.close();
//		System.out.println("2、磁盘序列号 " + property + ":" + serial);
		return serial;
	}

	/**
	 * 磁盘id
	 * 磁盘分区的逻辑地址，每个磁区有不同id编号
	 * 查看方式：diskpart > list disk > select disk 0 > datail disk
	 * @return
	 */

	/**
	 * 获取主板序列号
	 * 主板标识，记录主板关键信息
	 * @return
	 * @throws IOException
	 */
	public static String getBaseboardSerical() throws IOException{
		Process process = Runtime.getRuntime().exec(
				new String[] {"wmic","baseboard","get","serialnumber"});
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		String property = sc.next();
		String serial = sc.next();
		sc.close();
//		System.out.println("3、主板序列号 " + property + ":" + serial);
		return serial;
	}

	/**
	 * 获取当前网络的mac地址
	 * @param ipAdress
	 * @throws SocketException
	 */
	private static void getLocalMac(InetAddress ipAdress) throws SocketException {
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ipAdress).getHardwareAddress();
//		System.out.println("mac数组长度："+mac.length);
		StringBuffer macAdress = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				macAdress.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
//			System.out.println("每8位:"+str);
			if(str.length()==1) {
				macAdress.append("0"+str);
			}else {
				macAdress.append(str);
			}
		}
//		System.out.println("当前网络MAC地址:"+macAdress.toString().toUpperCase());
	}

	/**
	 * 获取所有网卡mac地址，无论有网无网
	 * @throws SocketException
	 */
	private static String getAllMac() throws SocketException {
		Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
//        System.out.print("所有网卡mac地址:");
        StringBuffer macBuffer = new StringBuffer();
		while (enumeration.hasMoreElements()) {
            StringBuffer stringBuffer = new StringBuffer();
            NetworkInterface networkInterface = enumeration.nextElement();
            if (networkInterface != null) {
                byte[] bytes = networkInterface.getHardwareAddress();
                if (bytes != null && bytes.length==6) {//把length为8的隧道除去

                	for (int i = 0; i < bytes.length; i++) {
                        if (i != 0) {
                            stringBuffer.append("-");
                        }
                        int tmp = bytes[i] & 0xff; //字节转换为整数
                        String str = Integer.toHexString(tmp);
                        if (str.length() == 1) {
                            stringBuffer.append("0" + str);
                        } else {
                            stringBuffer.append(str);
                        }
                    }
                	macBuffer.append(stringBuffer);
                    String mac = stringBuffer.toString().toUpperCase();
//                    System.out.println(mac);
                }
            }
        }
		String macAll = macBuffer.toString().toUpperCase();
//		System.out.println(macAll);
		return macAll;
	}

	public static String getMachineInfo() throws IOException,SocketException, UnknownHostException{
//		System.out.println(getCPUSerical()+getHardDiskSerical());
//		System.out.println(DigestUtils.md5Hex(getCPUSerical()+getHardDiskSerical()));
		return DigestUtils.md5Hex(getCPUSerical()+getHardDiskSerical()).toUpperCase();
	}

	public static void main(String[] args) throws Exception,IOException,InterruptedException{
/*		getCPUSerical();
		getHardDiskSerical();
		getBaseboardSerical();
		InetAddress ipAdress = InetAddress.getLocalHost();
		System.out.println("当前网络地址："+ipAdress);
		getLocalMac(ipAdress);
		getAllMac();
*/		getMachineInfo();
	}

}





