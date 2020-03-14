package com.lcp.blog.service;

import com.lcp.blog.dao.BlogUserDao;
import com.lcp.blog.entity.BlogUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author lcp
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CheckNickNameService {

    private final BlogUserDao blogUserDao;

    public boolean checkNickName(@Validated String nickname) {
        BlogUser user = new BlogUser();
        user.setNickname(nickname);
        return !this.blogUserDao.select(user).isEmpty();
    }
}
