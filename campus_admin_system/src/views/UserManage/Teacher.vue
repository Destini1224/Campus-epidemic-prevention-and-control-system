<template>
  <div class="news">
    <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>老师列表管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索区域 -->
    <div class="student-search" style="padding:0 0 10px 0;">
      <el-input style="width:250px;" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name" clearable @change="load">
      </el-input>
      <el-input style="width:250px;margin-left:5px;" placeholder="请输入地址" suffix-icon="el-icon-edit" v-model="address" clearable @change="load">
      </el-input>
      <el-button type="primary" @click="handleAdd" style="margin-left:5px;">新增<i style="margin-left:2px;" class="el-icon-circle-plus-outline"></i></el-button>
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
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="tel" label="电话">
      </el-table-column>
      <el-table-column prop="email" label="邮箱">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template slot-scope="scope">
          <el-button type="warning" @click="handleEdit(scope.row)">编辑<i style="margin-left:2px;" class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference" style="margin-left:5px;">删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹窗区域 -->
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
      <el-form :model="addForm" :rules="rules" ref="addForm" label-width="70px" size="medium">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" auto-complete="false" v-model="addForm.password" placeholder="请输入需更改的密码（无需更改请填写旧密码）">
          </el-input>
        </el-form-item>
        <el-form-item label=" 姓名" prop="name">
          <el-input type=" text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名">
          </el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input type=" text" auto-complete="false" v-model="addForm.tel" placeholder="请输入电话">
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input type=" text" auto-complete="false" v-model="addForm.email" placeholder="请输入邮箱">
          </el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input type=" text" auto-complete="false" v-model="addForm.address" placeholder="请输入地址">
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
    <div style=" padding: 10px 0;">
      <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'Teacher',
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      title: '',
      id: 0,
      name: '',
      address: '',
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        username: '',
        password: '',
        name: '',
        tel: '',
        email: '',
        address: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入需更改的密码（无需更改请填写旧密码）', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        tel: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的电话', trigger: 'blur' }
        ],
        email: [{ pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/, message: '请输入正确的邮箱', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    // 获取列表信息
    load() {
      // request.get(`http://localhost:9090/student/page?pageNum=${this.pageNum}&pageSize=${this.pageSize}`).then(res => {
      //   this.tableData = res.data
      //   this.total = res.total
      // })
      request
        .get('/teacher/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name,
            address: this.address
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
          request.post('/teacher', this.addForm).then(res => {
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
    resetForm(formName) {
      document.getElementById('resetFormButton').blur()
      if (this.title === '新增') {
        this.$refs[formName].resetFields()
      } else {
        this.getInfo()
      }
    },
    // 新增按钮点击事件
    handleAdd() {
      this.title = '新增'
      this.dialogFormVisible = true
      this.addForm = { username: '', password: '', name: '', tel: '', email: '', address: '' }
    },
    // 获得某条信息
    getInfo() {
      request.get('/teacher/' + this.id).then(res => {
        this.$nextTick(() => {
          this.addForm = { ...res[0] }
          this.addForm.password = ''
        })
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      this.title = '编辑'
      this.id = row.id
      this.getInfo()
      this.dialogFormVisible = true
    },
    // 删除按钮点击事件
    del(id) {
      request.delete('/teacher/' + id).then(res => {
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
      request.post('/teacher/del/batch', ids).then(res => {
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
