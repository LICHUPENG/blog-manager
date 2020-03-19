package com.lcp.blog.entity.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author lcp
 * @date 2019-12-05 16:25
 */
@Data
public class loginForm {

    @NotBlank
    @Size(max = 7)
    private String nickname;

    @Pattern(message = "手机号码格式不对",
            regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$")
    private String mobile;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 16)
    private String password;

    @NotBlank
    @Size(min = 6, max = 16)
    private String confirm_password;

}
