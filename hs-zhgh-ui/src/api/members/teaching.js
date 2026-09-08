/*
 * @Author: 程云 lpw210@163.com
 * @Date: 2025-09-16 15:12:15
 * @LastEditors: 程云 lpw210@163.com
 * @LastEditTime: 2025-09-22 14:59:02
 * @FilePath: \hs-zhgh-ui\src\api\members\teaching.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from "@/utils/request";

// 查询会员列表
export function listMembers(query) {
  return request({
    url: "/system/members/teaching/list",
    method: "get",
    params: query,
  });
}

// 查询会员详细
export function getMembers(membersId) {
  return request({
    url: "/system/members/teaching/" + membersId,
    method: "get",
  });
}

// 新增会员
export function addMembers(data) {
  return request({
    url: "/system/members/teaching",
    method: "post",
    data: data,
  });
}

// 修改会员
export function updateMembers(data) {
  return request({
    url: "/system/members/teaching",
    method: "put",
    data: data,
  });
}

// 删除会员
export function delMembers(membersId) {
  return request({
    url: "/system/members/teaching/" + membersId,
    method: "delete",
  });
}

// 根据工号查询
export function getMembersNum() {
  return request({
    url: "/system/members/teaching/membersNum",
    method: "get",
  });
}

// 会员申请
export function membersFlowable(data) {
  return request({
    url: "/system/members/teaching/membersFlowable",
    method: "post",
    // headers: { "Content-Type": "multipart/form-data" },
    data: data,
  });
}
