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
                <el-button @click="trainSearch"><el-icon><Search /></el-icon>查询</el-button><br>
              </el-row>


              <!-- 用户列表区域(多出来的数据进行分页处理)  -->
              <el-table :data="state.table" border stripe  height="440" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
                  <template #empty><el-empty description="无数据" /></template>
                  <el-table-column prop="trainId" label="车次" sortable width="137" align="center"/>
                  <el-table-column prop="departure" label="发站" sortable width="180" align="center"/>
                  <el-table-column prop="leaveTime" label="发点" sortable width="200" align="center"/>
                  <el-table-column prop="terminal" label="到站" sortable width="200" align="center"/>
                  <el-table-column prop="arriveTime" label="到时" sortable width="200" align="center"/>
                  <el-table-column label="Operation" width="260px" align="center">
                    <template #default="scope">
                      <el-button link type="primary" size="small" @click="handler(scope.row)"><el-icon><Edit /></el-icon>下单</el-button>
                    </template>
                  </el-table-column>
              </el-table>
            </el-card>
        </div>
        <el-pagination layout="prev, pager, next" :total="total" :page-size="pageSize" v-model:currentPage="pageIndex"/>
    </el-container>

</template>

<script setup>
  import {ref, reactive, onBeforeMount, getCurrentInstance,  computed} from "vue";
  import request from "../request";
  import {ElNotification, ElMessage} from "element-plus";

  const handler = ({

  })
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
      boxID:'',
      setID:'',
      settype:'',
      PID:'',
      tickeType:'',
      reason:''
    }
  })
  const load = () => {
    request.get('/train/page',{
      params: {currentPage: currentPage.value, pageSize: pageSize.value,
        trainId: search.trainId, departure: search.departure, leaveTime: search.leaveTime, terminal: search.terminal,arriveTime:search.arriveTime}
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
</script>

<style>
  .selector_container{
        height: 472px;
        min-height: 480px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .border{
      padding-top: 10px;
      padding-left: 20px;
      padding-right: 20px;
      padding-bottom: 10px;
    }
    .table{
      height: 480px;
      width: 1220px;
      margin-top: 10px;
      background: white;
      border-radius: 5px;
      border: 1px solid #3b99fc;
    }
    .el-card{
      height: 472px;
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