<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import '@/assets/global.css'
import '@/assets/base.css'
export default {
  name: 'MyApp'
}
</script>

<style lang="less" scoped>
html,
body,
#app {
  height: 100%;
}
</style>
