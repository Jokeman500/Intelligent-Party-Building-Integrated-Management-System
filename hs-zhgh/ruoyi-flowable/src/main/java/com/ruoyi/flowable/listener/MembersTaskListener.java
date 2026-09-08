package com.ruoyi.flowable.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.MembersConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.enums.MembersStatus;
import com.ruoyi.flowable.config.SpringContextHolder;
import com.ruoyi.members.domain.SysMembers;
import com.ruoyi.members.service.ISysMembersService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cy
 */
@Component
public class MembersTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        SysMembers sysMembers = (SysMembers) delegateTask.getVariable("commonClass");
        sysMembers.setMembersIsStatus(MembersConstants.IS_MEMBERS);

        // 修改
       ISysMembersService sysMembersService =  SpringContextHolder.getBean("sysMembersServiceImpl", ISysMembersService.class);
       sysMembersService.updateById(sysMembers);

       // 操作角色   分配会员角色 删除教职工角色
        ISysRoleService sysRoleService =  SpringContextHolder.getBean("sysRoleServiceImpl", ISysRoleService.class);
        ISysUserService sysUserService =  SpringContextHolder.getBean("sysUserServiceImpl", ISysUserService.class);
        List<SysRole> list2 = sysRoleService.list(new LambdaQueryWrapper<>(SysRole.class).eq(SysRole::getRoleName, MembersConstants.MEMBERS_ROLE));
        if(list2.size() == 0){
            return;
        }

        sysUserService.insertUserAuth(sysMembers.getUserId(),new Long[]{list2.get(0).getRoleId()});


    }
}
