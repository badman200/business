package com.tuoke.business.service.impl;

import com.tuoke.business.entity.Position;
import com.tuoke.business.mapper.PositionMapper;
import com.tuoke.business.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: idea_work
 * @description:
 * @author: ng
 * @create: 2020-06-03 20:03
 **/
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> getList() {
        return positionMapper.selectList(null);
    }
}
