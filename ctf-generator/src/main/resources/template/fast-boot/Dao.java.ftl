package ${package}<#if moduleName??>.${moduleName}</#if>.dao<#if subModuleName??>.${subModuleName}</#if>;

import ${package}.framework.common.dao.BaseDao;
import ${package}<#if moduleName??>.${moduleName}</#if>.entity<#if subModuleName??>.${subModuleName}</#if>.${ClassName}Entity;
import org.apache.ibatis.annotations.Mapper;

/**
* ${tableComment}
*
* @author ${author} ${email}
* @date 2022/08/05 11:25
*/
@Mapper
public interface ${ClassName}Dao extends BaseDao<${ClassName}Entity> {

}
