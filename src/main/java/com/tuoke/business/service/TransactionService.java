package com.tuoke.business.service;

import com.tuoke.business.entity.Transaction;

public interface TransactionService {
    /**
     * @Author ng
     * @Description 新增交易
     * @Date 20:52 2020/6/2
     * @Param [transaction]
     * @return com.tuoke.business.entity.Transaction
     **/
    public Transaction addTransaction(Transaction transaction);

}
