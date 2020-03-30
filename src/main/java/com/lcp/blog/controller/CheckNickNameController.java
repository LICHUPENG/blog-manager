package com.lcp.blog.controller;

import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.Result;
import com.lcp.blog.entity.form.RegisterForm;
import com.lcp.blog.entity.form.group.CheckName;
import com.lcp.blog.service.CheckNickNameService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lcp
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/check")
public class CheckNickNameController {

    private final CheckNickNameService checkNickNameService;

    @GetMapping("/nickname")
    public Result check(@Validated({CheckName.class}) RegisterForm form) {
        String nickname = form.getNickname();
        if (StringUtils.isBlank(nickname)) {
            return new Result("请输入昵称", AppException.FORM_INVALID);
        }
        if (this.checkNickNameService.checkNickName(nickname)) {
            return new Result("昵称已存在!", AppException.FORM_INVALID);
        }
        return new Result("昵称可用");
    }
}
