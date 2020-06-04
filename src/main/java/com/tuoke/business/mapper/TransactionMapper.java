package com.tuoke.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoke.business.entity.Transaction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TransactionMapper extends BaseMapper<Transaction> {

    /**
     * @Author ng
     * @Description 获取交易版本号
     * @Date 17:58 2020/6/3
     * @Param []
     * @return java.lang.Integer
     **/
    @Select("select max(version) from transaction where trade_id =#{id}")
    public Integer getMaxVersion(@Param("id")Integer id);

}
