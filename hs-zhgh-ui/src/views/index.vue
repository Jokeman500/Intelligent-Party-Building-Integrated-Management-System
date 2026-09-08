<template>
  <div class="union-dashboard">
    <!-- 顶部广告栏 + 个人信息 -->
    <div class="top-section">
      <div class="ad-banner">
        <div class="quick-nav">
          <h3>快捷导航</h3>
          <div class="quick-nav-content">
            <div class="nav-item" @click="memberInfoTo">
              <img src="../assets/images/memberInfo.png" alt="会员信息图标" />
              <span>会员建设</span>
            </div>
            <div class="nav-item" @click="culturalSports">
              <img
                src="../assets/images/sports-activities.png"
                alt="文体活动图标"
              />
              <span>文体活动</span>
            </div>
            <div class="nav-item" @click="facultyWelfare">
              <img src="../assets/images/benefit.png" alt="福利发放图标" />
              <span>福利发放</span>
            </div>
            <div class="nav-item" @click="taskTodo">
              <div class="nav-item-bg">{{ todoListTotal }}</div>
              <span>代办事项</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 个人信息卡片 -->
      <div class="user-card">
        <div class="user-card-left">
          <h3>个人信息</h3>
          <img src="../assets/logo/logo.jpg" alt="用户头像" />
          <div class="user-card-info">
            <div>姓名：admin</div>
            <div>工号：20050809001</div>
          </div>
        </div>

        <div class="user-info">
          <p class="info-item">
            <span class="info-label">单位：</span>
            <span class="info-value">
              <span >暂无</span>
            </span>
          </p>
          <p class="info-item">
            <span class="info-label">工会：</span>
            <span class="info-value">
              <span >暂无</span>
            </span>
          </p>
          <p class="info-item">
            <span class="info-label">角色：</span>
            <span class="info-value">
              <span 
                >
                超级管理员
              </span>
            </span>
          </p>
          <p class="info-item">
            <span class="info-label">手机：</span>
            <span class="info-value">xxxxxx</span>
          </p>
          <p class="info-item">
            <span class="info-label">登陆日期：</span>
            <span class="info-value">{{ loginDate }}</span>
          </p>
        </div>
      </div>
    </div>

    <!-- 主要内容区 -->
    <div class="main-content">
      <el-row :gutter="20">
        <!-- 活动报名 -->
        <el-col :span="12">
          <div class="content-card">
            <h3>通知公告</h3>
            <div class="activity-list">
              <div v-if="activities.length > 0">
                <div
                  class="activity-item"
                  v-for="(activity, index) in activities"
                  :key="index"
                >
                  <img
                    :src="activity.firstImage"
                    alt="活动图片"
                    class="activity-img"
                  />
                  <div class="activity-details">
                    <div class="activity-left">
                      <div class="activity-header">
                        <h4>{{ activity.offlineActivityName }}</h4>
                        <div class="activity-header-right">个人</div>
                      </div>
                      <div class="activity-time">
                        <p>
                          活动起始时间: {{ activity.offlineActivityStart }}~{{
                            activity.offlineActivityEnd
                          }}
                        </p>
                        <p>
                          报名起始时间:
                          {{ activity.offlineActivitySignupStart }}~{{
                            activity.offlineActivitySignupEnd
                          }}
                        </p>
                      </div>
                    </div>
                    <div class="activity-status">
                      <div
                        class="activityStatus activityStatus1 btn"
                        v-if="activity.offlineActivityStatus == 0"
                      >
                        未开始
                      </div>
                      <div
                        class="activityStatus activityStatus2 btn"
                        v-if="activity.offlineActivityStatus == 1"
                      >
                        进行中
                      </div>
                      <div
                        class="activityStatus activityStatus3 btn"
                        v-if="activity.offlineActivityStatus == 2"
                      >
                        已结束
                      </div>
                      <div
                        class="btn"
                        @click="handleSign(activity)"
                        :style="getStatusStyle(activity)"
                      >
                        {{ getStatusText(activity) }}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div v-else>
                <el-empty description="暂无数据"></el-empty>
              </div>
            </div>
          </div>
        </el-col>

        <!-- 信息资讯 -->
        <el-col :span="12">
          <div class="content-card">
            <div class="content-card-header">
              <h3>信息资讯</h3>
            </div>
            <el-empty description="暂无数据"></el-empty>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {},
      roleInfos: {},
      todoListTotal: 0,
      noticeList: [],
      noticeTopList: [],
      notice5List: [],
      notice6List: [],
      dynamicList: [],
      platformList0: [],
      platformList1: [],
      memberInfo: {},
      deptName: {},
      deptWorkName: {},
      roleInfo: "",
      loginDate:new Date(),
      navItems: [
        { name: "会员建设", icon: "el-icon-user" },
        { name: "福利发放", icon: "el-icon-gift" },
        { name: "文体活动", icon: "el-icon-trophy" },
        { name: "代办事项", icon: "el-icon-timer" },
      ],
      activities: [],
      activeTab: "notice1",
    };
  },
  created() {
    this.listActivity();
    this.todoList();
    this.listDynamicList();
    this.listEssay();
    this.listEssay1();
    this.listPlatformList0();
    this.listPlatformList1();

    if (this.$store.state.user.members) {
      this.memberInfo = this.$store.state.user.members;
      this.deptName = this.$store.state.user.deptName;
      this.deptWorkName = this.$store.state.user.deptWorkName;
      this.roleInfo = this.$store.state.user.roles.join(",");
      this.roleInfos = this.$store.state.user.user.roles;
    }

    this.memberInfo.avatar = this.$store.getters.avatar;
    this.memberInfo.loginDate = this.parseTime(
      this.$store.state.user.user.loginDate,
      "{y}-{m}-{d}"
    );
  },
  methods: {
    getStatusText(activity) {
      // 个人已报名状态优先显示
      if (activity.isSignup === "2") {
        return "已报名";
      }
      // 根据活动报名状态显示
      switch (activity.offlineActivitySignupStatus) {
        case "0":
          return "未开始";
        case "1":
          // 区分其他个人状态
          if (activity.isSignup === "1") return "已提交";
          if (activity.isSignup === "3") return "已驳回";
          return "立即报名";
        case "2":
          return "已结束";
        default:
          return "未知状态";
      }
    },
    getStatusStyle(activity) {
      // 用内联样式直接返回颜色配置
      if (activity.isSignup === "2") {
        return {
          backgroundColor: "#4CAF50", // 绿色-已报名
          color: "white",
        };
      }

      switch (activity.offlineActivitySignupStatus) {
        case "0": // 未开始
          return {
            backgroundColor: "#f1f1f1", // 浅灰
            color: "#666",
          };
        case "1": // 进行中
          if (activity.isSignup === "1") {
            return { backgroundColor: "#2196F3", color: "white" }; // 蓝色-已提交
          }
          if (activity.isSignup === "3") {
            return { backgroundColor: "#ffc107", color: "#333" }; // 黄色-已驳回
          }
          return {
            backgroundColor: "#008CBA", // 深蓝色-立即报名
            color: "white",
          };
        case "2": // 已结束
          return {
            backgroundColor: "#e0e0e0", // 深灰
            color: "#666",
          };
        default:
          return {
            backgroundColor: "#9e9e9e", // 灰色-未知
            color: "white",
          };
      }
    },
    noticeTopHandel(item) {
      this.$router.push({
        path: "/essay-query",
        query: { newsEssayId: item.newsEssayId },
      });
    },
    moreHandel() {
      switch (this.activeTab) {
        case "notice1":
          this.$router.push({ path: "/news/news-essay" });
          break;
        case "notice2":
          this.$router.push({ path: "/activity-list/index" });
          break;
        case "notice3":
          this.$router.push({
            path: "/faculty-culture/female-platform/female-demeanor",
          });
          break;
        case "notice4":
          this.$router.push({
            path: "/faculty-culture/female-platform/female-news",
          });
          break;
        default:
          break;
      }
    },
    noticeHandel(item) {
      this.$router.push({
        path: "/essay-query",
        query: { newsEssayId: item.newsEssayId },
      });
    },
    unionDynamics(row) {
      this.$router.push({
        path: "/union-dynamics/view-activity/index",
        query: { dynamicId: row.dynamicId },
      });
    },
    femaleDemeanor(row) {
      this.$router.push({
        path: "/view-female-demeanor/index",
        query: { femaleId: row.femaleId },
      });
    },
    femaleNew(row) {
      this.$router.push({
        path: "/view-female-news/index",
        query: { femaleId: row.femaleId },
      });
    },
    listEssay() {
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        newsTypeCode: "1000",
      };
      listEssay(queryParams).then((response) => {
        this.noticeTopList = response.rows.map((item) => ({
          ...item,
          newsEssayImg: process.env.VUE_APP_BASE_API + item.newsEssayImg,
        }));
      });
    },
    listEssay1() {
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        newsTypeCode: "1100",
      };
      listEssay(queryParams).then((response) => {
        this.noticeList = response.rows;
      });
    },
    // 基层工会动态
    listDynamicList() {
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        auditState: 1, // 只要已审核过的动态
      };
      listDynamic(queryParams).then((response) => {
        this.dynamicList = response.rows;
      });
    },
    listPlatformList0() {
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        columns: 0,
      };
      listPlatform(queryParams).then((response) => {
        this.platformList0 = response.rows;
      });
    },
    listPlatformList1() {
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        columns: 1,
      };
      listPlatform(queryParams).then((response) => {
        this.platformList1 = response.rows;
      });
    },
    todoList() {
      let queryParams = {
        pageNum: 1,
        pageSize: 100,
      };
      todoList(queryParams).then((response) => {
        this.todoListTotal = response.data.total;
      });
    },
    handleSign(activity) {
      // 未开始、已结束、已报名状态下不执行报名操作
      if (
        activity.offlineActivitySignupStatus === "0" ||
        activity.offlineActivitySignupStatus === "2" ||
        activity.isSignup === "1" ||
        activity.isSignup === "2"
      ) {
        return;
      }
      const params = {
        offlineActivityId: activity.offlineActivityId,
      };
      signUpIndividual(params).then((response) => {
        this.$modal.msgSuccess("报名成功，等待审批");
        this.listActivity();
      });
    },
    listActivity() {
      let queryParams = {
        pageNum: 1,
        pageSize: 3,
        offlineActivityTypeRegistrationType: 0,
      };
      listActivity(queryParams).then((response) => {
        this.activities = response.rows;
      });
    },
    facultyWelfare() {
      this.$router.push({
        path: "/faculty-services/faculty-welfare/welfare-personnel-database",
      });
    },
    associationManagement() {
      this.$router.push({
        path: "/faculty-culture/cultural-association/association-management",
      });
    },
    culturalSports() {
      this.$router.push({
        path: "/faculty-culture/cultural-sports/event-release",
      });
    },
    memberInfoTo() {
      this.$router.push({ path: "/user/profile" });
    },
    taskTodo() {
      this.$router.push({ path: "/task/todo" });
    },
  },
};
</script>

<style lang="scss" scoped>
// 基础样式变量
$primary-color: #4860bf;
$text-color: #333333;
$text-secondary: #4d4d4d;
$text-light: #7f8c8d;
$border-radius: 8px;
$spacing-sm: 8px;
$spacing-md: 15px;
$spacing-lg: 20px;

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.union-dashboard {
  padding: $spacing-lg;
}

// 顶部区域样式
.top-section {
  display: flex;
  justify-content: space-between;
  margin-bottom: $spacing-lg;
  gap: $spacing-lg;

  .ad-banner {
    padding: 22px;
    border-radius: 10px;
    width: 66%;
    height: 235px;
    background-color: #fff;

    h3 {
      font-family: MiSans, MiSans;
      font-weight: 700;
      color: #424242;
      text-align: left;
      font-style: normal;
      text-transform: none;
    }

    .quick-nav {
      background-color: #fff;
      border-radius: 10px;

      h3 {
        font-family: MiSans, MiSans;
        font-weight: 600;
        color: #424242;
        text-align: left;
        font-style: normal;
        text-transform: none;
      }

      .quick-nav-content {
        display: flex;
        justify-content: space-around;
        margin-top: 18px;

        .nav-item {
          transition: 0.7s;
          display: flex;
          flex-direction: column;
          justify-content: center;
          width: 18%;
          height: 153px;
          align-items: center;
          padding: 10px 19px;
          background: #f2f2f2;
          border-radius: 10px;
          cursor: pointer;

          &:hover {
            transform: translateY(-3px);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
          }

          img {
            width: 62px;
            margin-bottom: 15px;
          }

          span {
            font-family: MiSans, MiSans;
            font-weight: 500;
          }

          .nav-item-bg {
            display: flex;
            justify-content: center;
            margin-bottom: 15px;
            align-items: center;
            color: #fff;
            font-size: 22px;
            font-weight: 700;
            width: 62px;
            height: 62px;
            border-radius: 10px;
            background-image: url("../assets/images/to-do.png");
            background-size: 100% 100%;
          }
        }
      }
    }
  }

  .user-card {
    width: 33%;
    height: 235px;
    display: flex;
    align-items: center;
    background: white;
    padding: 22px 30px;
    border-radius: $border-radius;
    overflow: hidden;
    /* 防止内容溢出卡片 */

    .user-card-left {
      display: flex;
      flex-direction: column;
      align-items: center;
      min-width: 120px;
      /* 固定左侧宽度，防止压缩 */

      h3 {
        margin-top: 2px;
        font-family: MiSans, MiSans;
        font-weight: 700;
        color: #424242;
        text-align: left;
        font-style: normal;
        text-transform: none;
      }

      img {
        width: 78px;
        height: 78px;
        margin: 20px 0 13px 0;
        border-radius: 50%;
        object-fit: cover;
      }

      .user-card-info {
        div {
          text-align: center;
          font-family: MiSans, MiSans;
          font-size: 14px;
          font-weight: 600;
          color: $text-secondary;
          line-height: 30px;
          font-style: normal;
          text-transform: none;
        }
      }
    }

    .user-info {
      margin-left: 20px;
      /* 减少左边距，增加可用空间 */
      flex: 1;
      /* 让信息区域占满剩余空间 */
      min-width: 0;
      /* 允许内容收缩 */
      overflow: hidden;
      /* 隐藏溢出内容 */

      // 新增信息项样式
      .info-item {
        display: flex;
        margin: 8px 0;
        /* 减小行间距 */
        font-size: 14px;
        line-height: 1.4;
        /* 增加行高，便于换行 */
        white-space: normal;
        /* 允许文本换行 */

        .info-label {
          flex-shrink: 0;
          /* 标签不收缩 */
          width: 70px;
          /* 固定标签宽度 */
          color: $text-color;
          font-weight: 500;
        }

        .info-value {
          flex: 1;
          /* 内容区域占满剩余空间 */
          color: $primary-color;
          font-weight: 400;
          min-width: 0;
          /* 允许内容收缩 */
          word-wrap: break-word;
          /* 长单词换行 */
          overflow: hidden;
          /* 隐藏溢出内容 */
        }
      }
    }
  }
}

// 主要内容区样式
.main-content {
  .el-row {
    width: 100%;

    .el-col {
      &:nth-child(1) {
        padding-right: 10px;
      }

      &:nth-child(2) {
        padding-left: 10px;
      }
    }
  }

  .content-card {
    background: white;
    padding: $spacing-lg;
    border-radius: $border-radius;
    margin-bottom: $spacing-lg;
    min-height: 550px;

    h3 {
      font-family: MiSans, MiSans;
      font-weight: 600;
      color: #424242;
      text-align: left;
      font-style: normal;
      text-transform: none;
      margin-bottom: $spacing-md;
    }

    .content-card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: $spacing-md;
    }
  }
}

// 活动列表样式
.activity-list {
  min-height: 275px;

  .activity-item {
    display: flex;
    width: 100%;
    min-height: 120px;
    margin: 15px 0;
    padding: 1%;
    border-radius: 8px;
    box-sizing: border-box;
    border: 1px solid #f0f0f0;
    transition: all 0.3s;

    &:hover {
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    }

    .activity-img {
      width: 20%;
      min-width: 80px;
      height: auto;
      max-height: 100px;
      margin-right: 2%;
      border-radius: 4px;
      object-fit: cover;
    }

    .activity-details {
      display: flex;
      justify-content: space-between;
      width: 78%;

      .activity-left {
        flex: 1;
        min-width: 0;

        .activity-header {
          display: flex;
          align-items: center;
          margin: 5px 0;
          flex-wrap: wrap;

          h4 {
            font-size: 15px;
            font-weight: 600;
            color: $text-color;
            flex: 1;
            min-width: 0;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
          }

          .activity-header-right {
            min-width: 60px;
            height: 20px;
            margin-left: 5px;
            background: $primary-color;
            border-radius: 4px;
            color: #fff;
            font-size: 12px;
            line-height: 20px;
            text-align: center;
          }
        }

        .activity-time {
          font-family: MiSans, MiSans;
          font-weight: 400;
          font-size: 13px;
          color: $text-color;
          line-height: 22px;

          p {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
          }
        }
      }

      .activity-status {
        display: flex;
        align-items: center;
        margin-left: 2%;
        min-width: 160px;

        .btn {
          width: 70px;
          height: 32px;
          border-radius: 6px;
          font-size: 13px;
          line-height: 32px;
          cursor: pointer;
          text-align: center;
          flex-shrink: 0;

          &:not(:last-child) {
            margin-right: 5px;
          }
        }

        .activityStatus {
          color: #fff;

          &.activityStatus1 {
            background-color: #909399;
          }

          &.activityStatus2 {
            background-color: #67c23a;
          }

          &.activityStatus3 {
            background-color: #e6a23c;
          }
        }

        .submit {
          background-image: url("../assets/images/submit.png");
        }
      }
    }
  }
}

// 信息资讯列表样式
.info-list {
  padding: 10px 0;

  li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 12px 0;
    border-bottom: 1px solid #eee;
    cursor: pointer;
    overflow: hidden;
    transition: background-color 0.2s;

    &:hover {
      background-color: #f9f9f9;
    }

    > div:first-child {
      flex: 1;
      margin-right: 15px;
      min-width: 0;
      display: flex;
      align-items: center;
    }

    span {
      &:not(.date) {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        flex: 1;
        min-width: 0;
      }
    }

    .date {
      color: #999;
      font-size: 12px;
      text-align: right;
      white-space: nowrap;
    }
  }
}

// 标签样式
.el-tag {
  margin-right: 8px;
}

// 响应式样式优化
@media screen and (max-width: 1200px) {
  .top-section {
    flex-direction: column;

    .ad-banner,
    .user-card {
      width: 100%;
      height: auto;
    }

    .user-card {
      margin-top: $spacing-lg;
      flex-direction: column;
      /* 在中等屏幕上改为垂直布局 */
    }

    .user-info {
      margin-left: 0;
      margin-top: 15px;
      width: 100%;
    }
  }

  .main-content {
    .el-row {
      .el-col {
        &:nth-child(1),
        &:nth-child(2) {
          width: 100% !important;
          padding: 0;
        }
      }
    }
  }
}

@media screen and (max-width: 768px) {
  .activity-item {
    flex-direction: column;
    height: auto;

    .activity-img {
      width: 100%;
      max-height: 150px;
      margin-right: 0;
      margin-bottom: 10px;
    }

    .activity-details {
      width: 100%;
      flex-direction: column;

      .activity-status {
        margin-left: 0;
        margin-top: 10px;
        justify-content: flex-start;
        flex-wrap: wrap;
      }
    }
  }

  .quick-nav-content {
    flex-wrap: wrap;

    .nav-item {
      width: 45%;
      margin-bottom: 10px;
    }
  }

  // 小屏幕下个人信息调整
  .user-card {
    padding: 15px;
  }

  .user-info .info-item {
    font-size: 13px;
  }
}

// 针对个人信息可能溢出的特殊情况增加额外断点处理
@media screen and (min-width: 992px) and (max-width: 1199px) {
  .user-card {
    width: 35%;
    /* 在这个区间稍微增加宽度 */
  }
}
</style>
