package com.zdf.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.enums.ResultEnum;
import com.lenovo.exception.Asserts;
import com.lenovo.result.ResultCode;
import com.lenovo.vaildator.Check4List;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hutool-Excel工具类封装
 *
 * @author william
 * @description
 * @Date: 2020-07-31 15:55
 */
@Slf4j
public class ExcelUtils {

    private static final int MAXROWS = 100000;

    /**
     * 根据请求的excel输出对应的接收实体列表,并同时进行重复行校验
     * <br> 注意：
     * <li> 要校验重复行的属性需要标注@NotDuplicate注解
     * <li> 此方法只抓取excel第一页
     * <li> excel的第一行必须为标题
     * <li> excel标题名与实体名称必须匹配至少一个，否则匹配失败返回null
     *
     * @param file 请求入参
     * @param clz  接受数据的实体类
     * @param <T>
     * @return
     */
    public static <T> List<T> getExcelInfoThenCheck(MultipartFile file, Class<T> clz) {
        List<T> excelInfo = getExcelInfo(file, clz);
        if (excelInfo.isEmpty()){
            Asserts.fail(ResultCode.ERROR,"uploaded file is empty");
        }
        String errorStr = Check4List.getNullOrDuplicateErrorString(excelInfo);
        if (!errorStr.isEmpty()) {
            Asserts.fail(ResultCode.ERROR, errorStr);
        }
        return excelInfo;
    }

    /**
     * 根据请求的excel输出对应的接收实体列表
     * <br> 注意：
     * <li> 此方法只抓取excel第一页
     * <li> excel的第一行必须为标题
     * <li> excel标题名与实体名称必须匹配至少一个，否则匹配失败返回null
     *
     * @param file 请求入参
     * @param clz  接受数据的实体类
     * @param <T>
     * @return
     */
    public static <T> List<T> getExcelInfo(MultipartFile file, Class<T> clz) {
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream(), 0);
            List<List<Object>> readResult = reader.read(0);
            if (readResult.isEmpty()) {
                return null;
            }
            //获取字段名列表names
            List<String> names = readResult.get(0).stream().map(e -> {
                return e.toString();
            }).collect(Collectors.toList());

            //将读出的excel数据装载为实体列表
            List<T> results = new ArrayList<>();
            //遍历行
            for (int i = 1; i < readResult.size(); i++) {
                //返回数据类泛型的新实例，并获取对应的属性列表
                T result = clz.newInstance();
                Field[] declaredFields = clz.getDeclaredFields();
                //遍历列
                for (int j = 0; j < names.size(); j++) {
                    //当前列名
                    String name = names.get(j);
                    //当前单元格数据
                    Object obj = readResult.get(i).get(j);

                    //检查此字段在实体中是否有对应set方法，如有，尝试赋值，如无或赋值失败以及各种bug，抛出异常并继续循环
                    try {
                        checkAndSetProperty(clz, result, declaredFields, name, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        continue;
                    }
                }
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param clz            对象数据类
     * @param result         对象数据类新实例
     * @param declaredFields 属性列表
     * @param name           当前单元格字段名称
     * @param obj            当前单元格字段值
     * @param <T>            对象数据类泛型
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T> void checkAndSetProperty(Class<T> clz, T result, Field[] declaredFields, String name, Object obj) throws Exception {
        //校验属性中是否有set方法与字段名匹配，如果匹配则给该属性赋值
        for (Field declaredField : declaredFields) {
            if (Objects.equals(declaredField.getName(), name)) {
                //如果属性名匹配到了excel字段名，尝试获取set方法并赋值
                Class<?> type = declaredField.getType();
                Method setMethod = clz.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), type);
                //excel读出的数据类型与实体属性是否一致
                if (Objects.equals(type, obj.getClass())) {
                    //一致直接赋值完事
                    setMethod.invoke(result, obj);
                } else {
                    //如果不巧遇到excel类型与实体属性类型不一致，尝试匹配为实体属性类型，搞不定就抛异常
                    if (type == Integer.class) {
                        setMethod.invoke(result, Integer.parseInt(obj.toString()));
                    } else if (type == BigDecimal.class) {
                        setMethod.invoke(result, new BigDecimal(obj.toString()));
                    } else if (type == String.class) {
                        setMethod.invoke(result, obj.toString());
                    } else if (type == Boolean.class) {
                        setMethod.invoke(result, Boolean.parseBoolean(obj.toString()));
                    }
                }
            }
        }
    }

    /**
     * 输出表格：数据不能为空
     *
     * @param fileName
     * @param rows
     * @param response
     * @param <T>
     */
    public static <T> void exportExcel(@NonNull String fileName, List<T> rows, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //xlsx
            fileName = fileName + ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
            ExcelWriter bigWriter = ExcelUtil.getBigWriter();
            //装载标题
            T t = rows.get(0);
            Class<?> clz = t.getClass();
            Field[] fields = clz.getDeclaredFields();
            List<String> titleName = new ArrayList<>();
//            for (Field field : fields) {
//                titleName.add(field.getName());
//                bigWriter.addHeaderAlias(field.getName(), field.getName());
//            }
            for (Field field : fields) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if (excelProperty == null){
                    continue;
                }
                String value = StringUtils.join(excelProperty.value());
                titleName.add(value);
                bigWriter.addHeaderAlias(field.getName(), value);
            }
            //标题写入
            bigWriter.writeHeadRow(titleName);
            //数据写入
            bigWriter.write(rows);
            bigWriter.flush(outputStream);
            bigWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("关闭导出excel流异常:{}", e, e.getMessage());
                }
            }
        }
    }

    /**
     * 输出空表格模板
     *
     * @param fileName
     * @param clz
     * @param response
     * @param <T>
     */
    public static <T> void exportExcel(@NonNull String fileName, Class<T> clz, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //xlsx
            fileName = fileName + ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
            ExcelWriter bigWriter = ExcelUtil.getBigWriter();
            //装载标题
            T t = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();

            List<String> titleName = new ArrayList<>();
            for (Field field : fields) {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if (excelProperty == null){
                    continue;
                }
                String value = StringUtils.join(excelProperty.value());
//                titleName.add(field.getName());
                titleName.add(value);
                bigWriter.addHeaderAlias(value, value);
            }
            //标题写入
            bigWriter.writeHeadRow(titleName);
            bigWriter.flush(outputStream);
            bigWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("关闭导出excel流异常:{}", e, e.getMessage());
                }
            }
        }
    }

    /**
     * 导入供应商模板
     * @param file
     * @return
     */
    public static List<Map<String,Object>> getImportExcel(MultipartFile file){
        List<Map<String,Object>> excelInfo = getExcelInfo(file);

        if (excelInfo.isEmpty()){
            Asserts.fail(ResultCode.ERROR,"uploaded file is empty");
        }
        String errorStr = Check4List.getNullOrDuplicateErrorString(excelInfo);
        if (!errorStr.isEmpty()) {
            Asserts.fail(ResultCode.ERROR, errorStr);
        }
        return excelInfo;
    }


    public static List<Map<String,Object>> getExcelInfo(MultipartFile file){
        try {
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream(), 0);
            List<List<Object>> readResult = reader.read(0);
            if (readResult.isEmpty()) {
                return null;
            }

            //获取字段名列表names
            List<String> names = readResult.get(0).stream().map(e -> {
                return e.toString();
            }).collect(Collectors.toList());

            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map = new HashMap<>();

            for(int i=0;i<readResult.size()-1;i++){
                for (int j=0;j<names.size();j++){
                    try {
                        map.put(names.get(j),readResult.get(i+1).get(j));
                    }catch (Exception e){
                        continue;
                    }
                }
                list.add(map);
            }
            return list;
        }catch (Exception e){
            log.error("导入文件流异常:{}", e, e.getMessage());
        }
        return null;
    }

    /**
     * 下载Excel文件
     * @param fileName  下载文件名
     * @param response
     * @param workbook Excel工作簿对象
     * @throws IOException
     */
    public static void downFile(String fileName, HttpServletResponse response, XSSFWorkbook workbook) {
        try (OutputStream os = response.getOutputStream()) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/binary;charset=utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
