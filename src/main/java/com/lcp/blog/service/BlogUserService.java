package com.lcp.blog.service;

import com.lcp.blog.dao.BlogUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcp
 * @date 2020-01-07 14:31
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BlogUserService {

    private final BlogUserDao userDao;

}
