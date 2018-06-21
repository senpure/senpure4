package com.senpure.base.util;


import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Download {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final String DOWNLOAD_CONTENT_TYPE = "application/x-msdownload";

    public static void execute(HttpServletResponse response, String pathname)
    {
        execute(response, new File(pathname));

    }

    public static void execute(HttpServletResponse response, File file) {


        execute(response, file, false);
    }

    public static void execute(HttpServletResponse response, File file, boolean delete) {
        try {
            execute(response, new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        if (delete) {
            file.delete();
        }
    }


    public static void execute(HttpServletResponse response, File file, String fileName) {
        execute(response, file, fileName, false);
    }

    public static void execute(HttpServletResponse response, File file, String fileName, boolean delete) {
        try {
            execute(response, new FileInputStream(file), fileName);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        if (delete) {
            file.delete();
        }
    }

    public static void execute(HttpServletResponse response, InputStream inputStream,
                               String fileName) {

        execute(response, inputStream, fileName, DEFAULT_BUFFER_SIZE);
    }

    public static void execute(HttpServletResponse response, InputStream inputStream,
                               String fileName, int bufferSize) {
        response.setContentType(DOWNLOAD_CONTENT_TYPE);
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO-8859-1"));
            byte[] b = new byte[bufferSize];

            int i = 0;
            OutputStream out = response.getOutputStream();
            while ((i = inputStream.read(b)) > 0) {
                out.write(b, 0, i);
            }
            out.flush();
            out.close();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }




}
