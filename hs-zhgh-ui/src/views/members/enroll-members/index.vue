<template>
  <div>
    <transition name="fade" mode="out-in">
      <div class="a4-form-container" v-if="agreed && !complete">
        <div class="a4-form">
          <div class="form-header">
            <h1>河南工业大学基层工会会员申请表</h1>
          </div>

          <!-- 表单表格 -->
          <div class="form-table">
            <div class="form-table-top">
              <div class="form-left-section">
                <!-- 第一行 -->
                <div class="form-row">
                  <div class="form-label">
                    <span class="required">*</span>姓名
                  </div>
                  <div class="form-input-left">
                    <el-input
                      v-model="form.membersName"
                      placeholder="请输入姓名"
                      size="small"
                      borderless
                      disabled
                    ></el-input>
                  </div>
                  <div class="form-label">
                    <span class="required">*</span>性别
                  </div>
                  <div class="form-input-right gender-input">
                    <el-radio-group v-model="form.membersSex" size="small" disabled>
                      <el-radio label="0">男</el-radio>
                      <el-radio label="1">女</el-radio>
                    </el-radio-group>
                  </div>
                </div>

                <!-- 第二行 -->
                <div class="form-row">
                  <div class="form-label">
                    <span class="required">*</span>工号
                  </div>
                  <div class="form-input-left">
                    <el-input
                      v-model="form.membersNum"
                      placeholder="请输入工号"
                      size="small"
                      borderless
                      disabled
                    ></el-input>
                  </div>
                  <div class="form-label">出生日期</div>
                  <div class="form-input-right">
                    <el-date-picker
                      v-model="form.membersBir"
                      type="date"
                      placeholder="选择日期"
                      value-format="yyyy-MM-dd"
                      size="small"
                      style="width: 100%"
                    ></el-date-picker>
                  </div>
                </div>

                <!-- 第三行 -->
                <div class="form-row">
                  <div class="form-label">学历</div>
                  <div class="form-input-left">
                    <el-input v-model="form.membersBackground" placeholder="请输入学历" size="small" borderless></el-input>
                   
                  </div>
                  <div class="form-label">
                    <span class="required">*</span>民族
                  </div>
                  <div class="form-input-right">
                    <el-input
                    disabled
                      v-model="form.membersNation"
                      placeholder="请输入民族"
                      size="small"
                      borderless
                    ></el-input>
                  </div>
                </div>

                <!-- 第四行 -->
                <div class="form-row">
                  <div class="form-label">政治面貌</div>
                  <div class="form-input-left">
                    <!-- <el-input
                      v-model="form.membersPolitical"
                      placeholder="请输入政治面貌"
                      size="small"
                      borderless
                    ></el-input> -->
                    <el-select
                      v-model="form.membersBackground"
                      placeholder="请选择政治面貌"
                      size="small"
                      clearable
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in politics"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      />
                    </el-select>
                  </div>
                  <div class="form-label">
                    <span class="required">*</span>入校日期
                  </div>
                  <div class="form-input-right">
                    <el-date-picker
                    disabled
                      v-model="form.membersSchoolDate"
                      type="date"
                      placeholder="选择日期"
                      value-format="yyyy-MM-dd"
                      size="small"
                      style="width: 100%"
                    ></el-date-picker>
                  </div>
                </div>
              </div>

              <!-- 照片区域 - 宽度加大 -->
              <div class="photo-cell">
                <div class="photo-label">照片</div>
                <div class="photo-upload-wrapper">
                  <el-upload
                    class="photo-upload"
                    action="#"
                    :auto-upload="false"
                    :show-file-list="false"
                    :before-upload="beforeAvatarUpload"
                    :on-change="handleFileChange"
                  >
                    <img v-if="previewUrl" :src="previewUrl" class="avatar" />
                    <div v-else class="upload-placeholder">
                      <i class="el-icon-camera"></i>
                      <div>点击上传照片</div>
                    </div>
                  </el-upload>
                </div>
              </div>
            </div>

            <!-- 表格下半部分 -->
            <div class="form-table-bottom">
              <!-- 第五行 -->
              <div class="form-row">
                <div class="form-label">
                  <span class="required">*</span>联系电话
                </div>
                <div class="form-input-left">
                  <el-input
                  disabled
                    v-model="form.membersTel"
                    placeholder="请输入电话"
                    size="small"
                    borderless
                  ></el-input>
                </div>
                <div class="form-label">
                  <span class="required">*</span>婚姻状况
                </div>
                <div class="form-input">
                  <el-select
                  disabled
                    v-model="form.membersMaritalStatus"
                    placeholder="请选择"
                    size="small"
                    style="width: 100%"
                  >
                    <el-option
                      v-for="dict in dict.type.sys_marital"
                      :key="dict.value"
                      :label="dict.label"
                      :value="parseInt(dict.value)"
                    ></el-option>
                  </el-select>
                </div>
                
              </div>

              <!-- 第六行 -->
              <div class="form-row">
                <!-- <div class="form-label">所在单位</div>
                <div class="form-input-left"> -->
                  <!-- <el-select v-model="form.union" placeholder="" clearable>
                    <el-option v-for="item in unionOptions" :key="item.value" :label="item.label"
                      :value="item.value"></el-option>
                  </el-select> -->
                  <!-- <CheckUnit
                    ref="CheckUnit"
                    :union="form.union"
                    :unit="form.unit"
                    :laborUnionType="1"
                    v-model="form.unit"
                    style="width: 100%"
                  ></CheckUnit> -->

                  <!-- <el-input v-model="form.deptId" placeholder="请输入单位" size="small" borderless></el-input> -->
                <!-- </div> -->
                <div class="form-label">
                  <span class="required">*</span>所在基层工会
                </div>
                <div class="form-input">
                  <!-- <CheckGrassrootsUnions
                    ref="CheckGrassrootsUnions"
                    :union="form.union"
                    v-model="form.union"
                    style="width: 100%"
                    @input="unionChange"
                  ></CheckGrassrootsUnions>  -->
                 </div>
              </div>
              <!-- 第七行 -->
              <div class="form-row">
                <div class="form-label">配偶是否为校内职工</div>
                <div class="form-input-left">
                  <el-radio-group
                    v-model="form.membersSpouseCampusStaff"
                    size="small"
                  >
                    <el-radio label="1">是</el-radio>
                    <el-radio label="0">否</el-radio>
                  </el-radio-group>
                </div>
                <div class="form-label">家庭住址</div>
                <div class="form-input">
                  <el-input
                    v-model="form.membersAddress"
                    placeholder="请输入地址"
                    size="small"
                    borderless
                  ></el-input>
                </div>
              </div>

              <!-- 第八行 -->
              <div class="form-row">
                <div class="form-label">
                  <span class="required">*</span>所在校区
                </div>
                <div class="form-input-left">
                  <el-input
                  disabled
                    v-model="form.schoolId"
                    placeholder="请输入所在校区"
                    size="small"
                    borderless
                  ></el-input>
                  
                </div>
                <div class="form-label">
                  <span class="required">*</span>用人方式
                </div>
                <div class="form-input">
                  <el-select
                  disabled
            v-model="form.membersModeEmployment"
            placeholder="请选择教职工用人方式"
            style="width: 100%"
          >
            <el-option
              v-for="dict in dict.type.sys_mode_employment"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
                </div>
              </div>

              <!-- 个人工作简历 -->
              <div class="form-row full-row">
                <div class="form-label">个人工作简历</div>
                <div class="form-input full-input">
                  <el-input
                    type="textarea"
                    :rows="3"
                    placeholder="请填写个人工作简历"
                    v-model="form.membersResume"
                    resize="none"
                  ></el-input>
                </div>
              </div>

              <!-- 家庭主要成员/单位 -->
              <div class="form-row full-row">
                <div class="form-label">家庭主要成员/单位</div>
                <div class="form-input full-input">
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请填写家庭成员及单位"
                    v-model="form.membersMainFamily"
                    resize="none"
                  ></el-input>
                </div>
              </div>

              <!-- 特长/爱好 -->
              <div class="form-row full-row">
                <div class="form-label">特长/爱好</div>
                <div class="form-input full-input">
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请填写特长/爱好"
                    v-model="form.membersHobby"
                    resize="none"
                  ></el-input>
                </div>
              </div>

              <!-- 备注 -->
              <div class="form-row full-row">
                <div class="form-label">备注</div>
                <div class="form-input full-input">
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请填写备注信息"
                    v-model="form.membersRemarks"
                    resize="none"
                  ></el-input>
                </div>
              </div>
            </div>
          </div>

          <!-- 提交按钮 -->
          <div class="form-footer">
            <el-button type="primary" size="medium" @click="submitForm"
              >提交申请</el-button
            >
            <el-button size="medium" @click="resetForm">重置</el-button>
          </div>
        </div>
      </div>
    </transition>

    <div class="union-application-grand" v-if="!agreed">
      <!-- 红色背景装饰条 -->
      <div class="red-banner"></div>

      <transition name="fade" mode="out-in">
        <!-- 申请表单区域 -->
        <div class="grand-application-card">
          <!-- 金色装饰边框 -->
          <div class="gold-border top"></div>
          <div class="gold-border right"></div>
          <div class="gold-border bottom"></div>
          <div class="gold-border left"></div>

          <!-- 工会徽章 -->
          <div class="union-badge">
            <div class="badge-inner">
              <svg viewBox="0 0 100 100" class="badge-icon">
                <circle cx="50" cy="50" r="45" fill="#c10b0b" />
                <path
                  d="M50,20 L60,40 L80,45 L65,60 L70,80 L50,70 L30,80 L35,60 L20,45 L40,40 Z"
                  fill="#FFD700"
                />
                <text
                  x="50"
                  y="55"
                  text-anchor="middle"
                  fill="#fff"
                  font-size="16"
                  font-weight="bold"
                >
                  工
                </text>
              </svg>
            </div>
          </div>

          <!-- 申请内容 -->
          <div class="grand-application-content">
            <h1 class="grand-title">河南工业大学工会</h1>
            <h2 class="grand-subtitle">入会申请书</h2>

            <div class="seal-decoration">
              <div class="seal-line"></div>
              <div class="seal-icon">印</div>
              <div class="seal-line"></div>
            </div>

            <div class="application-text-container">
              <p class="grand-application-text">
                我自愿加入河南工业大学工会，遵守工会章程，执行工会决议，积极参加
                工会活动，为全面建成小康社会、把我国建设成为富强民主文明和谐美丽的
                社会主义现代化强国、实现中华民族伟大复兴的中国梦而奋斗。
              </p>
            </div>

            <div class="grand-signature">
              <div class="signature-line"></div>
              <div class="signature-details">
                <div class="applicant-info">
                  <span class="info-label">申请人：</span>
                  <span class="info-value">{{ this.form.membersName }}</span>
                </div>
                <div class="date-info">
                  <span class="info-label">日期：</span>
                  <span class="info-value">{{ applicationDate }}</span>
                </div>
              </div>
            </div>
          </div>

          <!-- 申请按钮 -->
          <div class="grand-action-buttons">
            <button class="grand-agree-button" @click="handleAgree">
              <span>郑重承诺并申请加入</span>
              <svg class="icon" viewBox="0 0 24 24">
                <path
                  fill="currentColor"
                  d="M9,20.42L2.79,14.21L5.62,11.38L9,14.77L18.88,4.88L21.71,7.71L9,20.42Z"
                />
              </svg>
            </button>
          </div>
        </div>
      </transition>
    </div>
    <div class="union-application-grand" v-if="complete">
      <!-- 红色背景装饰条 -->
      <div class="red-banner"></div>

      <transition name="fade" mode="out-in">
        <!-- 成功状态 -->
        <div class="grand-success-card">
          <div class="success-badge">
            <div class="success-badge-inner">
              <svg viewBox="0 0 100 100">
                <circle cx="50" cy="50" r="45" fill="#c10b0b" />
                <path
                  d="M40,55 L50,65 L70,45"
                  fill="none"
                  stroke="#FFD700"
                  stroke-width="8"
                  stroke-linecap="round"
                />
              </svg>
            </div>
          </div>

          <h2 class="grand-success-title">入会申请已受理</h2>
          <p class="grand-success-message">欢迎您的加入</p>

          <div class="success-details">
            <p>
              您的申请编号：<span class="highlight">{{
                applicationNumber
              }}</span>
            </p>
            <p>我们将在<strong></strong>后续完成审核</p>
            <p>请保持联系方式畅通</p>
          </div>

          <button class="grand-back-button" @click="handleReset">
            <svg class="icon" viewBox="0 0 24 24" style="margin-right: 8px">
              <path
                fill="currentColor"
                d="M20,11V13H8L13.5,18.5L12.08,19.92L4.16,12L12.08,4.08L13.5,5.5L8,11H20Z"
              />
            </svg>
            返回首页
          </button>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import { getUserProfile } from "@/api/system/user";
// import {
//   membersFlowable,
//   listSchool,
// } from "@/api/member-profile/member-management";
import CheckGrassrootsUnions from "@/components/CheckGrassrootsUnions";
// import CheckUnit from "@/components/CheckUnit";
import {listMembers,getMembersNum,membersFlowable} from "@/api/members/teaching";

export default {
  name: "UnionMemberApplicationForm",
  dicts: [
        "sys_marital",
        "sys_user_sex",
        "sys_mode_employment",
        "sys_members_status",
        "sys_background",
        "sys_nation",
      ],
  components: {
    CheckGrassrootsUnions,
    // CheckUnit,
  },
  data() {
    return {
      
      nation: [
        { value: "汉族", label: "汉族" },
        { value: "壮族", label: "壮族" },
        { value: "满族", label: "满族" },
        { value: "回族", label: "回族" },
        { value: "苗族", label: "苗族" },
        { value: "维吾尔族", label: "维吾尔族" },
        { value: "土家族", label: "土家族" },
        { value: "彝族", label: "彝族" },
        { value: "蒙古族", label: "蒙古族" },
        { value: "藏族", label: "藏族" },
        { value: "布依族", label: "布依族" },
        { value: "侗族", label: "侗族" },
        { value: "瑶族", label: "瑶族" },
        { value: "朝鲜族", label: "朝鲜族" },
        { value: "白族", label: "白族" },
        { value: "哈尼族", label: "哈尼族" },
        { value: "哈萨克族", label: "哈萨克族" },
        { value: "黎族", label: "黎族" },
        { value: "傣族", label: "傣族" },
        { value: "畲族", label: "畲族" },
        { value: "傈僳族", label: "傈僳族" },
        { value: "仡佬族", label: "仡佬族" },
        { value: "东乡族", label: "东乡族" },
      ],
      educationOptions: [
        { value: "博士", label: "博士" },
        { value: "硕士", label: "硕士" },
        { value: "本科", label: "本科" },
        { value: "大专", label: "大专" },
        { value: "高中及以下", label: "高中及以下" },
      ],
      politics: [
        { value: "中共党员", label: "中共党员" },
        { value: "中共预备党员", label: "中共预备党员" },
        { value: "共青团员", label: "共青团员" },
        { value: "民革党员", label: "民革党员" },
        { value: "民盟盟员", label: "民盟盟员" },
        { value: "民建会员", label: "民建会员" },
        { value: "民进会员", label: "民进会员" },
        { value: "农工党党员", label: "农工党党员" },
        { value: "致公党党员", label: "致公党党员" },
        { value: "群众", label: "群众" },
      ],
      unionOptions: [],
      schoolOptions: [],
      form: {
        membersName: "",
        membersSex: "",
        membersNum: "",
        membersBir: "",
        membersBackground: "",
        membersNation: "",
        membersPolitical: "",
        membersSchoolDate: "",
        membersTel: "",
        // deptId: "",
        union: "",
        unit: "",
        membersMaritalStatus: "",
        membersSpouseCampusStaff: "",
        membersAddress: "",
        schoolId: "",
        membersModeEmployment: 0,
        membersResume: "",
        membersMainFamily: "",
        membersHobby: "",
        membersRemarks: "",
        // membersImg: "",
      },
      previewUrl: "",
      roleGroup: "",
      agreed: false,
      complete: false,
      applicationDate: this.getCurrentDate(),
      applicationNumber:
        "GH" +
        Math.floor(Math.random() * 1000000)
          .toString()
          .padStart(6, "0"),
    };
  },
  created() {
    this.getUser();
    // this.listSchool();
    this.getMembersNumHandler();
    // this.form = JSON.parse(JSON.stringify(this.$store.state.user.members));

    // if (this.form.deptIdFirst) {
    // }
    // this.form.this.form.union = "";
    // this.form.unit = "";
    // delete this.form.updateBy;
    // delete this.form.remark;
    // delete this.form.pageNum;
    // delete this.form.pageSize;
    // delete this.form.createBy;
    // delete this.form.createTime;
    // delete this.form.updateTime;
    // delete this.form.delFlag;
    // this.previewUrl = this.$store.state.user.members.membersImg;
  },
  methods: {
    getMembersNumHandler() {
      getMembersNum().then((response) => {
      this.form = response.data;
      this.form.membersModeEmployment = parseInt(this.form.membersModeEmployment);
        this.form.membersAdmissionTime = parseInt(this.form.membersAdmissionTime);
        this.form.membersMaritalStatus = parseInt(this.form.membersMaritalStatus);
        this.previewUrl = this.form.membersImg;
    })
    },
    unionChange() {
      this.form.unit = "";
    },
    getUser() {
      getUserProfile().then((response) => {
        this.roleGroup = response.roleGroup;
      });
    },
    listSchool() {
      let query = {
        pageNum: 1,
        pageSize: 100,
      };
      // listSchool(query).then((response) => {
      //   this.schoolOptions = response.rows;
      // });
    },
    handleAgree() {
      console.log(">>>>>>>>>>>>>>");

      this.$emit("submitting");
      this.agreed = true;
      this.complete = false;
      this.$emit("agreed");
    },
    handleReset() {
      this.agreed = false;
      this.complete = false;
      this.resetForm();
      this.applicationNumber =
        "GH" +
        Math.floor(Math.random() * 1000000)
          .toString()
          .padStart(6, "0");
    },
    getCurrentDate() {
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const day = String(now.getDate()).padStart(2, "0");
      return `${year}年${month}月${day}日`;
    },
    submitForm() {
      console.log("提交申请");
      console.log(this.form);
      membersFlowable(this.form).then((response) => {
            this.complete = true;
            this.$message.success("提交成功");
          });

      // console.log("提交表单:", this.form);
      // switch ("") {
      //   case this.form.membersName:
      //     this.$message.error("请输入姓名");
      //     break;
      //   case this.form.membersSex:
      //     this.$message.error("请输入性别");
      //     break;
      //   case this.form.membersNum:
      //     this.$message.error("请输入工号");
      //     break;
      //   // case this.form.membersNation:
      //   //   this.$message.error("请输入民族");
      //   //   break;
      //   case this.form.membersSchoolDate:
      //     this.$message.error("请输入入校日期");
      //     break;
      //   case this.form.membersTel:
      //     this.$message.error("请输入联系电话");
      //     break;
      //   // case this.form.schoolId:
      //   //   this.$message.error("请输入所在校区");
      //   //   break;
      //   case this.form.membersModeEmployment:
      //     this.$message.error("请输入用人方式");
      //     break;
      //   // case this.form.union:
      //   //   this.$message.error("请选择基层工会");
      //   //   break;
      //   case this.form.membersMaritalStatus:
      //     this.$message.error("请选择婚姻状况");
      //     break;
      //   // default:
      //   //   this.form.deptIdFirst = this.form.union;
      //   //   if (this.form.unit == "") {
      //   //     this.form.deptId = this.form.union;
      //   //   } else {
      //   //     this.form.deptId = this.form.unit;
      //   //   }

      //     const formData = new FormData();
      //     for (const key in this.form) {
      //       if (this.form[key] == null) {
      //         this.form[key] = "";
      //       }
      //       // if (Object.prototype.hasOwnProperty.call(object, key)) {
      //       formData.append(key, this.form[key]);
      //       // }
      //     }
      //     for (let pair of formData.entries()) {
      //       console.log(pair[0] + ", " + pair[1]);
      //     }
      //     this.form.societyId = 0;
      //     // membersFlowable(formData).then((response) => {
      //     //   this.complete = true;
      //     //   this.$message.success("提交成功");
      //     // });
      //     break;
      // }
    },
    resetForm() {
      this.form = {
        membersName: "",
        membersSex: "",
        membersNum: "",
        membersBir: "",
        membersBackground: "",
        membersNation: "",
        membersPolitical: "",
        membersSchoolDate: "",
        membersTel: "",
        // deptId: "",
        union: "",
        unit: "",
        membersMaritalStatus: "",
        membersSpouseCampusStaff: "",
        membersAddress: "",
        schoolId: "",
        membersModeEmployment: "",
        membersResume: "",
        membersMainFamily: "",
        membersHobby: "",
        membersRemarks: "",
      };
      // this.form.membersImg = "";
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      console.log(file);

      // this.form.membersImg = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg" || file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG/PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    handleFileChange(file, fileList) {
      console.log(file);
      console.log(fileList);

      // 清除之前的预览
      this.clearFile();
      // 保存文件信息
      this.selectedFile = file.raw;
      this.fileName = file.name;
      this.fileType = file.type;
      this.form.file = file.raw;

      // 判断是否是图片

      // 生成预览URL
      this.previewUrl = URL.createObjectURL(file.raw);
      // 创建FileReader对象读取文件
      const reader = new FileReader();

      reader.onload = (event) => {
        const binaryData = event.target.result;
        console.log(binaryData);
        //
      };
      reader.readAsArrayBuffer(file.raw);
    },

    clearFile() {
      // 释放预览URL内存
      if (this.previewUrl && this.isImage) {
        URL.revokeObjectURL(this.previewUrl);
      }

      this.previewUrl = "";
      this.fileName = "";
      this.fileType = "";
      this.isImage = false;
      this.selectedFile = null;
    },
  },
  // 组件销毁时释放内存
  beforeUnmount() {
    if (this.previewUrl && this.isImage) {
      URL.revokeObjectURL(this.previewUrl);
    }
  },
};
</script>

<style scoped>
/* 基础样式 */
* {
  box-sizing: border-box;
  margin: 0;
  font-family: "Microsoft YaHei", sans-serif;
}

.required {
  color: red;
  margin-right: 3px;
}

/* A4纸容器 */
.a4-form-container {
  background-color: #f5f5f5;
  padding: 20px;
  display: flex;
  justify-content: center;
}

/* A4纸大小和样式 */
.a4-form {
  width: 233mm;
  min-height: 297mm;
  background-color: white;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  padding: 15mm;
  position: relative;
  border: 1px solid #ccc;
}

/* 表头样式 */
.form-header {
  text-align: center;
  margin-bottom: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e8e8e8;
}

.form-header h1 {
  font-size: 22px;
  font-weight: bold;
  margin: 0;
  color: #333;
}

/* 表单表格样式 */
.form-table {
  width: 100%;
  border: 1px solid #000;
  border-collapse: collapse;
  border-right: 0;
}

.form-table-top {
  display: flex;
}

.form-table-top .form-row:last-child {
  border-bottom: none;
}

.form-left-section {
  flex: 1;
}

/* 表单行样式 */
.form-row {
  display: flex;
  border-bottom: 1px solid #000;
  min-height: 40px;
}

.form-table-bottom .form-row:first-child {
  border-top: 1px solid #000;
}

.form-table-bottom .form-row:last-child {
  border-bottom: none;
}

.el-radio-group {
  line-height: 26px;
  padding-top: 7px;
}

/* 全行样式 */
.full-row {
  display: flex;
}

.full-row .form-label {
  width: 120px;
}

.full-row .full-input {
  flex: 1;
  border-right: 1px solid #000;
}

/* 表单项标签样式 */
.form-label {
  width: 120px;
  font-size: 14px;
  color: #333;
  text-align: center;
  padding: 10px 5px;
  background-color: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: 1px solid #000;
  flex-shrink: 0;
}

/* 表单项输入区域样式 */
.form-input-left {
  width: 190px;
  padding: 8px 10px;
  display: flex;
  align-items: center;
  border-right: 1px solid #000;
  min-height: 40px;
}

.form-input-right {
  width: 190px;
  padding: 8px 10px;
  display: flex;
  align-items: center;
  border-right: 1px solid #000;
  min-height: 40px;
}

.form-input {
  flex: 1;
  padding: 8px 10px;
  display: flex;
  align-items: center;
  border-right: 1px solid #000;
  min-height: 40px;
}

.gender-input {
  justify-content: flex-start;
  padding-left: 15px;
}

/* 照片区域样式 - 加宽 */
.photo-cell {
  width: 150px;
  /* 从120px增加到150px */
  display: flex;
  flex-direction: column;
  border-right: 1px solid #000;
  background-color: #f9f9f9;
}

.photo-label {
  text-align: center;
  padding: 10px 5px;
  border-bottom: 1px solid #000;
  font-size: 14px;
}

.photo-upload-wrapper {
  flex: 1;
  border: 1px dashed #999;
  margin: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #fafafa;
  height: calc(100% - 10px);
}

.photo-upload {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.avatar {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.upload-placeholder {
  text-align: center;
  color: #8c939d;
  font-size: 12px;
}

.upload-placeholder i {
  font-size: 24px;
  color: #8c939d;
  margin-bottom: 5px;
}

/* 调整Element组件样式 */
.el-input,
.el-select,
.el-date-editor {
  width: 100%;
}

.el-textarea__inner {
  min-height: 80px !important;
  border: none !important;
  padding: 5px !important;
  line-height: 1.5;
}

.el-radio {
  margin-right: 15px;
}

/* 底部按钮 */
.form-footer {
  margin-top: 30px;
  text-align: center;
}

.form-footer .el-button {
  padding: 10px 25px;
  font-size: 14px;
}

/* 打印样式 */
@media print {
  .a4-form-container {
    background-color: white;
    padding: 0;
  }

  .a4-form {
    box-shadow: none;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 15mm;
    border: none;
  }

  .form-footer {
    display: none;
  }
}

/* 基础样式 */
.union-application-grand {
  font-family: "SimSun", "STKaiti", "Microsoft YaHei", serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  background-color: #f9f3e9;
  background-image: url('data:image/svg+xml;utf8,<svg width="100" height="100" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg"><path fill="%23e8d9c5" d="M0,0 L100,0 L100,100 L0,100 Z M20,20 C40,20 40,40 60,40 C80,40 80,60 100,60 L100,80 C80,80 80,60 60,60 C40,60 40,80 20,80 Z" opacity="0.1"/></svg>');
  padding: 40px 20px;
  position: relative;
}

.red-banner {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 12px;
  background: linear-gradient(90deg, #9c0000, #c10b0b, #9c0000);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

/* 主卡片样式 */
.grand-application-card,
.grand-success-card {
  width: 100%;
  max-width: 720px;
  background: white;
  border-radius: 4px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
  overflow: hidden;
  position: relative;
  margin-top: 40px;
  padding: 40px;
  border: 1px solid #e0d0b8;
}

/* 金色边框 */
.gold-border {
  position: absolute;
  background: linear-gradient(90deg, #d4af37, #f9d423, #d4af37);
  z-index: 2;
}

.gold-border.top {
  top: 0;
  left: 0;
  right: 0;
  height: 3px;
}

.gold-border.right {
  top: 0;
  right: 0;
  bottom: 0;
  width: 3px;
}

.gold-border.bottom {
  bottom: 0;
  left: 0;
  right: 0;
  height: 3px;
}

.gold-border.left {
  top: 0;
  left: 0;
  bottom: 0;
  width: 3px;
}

/* 工会徽章 */
.union-badge {
  position: absolute;
  top: -30px;
  left: 50%;
  transform: translateX(-50%);
  width: 80px;
  height: 80px;
  background: white;
  border-radius: 50%;
  border: 3px solid #d4af37;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 3;
}

.badge-inner {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  overflow: hidden;
}

.badge-icon {
  width: 100%;
  height: 100%;
}

/* 标题样式 */
.grand-title {
  color: #c10b0b;
  font-size: 32px;
  font-weight: bold;
  text-align: center;
  margin: 20px 0 10px;
  letter-spacing: 4px;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

.grand-subtitle {
  color: #333;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 30px;
  position: relative;
}

/* 印章装饰 */
.seal-decoration {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
}

.seal-line {
  flex: 1;
  height: 1px;
  background: linear-gradient(90deg, transparent, #c10b0b, transparent);
}

.seal-icon {
  width: 40px;
  height: 40px;
  background: #c10b0b;
  color: #ffd700;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 15px;
  font-size: 18px;
  font-weight: bold;
  transform: rotate(15deg);
  border: 1px dashed #ffd700;
}

/* 申请文本 */
.application-text-container {
  background: #fffaf0;
  border: 1px solid #e8d9c5;
  padding: 30px;
  margin: 20px 0;
  position: relative;
}

.application-text-container::before {
  content: "";
  position: absolute;
  top: 5px;
  left: 5px;
  right: 5px;
  bottom: 5px;
  border: 1px solid #e8d9c5;
  pointer-events: none;
}

.grand-application-text {
  color: #333;
  font-size: 18px;
  line-height: 2;
  text-align: justify;
  text-indent: 2em;
  margin: 0;
}

/* 签名部分 */
.grand-signature {
  margin-top: 40px;
  padding-top: 20px;
  position: relative;
}

.signature-line {
  height: 1px;
  background: linear-gradient(90deg, transparent, #c10b0b, transparent);
  margin-bottom: 20px;
}

.signature-details {
  display: flex;
  justify-content: space-between;
  font-size: 16px;
}

.info-label {
  color: #666;
}

.info-value {
  color: #333;
  font-weight: bold;
  margin-left: 10px;
}

/* 按钮样式 */
.grand-action-buttons {
  margin-top: 40px;
  text-align: center;
}

.grand-agree-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 14px 36px;
  background: linear-gradient(90deg, #9c0000, #c10b0b);
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 18px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(156, 0, 0, 0.3);
  position: relative;
  overflow: hidden;
}

.grand-agree-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(156, 0, 0, 0.4);
}

.grand-agree-button::before {
  content: "";
  position: absolute;
  top: -50%;
  left: -50%;
  width: 200%;
  height: 200%;
  background: linear-gradient(
    to bottom right,
    rgba(255, 255, 255, 0) 45%,
    rgba(255, 255, 255, 0.8) 50%,
    rgba(255, 255, 255, 0) 55%
  );
  transform: rotate(30deg);
  transition: all 0.3s;
}

.grand-agree-button:hover::before {
  left: 100%;
}

.grand-agree-button .icon {
  width: 24px;
  height: 24px;
  margin-left: 10px;
}

/* 成功卡片 */
.grand-success-card {
  text-align: center;
  padding: 60px 40px;
}

.success-badge {
  width: 120px;
  height: 120px;
  margin: 0 auto 30px;
  background: white;
  border-radius: 50%;
  border: 3px solid #d4af37;
  display: flex;
  align-items: center;
  justify-content: center;
}

.success-badge-inner {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
}

.grand-success-title {
  color: #c10b0b;
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 15px;
}

.grand-success-message {
  color: #333;
  font-size: 20px;
  margin-bottom: 30px;
}

.success-details {
  background: #fffaf0;
  border: 1px solid #e8d9c5;
  padding: 20px;
  margin: 30px 0;
  text-align: center;
  font-size: 16px;
  line-height: 1.8;
}

.success-details p {
  margin: 10px 0;
}

.highlight {
  color: #c10b0b;
  font-weight: bold;
}

.grand-back-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 12px 24px;
  background: #f9f3e9;
  color: #333;
  border: 1px solid #d4af37;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.grand-back-button:hover {
  background: #e8d9c5;
}

.grand-back-button .icon {
  width: 20px;
  height: 20px;
}

/* 动画效果 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease, transform 0.5s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
  transform: translateY(20px);
}
</style>
