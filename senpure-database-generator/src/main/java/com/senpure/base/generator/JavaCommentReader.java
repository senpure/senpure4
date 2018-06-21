package com.senpure.base.generator;

import com.senpure.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by 罗中正 on 2017/9/22.
 */
public class JavaCommentReader {

    private Logger logger = LoggerFactory.getLogger(getClass());
    public void read(File file, Model model, boolean entityComment) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linesCommentStr = "";
            boolean linesCommentOver = true;
            while (reader.ready()) {
                String line = reader.readLine().trim();
                if (line.startsWith("@")) {
                    continue;
                } else if (line.startsWith("//")) {
                    linesCommentStr = line;
                    linesCommentOver = true;
                } else if (line.startsWith("/*")) {
                    linesCommentStr = line;
                    linesCommentOver = false;
                } else if (line.length() > 0 && !linesCommentOver && !line.contains(";")) {
                    linesCommentStr = linesCommentStr + "\n " + line;
                } else if (line.startsWith("/**")) {
                    linesCommentStr = line;
                    linesCommentOver = false;
                } else if (line.startsWith("*")) {
                    linesCommentStr = linesCommentStr + "\n " + line;

                } else if (line.startsWith("*/")) {
                    linesCommentStr = linesCommentStr + "\n " + line;
                    linesCommentOver = true;
                } else if ((line.startsWith("private") || line.startsWith(" protected")) && line.endsWith(";")) {

                    line = StringUtil.cutMoreSpace(line);
                    int index = StringUtil.indexOf(line, " ", 1, true);
                    String name = line.substring(index + 1, line.length() - 1);
                    logger.debug("line {} 字段名{} 注释 {}",line,name,linesCommentStr);
                  ModelField modelField=  model.getModelFieldMap().get(name);
                    if (modelField != null) {
                        modelField.setExplain(linesCommentStr);
                    }

                } else if (line.startsWith("public class")) {
                    if (linesCommentStr.length() > 0) {
                        if (entityComment) {
                            model.setExplain(linesCommentStr);
                        }
                        linesCommentStr = "";
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
