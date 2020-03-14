package com.lcp.blog.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AppLander implements Serializable {

//    private static final long serialVersionUID = 1795711843927147377L;
//
//    /** 登录者标识 */
//    private String landerId;
//
//    /** 登陆者类型 */
//    private String landerType;
//
//    public AppLander() {
//        super();
//    }
//
//    public AppLander(String id, String type) {
//        super();
//        this.landerId = id;
//        this.landerType = type;
//    }
//
//    public AppLander(String cache) {
//        super();
//        if (StringUtils.isBlank(cache)) {
//            throw new AppException("请先登录！", AppException.AUTH_INVALID);
//        }
//        String[] caches = cache.split("\\|");
//        if (caches.length < 2) {
//            throw new AppException("系统错误，请重新登录！", AppException.AUTH_INVALID);
//        }
//        this.landerId = caches[0];
//        this.landerType = caches[1];
//    }
//
//    @Override
//    public String toString() {
//        return this.landerId + "|" + this.landerType;
//    }
}
