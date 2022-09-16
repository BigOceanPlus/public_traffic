<template>
  <el-table :data="state.table" stripe border style="width: 60%; background-color: azure">
    <template #empty><el-empty description="无数据" /></template>
    <el-table-column prop="id" label="ID" sortable width="180" />
    <el-table-column prop="name" label="Name" sortable width="180" />
    <el-table-column prop="password" label="Password" sortable width="180" />
    <el-table-column prop="type" label="Type" sortable width="180" />
    <el-table-column label="Operation" fixed="right" width="180">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handler(scope.row)"><el-icon><Edit /></el-icon>Edit</el-button>

        <el-popconfirm title="确定删除?" @confirm="deleteBtn(scope.$index, scope.row.name)"><template #reference>
          <el-button link type="danger" size="small"><el-icon><Delete /></el-icon>Delete</el-button>
        </template></el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin: 10px">
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 40]"
          :pager-count="9"
          small
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
  </div>
  <el-input v-model="search.id" size="large" placeholder="ID" style="width: 15%" clearable />
  <el-input v-model="search.name" size="large" placeholder="Name" style="width: 15%" clearable />
  <el-input v-model="search.password" size="large" placeholder="Password" style="width: 15%" clearable />
  <el-input v-model="search.type" size="large" placeholder="Type" style="width: 15%" clearable />
  <el-button @click="userSearch"><el-icon><Search /></el-icon>查询</el-button><br>
  <el-button @click="handler2">Add</el-button>

  <el-dialog draggable v-model="dialogFormVisible" title="data">
    <el-form :model="state.form" :rules="rules" ref="ruleFormRef">
      <el-form-item label="name" :label-width="150" prop="name" v-if="flag == 1">
        <el-input v-model="state.form.name" />
      </el-form-item>
      <el-form-item label="password" :label-width="150" prop="password">
        <el-input v-model="state.form.password" />
      </el-form-item>
      <el-form-item label="type" :label-width="150" prop="type">
        <el-input v-model="state.form.type" />
      </el-form-item>
    </el-form>
    <el-alert title="1:售票 2:分析 3:管理员" type="info" />
    <template #footer>
      <span>
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="Confirm">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, reactive, onBeforeMount, getCurrentInstance, onMounted, computed} from "vue";
import request from "../request";
import {ElNotification, ElMessage} from "element-plus";
import {useRoute} from 'vue-router'

const route = useRoute()
const pageSize = ref(10);
const currentPage = ref(1)
const total = ref(0)
const dialogFormVisible = ref(false)
const flag = ref(0)
const search = reactive({
  id: '',
  name: '',
  password: '',
  type: ''
})
const state = reactive({
  table: [],
  form: {
    name: '',
    password: '',
    type: ''
  },
})
const rules = reactive({
  name: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '用户名长度至少为3', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 12, message: '密码长度至少为6', trigger: 'blur'}
  ],
  type: [
    {required: true, message: '请输入类型', trigger: 'blur'},
    {pattern: /^[1-3]$/, message: '无效类型', trigger: 'blur'}
  ]
})

const { proxy } = getCurrentInstance()
const Confirm = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(flag.value === 1){
      if(valid){
        request.post('/user',state.form).then(res => {
          if(res.code){
            load()
            dialogFormVisible.value = false
            ElMessage.success("添加成功")
          }
          else{
            dialogFormVisible.value = false
            ElMessage.error("添加失败")
          }
        })
      }
      else{
        ElNotification({
          type: 'error',
          message: '未通过表单校验'
        })
      }
    }
    else if(flag.value === 2){
      if(valid){
        request.put('/user', state.form).then(res => {
          if(res.code){
            load()
            dialogFormVisible.value = false
            ElMessage.success("修改成功")
          }
          else{
            dialogFormVisible.value = false
            ElMessage.error("修改失败")
          }
        })
      }
      else{
        ElNotification({
          type: 'error',
          message: '未通过表单校验'
        })
      }
    }
  }
  )}

const load = () => {
  request.get('/user/page',{
    params: {currentPage: currentPage.value, pageSize: pageSize.value,
    id: search.id, name: search.name, password: search.password, type: search.type}
  }).then( res => {
    if(res.code){
      total.value = res.data.total
      state.table = res.data.data
    }
    else{
      ElMessage({
        type: 'error',
        message: res.msg
      })
    }
  })
}

const deleteBtn = (index, name_) => {
  request.delete("/user/" + name_).then(res => {
    if(res.code){
      state.table.splice(index,1)
      ElMessage.success("删除成功")
    }
    else{
      ElMessage.success("删除失败")
    }
  })
}

const handler = (row) => {
  dialogFormVisible.value = true
  state.form = JSON.parse(JSON.stringify(row))
  flag.value = 2
}

const handler2 = () => {
  dialogFormVisible.value = true
  state.form = {}
  //console.log(state.form)
  flag.value = 1
}

/*const filterTableData = computed(() =>
    state.table.filter(
        (data) =>
            !search.value || data.id.toString().toLowerCase().includes(search.value)
            || data.name.toLowerCase().includes(search.value.toLowerCase())
            || data.password.toLowerCase().includes(search.value.toLowerCase())
            || data.type.toString().toLowerCase().includes(search.value.toLowerCase())
    )
)*/

const handleSizeChange = (val) =>{
  pageSize.value = val
  load()
}

const handleCurrentChange = (val) =>{
  currentPage.value = val
  load()
}

const userSearch = () => {
  load()
}

onBeforeMount( () => {
  load()
})

onMounted( () => {
})

</script>


<style scoped>

</style>
