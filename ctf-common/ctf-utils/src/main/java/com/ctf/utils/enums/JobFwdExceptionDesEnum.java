package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum JobFwdExceptionDesEnum {

    NODENOTRECEIVED("Node not received"),
    NODEDATAPARSINGEXCEPTION("Node data parsing exception"),
    HAWBCANNOTFINDOUTBOUND("HAWB cannot find outbound"),
    TRACKINGNUMBERCANNOTBEFOUND("This tracking number cannot be found"),
    ;

    private String desc;

}
