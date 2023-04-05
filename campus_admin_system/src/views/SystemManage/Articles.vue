<template>
  <div class="news">
    <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>文章管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索区域 -->
    <div class="student-search" style="padding:0 0 10px 0;">
      <template>
        <el-select v-model="pub_type" clearable placeholder="请选择文章类型" @change="load">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </template>
      <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定批量删除这些文章吗？" @confirm="delBatch()">
        <el-button type="danger" slot="reference" style="margin-left:5px;">批量删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <!-- 主体数据展示区域 -->
    <el-table :data="tableData" border stripe style="width: 100%;height: 100%;" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <!-- <el-table-column prop="id" label="ID" width="90"></el-table-column> -->
      <el-table-column type="index" label="序号" width="50">
      </el-table-column>
      <el-table-column prop="title" label="文章标题">
      </el-table-column>
      <el-table-column prop="pub_time" label="发布时间">
      </el-table-column>
      <el-table-column prop="pub_author" label="文章作者">
      </el-table-column>
      <el-table-column prop="pub_content" label="文章内容">
      </el-table-column>
      <el-table-column prop="pub_type" label="文章类型">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template slot-scope="scope">
          <el-button type="warning" @click="handleEdit(scope.row)">编辑<i style="margin-left:2px;" class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference" style="margin-left:5px;">删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹窗区域 -->
    <el-dialog title="文章信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
      <el-form :model="addForm" :rules="rules" ref="addForm" label-width="80px" size="medium">
        <el-form-item prop="title" label="文章标题">
          <el-input type="text" auto-complete="false" v-model="addForm.title" placeholder="请填写文章标题">
          </el-input>
        </el-form-item>
        <el-form-item label="文章作者" prop="pub_author">
          <el-input type="text" auto-complete="false" v-model="addForm.pub_author" placeholder="请输入文章作者">
          </el-input>
        </el-form-item>
        <el-form-item label="文章内容" prop="pub_content">
          <el-input type="textarea" :rows="3" auto-complete="false" v-model="addForm.pub_content" placeholder="请输入文章内容">
          </el-input>
        </el-form-item>
        <el-form-item label="文章类型" prop="pub_type">
          <el-select v-model="addForm.pub_type" placeholder="请输入文章类型">
            <el-option label="疫情新闻" value="疫情新闻"></el-option>
            <el-option label="通知通告" value="通知通告"></el-option>
            <el-option label="媒体广外" value="媒体广外"></el-option>
            <el-option label="校园动态" value="校园动态"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button id="resetFormButton" @click="resetForm()">重置</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 分页区域 -->
    <div style=" padding: 10px 0;">
      <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'Articles',
  data() {
    return {
      options: [
        {
          value: '疫情新闻',
          label: '疫情新闻'
        },
        {
          value: '通知通告',
          label: '通知通告'
        },
        {
          value: '媒体广外',
          label: '媒体广外'
        },
        {
          value: '校园动态',
          label: '校园动态'
        }
      ],
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      pub_type: '',
      id: 0,
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        title: '',
        pub_time: '',
        pub_author: '',
        pub_content: '',
        pub_type: ''
      },
      rules: {
        title: [{ required: true, message: '请输入文章标题', trigger: 'blur' }],
        pub_author: [{ required: true, message: '请输入文章作者', trigger: 'blur' }],
        pub_content: [{ required: true, message: '请输入文章内容', trigger: 'blur' }],
        pub_type: [{ required: true, message: '请输入文章类型', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    // 获取列表信息
    load() {
      request
        .get('/articles/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            pub_type: this.pub_type
          }
        })
        .then(res => {
          this.tableData = res.data
          this.total = res.total
        })
    },
    // 更改每页条数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    // 更改页码
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    // 提交表单或修改表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          request.post('/articles', this.addForm).then(res => {
            if (res) {
              this.$message.success('保存成功')
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error('保存失败')
            }
          })
        } else {
          this.$message.error('信息不全或有误，请认真填写')
          return false
        }
      })
    },
    // 重置表单
    resetForm() {
      document.getElementById('resetFormButton').blur()
      // this.$refs[formName].resetFields()
      this.getInfo()
    },
    // // 新增按钮点击事件
    // handleAdd() {
    //   this.dialogFormVisible = true
    //   this.addForm = { title: '', pub_author: '', pub_content: '', pub_type: '' }
    // },
    // 获得某条信息
    getInfo() {
      request.get('/articles/' + this.id).then(res => {
        this.$nextTick(() => {
          this.addForm = { ...res[0] }
        })
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      this.id = row.id
      this.getInfo()
      this.dialogFormVisible = true
    },
    // 删除按钮点击事件
    del(id) {
      request.delete('/articles/' + id).then(res => {
        if (res) {
          this.$message.success('删除成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
    // 表格多选处理
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    // 批量删除
    delBatch() {
      let ids = this.multipleSelection.map(x => x.id)
      request.post('/articles/del/batch', ids).then(res => {
        if (res) {
          this.$message.success('批量删除成功')
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error('批量删除失败')
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.brand {
  font-size: 16px;
  margin-bottom: 20px;
}

.student-search {
  text-align: left;
}

.el-dialog__header {
  padding: 20px 20px 10px;
}

.el-dialog--center .el-dialog__body {
  padding: 20px 30px 0px;
}

.el-dialog .el-select {
  width: 100%;
}
</style>
