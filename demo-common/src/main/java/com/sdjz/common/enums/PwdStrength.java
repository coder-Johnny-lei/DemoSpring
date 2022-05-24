package com.sdjz.common.enums;

/**
 * @author : Johnny_JinLei
 * @version V1.0
 * @Project: becmas-parent-api
 * @Package com.hiwintech.common.constant
 * @Description: TODO
 * @date Date : 2021年11月01日 11:10 上午
 */
public enum PwdStrength {
    Simple(0,"simple"),
    Secondary(1,"secondary"),
    Complex(2,"complex");

    public int code;
    public String type;

    public int getCode(){
        return this.code;
    }

    public String getType(){
        return this.type;
    }

    PwdStrength(int code, String type){
        this.code = code;
        this.type = type;
    }

    public static PwdStrength codeOf(int code){
        for(PwdStrength pwdStrength : values()){
            if(pwdStrength.getCode() == code){
                return pwdStrength;
            }
        }
        throw new RuntimeException("No corresponding enumeration was found");
    }
}
