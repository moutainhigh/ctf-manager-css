package com.zdf.utils;

import cn.hutool.json.JSONUtil;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SFtpConnectionUtils {

    private static ChannelSftp channel;
    private static Session session;
//    private static String sftpPath;

    SFtpConnectionUtils() {
     /*
     使用端口号、用户名、密码以连接SFTP服务器
     */
        this.connectServer("10.128.62.133", 22, "yuyx3", "yuyx3@123456");
    }

    SFtpConnectionUtils(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword) {
        this.connectServer(ftpHost, ftpPort, ftpUserName, ftpPassword);
    }

    public static void connectServer(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword) {
        try {
            // 创建JSch对象
            JSch jsch = new JSch();
            // 根据用户名，主机ip，端口获取一个Session对象
            session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
            if (ftpPassword != null) {
                // 设置密码
                session.setPassword(ftpPassword);
            }
            Properties configTemp = new Properties();
            configTemp.put("StrictHostKeyChecking", "no");
            // 为Session对象设置properties
            session.setConfig(configTemp);
            // 设置timeout时间
            session.setTimeout(60000);
            session.connect();
            // 通过Session建立链接
            // 打开SFTP通道
            channel = (ChannelSftp) session.openChannel("sftp");
            // 建立SFTP通道的连接
            channel.connect();
        } catch (JSchException e) {
            //throw new RuntimeException(e);
        }
    }


    /**
     * 断开SFTP Channel、Session连接
     */
    public static void closeChannel() {
        try {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        } catch (Exception e) {

        }
    }


    /**
     * 上传文件
     *
     * @param localFile 本地文件
     * @param remoteFile 远程文件
     */
    public static void upload(String localFile, String remoteFile) {
        try {
            channel.put(localFile, remoteFile, ChannelSftp.OVERWRITE);
            channel.quit();
        } catch (SftpException e) {

        }
    }


    /**
     * 下载文件
     *
     * @param remoteFile 远程文件
     * @param localFile 本地文件
     */
    public static void download(String remoteFile, String localFile) {
        try {
            channel.get(remoteFile, localFile);
            channel.quit();
        } catch (SftpException e) {

        }
    }

    public static List<String>  getFileNames(String path){
        List<String> ret = new ArrayList<>();
        try {
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.cd(path);
            Vector<String> files = sftp.ls("*");
            for (int i = 0; i < files.size(); i++){
                Object obj = files.elementAt(i);
                if (obj instanceof ChannelSftp.LsEntry){
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                    if (true && !entry.getAttrs().isDir()){
                        ret.add(entry.getFilename());
                    }
                    if (true && entry.getAttrs().isDir()){
                        if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")){
                            ret.add(entry.getFilename());
                        }
                    }
                }
            }
        }catch (Exception e){

        }
        return ret;
    }

    //获取文件的上传时间
    public static String getLastModifiedTime(String sftpPath){
        try {
            ChannelSftp sftp = SFtpConnectionUtils.channel;
            SftpATTRS lstat = sftp.lstat(sftpPath);
            int mTime = lstat.getMTime();
            Date date = new Date(mTime * 1000L);
            String uploadTime = DateUtils.date2string(date, DateUtils.DATE_TIME_PATTERN);
            return uploadTime;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




/*    public static void main(String[] args) {
        SFtpConnectionUtils sftpCustom = new SFtpConnectionUtils();
        //上传测试
        String localfile = "E:/lalala/tt.xlsx";
        String remotefile = "/INBOUND_NL0A_20210928010027.csv";
        sftpCustom.upload(localfile, remotefile);
        //下载测试
        sftpCustom.download(remotefile, "D:/INBOUND_NL0A_20210928010027.csv");
        sftpCustom.closeChannel();
    }*/

    public static void main(String[] args) {
        try {
            SFtpConnectionUtils.connectServer("10.128.62.133",22,"projectxlscrm","projectxlscrm@123456");
            //远程文件
            String remoteFile = "/" + "_16545_2021101911484320211019123325.csv";
            //本地文件
            String localFile = "D:/data" + "/" + "_16545_2021101911484320211019123325.csv";

            SFtpConnectionUtils.download(remoteFile,localFile);
            SFtpConnectionUtils.closeChannel();

            List<Map<String, Object>> list = null;
            list = FileUtil.readCSVInfo(localFile);
            List<Map<String, Object>> collect = list.stream().limit(1).collect(Collectors.toList());
            log.info("===" + JSONUtil.toJsonStr(collect));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
