package com.wcsoft.mapper;

import com.wcsoft.entity.AppMenu;
import java.util.List;

public interface AppMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppMenu record);

    AppMenu selectByPrimaryKey(String id);

    List<AppMenu> selectAll();

    int updateByPrimaryKey(AppMenu record);
}