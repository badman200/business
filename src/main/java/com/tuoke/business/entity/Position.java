package com.tuoke.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: idea_work
 * @description:
 * @author: ng
 * @create: 2020-06-03 18:22
 **/
@Data
public class Position {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String securityCode;
    private Integer quantity;


}
