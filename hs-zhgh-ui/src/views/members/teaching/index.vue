<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="会员姓名" prop="membersName">
        <el-input
          v-model="queryParams.membersName"
          placeholder="请输入会员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员工号" prop="membersNum">
        <el-input
          type="number"
          min="0"
          v-model="queryParams.membersNum"
          placeholder="请输入会员工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="会员联系电话" prop="membersTel" label-width="100px">
        <el-input
          type="number"
          min="0"
          v-model="queryParams.membersTel"
          placeholder="请输入会员联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="所在校区" prop="school">
        <el-input
          v-model="queryParams.school"
          placeholder="请输入所在校区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['members:teaching:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['members:teaching:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['members:teaching:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['members:teaching:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="membersList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="membersName" />
      <el-table-column label="工号" align="center" prop="membersNum" width="100"/>
      <el-table-column label="性别" align="center" prop="membersSex" width="50">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sys_user_sex"
            :value="scope.row.membersSex"
          />
        </template>
      </el-table-column>
      <el-table-column label="个人照片" align="center" prop="membersImg">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.membersImg"
            style="width: 100px; height: 80px"
          ></el-image>
        </template>
      </el-table-column>

      <el-table-column
        label="出生日期"
        align="center"
        prop="membersBir"
        width="130"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.membersBir, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="联系电话"
        align="center"
        prop="membersTel"
        width="120"
      />
      <el-table-column
        label="学历"
        align="center"
        prop="membersBackground"
        width="50"
      />
      <el-table-column
        label="民族"
        align="center"
        prop="membersNation"
        width="50"
      />
      <el-table-column
        label="来校时间"
        align="center"
        prop="membersSchoolDate"
        width="150"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.membersSchoolDate, "{y}-{m}-{d}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="婚姻状况"
        align="center"
        prop="membersMaritalStatus"
      >
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sys_marital"
            :value="scope.row.membersMaritalStatus"
          />
        </template>
      </el-table-column>
      <el-table-column label="籍贯" align="center" prop="membersNativePlace" />
      <el-table-column
        label="家庭住址"
        align="center"
        prop="membersAddress"
        width="150"
      />
      <el-table-column
        label="所在校区"
        align="center"
        prop="school"
        width="130"
      />
      <el-table-column
        label="用人方式"
        align="center"
        prop="membersModeEmployment"
      >
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sys_mode_employment"
            :value="scope.row.membersModeEmployment"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="人员状态"
        align="center"
        prop="membersAdmissionTime"
      >
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sys_members_status"
            :value="scope.row.membersAdmissionTime"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="150"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d} {h}:{i}:{s}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        fixed="right"
        width="150"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['members:teaching:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['members:teaching:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改会员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="教职工姓名" prop="membersName">
          <el-input v-model="form.membersName" placeholder="请输入教职工姓名" />
        </el-form-item>
        <el-form-item label="教职工工号" prop="membersNum">
          <el-input
            type="number"
            min="0"
            v-model="form.membersNum"
            placeholder="请输入教职工工号"
          />
        </el-form-item>
        <el-form-item label="教职工出生日期" prop="membersBir">
          <el-date-picker
            clearable
            v-model="form.membersBir"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择教职工出生日期"
            style="width: 100%"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="教职工学历" prop="membersBackground">
          <el-input
            v-model="form.membersBackground"
            placeholder="请输入教职工学历"
          />
        </el-form-item>
        <el-form-item label="教职工民族" prop="membersNation">
          <el-input
            v-model="form.membersNation"
            placeholder="请输入教职工民族"
          />
        </el-form-item>
        <el-form-item label="教职工政治面貌" prop="membersPolitical">
          <el-input
            v-model="form.membersPolitical"
            placeholder="请输入教职工政治面貌"
          />
        </el-form-item>
        <el-form-item label="教职工来校时间" prop="membersSchoolDate">
          <el-date-picker
            clearable
            v-model="form.membersSchoolDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择教职工来校时间"
            style="width: 100%"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="教职工联系电话" prop="membersTel">
          <el-input
            type="number"
            v-model="form.membersTel"
            placeholder="请输入教职工联系电话"
          />
        </el-form-item>
        <el-form-item
          label="配偶是否为校内职工"
          prop="membersSpouseCampusStaff"
        >
        
          <el-radio-group v-model="form.membersSpouseCampusStaff">
            <el-radio label="0">否</el-radio>
            <el-radio label="1">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="教职工籍贯" prop="membersNativePlace">
          <el-input
            v-model="form.membersNativePlace"
            placeholder="请输入教职工籍贯"
          />
        </el-form-item>
        <el-form-item label="教职工家庭住址" prop="membersAddress">
          <el-input
            v-model="form.membersAddress"
            placeholder="请输入教职工家庭住址"
          />
        </el-form-item>
        <el-form-item label="教职工所在校区" prop="school">
          <el-input v-model="form.school" placeholder="请输入教职工所在校区" />
        </el-form-item>
        <el-form-item label="教职工用人方式" prop="membersModeEmployment">
          <el-select
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
        </el-form-item>
        <el-form-item label="个人工作简历" prop="membersResume">
          <el-input
            v-model="form.membersResume"
            placeholder="请输入个人工作简历"
          />
        </el-form-item>
        <el-form-item label="家庭主要成员/单位" prop="membersMainFamily">
          <el-input
            v-model="form.membersMainFamily"
            placeholder="请输入家庭主要成员/单位"
          />
        </el-form-item>
        <el-form-item label="教职工特长/爱好" prop="membersHobby">
          <el-input
            v-model="form.membersHobby"
            placeholder="请输入教职工特长/爱好"
          />
        </el-form-item>
        <el-form-item label="教职工备注" prop="membersRemarks">
          <el-input
            v-model="form.membersRemarks"
            placeholder="请输入教职工备注"
          />
        </el-form-item>
        <el-form-item label="教职工个人照片" prop="membersImg">
          
          <el-upload
            class="upload-demo"
            action="http://localhost:8080/common/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="1"
            :on-exceed="handleExceed"
            :file-list="fileList"
            :on-success="handlerSuccess"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过500kb
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item label="教职工手签" prop="membersSign">
          <el-input
            v-model="form.membersSign"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="身份证号码" prop="membersContactNumber">
          <el-input
            v-model="form.membersContactNumber"
            placeholder="请输入身份证号码"
          />
        </el-form-item>
        <el-form-item label="教职工人员状态" prop="membersAdmissionTime">
          <el-select
            v-model="form.membersAdmissionTime"
            placeholder="请选择教职工人员状态"
            style="width: 100%"
          >
            <el-option
              v-for="dict in dict.type.sys_members_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教职工年龄" prop="membersAge">
          <el-input v-model="form.membersAge" placeholder="请输入教职工年龄" />
        </el-form-item>
        <!-- <el-form-item label="教职工国籍" prop="membersNationality">
          <el-input
            v-model="form.membersNationality"
            placeholder="请输入教职工国籍"
          />
        </el-form-item>
        <el-form-item
          label="教职工技术等级"
          prop="membersTechnical"
        >
          <el-input
            v-model="form.membersTechnical"
            placeholder="请输入教职工技术等级"
          />
        </el-form-item>
        <el-form-item
          label="教职工专业技术职称"
          prop="membersProfessional"
        >
          <el-input
            v-model="form.membersProfessional"
            placeholder="请输入教职工专业技术职称"
          />
        </el-form-item> -->
        <el-form-item label="教职工邮箱" prop="membersEmail">
          <el-input
            v-model="form.membersEmail"
            placeholder="请输入教职工邮箱"
          />
        </el-form-item>
        <!-- <el-form-item label="教职工行政职务" prop="membersAdministrative">
          <el-input
            v-model="form.membersAdministrative"
            placeholder="请输入教职工行政职务"
          />
        </el-form-item> -->
        <el-form-item label="教职工配偶姓名" prop="membersSpouseName">
          <el-input
            v-model="form.membersSpouseName"
            placeholder="请输入教职工配偶姓名"
          />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMembers,
  getMembers,
  delMembers,
  addMembers,
  updateMembers,
} from "@/api/members/teaching";
export default {
  dicts: [
    "sys_marital",
    "sys_user_sex",
    "sys_mode_employment",
    "sys_members_status",
    "sys_background",
    "sys_nation",
  ],
  name: "Members",
  data() {
    return {
      fileList: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 会员表格数据
      membersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        membersName: null,
        membersNum: null,
        membersSex: null,
        membersBir: null,
        membersBackground: null,
        membersNation: null,
        membersPolitical: null,
        membersSchoolDate: null,
        membersTel: null,
        deptId: null,
        societyId: null,
        membersMaritalStatus: null,
        membersSpouseCampusStaff: null,
        membersNativePlace: null,
        membersAddress: null,
        school: null,
        membersModeEmployment: null,
        membersResume: null,
        membersMainFamily: null,
        membersHobby: null,
        membersRemarks: null,
        membersImg: null,
        membersSign: null,
        membersContactNumber: null,
        membersAdmissionTime: null,
        membersStatus: null,
        membersAge: null,
        membersIsStatus: null,
        membersNationality: null,
        membersIdentityType: null,
        membersIdentityNum: null,
        membersTechnical: null,
        membersProfessional: null,
        membersEmail: null,
        membersAdministrative: null,
        membersSpouseName: null,
        membersHouseholdType: null,
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
        membersName: [
          { required: true, message: "教职工姓名不能为空", trigger: "blur" },
        ],
        membersNum: [
          { required: true, message: "教职工工号不能为空", trigger: "blur" },
        ],
        membersSex: [
          { required: true, message: "教职工性别不能为空", trigger: "change" },
        ],
        membersBir: [
          { required: true, message: "教职工出生日期不能为空", trigger: "blur" },
        ],
        membersSchoolDate: [
          { required: true, message: "教职工来校时间不能为空", trigger: "blur" },
        ],
        membersTel: [
          { required: true, message: "教职工联系电话不能为空", trigger: "blur" },
        ],
        membersSpouseCampusStaff: [
          { required: true, message: "配偶是否为校内职工不能为空", trigger: "blur" },
        ],
        school: [
          { required: true, message: "教职工所在校区不能为空", trigger: "blur" },
        ],
        membersModeEmployment: [
          { required: true, message: "教职工用人方式不能为空", trigger: "blur" },
        ],
        membersImg: [
          { required: true, message: "教职工个人照片不能为空", trigger: "blur" },
        ],
        membersAdmissionTime: [
          { required: true, message: "教职工人员状态不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handlerSuccess(response, file, fileList){
      this.form.membersImg = response.url
       
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    /** 查询会员列表 */
    getList() {
      this.loading = true;
      listMembers(this.queryParams).then((response) => {
        this.membersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        membersId: null,
        userId: null,
        membersName: null,
        membersNum: null,
        membersSex: null,
        membersBir: null,
        membersBackground: null,
        membersNation: null,
        membersPolitical: null,
        membersSchoolDate: null,
        membersTel: null,
        deptId: null,
        societyId: null,
        membersMaritalStatus: null,
        membersSpouseCampusStaff: '0',
        membersNativePlace: null,
        membersAddress: null,
        school: null,
        membersModeEmployment: null,
        membersResume: null,
        membersMainFamily: null,
        membersHobby: null,
        membersRemarks: null,
        membersImg: null,
        membersSign: null,
        membersContactNumber: null,
        membersAdmissionTime: null,
        membersStatus: null,
        membersAge: null,
        membersIsStatus: null,
        membersNationality: null,
        membersIdentityType: null,
        membersIdentityNum: null,
        membersTechnical: null,
        createTime: null,
        membersProfessional: null,
        membersEmail: null,
        membersAdministrative: null,
        membersSpouseName: null,
        membersHouseholdType: null,
        createBy: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
      };
      this.fileList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.membersId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
        this.form.membersSpouseCampusStaff = '0';
      this.open = true;
      this.title = "添加会员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const membersId = row.membersId || this.ids;
      getMembers(membersId).then((response) => {
        this.form = response.data;
        this.form.membersModeEmployment = parseInt(this.form.membersModeEmployment);
        this.form.membersAdmissionTime = parseInt(this.form.membersAdmissionTime);
        this.fileList = [{ url: this.form.membersImg , name: "个人照片"}];
        this.open = true;
        this.title = "修改会员";
      });
    },
    /** 提交按钮 */
    submitForm() {

      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.membersId != null) {
            updateMembers(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMembers(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const membersIds = row.membersId || this.ids;
      this.$modal
        .confirm('是否确认删除会员编号为"' + membersIds + '"的数据项？')
        .then(function () {
          return delMembers(membersIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/members/teaching/export",
        {
          ...this.queryParams,
        },
        `教职工数据统计表_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
