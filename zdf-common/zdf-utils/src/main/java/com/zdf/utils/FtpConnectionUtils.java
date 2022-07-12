package com.zdf.utils;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FtpConnectionUtils {

    /**
     * 连接ftp服务器
     * @param ip  ftp地址
     * @param port  端口
     * @param username 账号
     * @param password 密码
     * @return
     * @throws IOException
     */
    public static FTPClient ftpConnection(String ip, String port, String username, String password) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setStrictReplyParsing(false);
            ftpClient.connect(ip, Integer.parseInt(port));
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                log.error("--ftp连接失败--");
                System.exit(1);
            }
            ftpClient.enterLocalPassiveMode();//这句最好加告诉对面服务器开一个端口
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
     * 断开FTP服务器连接
     * @param ftpClient  初始化的对象
     * @throws IOException
     */
    public static void close(FTPClient ftpClient) throws IOException{
        if(ftpClient!=null && ftpClient.isConnected()){
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * 下载ftp服务器文件方法
     * @param ftpClient FTPClient对象
     * @param newFileName 新文件名
     * @param fileName 原文件（路径＋文件名）
     * @param downUrl  下载路径
     * @return
     * @throws IOException
     */
    public static boolean downFile(FTPClient ftpClient, String newFileName, String fileName, String downUrl) throws IOException {
        boolean isTrue = false;
        OutputStream os=null;
        File localFile = new File(downUrl + "/" + newFileName);
        if (!localFile.getParentFile().exists()){//文件夹目录不存在创建目录
            localFile.getParentFile().mkdirs();
            localFile.createNewFile();
        }

        //连接尝试后，应检查回复代码以验证
        int reply=ftpClient.getReplyCode();
//        if(!FTPReply.isPositiveCompletion(reply)){  //没验证成功
//            //断开ftp连接
//            ftpClient.disconnect();
//            return false;
//        }
        os = new FileOutputStream(localFile);
        isTrue = ftpClient.retrieveFile(new String(fileName.getBytes(),"ISO-8859-1"), os);
        os.flush();
        os.close();
        return isTrue;
    }

    /**
     * 获取所有文件的名称
     * @param ftpClient
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Map<String,Object>> getFileNames(FTPClient ftpClient, String path)throws Exception{
        List list = new ArrayList<Map<String,Object>>();
        FTPFile[] ftpFiles = ftpClient.listFiles(path);
        for (FTPFile ftpFile : ftpFiles) {
            Map<String, Object> map = new HashMap<>();
            String name = ftpFile.getName();
            if(StrUtil.endWithIgnoreCase(name,"txt") || StrUtil.endWithIgnoreCase(name,"xlsx")){
                map.put("name",name);
                map.put("path",path);
                map.put("suffix",name.substring(name.lastIndexOf("."),name.length()));
                list.add(map);
            }
        }
        return list;
    }

    public static FTPFile[] getFtpFile(FTPClient ftpClient, String path) throws Exception{
        return ftpClient.listFiles(path);
    }
}
