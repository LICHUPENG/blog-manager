package com.lcp.blog.controller;

import com.lcp.blog.common.utils.AppException;
import com.lcp.blog.common.utils.Result;
import com.lcp.blog.entity.form.RegisterForm;
import com.lcp.blog.entity.form.group.Register;
import com.lcp.blog.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author dell
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class AuthController {

    private final UserLoginService userLoginService;

    /**
     * 模拟Feign远程调用，这里暴露一个请求接口`/feignHello`
     * 这个请求接口将调用`AuthFeignService`（Feign接口）
     * Feign接口（`AuthFeignService`接口）将会通过`@FeignClient(value = "template-admin")`在Eureka注册中心寻找`template-admin`模块
     * Feign接口类似Controller的Rest接口，也暴露一个地址，这个地址对应`template-admin`模块的一个Controller接口地址
     *
     * @param name
     * @return
     */
    @GetMapping("/feignHello/{name}")
    public String feignHello(@PathVariable String name) {
        return name;
    }

    @PostMapping("/login")
    public Result login(@RequestParam("user") String user,
                        @RequestParam("password") String password) {
        if (!this.userLoginService.checkPassword(user, password)) {
            return new Result("用户名或者密码错误，请重新输入！", 403);
        }
        return new Result();
    }

    @PostMapping("/register")
    public Result register(@Validated({Register.class}) RegisterForm form) {
        String register = this.userLoginService.register(form);
        if (!StringUtils.isEmpty(register)) {
            return new Result(register, AppException.FORM_INVALID);
        }
        return new Result("注册成功！");
    }

    @PostMapping("/logout")
    public Result logout() {
        return new Result("退出成功！");
    }

}