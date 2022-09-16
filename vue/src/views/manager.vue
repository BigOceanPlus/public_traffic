
<template>
  <div id="all">
    <el-header>
      <img src="../../public/img/title.jpg" width="50" style="border-radius:7px">
      <span>售票及分析系统人员信息管理系统</span>

      <el-button style="background: #3b99fc; width: 40px; height: 40px; position: absolute; right: 10px; top: 10px;" @click="over"><el-icon color=""><SwitchButton /></el-icon></el-button>
    </el-header>

    <el-container>
      <!-- 卡片视图区域 -->
      <el-card>
        <el-row :gutter="20">
            <!-- 搜索与添加区域 -->
            <el-input v-model="search.id" size="large" placeholder="ID" style="width: 15%" clearable />
            <el-input v-model="search.name" size="large" placeholder="用户名" style="width: 15%" clearable />
            <el-input v-model="search.password" size="large" placeholder="密码" style="width: 15%" clearable />
            <el-input v-model="search.type" size="large" placeholder="用户权限" style="width: 15%" clearable />
            <el-button @click="userSearch"><el-icon><Search /></el-icon>查询</el-button><br>
            <el-button @click="handler2">添加用户</el-button>
        </el-row>

           <!-- 弹出信息填空表格(添加用户角色,修改其权限等操作) -->
          <el-dialog draggable v-model="dialogFormVisible" title="data">
            <el-form :model="state.form" :rules="rules" ref="ruleFormRef">              
           
              <el-form-item label="用户名" :label-width="150" prop="name" v-if="flag == 1">
                <el-input v-model="state.form.name" />
              </el-form-item>
              <el-form-item label="密码" :label-width="150" prop="password">
                <el-input v-model="state.form.password" />
              </el-form-item>
              <el-form-item label="权限" :label-width="150" prop="type">
                <el-input v-model="state.form.type" />
              </el-form-item>
            </el-form>
            <el-alert title="1:售票 2:分析 3:管理员" type="info" />
            <template #footer>
              <span>
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="Confirm">确定</el-button>
              </span>
            </template>
          </el-dialog>

        <!-- 用户列表区域(多出来的数据进行分页处理)  -->
        <el-table :data="state.table" border stripe current-row-key="UID" height="500" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <template #empty><el-empty description="无数据" /></template>
            <el-table-column prop="id" label="ID" sortable width="180" align="center"/>
            <el-table-column prop="name" label="用户名" sortable width="180" align="center"/>
            <el-table-column prop="password" label="密码" sortable width="200" align="center"/>
            <el-table-column prop="type" label="用户权限" sortable width="200" align="center"/>
            <el-table-column label="Operation" width="260px" align="center">
              <template #default="scope">
                <el-button link type="primary" size="small" @click="handler(scope.row)"><el-icon><Edit /></el-icon>编辑</el-button>

                <el-popconfirm title="确定删除?" @confirm="deleteBtn(scope.$index, scope.row.name)">
                <template #reference>
                    <el-button link type="danger" size="small"><el-icon><Delete /></el-icon>删除</el-button>
                </template>
                </el-popconfirm>
              </template>
            </el-table-column>
        </el-table>
      </el-card>
    </el-container>

      <el-pagination layout="prev, pager, next" :total="total" :page-size="pageSize" v-model:currentPage="currentPage" @current-change="handleCurrentChange" />
      
    <el-footer style="height: 30px;">
      <span>@author:yy/BigOcean</span>
      <span>@version:1.0.0</span>
      <span>@year:2022-2022</span>
    </el-footer>
  </div>
</template>
 
<script setup>
  import {ref, reactive, onBeforeMount, getCurrentInstance, onMounted, computed} from "vue";
import request from "../request";
import {ElNotification, ElMessage} from "element-plus";
  import {useRouter} from 'vue-router'

const router = useRouter()
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
          title: "警告",
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

/*const handleSizeChange = (val) =>{
  pageSize.value = val
  load()
}*/

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

  const over = () => {
    localStorage.removeItem("flag")
    localStorage.removeItem("token")
    localStorage.removeItem("startTime")
    ElNotification({
      title: "提示",
      message: "成功退出系统",
      type: 'info'
    })
    router.push("/")
  }
  
</script>
 
<style>
  *{
    margin: 0;
    padding: 0;
  }
#all{
    display: flex;
    flex-direction: column;
    height: 100vh;
    align-items: center;
}
.el-header{
  display: flex;
  align-items: center;
  background: #3b99fc;
  flex-shrink: 0;
  width: 100vw;
}
.el-header span{
    font-size: 30px;
    font-weight: 700;
    font-family: "宋体";
    color: aliceblue;
}
.border{
  padding-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 10px;
  display: flex;
  justify-content: center;
}
.table{
  height: 475px;
  width: 1220px;
  margin-top: 10px;
  background: white;
  border-radius: 5px;
  border: 1px solid #3b99fc;
}

.el-footer{
  display: flex;
  align-items: center;
}
.el-card{
  display: flex;
  justify-content: center;
}
.el-row{
  display: flex;
  align-items: center;
}
.el-row .el-input{
  height: 30px;
  margin-left: 10px;
}
.el-row .el-button{
  height: 30px;
  margin-left: 10px;
}
</style>
