package com.sdjz.common.enums;


import lombok.Getter;

@Getter
public enum SuccessSatus {

    FAIL(0),

    SUCCESS(1);

    public Integer status;

    SuccessSatus(Integer status){
        this.status = status;
    }
}
