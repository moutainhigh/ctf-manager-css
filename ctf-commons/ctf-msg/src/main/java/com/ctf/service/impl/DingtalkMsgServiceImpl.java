package com.ctf.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.ctf.RedisUtil;
import com.ctf.service.DingtalkMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ctf.utils.utils.DateUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送钉钉消息
 */
@Service
@Slf4j
public class DingtalkMsgServiceImpl implements DingtalkMsgService {

    /**
     *  钉钉 应用凭证
     */
    @Value("${dingtalk.agentId}")
    private String agentid;//发送消息时使用的微应用的AgentID
    @Value("${dingtalk.miniappid}")
    private String miniappid;
    @Value("${dingtalk.appkey}")
    private String appkey;//应用的唯一标识key
    @Value("${dingtalk.appsecret}")
    private String appsecret;//应用的密钥


    @Autowired
    private RedisUtil redisUtils;

    /**
     获取企业凭证
     基本信息
     请求方式：GET
     请求地址：https://oapi.dingtalk.com/gettoken
     * @param appkey 应用的唯一标识key。
     * @param appsecret 应用的密钥。AppKey和AppSecret可在钉钉开发者后台的应用详情页面获取。
     * @return
     */
    @Override
    public JSONObject gettoken(String appkey, String appsecret) {
        JSONObject jsonObject = new JSONObject();
        String key = "dingtalk_access_token"+"_"+appkey;
        String dingtalk_access_token = redisUtils.get(key).toString();//钉钉-调用服务端API的应用凭证
        if(StrUtil.isNotEmpty(dingtalk_access_token)){
            jsonObject.set("errcode", 0);
            jsonObject.set("access_token", dingtalk_access_token);
            jsonObject.set("errmsg", "ok");
            return jsonObject;
        }
        HttpResponse response = HttpRequest.get("https://oapi.dingtalk.com/gettoken?appkey="+appkey+"&appsecret="+appsecret)
                .execute();
        String feedback = response.body();
        jsonObject = new JSONObject(feedback);

        Integer errcode = jsonObject.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode) || !errcode.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject.getStr("errmsg");
//            log.warn(errmsg);
            return jsonObject;
        }
        //将凭证设置在redis里面，有效期7200秒，两个小时
        dingtalk_access_token = jsonObject.getStr("access_token");
        redisUtils.set(key, dingtalk_access_token, 7200);
        return jsonObject;
    }

    /**
     手机号获取userid
     基本信息
     请求方式：GET
     请求地址：https://oapi.dingtalk.com/user/get_by_mobile
     * @param access_token 服务端API授权凭证
     * @param mobile 要获取的用户手机号。
     * @return
     */
    @Override
    public JSONObject userGetByMobile(String access_token, String mobile) {
        HttpResponse response = HttpRequest.get("https://oapi.dingtalk.com/user/get_by_mobile?access_token="+access_token+"&mobile="+mobile)
                .execute();
        String feedback = response.body();
        JSONObject jsonObject = new JSONObject(feedback);

        Integer errcode = jsonObject.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode) || !errcode.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject.getStr("errmsg");
//            log.warn(errmsg);
        }
        return jsonObject;
    }

    /**
     发送工作通知
     基本信息
     请求方式：POST
     请求地址：https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2

     注意：
        1.同一个应用相同内容的消息，同一个用户一天只能接收一次。
        2.同一个应用给同一个用户发送消息，企业内部应用一天不得超过500次。
        3.通过设置to_all_user参数全员推送消息，一天最多3次。且企业发送消息单次最多只能给5000人发送，ISV发送消息单次最多能给1000人发送

     * @param access_token 服务端API的应用凭证
     * @param body Body参数{支持发送 文本、图片、语音、文件、链接、OA、markdown、卡片 等消息,按照格式定义}
     * @return
     */
    @Override
    public JSONObject sendMessage(String access_token, Map<String, Object> body) {
        //组装请求的参数
        JSONObject request = new JSONObject();
        request.set("agent_id", body.get("agent_id"));
        request.set("userid_list", body.get("userid_list"));
        request.set("msg", body.get("msg"));
        HttpResponse response = HttpRequest.post("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?access_token="+access_token)
                .body(request.toString())
                .execute();
        String feedback = response.body();
        JSONObject jsonObject = new JSONObject(feedback);

        Integer errcode = jsonObject.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode) || !errcode.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject.getStr("errmsg");
//            log.warn(errmsg);
        }

        return jsonObject;
    }

    /**
     获取工作通知消息的发送结果
     基本信息
     请求方式：POST
     请求地址：https://oapi.dingtalk.com/topapi/message/corpconversation/getsendresult

     注意：
        1.通过接口发送工作通知，当接收人列表超过100人时，不支持调用该接口，否则系统会返回调用超时。

     * @param access_token
     * @param body
     * @return
     */
    @Override
    public JSONObject getSendResult(String access_token, Map<String, Object> body) {
        //组装请求的参数
        JSONObject request = new JSONObject();
        request.set("agent_id", body.get("agent_id"));
        request.set("task_id", body.get("task_id"));
        HttpResponse response = HttpRequest.post("https://oapi.dingtalk.com/topapi/message/corpconversation/getsendresult?access_token="+access_token)
                .body(request.toString())
                .execute();
        String feedback = response.body();
        JSONObject jsonObject = new JSONObject(feedback);

        Integer errcode = jsonObject.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode) || !errcode.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject.getStr("errmsg");
//            log.warn(errmsg);
        }
        return jsonObject;
    }

    /**
     * 通过手机号给钉钉用户发送消息 (自定义的业务接口)
     * @param mobile 接收消息用户的手机号
     * @param message 发送的消息(目前仅发送文本消息，其实钉钉可以支持发送 文本、图片、语音、文件、链接、OA、markdown、卡片 等消息)
     * @return
     */
    @Override
    public JSONObject sendMessageByMobile(String mobile, String message) {
        //1.获取企业凭证,拿到token
        JSONObject jsonObject1 = this.gettoken(appkey, appsecret);
        String access_token = jsonObject1.getStr("access_token");
        Integer errcode1 = jsonObject1.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode1) || !errcode1.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject1.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject1.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject1.set("errmsg", errmsg);
            return jsonObject1;
        }

        //2.手机号获取userid,拿到钉钉userid
        JSONObject jsonObject2 = this.userGetByMobile(access_token, mobile);
        String userid = jsonObject2.getStr("userid");
        Integer errcode2 = jsonObject2.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode2) || !errcode2.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject2.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject2.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject2.set("errmsg", errmsg);
            return jsonObject2;
        }
        //3.发送工作通知,异步发送通知，拿到task_id
        Map<String, Object> body1 = new HashMap<>();
        body1.put("agent_id", agentid);
        body1.put("userid_list", userid);
        Map<String, Object> msg = new HashMap<>();
        msg.put("msgtype", "text");
        msg.put("text", new JSONObject().set("content", message+"[时间："+ DateUtils.getLocalToStr(LocalDateTime.now()) +"]"));
        body1.put("msg", msg);
        JSONObject jsonObject3 = this.sendMessage(access_token, body1);
        String task_id = jsonObject3.getStr("task_id");
        Integer errcode3 = jsonObject3.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode3) || !errcode3.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject3.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject3.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject3.set("errmsg", errmsg);
            return jsonObject3;
        }

        //4.获取工作通知消息的发送结果
        Map<String, Object> body2 = new HashMap<>();
        body2.put("agent_id", agentid);
        body2.put("task_id", task_id);
        JSONObject jsonObject4 = this.getSendResult(access_token, body2);
        Integer errcode4 = jsonObject4.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode4) || !errcode4.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject4.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject4.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject4.set("errmsg", errmsg);
            return jsonObject4;
        }

        return jsonObject4;
    }

    @Override
    public JSONObject sendMessageByMobile(String mobile, String message, String agentid, String appkey, String appsecret) {

        //1.获取企业凭证,拿到token
        JSONObject jsonObject1 = this.gettoken(appkey, appsecret);
        String access_token = jsonObject1.getStr("access_token");
        Integer errcode1 = jsonObject1.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode1) || !errcode1.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject1.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject1.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject1.set("errmsg", errmsg);
            return jsonObject1;
        }

        //2.手机号获取userid,拿到钉钉userid
        JSONObject jsonObject2 = this.userGetByMobile(access_token, mobile);
        String userid = jsonObject2.getStr("userid");
        Integer errcode2 = jsonObject2.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode2) || !errcode2.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject2.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject2.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject2.set("errmsg", errmsg);
            return jsonObject2;
        }
        //3.发送工作通知,异步发送通知，拿到task_id
        Map<String, Object> body1 = new HashMap<>();
        body1.put("agent_id", agentid);
        body1.put("userid_list", userid);
        Map<String, Object> msg = new HashMap<>();
        msg.put("msgtype", "text");
        msg.put("text", new JSONObject().set("content", message+"[时间："+ DateUtils.getLocalToStr(LocalDateTime.now()) +"]"));
        body1.put("msg", msg);
        JSONObject jsonObject3 = this.sendMessage(access_token, body1);
        String task_id = jsonObject3.getStr("task_id");
        Integer errcode3 = jsonObject3.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode3) || !errcode3.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject3.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject3.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject3.set("errmsg", errmsg);
            return jsonObject3;
        }

        //4.获取工作通知消息的发送结果
        Map<String, Object> body2 = new HashMap<>();
        body2.put("agent_id", agentid);
        body2.put("task_id", task_id);
        JSONObject jsonObject4 = this.getSendResult(access_token, body2);
        Integer errcode4 = jsonObject4.getInt("errcode");
        if(ObjectUtil.isEmpty(errcode4) || !errcode4.equals(0)){
            String errmsg = StrUtil.isEmpty(jsonObject4.getStr("errmsg")) ? "接口调用失败，无返回值！" : jsonObject4.getStr("errmsg");
//            log.warn(errmsg);
            jsonObject4.set("errmsg", errmsg);
            return jsonObject4;
        }

        return jsonObject4;
    }


}
