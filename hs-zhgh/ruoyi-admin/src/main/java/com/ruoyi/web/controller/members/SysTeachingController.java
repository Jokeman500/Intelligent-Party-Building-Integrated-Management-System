package com.ruoyi.web.controller.members;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.constant.CommonConstants;
import com.ruoyi.common.constant.MembersConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.MembersStatus;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.ProcessConstants;
import com.ruoyi.flowable.common.enums.FlowComment;
import com.ruoyi.flowable.service.ISysFormService;
import com.ruoyi.system.domain.ActReProcdef;
import com.ruoyi.system.domain.SysForm;
import com.ruoyi.system.service.IActReProcdefService;
import com.ruoyi.system.service.ISysUserService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.members.domain.SysMembers;
import com.ruoyi.members.service.ISysMembersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 教职工Controller
 *
 * @author ruoyi
 * @date 2025-09-16
 */
@RestController
@RequestMapping("/system/members/teaching")
public class SysTeachingController extends BaseController {
    @Autowired
    private ISysMembersService sysMembersService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IActReProcdefService actReProcdefService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ISysFormService sysFormService;


    /**
     * 人才智库 - 教职工管理
     */
//    @PreAuthorize("@ss.hasPermi('system:members:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMembers sysMembers) {

        LambdaQueryWrapper<SysMembers> sysMembersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersIsStatus, MembersConstants.NOT_MEMBERS);

        if (StringUtils.isNotNull(sysMembers.getMembersName())) {
            sysMembersLambdaQueryWrapper.like(SysMembers::getMembersName, sysMembers.getMembersName());
        }

        if (StringUtils.isNotNull(sysMembers.getMembersNum())) {
            sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersNum, sysMembers.getMembersNum());
        }

        if (StringUtils.isNotNull(sysMembers.getMembersTel())) {
            sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersTel, sysMembers.getMembersTel());
        }

        if (StringUtils.isNotNull(sysMembers.getSchool())) {
            sysMembersLambdaQueryWrapper.like(SysMembers::getSchool, sysMembers.getSchool());
        }


        Page<SysMembers> sysMembersPage = new Page<>(sysMembers.getPageNum(), sysMembers.getPageSize());
        Page<SysMembers> page = sysMembersService.page(sysMembersPage, sysMembersLambdaQueryWrapper);
        return new TableDataInfo(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }

    /**
     * 人才智库 - 教职工管理 - 导出
     */
    @PreAuthorize("@ss.hasPermi('members:teaching:export')")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMembers sysMembers) {

        LambdaQueryWrapper<SysMembers> sysMembersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersIsStatus, MembersConstants.NOT_MEMBERS);

        if (StringUtils.isNotNull(sysMembers.getMembersName())) {
            sysMembersLambdaQueryWrapper.like(SysMembers::getMembersName, sysMembers.getMembersName());
        }

        if (StringUtils.isNotNull(sysMembers.getMembersNum())) {
            sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersNum, sysMembers.getMembersNum());
        }

        if (StringUtils.isNotNull(sysMembers.getMembersTel())) {
            sysMembersLambdaQueryWrapper.eq(SysMembers::getMembersTel, sysMembers.getMembersTel());
        }

        if (StringUtils.isNotNull(sysMembers.getSchool())) {
            sysMembersLambdaQueryWrapper.like(SysMembers::getSchool, sysMembers.getSchool());
        }

        List<SysMembers> list = sysMembersService.list(sysMembersLambdaQueryWrapper);
        ExcelUtil<SysMembers> util = new ExcelUtil<SysMembers>(SysMembers.class);
        util.exportExcel(response, list, "教职工数据","教职工信息统计表");
    }

    /**
     * 人才智库 - 教职工管理 - 详情（会员ID）
     */
//    @PreAuthorize("@ss.hasPermi('members:teaching:query')")
    @GetMapping(value = "/{membersId}")
    public AjaxResult getInfo(@PathVariable("membersId") Long membersId) {
        return success(sysMembersService.getById(membersId));
    }

    /**
     * 人才智库 - 教职工管理 - 新增按钮
     */
    @PreAuthorize("@ss.hasPermi('members:teaching:add')")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMembers sysMembers) {

        MembersStatus membersStatus = sysMembersService.insertSysTeaching(sysMembers);
//        return "200".equals(membersStatus.getCode());
        return CommonConstants.SUCCESS_CODE.equals(membersStatus.getCode())?success(membersStatus.getInfo()):error(membersStatus.getInfo());
    }


    /**
     * 人才智库 - 教职工管理 - 修改
     */
    @PreAuthorize("@ss.hasPermi('members:teaching:edit')")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMembers sysMembers) {

        SysMembers byId = sysMembersService.getById(sysMembers.getMembersId());

        if(byId == null){
            return error(MembersStatus.TEACHER_NOT_EXIST.getInfo());
        }

        boolean flag = false;
        if(!byId.getMembersName().equals(sysMembers.getMembersName())){
            flag = true;
        }

        if(!byId.getMembersNum().equals(sysMembers.getMembersNum())){
            flag = true;
        }

        if(flag){
            /*SysUser sysUser = new SysUser();
            sysUser.setUserId(byId.getUserId());
            sysUser.setUserName(sysMembers.getMembersNum().toString());
            sysUser.setNickName(sysMembers.getMembersName());
            sysUser.setUpdateBy(SecurityUtils.getUsername());*/

            SysUser user = sysUserService.getById(sysMembers.getUserId());
            if(user == null){
                return error(MembersStatus.USER_NOT_EXIST.getInfo());
            }

            user.setUserName(sysMembers.getMembersNum().toString());
            user.setNickName(sysMembers.getMembersName());
            user.setUpdateBy(SecurityUtils.getUsername());
            sysUserService.updateById(user);
        }

        return toAjax(sysMembersService.updateById(sysMembers));


    }

    /**
     * 删除会员
     */
    @PreAuthorize("@ss.hasPermi('members:teaching:remove')")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{membersIds}")
    public AjaxResult remove(@PathVariable Long[] membersIds) {
        MembersStatus membersStatus = sysMembersService.deleteSysTeaching(membersIds);
        return CommonConstants.SUCCESS_CODE.equals(membersStatus.getCode())?success(membersStatus.getInfo()):error(membersStatus.getInfo());

    }


    /**
     * 人才智库 - 教职工管理 - 详细（会员工号）
     */
    @GetMapping("/membersNum")
    public AjaxResult getMembersNum() {

        if(MembersConstants.SUPER_ADMIN.equals(SecurityUtils.getUsername())){
            return success(new SysMembers());
        }

        List<SysMembers> list = sysMembersService.list(new LambdaQueryWrapper<>(SysMembers.class).eq(SysMembers::getUserId, SecurityUtils.getUserId()));
        if(list.size() == 0){
            return error(MembersStatus.TEACHER_NOT_EXIST.getInfo());
        }

        return success(list.get(0));

    }


    /**
     *人才智库 - 申请入会
     */
    @PostMapping("/membersFlowable")
    public AjaxResult apply(@RequestBody SysMembers sysMember) {


        // 发起审批
        // 进行审批操作
        try {
            sysMember.setCreateBy(getUsername());
            // 查询入会流程定义
            LambdaQueryWrapper<ActReProcdef> actReProcdefLambdaQueryWrapper = new LambdaQueryWrapper<>();
            actReProcdefLambdaQueryWrapper.eq(ActReProcdef::getName, "申请入会审批流程");
            // version降序
            actReProcdefLambdaQueryWrapper.orderByDesc(ActReProcdef::getVersion);
            List<ActReProcdef> list = actReProcdefService.list(actReProcdefLambdaQueryWrapper);

            // 判断list是否为空或长度是否为0
            if (list != null && !list.isEmpty()) {
                // 判断流程是否已经开启
                if (list.get(0).getSuspensionState() != 1) {
                }

                // 启动流程实例
                HashMap<String, Object> variables = new HashMap<>();
                variables.put("commonClass", sysMember);

                // 绑定表单
                SysForm sysForm = new SysForm();
                sysForm.setFormName("申请入会表单");
                List<SysForm> sysForms = sysFormService.selectSysFormList(sysForm);
                if (sysForms.size() > 0) {
                    // 集合根据id降序
                    sysForms.sort(Comparator.comparing(SysForm::getFormId).reversed());
                    variables.put("formJson", sysForms.get(0).getFormContent());
                }

                SysUser user = SecurityUtils.getLoginUser().getUser();
                // 设置流程发起人
                identityService.setAuthenticatedUserId(user.getUserId().toString());
                variables.put(ProcessConstants.PROCESS_INITIATOR, user.getUserId());
                // 流程发起，跳过发起人节点
                ProcessInstance processInstance = runtimeService.startProcessInstanceById(list.get(0).getId(), variables);
                // 给第一步申请人节点设置任务执行人和意见
                Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
                if (Objects.nonNull(task)) {
                    taskService.addComment(task.getId(), processInstance.getProcessInstanceId(), FlowComment.NORMAL.getType(), user.getNickName() + "发起入会流程申请");
                    taskService.complete(task.getId(), variables);
                }

            } else {

                add(sysMember);
            }
            return success("入会申请提交成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
