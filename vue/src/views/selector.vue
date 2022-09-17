<template>
      <el-container class="selector_container">
        <div class="table" >
          <!-- 卡片视图区域 -->
            <el-card>   
              <el-row :gutter="20">  
                <el-input v-model="search.trainId" size="large" placeholder="火车车次" style="width: 15%" clearable />
                <el-input v-model="search.departure" size="large" placeholder="发站" style="width: 15%" clearable />
                <el-input v-model="search.leaveTime" size="large" placeholder="发点" style="width: 15%" clearable />
                <el-input v-model="search.terminal" size="large" placeholder="到站" style="width: 15%" clearable />
                <el-input v-model="search.arriveTime" size="large" placeholder="到时" style="width: 15%" clearable />
                <el-button @click="userSearch"><el-icon><Search /></el-icon>查询</el-button><br>
              </el-row>


              <!-- 用户列表区域(多出来的数据进行分页处理)  -->
              <el-table :data="state.table" border stripe  height="500px" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
                  <template #empty><el-empty description="无数据" /></template>
                  <el-table-column prop="trainId" label="车次" sortable width="65" align="center"/>
                  <el-table-column prop="departure" label="发站" sortable width="100" align="center"/>
                  <el-table-column prop="leaveTime" label="发点" sortable width="140" align="center"/>
                  <el-table-column prop="terminal" label="到站" sortable width="100" align="center"/>
                  <el-table-column prop="arriveTime" label="到时" sortable width="140" align="center"/>
                  <el-table-column prop="firstTicket" label="一等票" sortable width="100" align="center"/>
                  <el-table-column prop="firstCost" label="票额" sortable width="85" align="center"/>
                  <el-table-column prop="secondTicket" label="二等票" sortable width="100" align="center"/>
                  <el-table-column prop="secondCost" label="票额" sortable width="85" align="center"/>

                  <el-table-column label="Operation" width="263px" align="center">
                    <template #default="scope">
                      <el-button link type="primary" size="small" @click="handler(scope.row)"><el-icon><Edit /></el-icon>下单</el-button>
                    </template>
                  </el-table-column>
              </el-table>
            </el-card>
        </div>
        <el-pagination layout="prev, pager, next" :total="total" :page-size="pageSize" v-model:currentPage="currentPage"
                       @current-change="pageIndex"/>
    </el-container>

  <el-dialog draggable v-model="dialogFormVisible" title="购票窗口">
    <el-form :model="state.counts" :rules="rules" ref="ruleFormRef">
      <el-form-item label="数量(张)" :label-width="150" prop="type">
        <el-input v-model="state.counts" />
      </el-form-item>
    </el-form>
    <el-alert title="请选择购票数量" type="info" />
    <template #footer>
      <span>
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="Confirm">Confirm</el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup>
  import {ref, reactive, onBeforeMount, getCurrentInstance,  computed} from "vue";
  import request from "../request";
  import {ElNotification, ElMessage} from "element-plus";

  const dialogFormVisible = ref(false)
  const total = ref(0)
  const pageSize = ref(10)
  const currentPage = ref(1)
  const pageIndex = (val) => {
    currentPage.value = val
    load()
  }

  const userSearch = () => {
    load()
  }

  const handler = (row) => {
    dialogFormVisible.value = true
    state.form.trainId = row.trainId
    state.form.departure = row.departure
    state.form.leaveTime = row.leaveTime
    state.form.terminal = row.terminal
    state.form.arriveTime = row.arriveTime
    state.counts = 1

  }

  const rules = [{
    counts: [
      {required: true, message: '请输入数字', trigger: 'blur'},
      {pattern: /^[0-9]*[1-9][0-9]*$/, message: '无效数据', trigger: 'blur'}
    ]
  }]

  const Confirm = () => {
    request.post("/train",state.form,{params: {counts: state.counts}}).then(res => {
      if(res.code){
        dialogFormVisible.value = false
        ElMessage.success("添加成功")
      }
      else{
        dialogFormVisible.value = false
        ElMessage.error("添加失败")
      }
    })
  }

  const search = reactive({
    trainId: '',
    departure: '',
    leaveTime: '',
    terminal:'',
    arriveTime:''
  })
  const state = reactive({
    table: [],
    form: {
      trainId: '',
      departure: '',
      leaveTime: '',
      terminal:'',
      arriveTime:'',

      id:'',
      cost:'',
      type:''
    },
    counts: 1
  })
  const load = () => {
    request.get('/train/page',{
      params: {currentPage: currentPage.value, pageSize: pageSize.value,
        trainId: search. trainId, departure: search.departure, leaveTime: search.leaveTime, terminal: search.terminal,arriveTime:search.arriveTime}
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
  const trainSearch = () => {
      load()
  }
  onBeforeMount( ()=>{
    load()
  })
</script>

<style>
  .selector_container{
        height: 100%;
        min-height: 480px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .border{
      height: 100%;
      padding-top: 10px;
      padding-left: 20px;
      padding-right: 20px;
      padding-bottom: 10px;
    }
    .table{
      height: 100%;
      width: 1220px;
      margin-top: 10px;
      background: white;
      border-radius: 5px;
      border: 1px solid #3b99fc;
    }
    .el-card{
      height: 99.5%;
    }
    .el-row{
      height: 30px;
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