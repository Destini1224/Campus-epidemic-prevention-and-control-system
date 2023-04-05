<template>
  <div class="box">
    <div class="container">
      <div class="brand">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item><i class="el-icon-location" style="padding-right: 5px;"></i>首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>请假申请</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div>
        <div class="aside">
          <SideMenu></SideMenu>
        </div>
        <el-main class="main">
          <!-- 主体数据展示区域 -->
          <el-button type="primary" @click="handleAdd" style="margin:20px 0px 5px 20px;">申请<i style="margin-left:2px;" class="el-icon-circle-plus-outline"></i></el-button>
          <el-table :data="tableData" border stripe style="width: 97%;margin:20px 0 20px 20px;">
            <!-- <el-table-column prop="id" label="ID" width="80"></el-table-column> -->
            <el-table-column type="index" label="序号" width="80">
            </el-table-column>
            <el-table-column prop="leave_type" label="请假类型" width="80">
            </el-table-column>
            <el-table-column prop="start" label="开始时间" width="105">
            </el-table-column>
            <el-table-column prop="end" label="结束时间" width="105">
            </el-table-column>
            <el-table-column prop="reason" label="请假事由" width="180">
            </el-table-column>
            <el-table-column prop="leave_yn" label="是否离校" width="105">
              <template slot-scope="scope">
                {{ scope.row.leave_yn === '0' ? '否' : '是' }}
              </template>
            </el-table-column>
            <el-table-column prop="emergency_tel" label="紧急联系人电话" width="112">
            </el-table-column>
            <el-table-column prop="remarks" label="备注说明">
            </el-table-column>
            <el-table-column fixed="right" prop="status" label="审批结果" width="112">
              <template slot-scope="scope">
                <div slot="reference" class="name-wrapper">
                  <el-tag v-if="scope.row.status === '0'" type="warning">未审批</el-tag>
                  <el-tag v-else-if="scope.row.status === '1'" type="success">审批通过</el-tag>
                  <el-tag v-else-if="scope.row.status === '2'" type="danger">审批未通过</el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column fixed="right" prop="status" label="审批状态">
              <template slot-scope="scope">
                <div slot="reference" class="name-wrapper">
                  <el-tag :type="scope.row.status === '0' ? 'warning' : 'success'">{{ scope.row.status === '0' ? '未审批' : '已审批' }}</el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="180">
              <template slot-scope="scope">
                <!-- <el-button type="warning" @click="handleEdit(scope.row.id)" :disabled="scope.row.status === '0' ? false : true">编辑<i style="margin-left:2px;" class="el-icon-edit"></i></el-button> -->
                <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定取消吗？" @confirm="del(scope.row.id)">
                  <el-button type="danger" slot="reference" style="margin-left:5px;" :disabled="scope.row.status === '0' ? false : true">取消申请<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
          <!-- 新增弹窗区域 -->
          <el-dialog title="请假申请" :visible.sync="dialogFormVisible" width="32%" top="6vh" center>
            <el-form :model="addForm" :rules="rules" ref="addForm" label-width="120px" size="medium">
              <el-form-item label="用户名" prop="username">
                <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名" disabled>
                </el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input type="text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名">
                </el-input>
              </el-form-item>
              <el-form-item label="请假类型" prop="leave_type">
                <el-select v-model="addForm.leave_type" placeholder="请选择请假类型">
                  <el-option label="事假" value="事假"></el-option>
                  <el-option label="病假" value="病假"></el-option>
                  <el-option label="实习" value="实习"></el-option>
                  <el-option label="丧假" value="丧假"></el-option>
                  <el-option label="婚假" value="婚假"></el-option>
                  <el-option label="探亲访友" value="探亲访友"></el-option>
                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="开始时间" prop="start">
                <el-date-picker type="datetime" format="yyyy 年 MM 月 dd 日 HH时mm分ss秒" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" v-model="addForm.start" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="结束时间" prop="end">
                <el-date-picker type="datetime" format="yyyy 年 MM 月 dd 日 HH时mm分ss秒" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" v-model="addForm.end" style="width: 100%;"></el-date-picker>
              </el-form-item>
              <el-form-item label="请假事由" prop="reason">
                <el-input type="text" auto-complete="false" v-model="addForm.reason" placeholder="请填写请假事由">
                </el-input>
              </el-form-item>
              <el-form-item label="是否离校" prop="leave_yn">
                <el-select v-model="addForm.leave_yn" placeholder="请选择是否离校">
                  <el-option label="是" value="1"></el-option>
                  <el-option label="否" value="0"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="紧急联系人电话" prop="emergency_tel">
                <el-input type="text" auto-complete="false" v-model="addForm.emergency_tel" placeholder="请输入紧急联系人电话">
                </el-input>
              </el-form-item>
              <el-form-item label="备注说明" prop="remarks">
                <el-input type="textarea" :rows="2" auto-complete="false" v-model="addForm.remarks" placeholder="备注说明">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
            </div>
          </el-dialog>
          <!-- 分页区域 -->
          <div style="display: flex;align-items: center;justify-content: center;">
            <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
            </el-pagination>
          </div>
        </el-main>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
import SideMenu from '@/components/SideMenu/StuSideMenu.vue'
export default {
  name: 'StudentLeave',
  components: {
    SideMenu
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: '',
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        username: '',
        name: '',
        leave_type: '',
        start: '',
        end: '',
        reason: '',
        leave_yn: '',
        emergency_tel: '',
        remarks: '',
        status: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        leave_type: [{ required: true, message: '请选择请假类型', trigger: 'change' }],
        start: [{ required: true, message: '请选择开始时间', trigger: 'change' }],
        end: [{ required: true, message: '请选择结束时间', trigger: 'change' }],
        reason: [{ required: true, message: '请填写请假事由', trigger: 'blur' }],
        leave_yn: [{ required: true, message: '请选择是否离校', trigger: 'change' }],
        emergency_tel: [{ required: true, message: '请输入紧急联系人电话', trigger: 'blur' }]
      }
    }
  },
  created() {
    // username的信息从保存在客户端中的信息中获取
    this.username = JSON.parse(window.localStorage.getItem('username'))
    this.load()
  },
  methods: {
    // 获取列表信息
    load() {
      request
        .get('/leave/leavePage', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            username: this.username
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
          request.post('/leave', this.addForm).then(res => {
            if (res) {
              this.$message.success('申请成功')
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error('申请失败')
            }
          })
        } else {
          this.$message.error('信息不全或有误，请认真填写')
          return false
        }
      })
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 新增按钮点击事件
    handleAdd() {
      this.dialogFormVisible = true
      this.addForm = { username: this.username, name: '', leave_type: '', start: '', end: '', reason: '', leave: '', emergency_tel: '', remarks: '', status: '' }
    },
    // // 审批按钮点击事件
    // handleEdit(id) {
    //   request.post('/leave/' + id).then(res => {
    //     if (res) {
    //       this.$message.success('审批成功')
    //       this.load()
    //     } else {
    //       this.$message.error('审批失败')
    //     }
    //   })
    // },
    // 删除按钮点击事件
    del(id) {
      request.delete('/leave/' + id).then(res => {
        if (res) {
          this.$message.success('删除成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('删除失败')
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
.box {
  background-color: #eee;
}

.container {
  margin: 0px 60px 10px 60px;
}

.brand {
  font-size: 16px;
  margin: 20px 0 20px 0;
}

.aside {
  height: 100vh;
  float: left;
  margin-right: 10px;
}

.main {
  height: 100vh;
  background-color: #fff;
  padding: 0;
}

.el-select {
  width: 100%;
}
</style>
