package com.ctf.utils.result;

/**
 * @author ciro
 * @date 2022/2/16 17:23
 * @description: 系统提示语
 */
public interface SysTips {
    /**
     * 系统用户信息出错
     */
    String USER_INFO_ERROR = "获取系统用户信息出错！";
    /**
     * 修改根据ID获取出错提示
     */
    String OBJECT_NOT_FOUND = "获取对应操作信息出错！";
    /**
     * 请求参数有误提示
     */
    String REQ_PARAMS_ERROR = "请求参数有误，请检查后再试！";
    /**
     * 授权提示
     */
    String NO_AUTH_WARN = "您没有操作权限，请联系管理员授权！";
    /**
     * 操作成功提示
     */
    String SUCCESS_MSG = "操作成功！";
    /**
     * 操作失败提示
     */
    String ERROR_MSG = "操作失败！";
    /**
     * 文件上传成功提示
     */
    String UPLOAD_SUCCESS = "文件上传成功！";
    /**
     * 文件上传失败提示
     */
    String UPLOAD_ERROR = "文件上传失败！";
    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "登录成功！";
    /**
     * 登录失败
     */
    String LOGIN_ERROR = "用户名或密码错误，请检查后再试！";
    /**
     * 第三方登录失败
     */
    String THIRD_LOGIN_ERROR = "第三方登录异常,请联系管理员！";
    /**
     * 文件不存在
     */
    String FILE_NO_EXIST = "文件不存在！";
    /**
     * 值可用
     */
    String VALUE_AVALIABLE = "该值可用！";
    /**
     * 值不可用
     */
    String VALUE_NO_AVALIABLE = "该值不可用，系统中已存在！";
    /**
     * 验证码无效
     */
    String INVALID_CAPTCHA = "验证码无效！";
    /**
     * 验证码错误
     */
    String CAPTCHA_ERROR = "验证码错误！";
    /**
     * 获取验证码出错
     */
    String GET_CAPTCHA_ERROR = "获取验证码出错！";
    /**
     * 退出登录失败
     */
    String OUT_LOGIN_ERROR = "退出登录失败！";
    /**
     * 退出登录成功
     */
    String OUT_LOGIN_SUCCESS = "退出登录成功！";
    /**
     * Token无效
     */
    String INVALID_TOKEN = "Token无效！";
    /**
     * 手机号不允许为空
     */
    String PHONE_NO_EMPTY = "手机号不允许为空！";
    /**
     * 验证码有效
     */
    String CODE_EFFECTIVE = "验证码10分钟内，仍然有效！";
    /**
     * 手机已注册
     */
    String PHONE_REGISTERED = "手机号已经注册，请直接登录！";
    /**
     * 用户不存在
     */
    String ACCOUNT_NON_EXISTENT = "该用户不存在，请注册！";
    /**
     * 用户注销
     */
    String ACCOUNT_CANCELLATION = "该用户已注销！";
    /**
     * 用户冻结
     */
    String ACCOUNT_FROZEN = "该用户已冻结！";
    /**
     * 用户冻结
     */
    String ACCOUNT_RESIGNED = "该用户已离职！";
    /**
     * 短信验证码发送失败（第三方原因）
     */
    String SMS_CODE_SEND_ERROR = "短信验证码发送失败,请稍后重试！";
    /**
     * 短信发送失败（系统原因）
     */
    String SMS_CODE_SEND_ERROR_EXCEPTION = "短信发送失败，请联系管理员！";
    /**
     * 手机验证码错误
     */
    String SMS_CODE_ERROR = "手机验证码错误！";
    /**
     * 用户未绑定部门
     */
    String NO_DEPARTMENT = "用户暂未归属部门,不可登录!";
    /**
     * 全部已读
     */
    String READ_ALL = "全部已读！";
    /**
     * 错误,类型编码为空!
     */
    String CODING_TYPE_ISEMPTY = "类型编码为空！";
    /**
     * 参数有误.[null]!
     */
    String NULL_PARAMETER_ERROR = "加载分类字典树参数有误.[null]!";
    /**
     * 参数有误.[code]!
     */
    String CODE_PARAMETER_ERROR = "FIRST参数有误.[code]!";
    /**
     * ids 不能为空
     */
    String IDS_ISEMPTY = "ids不能为空！";
    /**
     * id 不能为空
     */
    String ID_ISEMPTY = "id不能为空";

    /**
     * 添加成功!
     */
    String ADD_SUCCESS = "添加成功！";
    /**
     * 添加成功!
     */
    String SAVE_SUCCESS = "保存成功！";
    /**
     * 添加失败!
     */
    String ADD_FAIL = "添加失败！";
    /**
     * 添加失败，存在默认且有效数据
     */
    String ADD_FAIL_NON_UNIQUE = "已存在一条默认且有效，请先取消原有默认再进行新增！";
    /**
     * 删除成功!
     */
    String DEL_SUCCESS = "删除成功！";
    /**
     * 编辑成功!
     */
    String EDIT_SUCCESS = "编辑成功！";
    /**
     * 编辑成功!
     */
    String EDIT_FAIL = "编辑失败！";
    /**
     * 批量删除成功!
     */
    String BATCH_DEL_SUCCESS = "批量删除成功！";
    /**
     * 无匹配数据
     */
    String NO_MATCHING_DATA = "无匹配数据！";
    /**
     * 字典Code格式不正确！
     */
    String DICTIONARY_CODE_FORMAT_IS_INCORRECT = "字典Code格式不正确！";
    /**
     * ElasticSearch获取索引字段映射
     */
    String NOT_FOUND = "404 Not Found";
    /**
     * 重复提交
     */
    String DO_NOT_RESUBMIT = "请勿重复提交！";


    //CRM - START

    String TENANT_CODE_SAME = "存在相同租户编码！";

    String SYS_URL_TYPE_SAME = "存在系统类型！";

    String ERROR_MSG_ONE = "存在角色绑定用户,无法删除！";

    String DELETE_ADMIN_ERROR = "超级管理员不能删除！";

    String NOT_ONE_DATA_ERROR = "存在多条数据！";

    String CREDIT_CODE_EXIT_ERROR = "统一社会信用代码已存在！";

    String CUSTOMER_NAME_EXIT_ERROR = "客户名称已存在！";

    String CUSTOMER_NAME_LONG_ERROR = "客户名称太长(超过50)！";

   String CURRENT_STATE_NOT_OPT= "当前状态无法进行操作";

    String UNDER_REVIEW_NOT_OPT= "审核中无法操作";

    String NOT_EDIT_CHARGER_ERROR= "无法更新负责人!";

    String NUM_ALREADY_EXISTS= "该编号已存在";

    String TYPE_ALREADY_EXISTS= "该额度类型已存在";


    String LEGAL_ENTITY_GRANTED_CREDIT= "该法人主体已经授信";

    String LEGAL_CUST_CREDIT= "该客户已经授信";

    String INSUFFICIENT_REMAINING_AMOUNT= "授信额度不够";

    String CREDIT_AMOUNT_ERROR= "授信金额不能小于等与0";

    String CUTS_IN_RISK_ERROR = "客户存在风险，请联系相关人员！";

    String POST_INCIDENCE_RELATION_ONE = "有存在岗位绑定关系,无法删除！";

    String EXIT_SAME_ERROR= "存在相同数据!";


    String NOT_CHARGER_ERROR= "请联系负责人修改!";

    String SYS_USER_ON_JOB_REMIND= "有在职员工,无法删除！";

    String CREDIT_DELETE_ERROR = "已分配,不能删除!";





    // CRM - END



    //WMS - START

    String MATERIAL_NOT_EXIT = "物料信息不存在";

    String PARAM_ERROR = "参数错误";

    String MATERIAL_CODE_REPEAT = "物料编码重复！";

    String MATERIAL_NAME_REPEAT = "物料名称重复！";

    String IS_CHANGE_ORDER_ERROR = "已转为出库单不能修改！";

    String WAVE_IS_ALLOCATEll = "波次已分配！";

    String OUTBOUND_IS_DISTRIBUTION = "出库单已分配！";

    String OUTBOUND_IS_FINISH = "出库单已出库！";

    String OUTBOUND_IS_STOCK = "出库单缺货中！";

    String CREATE_PACKING_SUCCESS = "生成拣货下架单成功！";

    String CREATE_PACKING_ERROR = "生成拣货下架单失败！";

    String CANCEL_PACKING_SUCCESS = "撤销拣货下架单成功！";

    String CANCEL_PACKING_ERROR = "撤销拣货下架单失败！";

    String CHANGE_OUTBOUND_ORDER_SUCCESS = "转出库单成功！";

    String OUTBOUND_DISTRIBUTION_SUCCESS = "出库单已分配成功！";

    String CANCEL_OUTBOUND_DISTRIBUTION_SUCCESS = "撤销出库单已分配成功！";

    String CREATE_WAVE_SUCCESS = "成功生成波次！";

    String TO_WAVE_NOT_SAME_ERROR = "请选择同仓库、同货主出库单生成！";

    String EMPTY_OUTBOUND_ORDER_ERROR = "请选择出库单！";

    String PACKING_FINISH_ERROR = "已完成下架，请勿重新下架！";

    String PACKING_ERROR = "正在拣货下架中，请勿重新下架！";

    String PACKING_ACCOUNT_ERROR = "待拣货下架与实际拣货下架不符！";

    String PACKING_MATERIALCODE_ERROR = "拣货下架物料编号不同！";

    String PACKING_LOCATION_ERROR = "拣货下架库位不同！";

    String PACKING_FINISH_SUCCESS = "拣货下架完成！";

    String SHIPPER_REVIEW_SCANNED_ACCOUNT_ERROR = "扫描数量和待扫描数量不相等！";

    String OUTBOUND_ISWAVE_ERROR = "出库单已生成波次！";

    String THE_SAME_ALLOCATION_STRATEGY_ERROR = "已存在相同分配策略！";

    String THE_SAME_ALLOCATION_STRATEGY_DETAIL_ERROR = "已存在相同分配策略配置！";

    String EMPTY_ALLOCATION_STRATEGY_CODE = "分配策略编码为空！";

    String EMPTY_ALLOCATION_STRATEGY_ID = "分配策略ID为空！";


    String THE_SAME_MATERIAL_CANNOT_SAME_CONTAINER = "相同物料不能放在同一个容器！";

    String NOT_THIS_ORDER_MATERIALS_INFORMATION = "不是该订单的物料信息！";

    String CONTAINER_DOES_NOT_EXIST = "容器不存在或已关闭！";

    String  INEXISTENCE_MATERIALS_INFORMATION ="物料信息不存在！";

    String  SN_NUMBER_INEXISTENCE_MATERIALS_INFORMATION ="物料序列号不存在该物料信息！";

    String STORAGE_LOCATION_INEXISTENCE_EXIST = "库位编号不存在！";

    String EXIT_ALLCATION_STRATEGY_DETAIL = "请先删除策略配置详情!";

    String EXIT_SAMENAME = "存在相同名称!";

    String ALL_COMFIRM_CANCEL_ERR = "已全部确认收货，无法撤销收货！";

    String ALL_CANCELLATION_CANCEL_ERR = "此状态，无法撤销收货！";

    // WMS -END

    String ALL_NO_MOVE_THE_LIBRARY_NUMBER_ERR = "移库数量不能等于0！";
}

