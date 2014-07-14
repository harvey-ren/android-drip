package com.iwise.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iwise.utils.JsonUtil;
import com.iwise.utils.ResultCode;

/**
 * 备份响应解析类
 * 
 * @author Harvey
 * 
 */
public class ResponseParser
{

	private static ResponseParser responseParser;

	private ResponseParser()
	{
	}

	public static ResponseParser getInstance()
	{
		if (responseParser == null)
		{
			responseParser = new ResponseParser();
		}
		return responseParser;
	}

	public NetWorkResponse parse(String resultString)
	{
		NetWorkResponse response = new NetWorkResponse();

		JSONObject jsonObj;
		try
		{
			jsonObj = new JSONObject(resultString);

			// 是否有result对象
			JSONObject resultJsonObj = JsonUtil.getJSONObject(jsonObj, "result", null);

			if (resultJsonObj != null)
			{
				// 获取结果码
				String resultCode = JsonUtil.getString(resultJsonObj, "resultcode", "0");

				// 设置结果码
				response.setResultCode(resultCode);

				// 如果结果码不为成功码
				if (!resultCode.equals(ResultCode.SUCCESS))
				{
					// 设置错误信息
					response.setInfo(JsonUtil.getString(resultJsonObj, "resultinfo", "未获取到响应信息"));
				}
			}

			// 设置imei身份识别码
			response.setImei(JsonUtil.getString(jsonObj, "imei", "0000000000000"));

			// 是否成功
			response.setIsOk(JsonUtil.getInt(jsonObj, "isok", 0));

			// 平台服务版本号
			response.setSv(JsonUtil.getString(jsonObj, "sv", "1.0"));

			// 服务器url
			response.setPreurl(JsonUtil.getInt(jsonObj, "preurl", 0));

			// 用户ID
			response.setUserId(JsonUtil.getString(jsonObj, "userid", ""));

			// 是否更新
			response.setIsUpdate(JsonUtil.getInt(jsonObj, "isupdate", 0));

			// 升级地址
			response.setUpdateUrl(JsonUtil.getString(jsonObj, "updateurl", ""));

			// 文件大小
			response.setFileSize(JsonUtil.getString(jsonObj, "versionsize", "0"));

			// 版本号
			response.setVersionCode(JsonUtil.getString(jsonObj, "version", "1.0"));

			// 客户端描述
			response.setVersionDesc(JsonUtil.getString(jsonObj, "versiondesc", ""));

		} catch (JSONException e)
		{
			return response;
		}
		return response;
	}
}
