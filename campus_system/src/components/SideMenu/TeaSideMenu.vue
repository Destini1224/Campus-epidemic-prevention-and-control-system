<template>
  <el-aside width="200px">
    <el-menu router unique-opened :default-active="this.$route.path" class="el-menu-vertical-demo">
      <el-menu-item index="/teacherInfo">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span>个人信息</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacherHealth">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span>健康打卡</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/healthStu">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span>学生健康</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/confirmed">
        <template slot="title">
          <i class="el-icon-document-checked"></i>
          <span>确诊信息</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacherDeparture">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>离校审批</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacherBack">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>返校审批</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacherLeave">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>请假审批</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/materials">
        <template slot="title">
          <i class="el-icon-s-goods"></i>
          <span>物资库存</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacherApplicaiton">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>物资审批</span>
          <i class="el-icon-arrow-right"></i>
        </template>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script>
export default {
  name: 'TeaSideMenu'
}
</script>

<style lang="less" scoped>
.el-aside {
  height: 100vh;
  line-height: 200px;
}

.el-menu-vertical-demo {
  height: 504px;
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
