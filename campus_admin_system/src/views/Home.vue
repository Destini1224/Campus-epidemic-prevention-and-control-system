<template>
  <el-container>
    <el-aside width="210px">
      <SideMenu></SideMenu>
    </el-aside>
    <el-container>
      <el-header>
        <strong style="font-size:20px;">校园疫情防控后台管理系统</strong>
        <div class="header-avatar">
          <el-avatar size="medium" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          <el-dropdown trigger="click" @command="handleCommand">
            <span class="el-dropdown-link">
              {{username}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="userInfo">
                个人中心
              </el-dropdown-item>
              <el-dropdown-item command="out">
                退出
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-link href="http://localhost:8080/#/login" target="_blank">网站</el-link>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideMenu from '@/components/SideMenu/SideMenu.vue'
export default {
  name: 'Home',
  components: {
    SideMenu
  },
  data() {
    return {
      username: ''
    }
  },
  created() {
    // username的信息从保存在客户端中的信息中获取
    this.username = JSON.parse(window.localStorage.getItem('username'))
  },
  methods: {
    handleCommand(cmditem) {
      if (!cmditem) {
        this.$message('菜单选项缺少command属性')
        return
      }
      switch (cmditem) {
        case 'out':
          this.out()
          break
        case 'userInfo':
          this.userInfo()
          break
      }
    },
    out() {
      this.$router.push('/login')
    },
    userInfo() {
      this.$router.push('/userInfo')
    }
  }
}
</script>
<style lang="less" scoped>
.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}

.header-avatar {
  float: right;
  width: 170px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.el-header {
  background-color: #00bca3;
  // border-bottom: 1px solid #ddd;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  line-height: 200px;
}

.el-main {
  color: #333;
  text-align: center;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-link.is-underline:hover:after {
  border: 0;
}
</style>
