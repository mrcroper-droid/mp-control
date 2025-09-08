package com.wcsoft.mapper;

import com.wcsoft.entity.AppReply;
import java.util.List;

public interface AppReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppReply record);

    AppReply selectByPrimaryKey(String id);

    List<AppReply> selectAll();

    int updateByPrimaryKey(AppReply record);
}