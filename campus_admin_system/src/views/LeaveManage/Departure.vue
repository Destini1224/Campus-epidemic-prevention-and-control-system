<template>
  <div class="news">
    <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>请假管理</el-breadcrumb-item>
      <el-breadcrumb-item>离校申请管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索区域 -->
    <div class="student-search" style="padding:0 0 10px 0;">
      <el-input style="width:250px;" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name" clearable @change="load">
      </el-input>
      <el-select style="width:250px;margin-left:5px;" v-model="status" clearable placeholder="请选择审批状态" @change="load">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <!-- <el-button type="primary" @click="handleAdd" style="margin-left:5px;">新增<i style="margin-left:2px;" class="el-icon-circle-plus-outline"></i></el-button> -->
      <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch()">
        <el-button type="danger" slot="reference" style="margin-left:5px;">批量删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <!-- 主体数据展示区域 -->
    <el-table :data="tableData" border stripe style="width: 100%;height: 100%;" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <!-- <el-table-column prop="id" label="ID" width="80"></el-table-column> -->
      <el-table-column type="index" label="序号" width="50">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="112">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="70">
      </el-table-column>
      <el-table-column prop="plan_leave" label="计划离校时间" width="105">
      </el-table-column>
      <el-table-column prop="plan_reach" label="计划到达时间" width="105">
      </el-table-column>
      <el-table-column prop="destination" label="目的地" width="105">
      </el-table-column>
      <el-table-column prop="emergency_tel" label="紧急联系人电话" width="112">
      </el-table-column>
      <el-table-column prop="plan_back" label="计划返校时间" width="105">
      </el-table-column>
      <el-table-column prop="evidences" label="学校要求的返校证明材料">
      </el-table-column>
      <el-table-column prop="remarks" label="备注说明">
      </el-table-column>
      <el-table-column fixed="right" prop="status" label="审批结果" width="112">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag v-if="scope.row.status === '0'" type="danger">未审批</el-tag>
            <el-tag v-else-if="scope.row.status === '1'" type="success">审批通过</el-tag>
            <el-tag v-else-if="scope.row.status === '2'" type="warning">审批未通过</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" prop="status" label="审批状态">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag :type="scope.row.status === '0' ? 'danger' : 'success'">{{ scope.row.status === '0' ? '未审批' : '已审批' }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="260">
        <template slot-scope="scope">
          <el-button type="warning" @click="handleEdit(scope.row)" :disabled="scope.row.status === '0' ? false : true">审批<i style="margin-left:2px;" class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference" style="margin-left:5px;">删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹窗区域 -->
    <el-dialog title="离校申请审批" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
      <el-form :model="addForm" :rules="rules" ref="addForm" label-width="80px" size="medium">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input type="text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="计划离校时间" prop="plan_leave">
          <el-date-picker type="datetime" format="yyyy 年 MM 月 dd 日 HH时mm分ss秒" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择计划离校时间" v-model="addForm.plan_leave" style="width: 100%;" disabled></el-date-picker>
        </el-form-item>
        <el-form-item label="计划到达时间" prop="plan_reach">
          <el-date-picker type="datetime" format="yyyy 年 MM 月 dd 日 HH时mm分ss秒" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择计划到达时间" v-model="addForm.plan_reach" style="width: 100%;" disabled></el-date-picker>
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input type="text" auto-complete="false" v-model="addForm.destination" placeholder="请填写目的地" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="紧急联系人电话" prop="emergency_tel">
          <el-input type="text" auto-complete="false" v-model="addForm.emergency_tel" placeholder="请输入紧急联系人电话" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="计划返校时间" prop="plan_reach">
          <el-date-picker type="datetime" format="yyyy 年 MM 月 dd 日 HH时mm分ss秒" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择计划返校时间" v-model="addForm.plan_back" style="width: 100%;" disabled></el-date-picker>
        </el-form-item>
        <el-form-item label="学校要求的返校证明材料" prop="evidences">
          <el-input type="textarea" :rows="4" auto-complete="false" v-model="addForm.evidences" placeholder="请填写学校要求的返校证明材料" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="备注说明" prop="remarks">
          <el-input type="textarea" :rows="2" auto-complete="false" v-model="addForm.remarks" placeholder="备注说明" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="审批状态" prop="status">
          <el-select v-model="addForm.status" placeholder="请选择审批状态">
            <el-option label="未审批" value="0"></el-option>
            <el-option label="审批通过" value="1"></el-option>
            <el-option label="审批未通过" value="2"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 分页区域 -->
    <div style=" padding: 10px 0;">
      <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'Departure',
  data() {
    return {
      options: [
        {
          value: '0',
          label: '未审批'
        },
        {
          value: '1',
          label: '审批通过'
        },
        {
          value: '2',
          label: '审批未通过'
        }
      ],
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      id: 0,
      name: '',
      status: '',
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        username: '',
        name: '',
        plan_leave: '',
        plan_reach: '',
        destination: '',
        emergency_tel: '',
        plan_back: '',
        evidences: '',
        remarks: '',
        status: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        plan_leave: [{ required: true, message: '请输入计划离校时间', trigger: 'blur' }],
        plan_reach: [{ required: true, message: '请输入计划到达时间', trigger: 'blur' }],
        destination: [{ required: true, message: '请填写目的地', trigger: 'blur' }],
        emergency_tel: [{ required: true, message: '请输入紧急联系人电话', trigger: 'blur' }],
        plan_back: [{ required: true, message: '请输入计划返校时间', trigger: 'blur' }],
        status: [{ required: true, message: '请选择审批状态', trigger: 'change' }]
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    // 获取列表信息
    load() {
      request
        .get('/departure/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name,
            status: this.status
          }
        })
        .then(res => {
          this.tableData = res.data
          this.total = res.total
        })
    },
    // 更改每页条数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    // 更改页码
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    // 提交表单或修改表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.addForm.status === '未审批') {
            this.addForm.status = '0'
          } else if (this.addForm.status === '审批通过') {
            this.addForm.status = '1'
          } else if (this.addForm.status === '审批未通过') {
            this.addForm.status = '2'
          }
          request.post('/departure', this.addForm).then(res => {
            if (res) {
              this.$message.success('保存成功')
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error('保存失败')
            }
          })
        } else {
          this.$message.error('信息不全或有误，请认真填写')
          return false
        }
      })
    },
    // 重置表单
    resetForm() {
      document.getElementById('resetFormButton').blur()
      this.getInfo()
    },
    // // 新增按钮点击事件
    // handleAdd() {
    //   this.dialogFormVisible = true
    //   this.addForm = { username: '', name: '', plan_leave: '', plan_reach: '', destination: '', emergency_tel: '', plan_back: '', evidences: '', remarks: '', status: '' }
    // },
    // 获得某条信息
    getInfo() {
      request.get('/departure/' + this.id).then(res => {
        this.$nextTick(() => {
          this.addForm = { ...res[0] }
        })
      })
    },
    // 审批按钮点击事件
    handleEdit(row) {
      this.id = row.id
      this.getInfo()
      this.dialogFormVisible = true
    },
    // 删除按钮点击事件
    del(id) {
      request.delete('/departure/' + id).then(res => {
        if (res) {
          this.$message.success('删除成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
    // 表格多选处理
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    // 批量删除
    delBatch() {
      let ids = this.multipleSelection.map(x => x.id)
      request.post('/departure/del/batch', ids).then(res => {
        if (res) {
          this.$message.success('批量删除成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('批量删除失败')
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.brand {
  font-size: 16px;
  margin-bottom: 20px;
}

.student-search {
  text-align: left;
}

.el-dialog__header {
  padding: 20px 20px 10px;
}

.el-dialog--center .el-dialog__body {
  padding: 20px 30px 0px;
}

.el-dialog .el-select {
  width: 100%;
}
</style>
