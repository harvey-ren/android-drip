package com.iwise.utils;

/**
 * 结果码敞亮
 * 
 * @ClassName: ResultCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Harvey
 * @date 2014-7-16 上午10:33:00
 * 
 */
public class ResultCode
{

	/**
	 * 响应成功码
	 */
	public static final int RESPONSE_SUCCESS_CODE = 1;
	/**
	 * 成功
	 */
	public static final String SUCCESS = "000000";

	/**
	 * 服务鉴权未通过
	 */
	public static final String SERVE_AUTHENTICA_FAIL = "100000";

	/**
	 * 请求参数格式错误
	 */
	public static final String REQUEST_PARAMETER_FORMAT_ERROR = "200000";

	/**
	 * 服务内部错误
	 */
	public static final String SERVE_INSIDE_ERROR = "300000";

	/**
	 * 发送失败
	 */
	public static final String SEND_FAIL = "300001";

	/**
	 * 用户名已经存在
	 */
	public static final String USERNAME_ALREADY_EXIST = "300101";

	/**
	 * 用户名或密码错误
	 */
	public static final String USERNAME_OR_PASSWORD_ERROR = "300102";

	/**
	 * 用户不存在
	 */
	public static final String USERNAME_NO_EXIST = "300103";

	/**
	 * 网络超时
	 */
	public static final String NETWORK_TIMEOUT = "400000";

	/**
	 * 请求JSON格式错误
	 */
	public static final String REQUEST_JSON_FORMAT_ERROR = "600000";

	/**
	 * 未注册(初始化)
	 */
	public static final String NOT_REGISTERED = "600001";

	/**
	 * 不需要更新
	 */
	public static final String NOT_NEED_UPDATE = "600002";

	/**
	 * 用户名或密码为空
	 */
	public static final String USERNAME_OR_PASSWORD_NULL = "600003";

	/**
	 * 激活码过期
	 */
	public static final String ACTIVE_CODE_EXPIRE = "600102";

	/**
	 * 激活码无效
	 */
	public static final String ACTIVE_CODE_INVALID = "600103";

	/**
	 * 验证码错误
	 */
	public static final String VERIFY_CODE_ERROR = "600104";

	/**
	 * 请求header头内容错误
	 */
	public static final String REQUEST_HEADER_CONTENT_ERROR = "700000";
}
