package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: xiong.qiu
 * @Date: 2018/8/22 14:41
 * @Description: 用于文件相关的工具类
 */
public class FileUtil {

    /**
     * @param filePath 文件存放路径（目录）
     * @param fileName 文件名
     * @param url      网络资源
     */
    public static void urlCreateFileImg(String filePath, String fileName, URL url) {
        //打开链接
        HttpURLConnection httpURLConnection = null;
        //创建输出流
        FileOutputStream fileOutputStream = null;
        try {
            //先创建目录
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //在创建文件
            File fileImg = new File(filePath+File.separator+fileName);
            if (!fileImg.exists()){
                fileImg.createNewFile();
            }
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            httpURLConnection.setRequestMethod("GET");
            //设置超时时间
            httpURLConnection.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inputStream = httpURLConnection.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] bytes = readInputStream(inputStream);
            fileOutputStream = new FileOutputStream(fileImg);
            //写入数据
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * 读取资源
     *
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inputStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //把outStream里的数据写入内存
            return outStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
