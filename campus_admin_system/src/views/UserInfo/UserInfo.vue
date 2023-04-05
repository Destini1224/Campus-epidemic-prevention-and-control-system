<template>
  <div class="index">
    <el-descriptions class="margin-top" :column="1" :size="size" border>
      <template slot="title">个人中心
      </template>
      <template slot="extra">
        <el-button type="primary" size="small" @click="handleEdit()">修改密码</el-button>
      </template>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{username}}
      </el-descriptions-item>
    </el-descriptions>

    <!-- 新增弹窗区域 -->
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
      <el-form :model="addForm" :rules="rules" ref="addForm" label-width="70px" size="medium">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名" disabled>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" auto-complete="false" v-model="addForm.password" placeholder="请填写你要修改的密码">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'UserInfro',
  data() {
    return {
      username: '',
      password: '',
      size: '',
      dialogFormVisible: false,
      addForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入您要修改的密码', trigger: 'blur' }]
      }
    }
  },
  created() {
    // username的信息从保存在客户端中的信息中获取
    this.username = JSON.parse(window.localStorage.getItem('username'))
  },
  methods: {
    // 提交表单或修改表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let _this = this
          request.post(`/admin/username?username=${this.addForm.username}&password=${this.addForm.password}`).then(res => {
            if (res) {
              _this.$message.success('修改成功')
              _this.dialogFormVisible = false
            } else {
              _this.$message.error('修改失败')
            }
          })
        } else {
          let _this = this
          _this.$message.error('信息不全或有误，请认真填写')
          return false
        }
      })
    },
    // 重置表单
    resetForm(formName) {
      document.getElementById('resetFormButton').blur()
      this.$refs[formName].resetFields()
    },
    // 编辑按钮点击事件
    handleEdit() {
      this.addForm.username = this.username
      this.dialogFormVisible = true
    }
  }
}
</script>

<style lang="less" scoped>
</style>
