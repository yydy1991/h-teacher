package com.barracuda.barracudateacher.tool;

import com.barracuda.common.core.domain.entity.SysUser;
import com.barracuda.common.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTool {

    private UserTool() {
    }

    /**
     * 获取当前用户
     */
    public static SysUser getCurrentUser() {
        SysUser result = null;
        try {
            result = ShiroUtils.getSysUser();
        } catch (Exception e) {
            log.error("Can not get current user." + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取当前用户登陆名称
     */
    public static String getCurrentUserLoginName() {
        String result = "";
        SysUser currentUser = getCurrentUser();
        if (currentUser != null) {
            result = currentUser.getLoginName();
        }
        return result;
    }

    /**
     * 是否为管理员
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
