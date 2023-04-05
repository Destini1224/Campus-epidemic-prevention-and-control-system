<template>
  <div class="box">
    <div class="container">
      <div class="brand">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item><i class="el-icon-location" style="padding-right: 5px;"></i>首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>个人信息</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div>
        <div class="aside">
          <SideMenu></SideMenu>
        </div>
        <el-main class="main">
          <el-descriptions title="个人信息" :column="2" :size="size" border>
            <template slot="extra">
              <el-button type="primary" icon="el-icon-edit" @click="handleEdit()">编辑个人信息</el-button>
            </template>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
              {{ this.listData.username}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user-solid"></i>
                姓名
              </template>
              {{ this.listData.name }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-school"></i>
                专业班级
              </template>
              {{ this.listData.stuClass }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-s-home"></i>
                宿舍号
              </template>
              {{ this.listData.dormitory }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                联系电话
              </template>
              {{ this.listData.tel }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-s-promotion"></i>
                邮箱
              </template>
              {{ this.listData.email }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                居住地
              </template>
              {{ this.listData.address }}
            </el-descriptions-item>
          </el-descriptions>
          <!-- 新增弹窗区域 -->
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
            <el-form :model="addForm" :rules="rules" ref="addForm" label-width="80px" size="medium">
              <el-form-item label="用户名" prop="username">
                <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名" disabled>
                </el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" auto-complete="false" v-model="addForm.password" placeholder="请输入需更改的密码（无需更改请填写旧密码）">
                </el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input type="text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名">
                </el-input>
              </el-form-item>
              <el-form-item label="专业班级" prop="stuClass">
                <el-input type="text" auto-complete="false" v-model="addForm.stuClass" placeholder="请输入专业班级">
                </el-input>
              </el-form-item>
              <el-form-item label="宿舍号" prop="dormitory">
                <el-input type="text" auto-complete="false" v-model="addForm.dormitory" placeholder="请输入宿舍号">
                </el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="tel">
                <el-input type="text" auto-complete="false" v-model="addForm.tel" placeholder="请输入联系电话">
                </el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input type="text" auto-complete="false" v-model="addForm.email" placeholder="请输入邮箱">
                </el-input>
              </el-form-item>
              <el-form-item label="居住地" prop="address">
                <el-input type="text" auto-complete="false" v-model="addForm.address" placeholder="请输入居住地">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
            </div>
          </el-dialog>
        </el-main>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
import SideMenu from '@/components/SideMenu/StuSideMenu.vue'
export default {
  name: 'StudentInfo',
  components: {
    SideMenu
  },
  data() {
    return {
      username: '',
      password: '',
      name: '',
      stuClass: '',
      dormitory: '',
      tel: '',
      email: '',
      address: '',
      size: '',
      dialogFormVisible: false,
      listData: [],
      addForm: {
        username: '',
        password: '',
        name: '',
        stuClass: '',
        dormitory: '',
        tel: '',
        email: '',
        address: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入需更改的密码（无需更改请填写旧密码）', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        stuClass: [{ required: true, message: '请输入班级', trigger: 'blur' }],
        dormitory: [{ required: true, message: '请输入宿舍号', trigger: 'blur' }],
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
    // username的信息从保存在客户端中的信息中获取
    this.username = JSON.parse(window.localStorage.getItem('username'))
    this.getStu()
  },
  methods: {
    // 提交表单或修改表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let _this = this
          request.post('/student/username', this.addForm).then(res => {
            if (res) {
              _this.$message.success('保存成功')
              _this.dialogFormVisible = false
              this.getStu()
            } else {
              _this.$message.error('保存失败')
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
      // this.addForm.password = ''
      this.addForm.name = this.listData.name
      this.addForm.stuClass = this.listData.stuClass
      this.addForm.dormitory = this.listData.dormitory
      this.addForm.tel = this.listData.tel
      this.addForm.email = this.listData.email
      this.addForm.address = this.listData.address
      this.dialogFormVisible = true
    },
    // 获取个人信息
    getStu() {
      request
        .get('/student/username', {
          params: {
            username: this.username
          }
        })
        .then(res => {
          this.listData = res[0]
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

.el-descriptions {
  padding: 20px 20px 0 20px;
}
</style>
