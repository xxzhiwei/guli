package com.atshixin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenR {
    private String token;
    private Long expiresTime; // 有效截止时间
    private Integer expiresDays; // 有效天数
}
