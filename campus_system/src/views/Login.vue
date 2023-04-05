<template>
  <div class="login clearfix">
    <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="80px" class="loginContainer clearfix">
      <h2 class="loginTitle">校园疫情防控系统</h2>
      <el-form-item label="账号类型" prop="type" style="width:380px;">
        <el-select v-model="loginForm.type" placeholder="请选择账号类型" style="width:100%;">
          <el-option label="学生" value="学生"></el-option>
          <el-option label="教师" value="教师"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名" prop="username" style="width:380px;">
        <el-input type="text" auto-complete="false" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" style="width:380px;">
        <el-input type="password" auto-complete="false" v-model="loginForm.password" placeholder="请输入密码">
        </el-input>
      </el-form-item>

      <el-form-item label="验证码" prop="code" style="width:380px;">
        <el-input type="text" auto-complete="false" v-model="loginForm.code" placeholder="点击图片更换验证码"></el-input>
        <el-image :src="captchaUrl" class="captchaImg"></el-image>
        <a href="javascript:;" @click="getImage" style="display: inline-block;">换一张</a>
      </el-form-item>
      <!-- <el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox> -->
      <el-form-item class="button">
        <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
        <el-button @click="resetForm('loginForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import request from '@/utils/request'
export default {
  name: 'Login',
  data() {
    return {
      // 验证码
      captchaUrl: '',
      loginForm: {
        username: '',
        password: '',
        type: '',
        code: '',
        // 后端返回
        token: ''
      },
      // checked: false,
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        type: [{ required: true, message: '请选择账号类型', trigger: 'change' }],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { min: 4, max: 4, message: '长度在 4 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // 获取验证码
    this.getSrc()
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let _this = this
          if (this.loginForm.type === '学生') {
            request.get('/student/login', { params: _this.loginForm }).then(function (response) {
              if (response.data != null) {
                // 将token信息保存在本地客户端
                localStorage.setItem('type', JSON.stringify('学生'))
                localStorage.setItem('username', JSON.stringify(response.data.username))
                localStorage.setItem('token', JSON.stringify(response.data.token))
                _this.$router.push('/home')
              } else {
                _this.$message.error('登录失败，' + response.message)
              }
            })
          } else if (this.loginForm.type === '教师') {
            request.get('/teacher/login', { params: _this.loginForm }).then(function (response) {
              if (response.data != null) {
                // 将token信息保存在本地客户端
                localStorage.setItem('type', JSON.stringify('教师'))
                localStorage.setItem('username', JSON.stringify(response.data.username))
                localStorage.setItem('token', JSON.stringify(response.data.token))
                _this.$router.push('/home')
              } else {
                _this.$message.error('登录失败，' + response.message)
              }
            })
          }
        } else {
          this.$message.error('error submit!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 用来更换验证码
    getImage() {
      this.getSrc()
    },
    // 获取验证码，代码复用，便于调用
    getSrc() {
      let _this = this
      // console.log("xxxx");
      // 异步请求：请求验证码图片
      request.get('/user/getImage?time=' + Math.random()).then(res => {
        // 把图片赋给url属性
        _this.captchaUrl = res
      })
    }
  }
}
</script>

<style lang="less" scoped>
.login {
  display: flex;
  align-items: center;
  justify-content: center;
}
.loginContainer {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  margin: 125px auto;
  padding: 15px 35px 15px 35px;
  background: #fff;
  background-clip: padding-box;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 15px auto 30px auto;
  text-align: center;
}
// .loginRemember {
//   text-align: left;
//   margin: 0px 0px 15px 0px;
// }

.captchaImg {
  width: 180px;
  margin: 10px 2px 0 0;
}

.el-form-item.button {
  margin-right: 65px;
}

.clearfix::after {
  content: '';
  display: block;
  clear: both;
}
</style>
