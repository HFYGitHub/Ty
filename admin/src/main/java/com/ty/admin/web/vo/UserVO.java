package com.ty.admin.web.vo;

import com.ty.common.entity.User;
import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String userName;

    public static UserVO from(User user) {
        if (user == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUserName(user.getUserName());
        return vo;
    }
}

