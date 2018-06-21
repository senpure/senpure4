package com.senpure.base.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;


@Component(value = "com.senpure.base.result.result")
public class Result {
    @Message(message = "失败")
    public static final int FAILURE = 0;
    @Message(message = "成功")
    public static final int SUCCESS = 1;

    @Message(message = "未知错误")
    public static final int ERROR_UNKNOWN = 10;
    @Message(message = "服务器发生错误")
    public static final int ERROR_SERVER = 11;
    @Message(message = "服务器繁忙，请稍后再试")
    public static final int ERROR_DIM = 12;

    @Message(message = "账号已经存在")
    public static final int ACCOUNT_ALREADY_EXISTS = 100;
    @Message(message = "账号不存在")
    public static final int ACCOUNT_NOT_EXIST = 101;
    @Message(message = "账号被禁用")
    public static final int ACCOUNT_BANED = 102;
    @Message(message = "密码不正确")
    public static final int PASSWORD_INCORRECT = 103;
    @Message(message = "输入格式错误")
    public static final int FORMAT_INCORRECT = 104;
    @Message(message = "账号在其他地方登陆,IP:{0}")
    public static final int ACCOUNT_OTHER_LOGIN = 105;
    @Message(message = "账号未登陆")
    public static final int ACCOUNT_NOT_LOGIN = 106;
    @Message(message = "账号未登陆或登陆超时")
    public static final int ACCOUNT_NOT_LOGIN_OR_SESSION_TIMEOUT = 107;
    @Message(message = "令牌过期请重新获取")
    public static final int TOKEN_TIMEOUT = 108;

    @Message(message = "请输入数字")
    public static final int INPUT_NUMBER = 200;

    @Message(message = "权限不足[{0}] [{1}] 验证 [{2}] 失败")
    public static  final int LACK_OF_PERMISSION_RESOURCE_INCORRECT=402;
    @Message(message = "权限不足[{0}]")
    public static  final int LACK_OF_PERMISSION=403;
    @Message(message = "目标不存在[{0}]")
    public static  final int  TARGET_NOT_EXIST=404;


    protected Logger logger;

    public Result() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @PostConstruct
    public void report() {

        Field[] fields = getClass().getDeclaredFields();

        FieldAndInstance fieldAndInstance = new FieldAndInstance();
        for (Field field : fields) {

            if ("logger".equals(field.getName())) {
                continue;
            }
            if ("int".equals(field.getGenericType().getTypeName())) {
                fieldAndInstance.fields.add(field);
            } else {

                logger.warn("该类型的字段不支持 [{}],仅支持 [int] ",field.getGenericType().getTypeName());
            }

        }
        if (fieldAndInstance.fields.size() > 0) {
            fieldAndInstance.instance=this;
            ResultHelper.fieldAndInstances.add(fieldAndInstance);
        }

    }


}
