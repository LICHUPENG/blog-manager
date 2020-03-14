package com.lcp.blog.entity.form;

import com.lcp.blog.entity.form.group.Register;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author lcp
 */
@Data
public class RegisterForm {

    @NotBlank
    @Length(min = 3, max = 16, message = "昵称只能由3到8位汉字字母数字或者下划线组成", groups = {Register.class})
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "昵称只能由3到8位汉字字母数字或者下划线组成", groups = {Register.class})
    private String nickname;

    @Pattern(regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$", message = "请输入正确的手机格式！", groups = {Register.class})
    private String mobile;

    @Email(message = "请输入正确的邮箱！", groups = {Register.class})
    private String email;

    @NotBlank(message = "密码不能为空！", groups = {Register.class})
    @Length(min = 6, max = 16, message = "密码长度6~16位！", groups = {Register.class})
    private String password;

    private String confirm_password;
}
