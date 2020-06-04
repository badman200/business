package com.tuoke.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuoke.business.entity.Position;
import com.tuoke.business.entity.Transaction;
import com.tuoke.business.mapper.PositionMapper;
import com.tuoke.business.mapper.TransactionMapper;
import com.tuoke.business.service.TransactionService;
import com.tuoke.business.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: idea_work
 * @description: TransactionServiceImpl
 * @author: ng
 * @create: 2020-06-02 20:55
 **/
@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private PositionMapper positionMapper;

    /**
     * @Author ng
     * @Description 新增交易
     * @Date 20:56 2020/6/2
     * @Param [transaction]
     * @return com.tuoke.business.entity.Transaction
     **/
    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        //新增 更新 取消类型验证原交易是否存在
        if(Constant.CANCEL==transaction.getOperaType()||Constant.UPDATE==transaction.getOperaType()){
            Assert.isNull(transactionMapper.selectOne(new QueryWrapper<Transaction>().eq("trade_id",transaction.getTradeId()).eq("version",1)),"交易不存在");
        }
        //取消类型验证
        if(Constant.CANCEL==transaction.getOperaType()){
            QueryWrapper<Transaction> qw=new QueryWrapper<>();
            qw.eq("trade_id",transaction.getTradeId());
            qw.eq("opera_type",Constant.CANCEL);
            Assert.notNull(transactionMapper.selectOne(qw),"交易已取消");
        }
        //更新统计
        this.changePosition(transaction);
        this.setVersion(transaction);
        transactionMapper.insert(transaction);
        return transaction;
    }


    /**
     * @Author ng
     * @Description 统计交易
     * @Date 18:35 2020/6/3
     * @Param [transaction]
     * @return void
     **/
    public void changePosition(Transaction transaction){
        Position position=new Position();
        position.setSecurityCode(transaction.getSecurityCode());
        //新增交易类型
        if(Constant.INSERT==transaction.getOperaType()){
            if(Constant.BUY==transaction.getTradeType()){
                position.setQuantity(transaction.getQuantity());
            }else{
                position.setQuantity(-transaction.getQuantity());
            }
        }
        //更新交易类型
        if(Constant.UPDATE==transaction.getOperaType()){
            QueryWrapper<Transaction> qw=new QueryWrapper<>();
            qw.eq("trade_id",transaction.getTradeId());
            qw.eq("version",transaction.getVersion()-1);
            Transaction oldTransaction=transactionMapper.selectOne(qw);
            Assert.isNull(oldTransaction,"交易不存在"+transaction);
            if(Constant.BUY==transaction.getTradeType()){
                position.setQuantity(transaction.getQuantity()-oldTransaction.getQuantity());
            }else{
                position.setQuantity(-(transaction.getQuantity()-oldTransaction.getQuantity()));
            }
        }
        //取消交易类型统计数量整个交易的数量
        if(Constant.CANCEL==transaction.getOperaType()){
            QueryWrapper<Transaction> qw=new QueryWrapper<>();
            qw.eq("trade_id",transaction.getTradeId());
            qw.orderByAsc("version");
            List<Transaction> list=transactionMapper.selectList(qw);
            Assert.isTrue(list.size()>0,"交易不存在"+transaction);
            //交易首次交易类型
            if(Constant.BUY==list.get(0).getTradeType()){
                //变更的值为最后一次的交易值
                position.setQuantity(-list.get(list.size()).getQuantity());
            }
            if(Constant.SELL==list.get(0).getTradeType()){
                position.setQuantity(list.get(list.size()).getQuantity());
            }
        }
        Position po=positionMapper.selectOne(new QueryWrapper<Position>().eq("security_code",position.getSecurityCode()));
        if(StringUtils.isEmpty(po)){
            positionMapper.insert(position);
        }else{
            po.setQuantity(po.getQuantity()+position.getQuantity());
            positionMapper.updateById(po);
        }

    }

    /**
     * @Author ng
     * @Description 新增 更新 设置版本号
     * @Date 18:06 2020/6/3
     * @Param [transaction]
     **/
    public void setVersion(Transaction transaction){
        Integer version=transactionMapper.getMaxVersion(transaction.getTradeId());
        if(!StringUtils.isEmpty(version)){
            transaction.setVersion(version+1);
        }else{
            transaction.setVersion(1);
        }
    }
}
