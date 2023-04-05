<template>
  <el-menu router unique-opened :default-active="this.$route.path" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <el-menu-item index="/index">
      <template slot="title">
        <i class="el-icon-s-home"></i>
        <span>首页</span>
      </template>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item index="/articles">
        <template slot="title">
          <i class="el-icon-s-tools"></i>
          <span>文章管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/articlesManage">
        <template slot="title">
          <i class="el-icon-s-tools"></i>
          <span>发布文章</span>
        </template>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </template>
      <el-menu-item index="/student">
        <template slot="title">
          <i class="el-icon-user-solid"></i>
          <span>学生列表管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/teacher">
        <template slot="title">
          <i class="el-icon-user-solid"></i>
          <span>教师列表管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/admin">
        <template slot="title">
          <i class="el-icon-user-solid"></i>
          <span>管理员列表管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-document"></i>
        <span slot="title">健康管理</span>
      </template>
      <el-menu-item index="/healthStu">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span>学生健康管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/healthTea">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span>教师健康管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/confirmed">
        <template slot="title">
          <i class="el-icon-document-checked"></i>
          <span>确诊信息管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span slot="title">请假管理</span>
      </template>
      <el-menu-item index="/departure">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>离校申请管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/back">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>返校申请管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/leave">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>请假申请管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-shopping-cart-full"></i>
        <span slot="title">防疫物资管理</span>
      </template>
      <el-menu-item index="/materials">
        <template slot="title">
          <i class="el-icon-s-goods"></i>
          <span>物资库存管理</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/application">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>物资申请管理</span>
        </template>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu'
}
</script>

<style lang="less" scoped>
.el-menu-vertical-demo {
  height: 100%;
}

.el-submenu .el-menu-item {
  min-width: 182px;
}

.el-menu {
  border-right: 0;
}
</style>
