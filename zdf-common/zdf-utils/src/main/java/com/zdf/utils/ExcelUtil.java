package com.zdf.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sun.rowset.internal.BaseRow;
import org.apache.poi.util.SAXHelper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExcelUtil {

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T>  rowModel) throws Exception {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        for (Sheet sheet : reader.getSheets()) {
            sheet.setClazz(rowModel);
            reader.read(sheet);
        }
        return getExtendsBeanList(excelListener.getDatas(),rowModel);
    }

    /**
     * 读取示例模板的excel数据
     * @param excel
     * @param rowModel
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends BaseRowModel> List<T> readTemplateExcel(MultipartFile excel, Class<T>  rowModel) throws Exception{
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        // 读取第一个sheet，第二行开始读取
        Sheet sheet = new Sheet(1, 2, rowModel);
        reader.read(sheet);
        return getExtendsBeanList(excelListener.getDatas(),rowModel);
    }


    /**
     * 读取某个 sheet 的 Excel
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T>  rowModel, int sheetNo)  throws Exception{
        return readExcel(excel, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T>  rowModel, int sheetNo,
                                                             int headLineNum) throws Exception {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel));
        return getExtendsBeanList(excelListener.getDatas(),rowModel);
    }

    /**
     * 返回 ExcelReader
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel,
                                         ExcelListener excelListener) throws Exception{
        String fileName = excel.getOriginalFilename();
        if (fileName == null ) {
            throw new Exception("文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new Exception("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            //do something
        }
        return null;
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T extends BaseRowModel> List<T> getExtendsBeanList(List<?> list, Class<T> typeClazz){
        return ConvertUtil.convertList(list,typeClazz);
    }


    /**
     * 数据验证
     * @param obj
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> String validateEntity(T obj)throws Exception{
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && !set.isEmpty()){
           for (ConstraintViolation<T> cv : set){
               Field declaredField = obj.getClass().getDeclaredField(cv.getPropertyPath().toString());
               ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
               //拼接错误信息，包含当前出错数据的标题名字+错误信息
               result.append(annotation.value()[0] + cv.getMessage()).append(",");
           }
        }
        return result.toString();
    }
}
