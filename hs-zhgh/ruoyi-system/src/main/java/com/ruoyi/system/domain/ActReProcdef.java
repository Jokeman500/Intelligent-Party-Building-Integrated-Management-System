package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("act_re_procdef")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActReProcdef implements Serializable {

    @TableId(value = "id_",type = IdType.AUTO)
    private String id;
    @TableField("rev_")
    private Integer rev;
    @TableField("name_")
    private String name;
    @TableField("key_")
    private String key;
    @TableField("version_")
    private Integer version;
    @TableField("deployment_id_")
    private String deploymentId;
    @TableField("resource_name_")
    private String resourceName;
    @TableField("dgrm_resource_name_")
    private String dgrmResourceName;
    @TableField("description_")
    private String description;
    @TableField("has_start_form_key_")
    private Integer hasStartFormKey;
    @TableField("has_graphical_notation_")
    private Integer hasGraphicalNotation;
    @TableField("suspension_state_")
    private Integer suspensionState;
    @TableField("tenant_id_")
    private String tenantId;
    @TableField("engine_version_")
    private String engineVersion;
    @TableField("derived_from_")
    private String derivedFrom;
    @TableField("derived_from_root_")
    private String derivedFromRoot;
    @TableField("derived_version_")
    private Integer derivedVersion;



}
