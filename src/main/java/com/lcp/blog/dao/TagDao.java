package com.lcp.blog.dao;

import com.lcp.blog.common.dao.BaseDao;
import com.lcp.blog.entity.Tag;
import com.lcp.blog.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lcp
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TagDao extends BaseDao<Tag> {

    private final TagMapper mapper;

    @Override
    protected Mapper<Tag> getMapper() {
        return this.mapper;
    }
}
