package com.ctf.feign;

import com.ctf.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用空运模块的服务
 *
 * @author william
 * @description
 * @Date: 2020-09-17 15:27
 */
@FeignClient(value = "jayud-freight-air-api")
public interface AirfreightClient {

    @RequestMapping(value = "/api/airfreight/bookingSpace")
    public Boolean doBookingSpace(@RequestParam(name = "json") String json);


    @RequestMapping(value = "/airfreight/toVivo/forwarder/bookingConfirmed")
    CommonResult forwarderBookingConfirmedFeedback(@RequestBody String value);


    /**
     * 提单跟踪信息回执给vivo
     */
    @PostMapping("/airfreight/toVivo/forwarder/ladingInfo")
    CommonResult forwarderLadingInfo(@RequestBody String value);

    /**
     * 提单文件传给vivo
     */
    @PostMapping("/airfreight/toVivo/forwarder/ladingFile")
    public CommonResult forwarderLadingFile(@RequestBody String value);


    /**
     * 货代抛空运费用数据到vivo
     */
    @PostMapping("/airfreight/toVivo/forwarder/forwarderAirFarePush")
    public CommonResult forwarderAirFarePush(@RequestBody String value);

    /**
     * 车辆信息传给vivo
     */
    @PostMapping("/airfreight/toVivo/forwarder/vehicleInfo")
    public CommonResult forwarderVehicleInfo(@RequestBody String value);
}
