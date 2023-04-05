<template>
  <div class="index">
    <h1>系统信息</h1>
    <el-descriptions :column="1" border>
      <el-descriptions-item label="xxx版本">
        1.0.1
      </el-descriptions-item>
      <el-descriptions-item label="服务器地址">
        #
      </el-descriptions-item>
      <el-descriptions-item label="操作系统">
        Window
      </el-descriptions-item>
      <el-descriptions-item label="运行环境">
        SpringBoot+Vue
      </el-descriptions-item>
      <el-descriptions-item label="JAVA版本">
        8
      </el-descriptions-item>
      <el-descriptions-item label="运行方式">
        SpringBoot主启动类
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: 'Index'
}
</script>

<style lang="less" scoped>
h1 {
  margin-bottom: 20px;
  text-align: left;
  font-size: 18px;
  font-weight: 400;
}
</style>
