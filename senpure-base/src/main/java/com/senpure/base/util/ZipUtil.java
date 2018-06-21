package com.senpure.base.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * dependency commons-compress
 */
public class ZipUtil {

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

    /**
     * 压缩为zip
     *
     * @param source 1.txt 2.jsp 3.log
     * @param target zip.zip
     */
    public static void compressZip(File[] source, File target) {
        List<File> files = new ArrayList<>();
        String dir = source[0].getParentFile().getAbsolutePath();
        for (File file : source) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                files.add(file);
            }
        }
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(target)) {
            addFiles2Out(zipOut, files, dir);
            zipOut.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static void addFiles2Out(ZipArchiveOutputStream zipOut, List<File> files, String dir) throws IOException {
        for (File file : files) {
            ZipArchiveEntry zipEntry = new ZipArchiveEntry(file, getFilePathName(dir, file));
            zipOut.putArchiveEntry(zipEntry);
            IOUtils.copy(new FileInputStream(file), zipOut);
            zipOut.closeArchiveEntry();
        }
    }

    public static void compressZip(File source, File target) {

        compressZip(new File[]{source}, target);
    }

    /**
     * 解压zip
     *
     * @param source
     * @param target
     */
    public static void decompressZip(File source, File target) {
        try {
            ZipArchiveInputStream zipIn = new ZipArchiveInputStream(new FileInputStream(source));
            ArchiveEntry entry = zipIn.getNextEntry();
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
                    int num = zipIn.read(data);
                    while (num > -1) {
                        out.write(data, 0, num);
                        num = zipIn.read(data);
                    }
                    out.close();
                }
                entry = zipIn.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addzip2zip(ZipArchiveOutputStream zipOut, ZipFile zipFile) throws IOException {
        Enumeration<ZipArchiveEntry> entryEnumeration = zipFile.getEntries();
        while (entryEnumeration.hasMoreElements()) {
            ZipArchiveEntry zipArchiveEntry = entryEnumeration.nextElement();
            InputStream inputStream =
                    zipFile.getInputStream(zipArchiveEntry);
            zipOut.putArchiveEntry(zipArchiveEntry);
            IOUtils.copy(inputStream, zipOut);
            zipOut.closeArchiveEntry();
            inputStream.close();
        }

    }

    /**
     * 将一个zip的文件加入到另一个zip
     *
     * @param add 1.zip
     * @param to  2.zip
     */
    public static void addzip2zip(File add, File to) {

        try {
            File zipTemp = File.createTempFile("temp", ".zip");
            ZipArchiveOutputStream zipOut = null;
            zipOut = new ZipArchiveOutputStream(zipTemp);
            ZipFile zipFile = new ZipFile(to);
            addzip2zip(zipOut, zipFile);
            zipFile = new ZipFile(add);
            addzip2zip(zipOut, zipFile);
            zipOut.finish();
            zipOut.close();
            File temp = new File(to.getParent(), System.currentTimeMillis() + to.getName());
            // File temp = File.createTempFile("temp", ".zip");
            to.renameTo(temp);
            zipTemp.renameTo(to);
            temp.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void add2zip(File source, File to) {

        add2zip(new File[]{source}, to);
    }

    public static void add2zip(File[] source, File to) {

        try {
            File zipTemp = File.createTempFile("temp", ".zip");
            ZipArchiveOutputStream zipOut;
            zipOut = new ZipArchiveOutputStream(zipTemp);
            ZipFile zipFile = new ZipFile(to);
            addzip2zip(zipOut, zipFile);
            List<File> files = new ArrayList<>();
            String dir = source[0].getParentFile().getAbsolutePath();
            for (File file : source) {
                if (file.isDirectory()) {
                    files.addAll(getFiles(file));
                } else {
                    files.add(file);
                }
            }
            addFiles2Out(zipOut, files, dir);
            zipOut.finish();
            zipOut.close();
            //  File temp = File.createTempFile("temp", ".zip");
            File temp = new File(to.getParent(), System.currentTimeMillis() + to.getName());
            to.renameTo(temp);
            zipTemp.renameTo(to);
            temp.delete();
            System.out.println(temp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将几个zip 合并为一个zip
     *
     * @param target
     * @param files
     */
    public static void mergeZip(File target, File[] files) {

        try {
            // Assert.isTrue(!target.exists(),"error,"+target.getAbsolutePath()+" can not exist !");
            ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(target);
            for (int i = 0; i < files.length; i++) {
                ZipFile zipFile = new ZipFile(files[i]);
                addzip2zip(zipOut, zipFile);
            }
            zipOut.finish();
            zipOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
