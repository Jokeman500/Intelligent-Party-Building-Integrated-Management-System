package com.ruoyi.members.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.MembersConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.MembersStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.members.mapper.SysMembersMapper;
import com.ruoyi.members.domain.SysMembers;
import com.ruoyi.members.service.ISysMembersService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@Service
@Transactional
public class SysMembersServiceImpl extends ServiceImpl<SysMembersMapper,SysMembers> implements ISysMembersService
{
    @Autowired
    private SysMembersMapper sysMembersMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询会员
     * 
     * @param membersId 会员主键
     * @return 会员
     */
    @Override
    public SysMembers selectSysMembersByMembersId(Long membersId)
    {
        return sysMembersMapper.selectSysMembersByMembersId(membersId);
    }

    /**
     * 查询会员列表
     * 
     * @param sysMembers 会员
     * @return 会员
     */
    @Override
    public List<SysMembers> selectSysMembersList(SysMembers sysMembers)
    {
        return sysMembersMapper.selectSysMembersList(sysMembers);
    }

    /**
     * 新增会员
     * 
     * @param sysMembers 会员
     * @return 结果
     */
    @Override
    public int insertSysMembers(SysMembers sysMembers)
    {
        sysMembers.setCreateTime(DateUtils.getNowDate());
        return sysMembersMapper.insertSysMembers(sysMembers);
    }

    /**
     * 修改会员
     * 
     * @param sysMembers 会员
     * @return 结果
     */
    @Override
    public int updateSysMembers(SysMembers sysMembers)
    {
        sysMembers.setUpdateTime(DateUtils.getNowDate());
        return sysMembersMapper.updateSysMembers(sysMembers);
    }

    /**
     * 批量删除会员
     * 
     * @param membersIds 需要删除的会员主键
     * @return 结果
     */
    @Override
    public int deleteSysMembersByMembersIds(Long[] membersIds)
    {
        return sysMembersMapper.deleteSysMembersByMembersIds(membersIds);
    }

    /**
     * 删除会员信息
     * 
     * @param membersId 会员主键
     * @return 结果
     */
    @Override
    public int deleteSysMembersByMembersId(Long membersId)
    {
        return sysMembersMapper.deleteSysMembersByMembersId(membersId);
    }

    /**
     * 新增教职工
     * @param sysMembers
     * @return
     */
    @Override
    public MembersStatus insertSysTeaching(SysMembers sysMembers) {

        // 工号不能重复（会员表  工号字段）
        List<SysMembers> list = list(new LambdaQueryWrapper<>(SysMembers.class).eq(SysMembers::getMembersNum, sysMembers.getMembersNum()));
        if(list.size() > 0){
            return MembersStatus.NUM_EXIST;
        }

        // 账号不能重复（用户表  账号）账号 == 工号
        List<SysUser> list1 = sysUserService.list(new LambdaQueryWrapper<>(SysUser.class).eq(SysUser::getUserName, sysMembers.getMembersNum()));
        if(list1.size() > 0){
            return MembersStatus.USERNAME_EXIST;
        }

        // 角色必须要存在（角色表）
       /* SysRole sysRole = new SysRole();
        sysRole.setRoleName(MembersConstants.TEACHER_ROLE);
        List<SysRole> sysRoles = sysRoleService.selectRoleList(sysRole);
        if(sysRoles.size() == 0){
            return MembersStatus.ROLE_NOT_EXIST;
        }*/
        List<SysRole> list2 = sysRoleService.list(new LambdaQueryWrapper<>(SysRole.class).eq(SysRole::getRoleName, MembersConstants.TEACHER_ROLE));
        if(list2.size() == 0){
            return MembersStatus.ROLE_NOT_EXIST;
        }

        // 用户，获取刚刚添加的记录的id
        SysUser sysUser = new SysUser();
        sysUser.setUserName(sysMembers.getMembersNum().toString());
        sysUser.setDeptId(MembersConstants.DEFAULT_DEPT_ID);
        sysUser.setNickName(sysMembers.getMembersName());
        sysUser.setEmail(sysMembers.getMembersEmail());
        sysUser.setPhonenumber(sysMembers.getMembersTel());
        sysUser.setSex(sysMembers.getMembersSex());
        sysUser.setAvatar(sysMembers.getMembersImg());
        sysUser.setPassword(SecurityUtils.encryptPassword(MembersConstants.DEFAULT_PASSWORD));
        sysUser.setCreateBy(SecurityUtils.getUsername());
        boolean save = sysUserService.save(sysUser);
        if(!save){
            return MembersStatus.ADD_USER_FAIL;
        }

        // 教职工
        sysMembers.setUserId(sysUser.getUserId());
        sysMembers.setCreateBy(SecurityUtils.getUsername());
        sysMembers.setDeptId(MembersConstants.DEFAULT_DEPT_ID);
        sysMembers.setSocietyId(MembersConstants.DEFAULT_ASSOCIATION_NAME);
        sysMembers.setMembersIsStatus(MembersConstants.NOT_MEMBERS);
        boolean save1 = save(sysMembers);
        if(!save1){
            return MembersStatus.ADD_TEACHER_FAIL;
        }


        // 用户角色分配
        sysUserService.insertUserAuth(sysUser.getUserId(),new Long[]{list2.get(0).getRoleId()});


        return MembersStatus.SUCCESS;
    }


    /**
     * 删除教职工
     */
    @Override
    public MembersStatus deleteSysTeaching(Long[] membersIds) {

        List<SysMembers> list = list(new LambdaQueryWrapper<>(SysMembers.class).in(SysMembers::getMembersId, membersIds));
        if(list.size() == 0){
            return MembersStatus.TEACHER_NOT_EXIST;
        }

        SysRole sysRole = new SysRole();
        sysRole.setRoleName(MembersConstants.TEACHER_ROLE);
        List<SysRole> sysRoles = sysRoleService.selectRoleList(sysRole);
        if(sysRoles.size() == 0){
            return MembersStatus.ROLE_NOT_EXIST;
        }


        // 删除教职工
        boolean b = removeByIds(Arrays.asList(membersIds));
        if(!b){
            return MembersStatus.DELETE_FAIL;
        }

        // 删除用户
        List<Long> userIds = list.stream().map(SysMembers::getUserId).collect(Collectors.toList());
        boolean b1 = sysUserService.removeByIds(userIds);
        if(!b1){
            return MembersStatus.USER_NOT_EXIST;
        }

        // 删除用户角色
        sysRoleService.deleteAuthUsers(sysRoles.get(0).getRoleId(),userIds.toArray(new Long[]{}));


        return MembersStatus.SUCCESS;
    }
}
