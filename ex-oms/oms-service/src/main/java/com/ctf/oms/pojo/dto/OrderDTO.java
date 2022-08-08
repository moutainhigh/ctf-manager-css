package com.ctf.oms.pojo.dto;

import com.ctf.oms.pojo.entity.OmsOrder;
import com.ctf.oms.pojo.entity.OmsOrderItem;
import com.ctf.ums.dto.MemberDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author H.m
 * @desc

 * @date 2022/8/5 10:30
 */
@Data
@Accessors(chain = true)
public class OrderDTO {

    private OmsOrder order;

    private List<OmsOrderItem> orderItems;

    private MemberDTO member;

}
