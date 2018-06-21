package com.senpure.template;

import com.senpure.template.sovereignty.Sovereignty;
import com.senpure.template.sovereignty.TemplateBean;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Generator
 *
 * @author 罗中正
 * @email senpure@senpure.com
 * @github https://github.com/senpure
 * @date 2018-05-08
 */
public class Generator {

    private static Logger logger = LoggerFactory.getLogger(Generator.class);

    public static void generate(TemplateBean bean, Template template, File file) {
        try {
            if (!Sovereignty.getInstance().checkSovereignty(template)) {
                logger.error("{} 缺少 ${sovereignty}",template.getName());
                System.exit(0);
            }
            FileOutputStream fos = new FileOutputStream(file);
            template.process(bean, new OutputStreamWriter(fos, "utf-8"));
            fos.close();
            if (!bean.checkSovereignty()) {
                logger.error("{} 没有调用 ${sovereignty}",template.getName());
                if (file.exists()) {
                    file.delete();
                }
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (TemplateException e) {
            logger.error("TemplateException", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

}
