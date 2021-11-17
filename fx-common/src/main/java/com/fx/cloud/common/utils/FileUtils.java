package com.fx.cloud.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author xun.guo
 */
public class FileUtils {

    public static void main(String[] args) {
        try {
            deletefile("E:\\发包\\20211104\\后端代码\\jar\\代码");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("ok");
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param delpath String
     * @return boolean
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean deletefile(String delpath) throws Exception {
        File file = new File(delpath);
        String fileName = file.getName();
        Boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            if (!fileName.equals("pom.xml")) {
                file.delete();
                System.out.println("成功删除文件" + file.getAbsolutePath());
            }
        } else if (!fileName.equals("src")) {
//            迭代删除
            for (File f : file.listFiles()) {
                deletefile(f.getAbsolutePath());
            }
            file.delete();
            System.out.println("成功删除文件" + file.getAbsolutePath());

        }

        return true;
    }

    /**
     * 输出某个文件夹下的所有文件夹和文件路径
     *
     * @param filepath String
     * @return boolean
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean readfile(String filepath)
            throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            System.out.println("遍历的路径为：" + file.getAbsolutePath());
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时（即文件夹下有子文件时），返回 true
            if (!file.isDirectory()) {
                System.out.println("该文件的绝对路径：" + file.getAbsolutePath());
                System.out.println("名称：" + file.getName());
            } else if (file.isDirectory()) {
                // 得到目录中的文件和目录
                String[] filelist = file.list();
                if (filelist.length == 0) {
                    System.out.println(file.getAbsolutePath()
                            + "文件夹下，没有子文件夹或文件");
                } else {
                    System.out
                            .println(file.getAbsolutePath() + "文件夹下，有子文件夹或文件");
                }
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    System.out.println("遍历的路径为：" + readfile.getAbsolutePath());
                    if (!readfile.isDirectory()) {
                        System.out.println("该文件的路径："
                                + readfile.getAbsolutePath());
                        System.out.println("名称：" + readfile.getName());
                    } else if (readfile.isDirectory()) {
                        System.out.println("-----------递归循环-----------");
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile() Exception:" + e.getMessage());
        }
        return true;
    }

}
