package com.fjc.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="Response对象", description="全局统一返回结果")
public class ResultJSON implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean flag;

    @ApiModelProperty(value = "状态码")
    private String code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "传输的数据")
    private Object data;

    private ResultJSON(){}

    private ResultJSON(Boolean flag, String code, String message){
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    private ResultJSON(Boolean flag, String code, String message, Object data){
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功并有数据
     */
    public static ResultJSON ok(Object data){
        return new ResultJSON(ResultCodeEnum.SUCCESS.getFlag(),
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功没数据
     */
    public static ResultJSON ok(){
        return new ResultJSON(ResultCodeEnum.SUCCESS.getFlag(),
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 请求失败
     */
    public static ResultJSON fail(Object data){
        return new ResultJSON(ResultCodeEnum.FAIL.getFlag(),
                ResultCodeEnum.FAIL.getCode(),
                ResultCodeEnum.FAIL.getMessage(),data);
    }

    /**
     * 系统异常
     */
    public static ResultJSON error(Object data){
        return new ResultJSON(ResultCodeEnum.ERROR.getFlag(),
                ResultCodeEnum.ERROR.getCode(),
                ResultCodeEnum.ERROR.getMessage(),data);
    }

    public ResultJSON data(Object data){
        this.setData(data);
        return this;
    }
    public ResultJSON message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultJSON flag(Boolean flag){
        this.setFlag(flag);
        return this;
    }
    public ResultJSON code(String code){
        this.setCode(code);
        return this;
    }
}