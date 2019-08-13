package bjsasc.licenseGenTools.entity;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * 全局常量
 *
 * @author wangnan
 *
 */
public class GlobalConstanst {

	/**
	 *
	 */
	public static final String DEFAULT_CFG_FILE_NAME = "license_info.xml";


	/**
	 *
	 */
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 *
	 */
	public static final SimpleDateFormat all_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat all_sdf_str = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * @return
	 */
	public static String getCfgPath() {
		return new File(System.getProperty("user.dir"), "config").getAbsolutePath();
	}

}
