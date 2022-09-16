
<template>
  <div id="all">
    <el-header>
      <img src="../../public/img/title.jpg" width="50" style="border-radius:7px">
      <span>售票及分析系统人员信息管理系统</span>
    </el-header>

    <el-container>
      <!-- 卡片视图区域 -->
      <el-card>
        <el-row :gutter="20">
          <el-col :span="8">
            <!-- 搜索与添加区域 -->
              <el-input placeholder="请输入内容" >
              <template #append>
                <el-icon><search/></el-icon>
              </template>
              </el-input>
          </el-col>

          <el-col :span="4">
            <el-button @click="handler2">添加用户</el-button>
          </el-col>
        </el-row>

           <!-- 弹出信息填空表格(添加用户角色,修改其权限等操作) -->
          <el-dialog draggable v-model="dialogFormVisible" title="data">
            <el-form :model="state.form" :rules="rules" ref="ruleFormRef">
              <el-form-item label="UID" :label-width="150" prop="UID" v-if="flag == 1">
                <el-input v-model="state.form.UID" />
              </el-form-item>
              <el-form-item label="密码" :label-width="150" prop="password">
                <el-input v-model="state.form.password" />
              </el-form-item>
              <el-form-item label="权限" :label-width="150" prop="type">
                <el-input v-model="state.form.type" />
              </el-form-item>
              <el-form-item label="姓名" :label-width="150" prop="name">
                <el-input v-model="state.form.name" />
              </el-form-item>
              <el-form-item label="职位" :label-width="150" prop="position">
                <el-input v-model="state.form.position" />
              </el-form-item>
            </el-form>
            <el-alert title="1:售票 2:分析 3:管理员" type="info" />
            <template #footer>
              <span>
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="Confirm">上传</el-button>
              </span>
            </template>
          </el-dialog>

        <!-- 用户列表区域(多出来的数据进行分页处理)  -->
        <el-table :data="displayUser" border stripe current-row-key="UID" height="500" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <el-table-column type="index"  label="序号" width="65" align="center" />
            <el-table-column prop="UID" sortable label="UID" width="140" align="center"/>                   
            <el-table-column prop="name" label="姓名" width="85" align="center"/>
            <el-table-column prop="type" label="权限" width="85" align="center"/>
            <el-table-column prop="position" label="职位" width="175" align="center"/>
            <el-table-column prop="password" label="密码" width="175" align="center"/>         
            <el-table-column label="操作" width="260px" align="center">
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
      <el-pagination layout="prev, pager, next" :total="total" :page-size="pageSize" v-model:currentPage="pageIndex"/>
    <el-footer>
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
  
  const dialogFormVisible = ref(false)
  const flag = ref(0)
  const search = ref('')
  const state = reactive({
    table: [],
    form: {
      UID: '',
      password: '',
      type: '',
      name: '',
      position:'',
    }
  })

  // 填写规则
  const rules = reactive({
    UID: [
      {required: true, message: '请输入用户工号', trigger: 'blur'},
      {min: 3, max: 10, message: '用户工号长度至少为3', trigger: 'blur'}
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
      {min: 5, max: 12, message: '密码长度至少为6', trigger: 'blur'}
    ],
    type: [
      {required: true, message: '请输入类型', trigger: 'blur'},
      {pattern: /^[1-3]$/, message: '无效类型', trigger: 'blur'}
    ]
  })
  
  // 表单提交按钮
  const { proxy } = getCurrentInstance()
  const Confirm = () => {
    proxy.$refs.ruleFormRef.validate((valid) => {
      if(flag.value === 1){
        if(valid){
          request.post('/user',state.form).then(res => {
            load()
            dialogFormVisible.value = false
            ElMessage.success("添加成功")
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
          request.put('/user',state.form).then(res => {
            load()
            dialogFormVisible.value = false
            ElMessage.success("修改成功")
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
    request.get('/user').then( res => {
      state.table = res
    })
  }
  
  const deleteBtn = (index, UID_) => {
    request.delete("/user/" + UID_).then(res => {
      state.table.splice(index,1)
      ElMessage.success("删除成功")
    })
  }
  
  const handler = (row) => {
    dialogFormVisible.value = true
    state.form = JSON.parse(JSON.stringify(row))
    flag.value = 2
  }
  
  const handler2 = () => {
    dialogFormVisible.value = true
    state.form.value = {}
    flag.value = 1
  }
  
  //测试数据
  const userlist=reactive([
    {
        UID:1,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:2,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:3,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:4,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:5,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:6,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:7,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:8,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:9,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:10,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:11,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:12,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:13,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:14,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
    {
        UID:15,
        password:123456,
        type:3,
        name:'yy',
        position:'经理',
    },
  ])
  
  onBeforeMount( () => {
    load()
  })


  //#region
  const pageSize = 11
  const total = computed(()=> {
    return userlist?.length ? userlist.length: 0
  })
  const pageIndex = ref(1)
  const displayUser = computed(()=> {
    const start = (pageIndex.value - 1) * pageSize
    return userlist.filter((item ,index) => {
      const isIn = index >= start && index < start + pageSize
      console.log(item,index,start,isIn)
      return isIn
    })
  })

  //#endregion
  
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
</style>
