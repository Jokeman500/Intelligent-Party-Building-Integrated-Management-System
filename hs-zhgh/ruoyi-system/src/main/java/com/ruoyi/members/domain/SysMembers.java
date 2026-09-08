package com.ruoyi.members.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.enums.DesensitizedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员对象 sys_members
 * 
 * @author ruoyi
 * @date 2025-09-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_members")
public class SysMembers extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 会员主键id */
    @TableId(value = "members_id",type = IdType.AUTO)
    private Long membersId;

    /** 用户id */
//    @Excel(name = "用户id")
    private Long userId;

    /** 会员姓名 */
    @Excel(name = "会员姓名")
    private String membersName;

    /** 会员工号 */
    @Excel(name = "会员工号")
    private Long membersNum;

    /** 会员性别 0男 1女 2未知 */
    @Excel(name = "会员性别",readConverterExp = "0=男,1=女,2=未知")
    private String membersSex;

    /** 会员出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会员出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date membersBir;

    /** 会员学历 */
    @Excel(name = "会员学历")
    private String membersBackground;

    /** 会员民族 */
    @Excel(name = "会员民族")
    private String membersNation;

    /** 会员政治面貌 */
    @Excel(name = "会员政治面貌")
    private String membersPolitical;

    /** 会员来校时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会员来校时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date membersSchoolDate;

    /** 会员联系电话 */
    @Excel(name = "会员联系电话")
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String membersTel;

    /** 会员所在单位id（基层工会下级） */
//    @Excel(name = "会员所在单位id", readConverterExp = "基=层工会下级")
    private Long deptId;

    /** 会员所在协会id */
//    @Excel(name = "会员所在协会id")
    private Long societyId;

    /** 婚姻状况 0未婚 1已婚 2离异(无小孩) 3离异(带小孩) 4离异(不带小孩) 5丧偶(无小孩) 6丧偶(有小孩) */
    @Excel(name = "婚姻状况",readConverterExp = "0=未婚,1=已婚,2=离异(无小孩),3=离异(带小孩),4=离异(不带小孩),5=丧偶(无小孩),6=丧偶(有小孩)")
    private String membersMaritalStatus;

    /** 配偶是否为校内职工 0否 1是 */
    @Excel(name = "配偶是否为校内职工",readConverterExp = "0=否,1=是")
    private String membersSpouseCampusStaff;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String membersNativePlace;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String membersAddress;

    /** 所在校区 */
    @Excel(name = "所在校区")
    private String school;

    /** 用人方式 0在编人员 1校聘人员  2自聘人员  3其他人员  4博士后 */
    @Excel(name = "用人方式",readConverterExp = "0=在编人员,1=校聘人员,2=自聘人员,3=其他人员,4=博士后")
    private String membersModeEmployment;

    /** 个人工作简历 */
    @Excel(name = "个人工作简历")
    private String membersResume;

    /** 家庭主要成员/单位 */
    @Excel(name = "家庭主要成员/单位")
    private String membersMainFamily;

    /** 特长/爱好 */
    @Excel(name = "特长/爱好")
    private String membersHobby;

    /** 会员备注 */
    @Excel(name = "会员备注")
    private String membersRemarks;

    /** 个人照片 */
//    @Excel(name = "个人照片")
    private String membersImg;

    /** 手签 */
//    @Excel(name = "手签")
    private String membersSign;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String membersContactNumber;

    /** 人员状态 0在职 1 离职  2退休 */
    @Excel(name = "人员状态",readConverterExp = "0=在职,1=离职,2=退休")
    private String membersAdmissionTime;

    /** 会员状态 0正常 1退会 2转会 */
//    @Excel(name = "会员状态 0正常 1退会 2转会")
    private String membersStatus;

    /** 年龄 */
    @Excel(name = "年龄")
    private String membersAge;

    /** 是否是会员 0是 1不是 */
//    @Excel(name = "是否是会员 0是 1不是")
    private String membersIsStatus;

    /** 国籍 0中国 1外国 */
    @Excel(name = "国籍",readConverterExp = "0=中国,1=外国")
    private String membersNationality;

    /** 身份类别 0身份证  1护照 2士官证 3港澳通行证 4台湾居民来往大陆通行证 */
//    @Excel(name = "身份类别 0身份证  1护照 2士官证 3港澳通行证 4台湾居民来往大陆通行证")
    private String membersIdentityType;

    /** 身份类型对应的身份号码 */
    @Excel(name = "身份类型对应的身份号码")
    private String membersIdentityNum;

    /** 技术等级 0初级 1中级 2高级 */
//    @Excel(name = "技术等级 0初级 1中级 2高级")
    private String membersTechnical;

    /** 专业技术职称 0正高级 1副高级 2中级 3初级 4其他 */
//    @Excel(name = "专业技术职称 0正高级 1副高级 2中级 3初级 4其他")
    private String membersProfessional;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String membersEmail;

    /** 行政职务*/
    @Excel(name = "行政职务")
    private String membersAdministrative;

    /** 配偶姓名 */
    @Excel(name = "配偶姓名")
    private String membersSpouseName;

    /** 户籍类型 0城镇户口  1农业户口 */
//    @Excel(name = "户籍类型 0城镇户口  1农业户口")
    private String membersHouseholdType;

    /** 删除标识 0未删除 2已删除 */
    @TableLogic
    private String delFlag;

    public void setMembersId(Long membersId) 
    {
        this.membersId = membersId;
    }

    public Long getMembersId() 
    {
        return membersId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setMembersName(String membersName) 
    {
        this.membersName = membersName;
    }

    public String getMembersName() 
    {
        return membersName;
    }

    public void setMembersNum(Long membersNum) 
    {
        this.membersNum = membersNum;
    }

    public Long getMembersNum() 
    {
        return membersNum;
    }

    public void setMembersSex(String membersSex) 
    {
        this.membersSex = membersSex;
    }

    public String getMembersSex() 
    {
        return membersSex;
    }

    public void setMembersBir(Date membersBir) 
    {
        this.membersBir = membersBir;
    }

    public Date getMembersBir() 
    {
        return membersBir;
    }

    public void setMembersBackground(String membersBackground) 
    {
        this.membersBackground = membersBackground;
    }

    public String getMembersBackground() 
    {
        return membersBackground;
    }

    public void setMembersNation(String membersNation) 
    {
        this.membersNation = membersNation;
    }

    public String getMembersNation() 
    {
        return membersNation;
    }

    public void setMembersPolitical(String membersPolitical) 
    {
        this.membersPolitical = membersPolitical;
    }

    public String getMembersPolitical() 
    {
        return membersPolitical;
    }

    public void setMembersSchoolDate(Date membersSchoolDate) 
    {
        this.membersSchoolDate = membersSchoolDate;
    }

    public Date getMembersSchoolDate() 
    {
        return membersSchoolDate;
    }

    public void setMembersTel(String membersTel) 
    {
        this.membersTel = membersTel;
    }

    public String getMembersTel() 
    {
        return membersTel;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setSocietyId(Long societyId) 
    {
        this.societyId = societyId;
    }

    public Long getSocietyId() 
    {
        return societyId;
    }

    public void setMembersMaritalStatus(String membersMaritalStatus) 
    {
        this.membersMaritalStatus = membersMaritalStatus;
    }

    public String getMembersMaritalStatus() 
    {
        return membersMaritalStatus;
    }

    public void setMembersSpouseCampusStaff(String membersSpouseCampusStaff) 
    {
        this.membersSpouseCampusStaff = membersSpouseCampusStaff;
    }

    public String getMembersSpouseCampusStaff() 
    {
        return membersSpouseCampusStaff;
    }

    public void setMembersNativePlace(String membersNativePlace) 
    {
        this.membersNativePlace = membersNativePlace;
    }

    public String getMembersNativePlace() 
    {
        return membersNativePlace;
    }

    public void setMembersAddress(String membersAddress) 
    {
        this.membersAddress = membersAddress;
    }

    public String getMembersAddress() 
    {
        return membersAddress;
    }

    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }

    public void setMembersModeEmployment(String membersModeEmployment) 
    {
        this.membersModeEmployment = membersModeEmployment;
    }

    public String getMembersModeEmployment() 
    {
        return membersModeEmployment;
    }

    public void setMembersResume(String membersResume) 
    {
        this.membersResume = membersResume;
    }

    public String getMembersResume() 
    {
        return membersResume;
    }

    public void setMembersMainFamily(String membersMainFamily) 
    {
        this.membersMainFamily = membersMainFamily;
    }

    public String getMembersMainFamily() 
    {
        return membersMainFamily;
    }

    public void setMembersHobby(String membersHobby) 
    {
        this.membersHobby = membersHobby;
    }

    public String getMembersHobby() 
    {
        return membersHobby;
    }

    public void setMembersRemarks(String membersRemarks) 
    {
        this.membersRemarks = membersRemarks;
    }

    public String getMembersRemarks() 
    {
        return membersRemarks;
    }

    public void setMembersImg(String membersImg) 
    {
        this.membersImg = membersImg;
    }

    public String getMembersImg() 
    {
        return membersImg;
    }

    public void setMembersSign(String membersSign) 
    {
        this.membersSign = membersSign;
    }

    public String getMembersSign() 
    {
        return membersSign;
    }

    public void setMembersContactNumber(String membersContactNumber) 
    {
        this.membersContactNumber = membersContactNumber;
    }

    public String getMembersContactNumber() 
    {
        return membersContactNumber;
    }

    public void setMembersAdmissionTime(String membersAdmissionTime) 
    {
        this.membersAdmissionTime = membersAdmissionTime;
    }

    public String getMembersAdmissionTime() 
    {
        return membersAdmissionTime;
    }

    public void setMembersStatus(String membersStatus) 
    {
        this.membersStatus = membersStatus;
    }

    public String getMembersStatus() 
    {
        return membersStatus;
    }

    public void setMembersAge(String membersAge) 
    {
        this.membersAge = membersAge;
    }

    public String getMembersAge() 
    {
        return membersAge;
    }

    public void setMembersIsStatus(String membersIsStatus) 
    {
        this.membersIsStatus = membersIsStatus;
    }

    public String getMembersIsStatus() 
    {
        return membersIsStatus;
    }

    public void setMembersNationality(String membersNationality) 
    {
        this.membersNationality = membersNationality;
    }

    public String getMembersNationality() 
    {
        return membersNationality;
    }

    public void setMembersIdentityType(String membersIdentityType) 
    {
        this.membersIdentityType = membersIdentityType;
    }

    public String getMembersIdentityType() 
    {
        return membersIdentityType;
    }

    public void setMembersIdentityNum(String membersIdentityNum) 
    {
        this.membersIdentityNum = membersIdentityNum;
    }

    public String getMembersIdentityNum() 
    {
        return membersIdentityNum;
    }

    public void setMembersTechnical(String membersTechnical) 
    {
        this.membersTechnical = membersTechnical;
    }

    public String getMembersTechnical() 
    {
        return membersTechnical;
    }

    public void setMembersProfessional(String membersProfessional) 
    {
        this.membersProfessional = membersProfessional;
    }

    public String getMembersProfessional() 
    {
        return membersProfessional;
    }

    public void setMembersEmail(String membersEmail) 
    {
        this.membersEmail = membersEmail;
    }

    public String getMembersEmail() 
    {
        return membersEmail;
    }

    public void setMembersAdministrative(String membersAdministrative) 
    {
        this.membersAdministrative = membersAdministrative;
    }

    public String getMembersAdministrative() 
    {
        return membersAdministrative;
    }

    public void setMembersSpouseName(String membersSpouseName) 
    {
        this.membersSpouseName = membersSpouseName;
    }

    public String getMembersSpouseName() 
    {
        return membersSpouseName;
    }

    public void setMembersHouseholdType(String membersHouseholdType) 
    {
        this.membersHouseholdType = membersHouseholdType;
    }

    public String getMembersHouseholdType() 
    {
        return membersHouseholdType;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("membersId", getMembersId())
            .append("userId", getUserId())
            .append("membersName", getMembersName())
            .append("membersNum", getMembersNum())
            .append("membersSex", getMembersSex())
            .append("membersBir", getMembersBir())
            .append("membersBackground", getMembersBackground())
            .append("membersNation", getMembersNation())
            .append("membersPolitical", getMembersPolitical())
            .append("membersSchoolDate", getMembersSchoolDate())
            .append("membersTel", getMembersTel())
            .append("deptId", getDeptId())
            .append("societyId", getSocietyId())
            .append("membersMaritalStatus", getMembersMaritalStatus())
            .append("membersSpouseCampusStaff", getMembersSpouseCampusStaff())
            .append("membersNativePlace", getMembersNativePlace())
            .append("membersAddress", getMembersAddress())
            .append("school", getSchool())
            .append("membersModeEmployment", getMembersModeEmployment())
            .append("membersResume", getMembersResume())
            .append("membersMainFamily", getMembersMainFamily())
            .append("membersHobby", getMembersHobby())
            .append("membersRemarks", getMembersRemarks())
            .append("membersImg", getMembersImg())
            .append("membersSign", getMembersSign())
            .append("membersContactNumber", getMembersContactNumber())
            .append("membersAdmissionTime", getMembersAdmissionTime())
            .append("membersStatus", getMembersStatus())
            .append("membersAge", getMembersAge())
            .append("membersIsStatus", getMembersIsStatus())
            .append("membersNationality", getMembersNationality())
            .append("membersIdentityType", getMembersIdentityType())
            .append("membersIdentityNum", getMembersIdentityNum())
            .append("membersTechnical", getMembersTechnical())
            .append("createTime", getCreateTime())
            .append("membersProfessional", getMembersProfessional())
            .append("membersEmail", getMembersEmail())
            .append("membersAdministrative", getMembersAdministrative())
            .append("membersSpouseName", getMembersSpouseName())
            .append("membersHouseholdType", getMembersHouseholdType())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
