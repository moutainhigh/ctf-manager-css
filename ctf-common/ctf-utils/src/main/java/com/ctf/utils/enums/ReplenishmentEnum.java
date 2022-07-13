package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;


public enum ReplenishmentEnum {
    ;

    @AllArgsConstructor
    @Getter
    public enum Model{
        INHOUSE("inhouse"),
        SP("SP"),
        ;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public enum ModelBizType{
//        IBU("inhouse","BU"),
        IPCG("inhouse","PCG"),
        IMBG("inhouse","MBG"),
        IDCG("inhouse","DCG"),
        ITSD("inhouse","T&SD"),

        SPCG("SP","PCG"),
        SMBG("SP","MBG"),
        SDCG("SP","DCG"),
        STSD("SP","T&SD"),
        ;
        private String name;
        private String value;
    }



    @AllArgsConstructor
    @Getter
    public enum Exception{

        NHFW("no hawb from wh","NO HAWB FROM WH"),
        NUBTL(" no update beyond time limit","NO UPDATE BEYOND TIME LIMIT"),
        NRFC("no response from carrier","NO RESPOSE FROM CARRIER"),
        ;

        private String message;
        private String value;

    }
}
