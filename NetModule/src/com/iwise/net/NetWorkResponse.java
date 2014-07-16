package com.iwise.net;

import java.io.Serializable;

/**
 * 响应类
 * 
 * @File: NetWorkResponse.java
 * @Package com.iwise.net
 * @Description:
 * @author Harvey
 * @date 2014-7-14 下午2:38:21
 * 
 */
public class NetWorkResponse implements Serializable
{

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 平台服务版本号
	 */
	private String sv = "";

	/**
	 * 请求结果码
	 */
	private String resultCode = "";

	/**
	 * 是否成功
	 */
	private int isOk = 0;

	/**
	 * 用户ID
	 */
	private String userId = "";

	/**
	 * 是否自动登陆
	 */
	private int isUser = -1;

	/**
	 * 是否需要升级客户端
	 */
	private int isUpdate = 0;

	/**
	 * 客户端升级地址
	 */
	private String updateUrl = "";

	/**
	 * 服务器url
	 */
	private int preurl = 0;

	/**
	 * 信息
	 */
	private String info = "";

	/**
	 * 手机身份识别码
	 */
	private String imei = "";

	/**
	 * 客户端描述
	 */
	private String versionDesc = "";

	/**
	 * 客户端大小
	 */
	private String fileSize = "";

	/**
	 * 客户端版本号
	 */
	private String versionCode = "";

	public String getSv()
	{
		return sv;
	}

	public void setSv(String sv)
	{
		this.sv = sv;
	}

	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public int getIsOk()
	{
		return isOk;
	}

	public void setIsOk(int isOk)
	{
		this.isOk = isOk;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public int getIsUser()
	{
		return isUser;
	}

	public void setIsUser(int isUser)
	{
		this.isUser = isUser;
	}

	public int getIsUpdate()
	{
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate)
	{
		this.isUpdate = isUpdate;
	}

	public String getUpdateUrl()
	{
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl)
	{
		this.updateUrl = updateUrl;
	}

	public int getPreurl()
	{
		return preurl;
	}

	public void setPreurl(int preurl)
	{
		this.preurl = preurl;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public String getImei()
	{
		return imei;
	}

	public void setImei(String imei)
	{
		this.imei = imei;
	}

	public String getVersionDesc()
	{
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc)
	{
		this.versionDesc = versionDesc;
	}

	public String getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}

	public String getVersionCode()
	{
		return versionCode;
	}

	public void setVersionCode(String versionCode)
	{
		this.versionCode = versionCode;
	}

}
