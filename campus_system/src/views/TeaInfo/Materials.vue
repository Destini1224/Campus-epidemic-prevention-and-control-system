<template>
  <div class="box">
    <div class="container">
      <div class="brand">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item><i class="el-icon-location" style="padding-right: 5px;"></i>首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>确诊信息</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div>
        <div class="aside">
          <SideMenu></SideMenu>
        </div>
        <el-main class="main">
          <!-- 主体数据展示区域 -->
          <!-- 搜索区域 -->
          <div class="student-search" style="padding:20px 0px 5px 20px;">
            <el-input style="width:250px;" placeholder="请输入物资名称" suffix-icon="el-icon-search" v-model="goods_name" clearable @change="load">
            </el-input>
            <el-button type="primary" @click="handleAdd" style="margin-left:5px;">新增<i style="margin-left:2px;" class="el-icon-circle-plus-outline"></i></el-button>
            <!-- <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch()">
              <el-button type="danger" slot="reference" style="margin-left:5px;">批量删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm> -->
          </div>
          <!-- 主体数据展示区域 -->
          <el-table :data="tableData" border stripe style="width: 97%;margin:20px 0 20px 20px;">
            <!-- <el-table-column prop="id" label="ID" width="80"></el-table-column> -->
            <el-table-column type="index" label="序号" width="80">
            </el-table-column>
            <el-table-column prop="goods_name" label="物资名称">
            </el-table-column>
            <el-table-column prop="storage" label="库存量(份)">
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
          <el-dialog title="物资信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
            <el-form :model="addForm" :rules="rules" ref="addForm" label-width="100px" size="medium">
              <el-form-item label="物资名称" prop="goods_name">
                <el-input type="text" auto-complete="false" v-model="addForm.goods_name" placeholder="请输入物资名称">
                </el-input>
              </el-form-item>
              <el-form-item label="库存量(?/份)" prop="storage">
                <el-input type=" text" auto-complete="false" v-model="addForm.storage" placeholder="请输入库存量">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button id="resetFormButton" @click="resetForm('addForm')">重置</el-button>
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="submitForm('addForm')">确 定</el-button>
            </div>
          </el-dialog>
          <!-- 分页区域 -->
          <div style="display: flex;align-items: center;justify-content: center;">
            <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
            </el-pagination>
          </div>
        </el-main>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
import SideMenu from '@/components/SideMenu/TeaSideMenu.vue'
export default {
  name: 'Materials',
  components: {
    SideMenu
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      id: 0,
      title: '',
      goods_name: '',
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        goods_name: '',
        storage: ''
      },
      rules: {
        goods_name: [{ required: true, message: '请输入物资名称', trigger: 'blur' }],
        storage: [{ required: true, message: '请输入库存量', trigger: 'blur' }]
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
        .get('/materials/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            goods_name: this.goods_name
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
          request.post('/materials', this.addForm).then(res => {
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
    resetForm(formName) {
      document.getElementById('resetFormButton').blur()
      if (this.title === '新增') {
        this.$refs[formName].resetFields()
      } else {
        this.getInfo()
      }
    },
    // 新增按钮点击事件
    handleAdd() {
      this.title = '新增'
      this.dialogFormVisible = true
      this.addForm = { username: '', password: '', name: '', stuClass: '', dormitory: '', tel: '', email: '', address: '' }
    },
    // 获得某条信息
    getInfo() {
      request.get('/materials/' + this.id).then(res => {
        this.$nextTick(() => {
          this.addForm = { ...res[0] }
        })
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      this.title = '编辑'
      this.id = row.id
      this.getInfo()
      this.dialogFormVisible = true
    },
    // 删除按钮点击事件
    del(id) {
      request.delete('/materials/' + id).then(res => {
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
      request.post('/materials/del/batch', ids).then(res => {
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
.box {
  background-color: #eee;
}

.container {
  margin: 0px 60px 10px 60px;
}

.brand {
  font-size: 16px;
  margin: 20px 0 20px 0;
}

.aside {
  height: 100vh;
  float: left;
  margin-right: 10px;
}

.main {
  height: 100vh;
  background-color: #fff;
  padding: 0;
}

.el-select {
  width: 100%;
}
</style>
