package com.tuoke.business.controller;

import com.tuoke.business.entity.Transaction;
import com.tuoke.business.service.PositionService;
import com.tuoke.business.service.TransactionService;
import com.tuoke.business.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @program: idea_work
 * @description:
 * @author: ng
 * @create: 2020-06-03 20:08
 **/
@RestController
@Validated
public class TransactionController  {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PositionService positionService;

    @RequestMapping("/")
    public R getPositionList(){
        return R.ok().put("data",positionService.getList());
    }

    @RequestMapping("/addTransaction")
    public R addTransaction(@RequestBody @Valid Transaction transaction){
        transactionService.addTransaction(transaction);
        return R.ok();
    }

}
