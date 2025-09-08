package com.wcsoft.mapper;

import com.wcsoft.entity.App;
import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(String id);

    int insert(App record);

    App selectByPrimaryKey(String id);

    List<App> selectAll();

    int updateByPrimaryKey(App record);
}