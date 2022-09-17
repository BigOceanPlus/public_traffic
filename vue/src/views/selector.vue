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
              <el-table :data="state.table" border stripe  height="400px" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
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
        <el-pagination layout="prev, pager, next" :total="199" :page-size="20" v-model:currentPage="pageIndex"/>
    </el-container>

</template>

<script setup>
  import {ref, reactive, onBeforeMount, getCurrentInstance,  computed} from "vue";
  import request from "../request";
  import {ElNotification, ElMessage} from "element-plus";

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
    request.get('/user/page',{
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