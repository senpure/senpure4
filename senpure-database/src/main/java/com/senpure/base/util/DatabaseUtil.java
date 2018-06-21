package com.senpure.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.sql.*;

/**
 * DatabaseUtil
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @time 2018-06-04 17:04:57
 */
public class DatabaseUtil {

    private static Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

    public static void checkAndCreateDatabase(DataSourceProperties prop) {
        String fullurl = prop.getUrl();
        String user = prop.getUsername();
        String password = prop.getPassword();
        int index = StringUtil.indexOf(fullurl, "/", 1, true);
        String url = fullurl.substring(0, index);
        String database = "";
        int j = fullurl.indexOf("?");
        if (j < 0) {
            database = fullurl.substring(index + 1);
        } else {
            database = fullurl.substring(index + 1, j);
        }
        fullurl = fullurl.toLowerCase();
        index = fullurl.indexOf("encoding");
        String charSet = null;
        if (index > 0) {
            int i = fullurl.indexOf("&amp;", index);
            if (i < 0) {
                i = fullurl.indexOf("&", index);
            }
            if (i < 0) {
                charSet = fullurl.substring(index + 9);
            } else {
                charSet = fullurl.substring(index + 9, i);
            }
        }
        Connection connection = null;
        try {
            String checkSql = "SELECT information_schema.SCHEMATA.SCHEMA_NAME FROM information_schema.SCHEMATA where SCHEMA_NAME=?";
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setString(1, database);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.debug("[{}]数据库存在", database);
            } else {
                logger.debug("[{}]数据库不存在，准备创建数据库", database);
                StringBuilder sb = new StringBuilder();
                sb.append("create DATABASE ");
                sb.append("`");
                sb.append(database);
                sb.append("`");
                if (charSet == null) {
                    sb.append(" default character set utf8 collate utf8_general_ci");
                } else {
                    sb.append(" default character set ");
                    sb.append(charSet.replace("_", "").replace("-", ""));
                }
                String createSql = sb.toString();
                logger.debug("创建数据库sql:{}", createSql);
                preparedStatement = connection.prepareStatement(checkSql);
                int update = preparedStatement.executeUpdate(createSql);
                if (update == 1) {
                    logger.debug("创建数据库[{}]成功", database);
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
