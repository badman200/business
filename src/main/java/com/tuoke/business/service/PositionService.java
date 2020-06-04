package com.tuoke.business.service;

import com.tuoke.business.entity.Position;

import java.util.List;

public interface PositionService {

    /**
     * @Author ng
     * @Description 获取统计列表
     * @Date 20:02 2020/6/3
     * @Param []
     * @return java.util.List<com.tuoke.business.entity.Position>
     **/
    public List<Position> getList();
}
