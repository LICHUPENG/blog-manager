package com.lcp.blog.service;

import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.AppFunction;
import com.lcp.blog.common.utils.FastuuidUtil;
import com.lcp.blog.dao.BlogUserDao;
import com.lcp.blog.dao.RoleDao;
import com.lcp.blog.dao.UserRoleDao;
import com.lcp.blog.entity.BlogUser;
import com.lcp.blog.entity.Role;
import com.lcp.blog.entity.UserRole;
import com.lcp.blog.entity.form.RegisterForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Objects;

/**
 * @author lcp
 * @date 2019-12-07 14:05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserLoginService {

    private final BlogUserDao blogUserDao;

    private final UserRoleDao userRoleDao;

    private final RoleDao roleDao;

    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterForm form) {
        if (!Objects.equals(form.getPassword(), form.getConfirm_password())) {
            return "两次输入密码不一致";
//            throw new AppException("两次输入密码不一致", AppException.FORM_INVALID);
        }

        String nickname = form.getNickname();
        BlogUser nicknameUser = new BlogUser();
        nicknameUser.setNickname(nickname);
        if (!this.blogUserDao.select(nicknameUser).isEmpty()) {
            return "昵称已存在";
//            throw new AppException("昵称已存在", AppException.FORM_INVALID);
        }

        String mobile = form.getMobile();
        BlogUser mobileUser = new BlogUser();
        mobileUser.setMobile(mobile);
        if (!this.blogUserDao.select(mobileUser).isEmpty()) {
            return "手机号码已存在";
//            throw new AppException("手机号码已存在", AppException.FORM_INVALID);
        }

        String email = form.getEmail();
        BlogUser emailUser = new BlogUser();
        emailUser.setEmail(email);
        if (!this.blogUserDao.select(emailUser).isEmpty()) {
            return "邮箱已存在";
//            throw new AppException("邮箱已存在", AppException.FORM_INVALID);
        }
        String salt = AppFunction.md5(form.getPassword());
        String encodedPassword = AppFunction.encryption(form.getPassword(), salt);
        String uuid = FastuuidUtil.genUUID();
        BlogUser user = new BlogUser();
        user.setUser_id(uuid);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setNickname(nickname);
        user.setPassword(encodedPassword);
        user.setSalt(salt);
        user.setCreated_time(AppFunction.getTime());
        this.blogUserDao.insertSelective(user);

        Role roleParams = new Role();
        roleParams.setRole("blog_user");
        Role role = this.roleDao.selectOne(roleParams);
        if (role == null) {
            return "未找到角色";
//            throw new AppException("未找到角色", AppException.FORM_INVALID);
        }
        UserRole userRole = new UserRole();
        userRole.setUser_id(uuid);
        userRole.setRole_id(role.getRole_id());
        this.userRoleDao.insertSelective(userRole);

        return null;
    }

    public boolean checkPassword(String user, String password) {
        Example example = new Example(BlogUser.class);
        example.createCriteria()
                .andEqualTo("mobile", user)
                .orEqualTo("email", user);
        BlogUser blogUser = this.blogUserDao.selectOneByExample(example);
        if (blogUser == null) {
//            return false;
            throw new AppException("用户名或密码错误！", 403);
        }
        String userPassword = blogUser.getPassword();
        String md5 = AppFunction.encryption(password, blogUser.getSalt());
        if (md5 == null ) {
            return false;
//            throw new AppException("系统繁忙，请重新输入！", 403);
        }
        if (Objects.equals(userPassword, md5)) {
            return true;
        }
        return false;
    }

}
