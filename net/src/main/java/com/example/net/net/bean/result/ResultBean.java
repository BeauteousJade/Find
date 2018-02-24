package com.example.net.net.bean.result;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ResultBean {

	private int code = 0;
	private String message = null;
	private String result = null;

	// code
	// 成功码
	public static final int CODE_SUCCESS = 1;
	// 表不存在
	public static final int CODE_TABLE_NOT_EXISTS = 2;
	// 数据库连接失败
	public static final int CODE_CONNECTION_FAIL = 3;
	// Json格式不规范
	public static final int CODE_JSON_FORMAT_NONSTANDARD = 4;
	// type的值范围错误
	public static final int CODE_TYPE_VALUE_ERROR = 5;
	// id 为null
	public static final int CODE_ID_IS_NULL = 6;
	// 更新字段为空
	public static final int CODE_UPDATE_FIELD_IS_NULL = 7;
	// 插入字段为空
	public static final int CODE_INSERT_FIELD_IS_NULL = 8;
	// 提交的方式错误
	public static final int CODE_SUMMIT_METHOD_ERROR = 9;
	// 操作为空
	public static final int CODE_ACTION_IS_NULL = 10;
	// 操作行为错误
	public static final int CODE_ACTION_ERROR = 11;
	// 没有id属性
	public static final int CODE_NO_ID = 11;
	// 登录是没有email和password
	public static final int CODE_NO_EMAIL_AND_PASSWORD = 12;
	// 虽然查询成功，但是没有结果集
	public static final int CODE_LOGIN_FAIL = 13;
	// json中参数number的不符合要求
	public static final int CODE_PARAMETER_NUMBER_ERROR = 14;
	// 验证码为空
	public static final int CODE_VERIFICATION_CODE_IS_NULL = 15;
	// 验证码错误
	public static final int CODE_VERIFICATION_CODE_ERROR = 16;
	// 邮箱为空
	public static final int CODE_EMAIL_IS_NULL = 17;
	// 邮箱格式错误
	public static final int CODE_EMAIL_FORMAT_NONSTANDARD = 18;
	// 发送邮件失败
	public static final int CODE_EMAIL_SEND_FAIL = 20;
	// 验证码存储错误
	public static final int CODE_VERIFICATION_CODE_STORE_ERROR = 21;
	// 验证码还未过期
	public static final int CODE_VERIFCATION_CODE_NOT_INVALID = 22;
	// ip延迟发送邮件
	public static final int CODE_IP_WAIT = 23;
	// 表名为空
	public static final int CODE_CLASS_NAME_IS_NULL = 24;
	// 修改密码失败
	public static final int CODE_MODIFY_PASSWORD_FAIL = 25;
	// 修改密码不是user表
	public static final int CODE_NOT_IS_USER_TABLE = 26;
	// 密码为空
	public static final int CODE_PASSWORD_IS_NULL = 27;
	// 修改密码失败，密码的长度小于10
	public static final int CODE_PASSWORD_LENGTH_LT_TEN = 28;
	// ClassName的数目错误
	public static final int CODE_CLASS_NAME_NUMBER_IS_ERROR = 29;
	// email已经注册
	public static final int CODE_EMAIL_IS_USED = 30;
	// 验证码已经过期
	public static final int CODE_VERIFICATION_CODE_OUT_OF_DATE = 31;
	// User不能直接操作
	public static final int CODE_USER_NOT_OPERATION_DITECT = 32;
	// id不能被更新
	public static final int CODE_ID_NOT_BEEN_UPDATED = 33;
	// 没有一条记录被更新
	public static final int CODE_NO_DATE_UPDATED = 34;
	// 不能查询User表的password字段
	public static final int CODE_NOT_QUERY_PASSWORD = 35;
	// 字段名为空
	public static final int CODE_FIELD_NAME_IS_NULL = 36;
	// 查询成功，但是没有结果
	public static final int CODE_NO_SUCH_DATA = 37;
	// 文件类型不正确
	public static final int CODE_FILE_TYPE_ERROR = 38;
	// 文件大小超过了限定的范围
	public static final int CODE_FILE_SIZE_OVER_LIMIT = 39;
	// 文件上传失败
	public static final int CODE_FILE_UPLOAD_FAIL = 40;
	// 表单类型错误
	public static final int CODE_FORM_TYPE_ERROR = 41;
	// autocrement的id
	public static final int CODE_AUTOCREMENT_ID_VALUE_ERROR = 42;
	// 其他错误
	public static final int CODE_OTHER_ERROR = 100;

	// message
	public static final String MESSAGE_SUCCESS = "success";
	public static final String MESSAGE_TABLE_NOT_EXISTS = "table not exists";
	public static final String MESSAGE_CONNECTION_FAIL = "DataBase connection failed";
	public static final String MESSAGE_JSON_FORMAT_NONSTANDARD = "json format nonstandard";
	public static final String MESSAGE_OTHER_ERROR = "other error";
	public static final String MESSAGE_TYPE_VALUE_ERROR = "type value error";
	public static final String MESSAGE_ID_IS_NULL = "id is null";
	public static final String MESSAGE_UPDATE_FIELD_IS_NULL = "update field is null";
	public static final String MESSAGE_INSERT_FIELD_IS_NULL = "insert field is null";
	public static final String MESSAGE_SUMMIT_METHOD_ERROR = "method must be post";
	public static final String MESSAGE_ACTION_IS_NULL = "action is null";
	public static final String MESSAGE_NO_ID = "bean don't have id";
	public static final String MESSAGE_ACTION_ERROR = "action is illegal";
	public static final String MESSAGE_NO_EMAIL_AND_PASSWORD = "There are not email and password";
	public static final String MESSAGE_LOGIN_FAIL = "Login fail, email or password is false";
	public static final String MESSAGE_PARAMETER_NUMBER_ERROR = "number of the paramter is error";
	public static final String MESSAGE_VERIFICATION_CODE_IS_NULL = "Verification Code is null";
	public static final String MESSAGE_VERIFICATION_CODE_ERROR = "Verification Code error";
	public static final String MESSAGE_EMAIL_IS_NULL = "Email is null";
	public static final String MESSAGE_EMAIL_FORMAT_NONSTANDARD = "Email format is nonstandard";
	public static final String MESSAGE_EMAIL_SEND_FAIL = "Email send fail";
	public static final String MESSAGE_VERIFICATION_CODE_STORE_ERROR = "Verification code strore error";
	public static final String MESSAGE_VERIFICATION_CODE_NOT_INVALID = "Email send fail, verification code is not invalid";
	public static final String MESSAGE_IP_WAIT = "This is request more times at the same time ,please request again after one minute";
	public static final String MESSAGE_CLASS_NAME_IS_NULL = "The class name is null";
	public static final String MESSAGE_MODIFY_PASSWORD_FAIL = "modify password fail";
	public static final String MESSAGE_NOT_IS_USER_TABLE = "sorry, this table must be user table";
	public static final String MESSAGE_PASSWORD_IS_NULL = "password is null";
	public static final String MESSAGE_PASSWORD_LENGTH_LT_TEN = "modify password fail, password length is less then 10";
	public static final String MESSAGE_CLASS_NAME_NUMBER_IS_ERROR = "The number of className is error, the number must be 1";
	public static final String MESSAGE_EMAIL_IS_USED = "The email have been registered";
	public static final String MESSAGE_VERIFICATION_CODE_OUT_OF_DATE = "The verification code is out of date";
	public static final String MESSAGE_USER_NOT_OPERATION_DIRECT = "not operation user table directly";
	public static final String MESSAGE_ID_NOT_BEEN_UPDATED = "id is not been updated";
	public static final String MESSAGE_NO_DATA_UPDATED = "There are no data been updated";
	public static final String MESSAGE_NOT_QUERY_PASSWORD = "It is forbidden that query password";
	public static final String MESSAGE_FIELD_NAME_IS_NULL = "The field name is null";
	public static final String MESSAGE_NO_SUCH_DATA = "no such data";
	public static final String MESSAGE_FILE_TYPE_ERROR = "The type of the file is error, the type must be jpg,png or gif";
	public static final String MESSAGE_FILE_SIZE_OVER_LIMIT = "The file size is over limit size";
	public static final String MESSAGE_FILE_OPLOAD_FAIL = "The File upLoad fail";
	public static final String MESSAGE_FORM_TYPE_ERROR = "The form type is error";
	public static final String MESSAGE_ATUOCREMENT_ID_VALUE_ERROR = "The value of autocrement id must be 0";

	public ResultBean(int code, String message, String result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public ResultBean() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		if (result == null) {
			result = "{}";
		}
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static ResultBean toObject(String string) {
		if (string == null) {
			return new ResultBean(CODE_OTHER_ERROR, MESSAGE_OTHER_ERROR, null);
		}
		try {
			JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
			ResultBean bean = new ResultBean();
			bean.setCode(jsonObject.get("code").getAsInt());
			bean.setMessage(jsonObject.get("message").getAsString());
			bean.setResult(jsonObject.get("result").getAsString());
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultBean(CODE_OTHER_ERROR, MESSAGE_OTHER_ERROR, null);
	}
}
