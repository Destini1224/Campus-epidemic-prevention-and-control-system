<template>
  <div class="box">
    <div class="container">
      <div class="brand">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item><i class="el-icon-location" style="padding-right: 5px;"></i>首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>健康打卡</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div>
        <div class="aside">
          <SideMenu></SideMenu>
        </div>
        <el-main class="main">
          <!-- 主体数据展示区域 -->
          <el-button type="primary" @click="handleAdd" style="margin:20px 0px 5px 20px;">健康打卡<i style="margin-left:2px;" class="el-icon-circle-plus-outline"></i></el-button>
          <el-table :data="tableData" border stripe style="width: 97%;margin:20px 0 20px 20px;">
            <!-- <el-table-column prop="id" label="ID" width="80"></el-table-column> -->
            <el-table-column type="index" label="序号" width="80">
            </el-table-column>
            <el-table-column prop="temperature" label="体温" width="80">
            </el-table-column>
            <el-table-column prop="address" label="地址" width="200">
            </el-table-column>
            <el-table-column prop="health" label="身体状况" width="250">
            </el-table-column>
            <el-table-column prop="contact_infection" label="是否接触感染人员" width="140">
            </el-table-column>
            <el-table-column prop="remarks" label="备注说明">
            </el-table-column>
            <el-table-column prop="last_time" label="更新时间" width="200">
            </el-table-column>
            <!-- <el-table-column fixed="right" label="操作" width="260">
              <template slot-scope="scope">
                <el-button type="warning" @click="handleEdit(scope.row.id)" :disabled="scope.row.status === '0' ? false : true">编辑<i style="margin-left:2px;" class="el-icon-edit"></i></el-button>
            <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
              <el-button type="danger" slot="reference" style="margin-left:5px;">删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
</template>
            </el-table-column> -->
          </el-table>
          <!-- 新增弹窗区域 -->
          <el-dialog title="健康打卡" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
            <el-form :model="addForm" :rules="rules" ref="addForm" label-width="80px" size="medium">
              <el-form-item label="用户名" prop="username">
                <el-input type="text" auto-complete="false" v-model="addForm.username" placeholder="请输入用户名" disabled>
                </el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input type="text" auto-complete="false" v-model="addForm.name" placeholder="请输入姓名">
                </el-input>
              </el-form-item>
              <el-form-item label="体温" prop="temperature">
                <el-input type="text" auto-complete="false" v-model="addForm.temperature" placeholder="请填写体温">
                </el-input>
              </el-form-item>
              <el-form-item label="地址" prop="address">
                <el-input type="text" auto-complete="false" v-model="addForm.address" placeholder="地址">
                </el-input>
              </el-form-item>
              <el-form-item label="身体状况" prop="health">
                <el-select v-model="addForm.health" placeholder="请选择身体状况">
                  <el-option label="健康状况正常" value="健康状况正常"></el-option>
                  <el-option label="发烧、干咳、乏力、咽痛" value="发烧、干咳、乏力、咽痛"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="是否接触感染人员" prop="contact_infection">
                <el-select v-model="addForm.contact_infection" placeholder="请选择是否接触感染人员">
                  <el-option label="是" value="是"></el-option>
                  <el-option label="否" value="否"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="备注说明" prop="remarks">
                <el-input type="textarea" :rows="2" auto-complete="false" v-model="addForm.remarks" placeholder="备注说明">
                </el-input>
              </el-form-item>
              <el-form-item label="用户类型" prop="u_type">
                <el-select v-model="addForm.u_type" disabled>
                  <el-option label="学生" value="学生"></el-option>
                  <el-option label="教师" value="教师"></el-option>
                </el-select>
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
import SideMenu from '@/components/SideMenu/StuSideMenu.vue'
export default {
  name: 'StudentHealth',
  components: {
    SideMenu
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: '',
      u_type: '',
      status: '',
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
        u_type: '',
        last_time: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        temperature: [{ required: true, message: '请输入体温', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        health: [{ required: true, message: '请选择健康状况', trigger: 'change' }],
        contact_infection: [{ required: true, message: '请选择是否接触感染人员', trigger: 'change' }],
        u_type: [{ required: true, message: '请选择用户类型', trigger: 'change' }]
      }
    }
  },
  created() {
    // username的信息从保存在客户端中的信息中获取
    this.username = JSON.parse(window.localStorage.getItem('username'))
    this.u_type = JSON.parse(window.localStorage.getItem('type'))
    this.load()
  },
  methods: {
    // 获取列表信息
    load() {
      request
        .get('/health/healthPage', {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            username: this.username
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
              this.$message.success('打卡成功')
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error('打卡失败')
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
      this.$refs[formName].resetFields()
    },
    // 新增按钮点击事件
    handleAdd() {
      this.dialogFormVisible = true
      this.addForm = { username: this.username, name: '', temperature: '', address: '', health: '', contact_infection: '', remarks: '', u_type: this.u_type }
    },
    // 审批按钮点击事件
    handleEdit(id) {
      request.post('/health/' + id).then(res => {
        if (res) {
          this.$message.success('审批成功')
          this.load()
        } else {
          this.$message.error('审批失败')
        }
      })
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
