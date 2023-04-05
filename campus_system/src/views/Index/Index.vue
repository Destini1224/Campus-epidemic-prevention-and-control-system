<template>
  <div class="index">
    <div class="block">
      <el-carousel height="500px" :interval="5000" arrow="never">
        <el-carousel-item v-for="(item,index) in Itemsrc" :key="index">
          <img :src="item.imgUrl">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="container">
      <el-row>
        <el-col :span="24">
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg" justify="space-between">
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>通知通告</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="handleNotices">更多<i class="el-icon-d-arrow-right"></i></el-button>
            </div>
            <div v-for="(item,index) in listData1" :key="index" class="text item">
              <a :href="item.pub_content" target="_blank">{{ item.title }}</a>
            </div>
          </el-card>

        </el-col>
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>校园动态</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="handleCampus">更多<i class="el-icon-d-arrow-right"></i></el-button>
            </div>
            <div v-for="(item,index) in listData2" :key="index" class="text item">
              <a :href="item.pub_content" target="_blank">{{ item.title }}</a>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>媒体校园</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="handleMedia">更多<i class="el-icon-d-arrow-right"></i></el-button>
            </div>
            <div v-for="(item,index) in listData3" :key="index" class="text item">
              <a :href="item.pub_content" target="_blank">{{ item.title }}</a>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="footer">
      <p>© 2022 GDUFS</p>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
import router from '@/router'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Index',
  data() {
    return {
      listData1: [],
      listData2: [],
      listData3: [],
      pub_type: '',
      Itemsrc: [{ imgUrl: require('@/assets/banner1.jpg') }, { imgUrl: require('@/assets/banner2.jpg') }, { imgUrl: require('@/assets/banner3.jpg') }, { imgUrl: require('@/assets/banner4.png') }]
    }
  },
  created() {
    this.load()
    // this.loadNotices()
    // this.loadCampus()
    // this.loadMedia()
  },
  methods: {
    // 获取通知通告列表信息
    loadNotices() {
      this.pub_type = '通知通告'
      request
        .get('/articles/types', {
          params: {
            pub_type: this.pub_type
          }
        })
        .then(res => {
          this.listData1 = res.data
        })
    },
    // 获取校园动态列表信息
    loadCampus() {
      this.pub_type = '校园动态'
      request
        .get('/articles/types', {
          params: {
            pub_type: this.pub_type
          }
        })
        .then(res => {
          this.listData2 = res.data
        })
    },
    // 获取媒体校园列表信息
    loadMedia() {
      this.pub_type = '媒体校园'
      request
        .get('/articles/types', {
          params: {
            pub_type: this.pub_type
          }
        })
        .then(res => {
          this.listData3 = res.data
        })
    },
    load() {
      Promise.resolve()
        .then(res => {
          return new Promise((resolve, reject) => {
            setTimeout(() => {
              this.loadNotices()
              resolve('第一个异步进程')
            }, 100)
          })
          // 只有当第一个then返回了promise对象才会接着调用下一个的then方法
        })
        .then(() => {
          return new Promise((resolve, reject) => {
            setTimeout(() => {
              this.loadCampus()
              resolve('第二个异步进程')
            }, 100)
          })
        })
        .then(() => {
          return new Promise((resolve, reject) => {
            setTimeout(() => {
              this.loadMedia()
              resolve('第三个异步进程')
            }, 100)
          })
        })
    },
    handleNotices() {
      router.push('/notices')
    },
    handleCampus() {
      router.push('/campus')
    },
    handleMedia() {
      router.push('/media')
    }
  }
}
</script>

<style lang="less" scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}

.box-card {
  margin: 20px 0;
  width: 95%;
}

.el-col-8 {
  display: flex;
  align-items: center;
  justify-content: center;
}

.footer {
  width: 100%;
  text-align: center;
}

img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

a {
  display: block;
  text-decoration: none;
  color: #000;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0;
}
</style>
