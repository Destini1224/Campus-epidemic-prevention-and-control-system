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
            <el-input style="width:250px;" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name" clearable @change="load">
            </el-input>
            <el-date-picker v-model="last_time" style="width:250px;margin-left:5px;" clearable type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd" @change="load">
            </el-date-picker>
            <!-- <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch()">
              <el-button type="danger" slot="reference" style="margin-left:5px;">批量删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm> -->
          </div>
          <!-- 主体数据展示区域 -->
          <el-table :data="tableData" border stripe style="width: 97%;margin:20px 0 20px 20px;">
            <!-- <el-table-column prop="id" label="ID" width="90"></el-table-column> -->
            <el-table-column type="index" label="序号" width="80">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="120">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="100">
            </el-table-column>
            <el-table-column prop="temperature" label="体温">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column prop="health" label="身体状况" width="120">
              <!-- <template slot-scope="scope">
          {{ scope.row.health === '0' ? '健康' : '发烧' }}
        </template> -->
            </el-table-column>
            <el-table-column prop="contact_infection" label="是否接触感染人员">
            </el-table-column>
            <el-table-column prop="remarks" label="备注说明">
            </el-table-column>
            <el-table-column prop="last_time" label="最新提交时间">
            </el-table-column>
            <!-- <el-table-column prop="u_type" label="用户类型">
              <template slot-scope="scope">
                <div slot="reference" class="name-wrapper">
                  <el-tag :type="scope.row.u_type === '学生' ? '' : 'success'">{{ scope.row.u_type }}</el-tag>
                </div>
              </template>
            </el-table-column> -->
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
          <el-dialog title="确诊信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
            <el-form :model="addForm" :rules="rules" ref="addForm" label-width="80px" size="medium">
              <el-form-item label="用户名" prop="username">
                <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名">
                </el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input type="text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名">
                </el-input>
              </el-form-item>
              <el-form-item label="体温" prop="temperature">
                <el-input type="text" auto-complete="false" v-model="addForm.temperature" placeholder="请输入体温">
                </el-input>
              </el-form-item>
              <el-form-item label="地址" prop="address">
                <el-input type="text" auto-complete="false" v-model="addForm.address" placeholder="请输入地址">
                </el-input>
              </el-form-item>
              <el-form-item label="身体状况" prop="health">
                <el-input type="text" auto-complete="false" v-model="addForm.health" placeholder="请输入身体状况">
                </el-input>
              </el-form-item>
              <el-form-item label="是否接触感染人员" prop="contact_infection">
                <el-input type="text" auto-complete="false" v-model="addForm.contact_infection" placeholder="请输入是否接触感染人员">
                </el-input>
              </el-form-item>
              <el-form-item label="备注说明" prop="remarks">
                <el-input type="textarea" :rows="2" auto-complete="false" v-model="addForm.remarks" placeholder="备注说明">
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
  name: 'Confirmed',
  components: {
    SideMenu
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      title: '',
      id: 0,
      name: '',
      health: '发烧、干咳、乏力、咽痛',
      u_type: '学生',
      last_time: '',
      dialogFormVisible: false,
      multipleSelection: [],
      addForm: {
        username: '',
        name: '',
        temperature: '',
        address: '',
        health: '',
        contact_infection: '',
        remarks: '',
        u_type: '学生',
        last_time: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        temperature: [{ required: true, message: '请输入体温', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        health: [{ required: true, message: '请输入身体状况', trigger: 'blur' }],
        contact_infection: [{ required: true, message: '请输入是否接触感染人员', trigger: 'blur' }]
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
        .get('/health/page', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name,
            health: this.health,
            u_type: this.u_type,
            last_time: this.last_time
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
          request.post('/health', this.addForm).then(res => {
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
      this.addForm = { username: '', name: '', temperature: '', address: '', health: '', contact_infection: '', remarks: '' }
    },
    // 获得某条信息
    getInfo() {
      request.get('/health/' + this.id).then(res => {
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
      request.delete('/health/' + id).then(res => {
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
      request.post('/health/del/batch', ids).then(res => {
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
