<template>
  <el-aside width="200px">
    <el-menu router unique-opened :default-active="this.$route.path" class="el-menu-vertical-demo">
      <el-menu-item index="/studentInfo">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span>个人信息</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/studentHealth">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span>健康打卡</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/studentDeparture">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>离校申请</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/studentBack">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>返校申请</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/studentLeave">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>请假申请</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/studentApplicaiton">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>物资申请</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: 'StuSideMenu'
}
</script>

<style lang="less" scoped>
.el-aside {
  height: 100vh;
  line-height: 200px;
}

.el-menu-vertical-demo {
  height: 350px;
}

.el-submenu .el-menu-item {
  min-width: 182px;
}

.el-menu {
  border-right: 0;
}

.el-icon-arrow-right {
  padding-left: 60px;
}
</style>
