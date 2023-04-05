<template>
  <div class="news">
    <div class="container clearfix">
      <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
        <el-breadcrumb-item><i class="el-icon-location"></i>首页</el-breadcrumb-item>
        <el-breadcrumb-item>疫情新闻</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="body">
        <div v-for="(item,index) in listData" :key="index" class="item">
          <a :href="item.pub_content" target="_blank">{{ item.title }}</a>
          <span>发布者：{{ item.pub_author }} 发布日期：{{ item.pub_time }}</span>
        </div>
      </div>
      <!-- 分页区域 -->
      <div class="pagination">
        <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'News',
  data() {
    return {
      total: 0,
      pageNum: 1,
      pageSize: 10,
      pub_type: '',
      listData: []
    }
  },
  created() {
    // this.load()
    this.loadNews()
  },
  methods: {
    // 获取疫情新闻列表信息
    loadNews() {
      this.pub_type = '疫情新闻'
      request
        .get('/articles/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            pub_type: this.pub_type
          }
        })
        .then(res => {
          this.listData = res.data
          this.total = res.total
        })
    },
    // 更改每页条数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.loadNews()
    },
    // 更改页码
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.loadNews()
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #eee;
  padding: 0 20px;
}

.brand {
  font-size: 16px;
  padding: 20px 100px 20px 60px;
}

.brand i {
  padding-right: 5px;
}

.body {
  background-color: #fff;
  width: 80%;
  margin: 0px 200px 20px 60px;
}

.clearfix::after {
  display: block;
  content: '';
  clear: both;
}

.body a {
  display: block;
  text-decoration: none;
  color: #000;
  font-weight: 700;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.item {
  padding: 20px 0 18px 10px;
}

.body span {
  font-size: 14px;
  color: #bbb;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0;
}
</style>
