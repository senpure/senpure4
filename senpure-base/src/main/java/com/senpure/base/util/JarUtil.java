package com.senpure.base.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JarUtil {
    public static int BUF_SIZE = 1024;
    private static List<File> getFiles(File dir) {
        File[] source = dir.listFiles();
        List<File> files = new ArrayList();
        for (File file : source) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                files.add(file);
            }

        }
        return files;
    }

    private static String getFilePathName(String dir, File file) {
        String p = file.getAbsolutePath().replace(dir + File.separator, "");
        return p;
    }
    public static void packageJar(File source, File target) {
        List<File> files = getFiles(source);
        String dir = source.getAbsolutePath();

        try {
            ArchiveOutputStream outputStream= new JarArchiveOutputStream(new FileOutputStream(target));

            addFiles2Out(outputStream,files,dir);
            outputStream.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static void addFiles2Out(ArchiveOutputStream out, List<File> files, String dir) throws IOException {
        for (File file : files) {
            ArchiveEntry entry=out.createArchiveEntry(file,getFilePathName(dir, file));
            out.putArchiveEntry(entry);
            IOUtils.copy(new FileInputStream(file), out);
            out.closeArchiveEntry();
           // ZipArchiveEntry zipEntry = new ZipArchiveEntry(file, getFilePathName(dir, file));
           // zipOut.putArchiveEntry(zipEntry);
           // IOUtils.copy(new FileInputStream(file), zipOut);
           // zipOut.closeArchiveEntry();
        }
    }
    public static void decompressJar(File source, File target) {
        try {
            ArchiveInputStream inputStream=new JarArchiveInputStream(new FileInputStream(source));
            ArchiveEntry entry = inputStream.getNextEntry();
            String targetPath = target.getPath();
            byte[] data = new byte[BUF_SIZE];
            while (entry != null) {
                String entryFilePath = targetPath + File.separator + entry.getName();
                File entryFile = new File(entryFilePath);
                File file = entryFile.getParentFile();
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!entry.isDirectory()) {
                    OutputStream out = new FileOutputStream(entryFile);
                    int num = inputStream.read(data);
                    while (num > -1) {
                        out.write(data, 0, num);
                        num = inputStream.read(data);
                    }
                    out.close();
                }
                entry = inputStream.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {



    }
}
