package com.tuoke.business;

import com.tuoke.business.entity.Transaction;
import com.tuoke.business.mapper.TransactionMapper;
import com.tuoke.business.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Transactional(value = TransactionMode.ROLLBACK)
@RunWith(SpringRunner.class)
@SpringBootTest(args = "--mpw.key=2ecb06a08e06ab82")
@AutoConfigureMockMvc
public class BusinessApplicationTests {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Transaction> userList = transactionMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testAddTransaction(){
        Transaction transaction=new Transaction(4, 1,"Test",20, 1,2);
        transactionService.addTransaction(transaction);
        System.out.println(transaction);
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }




}