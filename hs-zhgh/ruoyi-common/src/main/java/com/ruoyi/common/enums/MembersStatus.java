package com.ruoyi.common.enums;

/**
 * 会员状态
 * 
 * @author ruoyi
 */
public enum MembersStatus
{
    // 工号已存在
    NUM_EXIST(201, "工号已存在"),
    SUCCESS(200,"操作成功"),
    // 账号已存在
    USERNAME_EXIST(202, "账号已存在"),
    // 教职工角色不存在
    ROLE_NOT_EXIST(203, "教职工角色不存在"),
    // 会员角色不存在
    MEMBERS_ROLE_NOT_EXIST(203, "会员角色不存在"),
    // 用户新增失败
    ADD_USER_FAIL(204, "用户新增失败"),
    // 教职工新增失败
    ADD_TEACHER_FAIL(205, "教职工新增失败"),
    // 教职工不存在
    TEACHER_NOT_EXIST(206, "教职工不存在"),
    // 用户不存在
    USER_NOT_EXIST(207, "用户不存在"),
    // 删除失败
    DELETE_FAIL(208, "删除失败"),
    ;

    private final Integer code;
    private final String info;

    MembersStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
