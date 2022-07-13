package com.ctf.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.property.ExcelReadHeadProperty;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.exception.Asserts;
import com.lenovo.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author hanbin
 * @version 1.0.0
 * @ClassName FileUtil.java
 * @Description TODO
 * @createTime 2019年12月16日 19:36:00
 */
@Slf4j
public class FileUtil {

    /**
     * 通过网址生成字节码
     *
     * @param urlPath
     * @return
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else {
                data = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
        return data;

    }

    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 上传文件至指定地址
     * <br> by william
     * <br> 2020-08-03
     *
     * @param path 指定的存放位置
     * @param file 文件
     * @return 文件存储的位置
     */
    public static String uploadFile(String path, MultipartFile file) {
        String sourceFileName = file.getOriginalFilename();
        //文件格式后缀
        String prefix = sourceFileName.substring(sourceFileName.lastIndexOf(StrUtil.DOT));
        //原文件名+当前时间戳作为新文件名
        String destFileName = DateUtil.today() + StrUtil.UNDERLINE + IdUtil.simpleUUID() + prefix;
        File destFile = new File(path + destFileName);
        File destDirectory = new File(path);
        if (!destDirectory.exists() && !destDirectory.isDirectory()) {
            destDirectory.mkdirs();
        }
        try {
            BufferedOutputStream out = cn.hutool.core.io.FileUtil.getOutputStream(destFile);
            IoUtil.copy(file.getInputStream(), out);
            out.close();
        } catch (IOException e) {
            log.error("upload image error:{}", e, e.getMessage());
            Asserts.fail(ResultCode.ERROR, "unable to save file to path ");
        }
        return destFileName;
    }

    public static List<Map<String,Object>> readCSVInfo(String path) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        File file = new File(path);
        // 判断本地文件是否存在，存在则表示文件已下载
        if (!file.exists()) {
            log.debug("Local file exists,file path:{}", file.getPath());
        }
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int curLineNum = 0;
            List<String> list = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] arr = lineTxt.split("\\|",-1);
                if (curLineNum == 0) {
                    list = Arrays.asList(arr);
                    curLineNum += 1;
                    continue;
                }
                setTxtInfo(listMap, arr, list);
                curLineNum += 1;
            }
        } catch (Exception e) {
            log.info("读取文件失败==========");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listMap;
    }


    public static List<Map<String,Object>> readCSVTranInfo(String path) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        File file = new File(path);
        // 判断本地文件是否存在，存在则表示文件已下载
        if (!file.exists()) {
            log.debug("Local file exists,file path:{}", file.getPath());
        }

        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int curLineNum = 0;
            List<String> list = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] arr = lineTxt.split("\\|",-1);
                if (curLineNum == 0) {
                    // 去除字段 “ ”
                    for (String head : arr) {
                        String str= head.replace("\"", "");
                        list.add(str);
                    }
                    curLineNum += 1;
                    continue;
                }
                // 去除字段值 “ ”
                List<String> arrList = new ArrayList<>();
                for (String value : arr) {
                    arrList.add(value.replace("\"",""));
                }
                String[] array = arrList.toArray(new String[arrList.size()]);
                setCsvTranInfo(listMap, array, list);
                curLineNum += 1;
            }
        } catch (Exception e) {
            log.info("读取文件失败==========");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listMap;
    }



    private static void setCsvTranInfo(List<Map<String, Object>> listMap, String[] arr, List<String> list) {
        if (arr.length == list.size()){
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i), arr[i]);
            }
            listMap.add(map);
        }
    }

    public static List<Map<String,Object>> readTXTInfo(String path) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        File file = new File(path);
        // 判断本地文件是否存在，存在则表示文件已下载
        if (!file.exists()) {
            log.debug("Local file no exists,file path:{}", file.getPath());
        }
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int curLineNum = 0;
            List<String> list = new ArrayList<>();
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String[] arr = lineTxt.split("\t", -1);
                if (curLineNum == 0) {
                    list = Arrays.asList(arr);
                    curLineNum += 1;
                    continue;
                }
                setTxtInfo(listMap, arr, list);
                curLineNum += 1;
            }
        } catch (Exception e) {
            log.info("读取文件失败==========");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listMap;
    }


    private static void setTxtInfo(List<Map<String, Object>> listMap, String[] arr, List<String> list) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), arr[i]);
        }
        listMap.add(map);
    }

    public static List<Map<String,Object>> readExcelInfo(String path) {
        List<Map<String ,Object>> excelPropertyIndexModelList = new ArrayList<>();
        try {
            //监听器
            AnalysisEventListener<Map<Integer ,Object>> listener = new AnalysisEventListener<Map<Integer ,Object>>() {

                //读取每一行的数据
                @Override
                public void invoke(Map<Integer ,Object> excelPropertyIndexModel, AnalysisContext analysisContext) {
                    Map<String, Object> map = new HashMap<>();
                    ExcelReadHeadProperty excelReadHeadProperty = analysisContext.readSheetHolder().getExcelReadHeadProperty();
                    Map<Integer, Head> headMap = excelReadHeadProperty.getHeadMap();
                    Iterator<Map.Entry<Integer, Head>> iterator = headMap.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<Integer, Head> next = iterator.next();
                        Integer key = next.getKey();
                        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(next));
                        Map map1 = JSONObject.toJavaObject(jsonObject, Map.class);
                        String str = map1.get(key).toString();
                        map.put(str,excelPropertyIndexModel.get(key));
                    }
                    excelPropertyIndexModelList.add(map);
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    //读取之后的操作
                }

                @Override
                public void invokeHeadMap(Map headMap, AnalysisContext context) {
                    super.invokeHeadMap(headMap, context);
                    context.readSheetHolder().excelReadHeadProperty().setHeadMap(headMap);
//                    System.out.println("headMap: " + headMap);

                }
            };
            /* file.getInputStream() 输入流
             * listener 自定义的监听器
             */
            ExcelReader excelReader = EasyExcel.read(new FileInputStream(path),  listener).build();
            //readSheet(0) 指定读取哪一页的数据
            ReadSheet sheet = EasyExcel.readSheet(0).build();
            excelReader.read(sheet);
            //读取所有的sheet页的数据
            //excelReader.readAll();
            //关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelPropertyIndexModelList;
    }

    public static void downloadFtpFile(FTPFile file, String path) {
        File localFile = new File(path);
        OutputStream os=null;
        try {
            if (!localFile.getParentFile().exists()){//文件夹目录不存在创建目录
                localFile.getParentFile().mkdirs();
                localFile.createNewFile();
            }
            os = new FileOutputStream(localFile);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(os!=null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * csvToXLSX
     * @param csvFileAddress   csv file address
     * @param xlsxFileAddress   xlsx file address
     */
    public static void csvToXLSX(String csvFileAddress,String xlsxFileAddress) {
        try {
//            String csvFileAddress = "test.csv"; //csv file address
//            String xlsxFileAddress = "test.xlsx"; //xlsx file address
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine=null;
            int RowNum=0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split("\\|",-1);
                XSSFRow currentRow=sheet.createRow(RowNum);
                RowNum++;
                for(int i=0;i<str.length;i++){
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }

            FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+"Exception in try");
        }
    }

    //输入文件名返回文件前缀
    public static String getFilePrefix(String fileName){
        if (EmptyUtils.isEmpty(fileName)){
            return null;
        }
        String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        return filePrefix;
    }


    public static String ioToBase64(String fileName) {
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(fileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            Base64.Encoder encoder = Base64.getMimeEncoder();
            strBase64 = encoder.encodeToString(bytes);
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }

    public static void base64ToIo(String strBase64,String filePath){
        String string = strBase64;
        try {
            // 解码，然后将字节转换为文件
            Base64.Decoder decoder = Base64.getMimeDecoder();
            byte[] bytes = decoder.decode(string);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            FileOutputStream out = new FileOutputStream(filePath);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
