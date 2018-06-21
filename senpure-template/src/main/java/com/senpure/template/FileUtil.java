package com.senpure.template;

import com.senpure.base.AppEvn;
import com.senpure.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by 罗中正 on 2018/4/28 0028.
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String checkPath(String path) {
        if (!Charset.forName("gbk").newEncoder().canEncode(path)) {
            try {
                path = new String(path.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("error", e);
            }
        }
        return path;
    }

    public static File file(String path) {
        path = checkPath(path);
        File file;
        path = path.replace("\\", "/");
        String upperPath = path.toUpperCase();
        if (path.startsWith("/") || upperPath.startsWith("C:") || upperPath.startsWith("D:") || upperPath.startsWith("E:")
                || upperPath.startsWith("F:") || upperPath.startsWith("G:") || upperPath.startsWith("H:")
                || upperPath.startsWith("H:") || upperPath.startsWith("I:") || upperPath.startsWith("G:")
                ) {
            file = new File(path);
        } else if (path.startsWith("../")) {
            int count = StringUtil.count(path, "../");
            File parent = new File(AppEvn.getClassRootPath());
            for (int i = 0; i < count; i++) {
                parent = parent.getParentFile();
            }
            file = new File(parent, path.replace("../", ""));
        } else {
            file = new File(AppEvn.getClassRootPath(), path);
        }
        return file;
    }

    public static String fullFileEnd(String path) {
        if (!(path.endsWith("/") || path.endsWith(File.separator))) {
            path = path + "/";
        }
        return path;
    }

}
