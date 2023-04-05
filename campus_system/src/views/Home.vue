<template>
  <el-container>
    <!-- 头部区域 -->
    <el-header class="clearfix">
      <div class="left">欢迎访问校园疫情防控系统平台网站！
      </div>
      <div class="right">
        <el-avatar size="medium" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        <el-dropdown trigger="click" @command="handleCommand">
          <span class="el-dropdown-link">
            {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
            <el-dropdown-item command="out">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <!-- 导航区域 -->
    <div class="nav clearfix">
      <div class="left">
        <a href="#/index">
          <img src="@/assets/logo.jpg" alt="校园疫情防控系统" />
          <h3>校园疫情防控系统</h3>
        </a>
      </div>
      <div class="right">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/index">网站首页</el-menu-item>
          <el-menu-item index="/news">疫情新闻</el-menu-item>
          <el-menu-item index="/notices">通知通告</el-menu-item>
          <el-menu-item index="/campus">校园动态</el-menu-item>
          <el-menu-item index="/media">媒体新闻</el-menu-item>
        </el-menu>
      </div>
    </div>
    <router-view></router-view>
  </el-container>
</template>

<script>
export default {
  name: 'Home',
  components: {},
  data() {
    return {
      username: '',
      type: '',
      activeIndex: '1'
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
      this.type = JSON.parse(window.localStorage.getItem('type'))
      if (this.type === '学生') {
        this.$router.push('/studentInfo')
      } else if (this.type === '教师') {
        this.$router.push('/teacherInfo')
      }
    }
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: #eee;
  color: #333;
  text-align: center;
  line-height: 60px;

  .left {
    float: left;
    margin-left: 50px;
  }

  .right {
    float: right;
    width: 160px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin-right: 50px;

    .el-dropdown-link {
      cursor: pointer;
      color: #333;
    }
    .el-icon-arrow-down {
      font-size: 12px;
    }
  }
}
.nav {
  // border-bottom: solid 1px #e6e6e6;
  margin: 10px 10px 0 10px;
  .left {
    margin-left: 60px;
    float: left;
    text-align: center;
    img {
      width: 60px;
      height: 60px;
      vertical-align: -24px;
      padding-right: 10px;
    }
    h3 {
      display: inline;
      font-weight: normal;
      color: #333;
    }
  }
  .right {
    float: right;
    margin-right: 60px;
    .el-menu.el-menu--horizontal {
      border-bottom: 0px;
      .el-menu-item {
        height: 70px;
      }
    }
  }
}

// 媒体查询
@media screen and (max-width: 940px) {
  .el-header {
    background-color: #eee;
    color: #333;
    text-align: center;
    line-height: 60px;

    .left {
      float: left;
      margin-left: 25px;
    }

    .right {
      float: right;
      width: 130px;
      display: flex;
      justify-content: space-around;
      align-items: center;
      margin-right: 25px;

      .el-dropdown-link {
        cursor: pointer;
        color: #333;
      }
      .el-icon-arrow-down {
        font-size: 12px;
      }
    }
  }
  .nav {
    // border-bottom: solid 1px #e6e6e6;
    height: 60px;
    // margin: 5px;
    .left {
      height: 60px;
      padding-top: 5px;
      margin-left: 30px;
      float: left;
      text-align: center;
      img {
        width: 45px;
        height: 45px;
        vertical-align: -15px;
        padding-right: 10px;
      }
      h3 {
        display: inline;
        font-weight: normal;
        color: #333;
        font-size: 17px;
      }
    }
    .right {
      float: right;
      margin-right: 30px;
      .el-menu.el-menu--horizontal {
        border-bottom: 0px;
        .el-menu-item {
          height: 60px;
        }
      }
    }
  }
}

.clearfix::after {
  display: block;
  content: '';
  clear: both;
}
</style>
