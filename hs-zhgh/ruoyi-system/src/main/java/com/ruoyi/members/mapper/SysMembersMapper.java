package com.ruoyi.members.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.members.domain.SysMembers;

/**
 * 会员Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
public interface SysMembersMapper extends BaseMapper<SysMembers>
{
    /**
     * 查询会员
     * 
     * @param membersId 会员主键
     * @return 会员
     */
    public SysMembers selectSysMembersByMembersId(Long membersId);

    /**
     * 查询会员列表
     * 
     * @param sysMembers 会员
     * @return 会员集合
     */
    public List<SysMembers> selectSysMembersList(SysMembers sysMembers);

    /**
     * 新增会员
     * 
     * @param sysMembers 会员
     * @return 结果
     */
    public int insertSysMembers(SysMembers sysMembers);

    /**
     * 修改会员
     * 
     * @param sysMembers 会员
     * @return 结果
     */
    public int updateSysMembers(SysMembers sysMembers);

    /**
     * 删除会员
     * 
     * @param membersId 会员主键
     * @return 结果
     */
    public int deleteSysMembersByMembersId(Long membersId);

    /**
     * 批量删除会员
     * 
     * @param membersIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMembersByMembersIds(Long[] membersIds);
}
