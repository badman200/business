package com.tuoke.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tuoke.business.utils.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Transaction {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @Min(1)
    @Max(999999999)
    @NotNull
    private Integer tradeId;
    @Min(1)
    @Max(999999999)
    @NotNull
    private Integer version;
    @Length(min = 1, max = 50)
    @NotNull
    private String securityCode;
    @Min(1)
    @Max(999999999)
    @NotNull
    private Integer quantity;
    @Min(Constant.INSERT)
    @Max(Constant.CANCEL)
    @NotNull
    private Integer operaType;
    @Min(Constant.BUY)
    @Max(Constant.SELL)
    @NotNull
    private Integer tradeType;

    public Transaction() {
    }

    public Transaction(Integer id, Integer tradeId, Integer version, String securityCode, Integer quantity, Integer operaType, Integer tradeType) {
        this.id = id;
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.operaType = operaType;
        this.tradeType = tradeType;
    }

    public Transaction(Integer tradeId, Integer version, String securityCode, Integer quantity, Integer operaType, Integer tradeType) {
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.operaType = operaType;
        this.tradeType = tradeType;
    }
}