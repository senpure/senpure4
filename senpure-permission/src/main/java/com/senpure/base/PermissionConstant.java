package com.senpure.base;

import java.util.Date;

/**
 * Created by 罗中正 on 2017/10/11.
 */
public class PermissionConstant {

    /**
     * 数据库表前缀
     */
    public static final String DATA_BASE_PREFIX = "SENPURE";

    public static final String NAME = "SENPURE";

    public static final String CONTAINER_TOP_NAME = "SENPURE";

    public static final String SEQUENCE_CONTAINER_ID = "container_id";

    public static  final  String CONTAINER_SEPARTOR="-";
    public static final int CONTAINER_LEVEL_TOP = 0;
    public static final int CONTAINER_LEVEL_COMPANY = 1;
    public static final int CONTAINER_LEVEL_DEPARTMENT = 10;
    public static final int CONTAINER_LEVEL_GROUP = 100;

    public static final Long FORVER_TIME = 0L;
    public static final Date FORVER_DATE = new Date(0L);


    public static final String ACCOUNT_STATE_CREATE = "CREATE";
    public static final String ACCOUNT_STATE_NORMAL = "NORMAL";
    public static final String ACCOUNT_STATE_BAN = "BAN";

    public static final String TOP_ROLE_ID = "top.role.id";
    public static final String TOP_CONTAINER_ID = "top.container.id";


    public static final String VALUE_TYPE_ACCOUNT_DEFAULT = "ACCOUNT_DEFAULT_VALUE";
    public static final String VALUE_TYPE_SYSTEM = "SYSTEM_VALUE";


    public static final String DATE_FORMAT_KEY = "date_format";
    public static final String DATETIME_FORMAT_KEY = "datetime_format";
    public static final String TIME_FORMAT_KEY = "time_format";

    public static final String PERMISSION_TYPE_NORMAL = "NORMAL";
    public static final String PERMISSION_TYPE_OWNER = "OWNER";
    public static final String PERMISSION_TYPE_IGNORE = "IGNORE";

    public static int ALL_OPOTION_INT = -1;
    public static String ALL_OPOTION_STRING = "-1";


}
