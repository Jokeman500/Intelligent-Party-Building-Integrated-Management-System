package com.ruoyi.web.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教职工Controller
 *
 * @author cy
 */
@RestController
@RequestMapping("/members")
public class MembersController {

    /**
     * 人才智库 - 教职工管理
     * 这是查询教职工所有的信息，不需要传递参数
     * @return
     */
    @GetMapping
    public String getMembers(){
        return "com/ruoyi/members";
    }


    /**
     * 人才智库 - 教职工管理 - 新增
     * @return
     */
    @PostMapping
    public String postMembers(){
        return "com/ruoyi/members";
    }

}
