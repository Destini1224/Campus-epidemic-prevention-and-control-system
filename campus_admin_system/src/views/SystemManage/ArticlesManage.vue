<template>
  <div class="news">
    <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>发布文章</el-breadcrumb-item>
    </el-breadcrumb>
    <div style="width:900px;">
      <el-form :model="addForm" :rules="rules" ref="addForm" label-width="90px" size="medium">
        <el-form-item prop="title" label="文章标题">
          <el-input type="text" auto-complete="false" v-model="addForm.title" placeholder="请填写文章标题">
          </el-input>
        </el-form-item>
        <el-form-item label="文章作者" prop="pub_author">
          <el-input type="text" auto-complete="false" v-model="addForm.pub_author" placeholder="请输入文章作者">
          </el-input>
        </el-form-item>
        <el-form-item label="文章内容" prop="pub_content">
          <el-input type="textarea" :rows="15" auto-complete="false" v-model="addForm.pub_content" placeholder="请输入文章内容或相关链接">
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
        <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'ArticlesManage',
  data() {
    return {
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
  methods: {
    // 提交表单或修改表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          request.post('/articles', this.addForm).then(res => {
            if (res) {
              this.$message.success('发布成功')
              this.addForm = { title: '', pub_time: '', pub_author: '', pub_content: '', pub_type: '' }
            } else {
              this.$message.error('发布失败')
            }
          })
        } else {
          this.$message.error('出现错误，无法发布')
          return false
        }
      })
    },
    // 重置表单
    resetForm(formName) {
      document.getElementById('resetFormButton').blur()
      this.$refs[formName].resetFields()
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

.el-select {
  width: 100%;
}
</style>
