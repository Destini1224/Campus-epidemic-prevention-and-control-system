<template>
  <div class="news">
    <el-breadcrumb class="brand" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>健康管理</el-breadcrumb-item>
      <el-breadcrumb-item>老师健康管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索区域 -->
    <div class="student-search" style="padding:0 0 10px 0;">
      <el-input style="width:250px;" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name" clearable @change="load">
      </el-input>
      <template>
        <el-select style="width:250px;margin-left:5px;" v-model="health" clearable placeholder="请选择身体状况" @change="load">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </template>
      <el-date-picker v-model="last_time" style="width:250px;margin-left:5px;" clearable type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd" @change="load">
      </el-date-picker>
      <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定批量删除这些数据吗？" @confirm="delBatch()">
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
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="temperature" label="体温">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column prop="health" label="身体状况">
      </el-table-column>
      <el-table-column prop="contact_infection" label="是否接触感染人员">
      </el-table-column>
      <el-table-column prop="remarks" label="备注说明">
      </el-table-column>
      <el-table-column prop="last_time" label="最新提交时间" width="100">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template slot-scope="scope">
          <!-- <el-button type="warning" @click="handleEdit(scope.row)">编辑<i style="margin-left:2px;" class="el-icon-edit"></i></el-button> -->
          <el-popconfirm confirm-button-text='确定' cancel-button-text='取消' icon="el-icon-info" icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference" style="margin-left:5px;">删除<i style="margin-left:2px;" class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹窗区域 -->
    <el-dialog title="教师健康信息" :visible.sync="dialogFormVisible" width="30%" top="6vh" center>
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
          <el-select v-model="addForm.contact_infection" placeholder="请输入是否接触感染人员">
            <el-option label="是" value="是"></el-option>
            <el-option label="否" value="否"></el-option>
          </el-select>
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
    <div style=" padding: 10px 0;">
      <el-pagination :current-page="pageNum" :page-sizes="[5,10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js'
export default {
  name: 'HealthTea',
  data() {
    return {
      options: [
        {
          value: '健康状况正常',
          label: '健康状况正常'
        },
        {
          value: '发烧、干咳、乏力、咽痛',
          label: '发烧、干咳、乏力、咽痛'
        }
      ],
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      title: '',
      id: 0,
      name: '',
      health: '',
      u_type: '教师',
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
        u_type: '教师',
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
