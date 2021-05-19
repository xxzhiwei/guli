package com.atshixin.acl.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    private String id;

    private String username;

    private String password;

    private String nickName;

    private String salt;

    private String token;
}
