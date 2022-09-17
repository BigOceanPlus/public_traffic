<template>
    <el-dialog draggable v-model="dialogFormVisible" title="">
      <el-form :model="state.form" :rules="rules" ref="ruleFormRef">
        <!-- <el-form-item label="车次" :label-width="150" prop="trainId" >
          <el-input v-model="state.form.trainId" />
        </el-form-item>
        <el-form-item label="发站" :label-width="150" prop="departure">
          <el-input v-model="state.form.departure" />
        </el-form-item>
        <el-form-item label="发点" :label-width="150" prop="leaveTime">
          <el-input v-model="state.form.leaveTime" />
        </el-form-item>
        <el-form-item label="到站" :label-width="150" prop="terminal">
          <el-input v-model="state.form.terminal" />
        </el-form-item>
        <el-form-item label="到时" :label-width="150" prop="arriveTime">
          <el-input v-model="state.form.arriveTime" />
        </el-form-item> -->
        <el-form-item label="座位号" :label-width="150" prop="seat">
          <el-input v-model="state.form.seat" />
        </el-form-item>
        <el-form-item label="车票类型" :label-width="150" prop="settype">
            <el-select v-model="state.form.type" placeholder="请选择票种">
                <el-option label="一等" value="1" />
                <el-option label="二等" value="2" />
            </el-select>
        </el-form-item>
        <el-form-item label="身份证号" :label-width="150" prop="PID">
          <el-input v-model="state.form.PID" />
        </el-form-item>
        <el-form-item label="票价" :label-width="150" prop="PID">
          <el-input v-model="state.form.cost" />
        </el-form-item>
        <!--
        <el-form-item label="票种">
          <el-select v-model="state.form.tickeType" placeholder="请选择票种">
            <el-option label="全" value="全" />
            <el-option label="半" value="半" />
          </el-select>
        </el-form-item>
        <el-form-item label="事由" :label-width="150" prop="reason">
          <el-input v-model="state.form.reason" />
        </el-form-item>
        -->
      </el-form>
      <template #footer>
        <span>
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="Confirm">上传</el-button>
        </span>
      </template>
    </el-dialog>
  
    <div class="sale_container">
      <div class="table" >
        <div class="border">
          <!-- 表格 -->
          <el-table :data="state.table" border stripe height="95%" style="width: 100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <el-table-column type="index"  label="序号" align="center"/>
            <el-table-column prop="id" label="ID" width="65" align="center"/>
            <el-table-column prop="trainId" label="车次" width="65" align="center"/>
            <el-table-column prop="departure" label="发站" width="85" />
            <el-table-column prop="leaveTime" label="发点" width="65" align="center"/>
            <el-table-column prop="terminal" label="到站" width="85" />
            <el-table-column prop="arriveTime" label="到时" width="65" align="center"/>
            <el-table-column prop="seat" label="座位号" width="55" align="center"/>
            <el-table-column prop="PID" label="身份证号" width="175" />
            <el-table-column prop="type" label="票种" width="65" align="center"/>
            <el-table-column prop="cost" sortable label="票价" align="center"/>
            
            <el-table-column label="操作" width="135" align="center">
                <template #default="scope">
                  <el-button link type="primary" size="small" @click="handler(scope.row)"><el-icon><Edit /></el-icon>编辑</el-button>
  
                  <el-popconfirm title="确定删除?" @confirm="deleteSale(scope.$index, scope.row.id)">
                  <template #reference>
                      <el-button link type="danger" size="small"><el-icon><Delete /></el-icon>删除</el-button>
                  </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
          </el-table>
          <router-link to="/home/selector"><el-button class="mt-4" style="width: 100%" height="10px">新增票</el-button></router-link>
          <div style="width: 1080px;height: 40px;display: flex;align-items: center;">
            <div>应收(￥):</div>
            <div>{{ state.total }}</div>
            <div style="margin-left: 40px">实收：</div>

            <el-input v-model="state.number" style="width: 100px; margin: 40px" />
            <!-- 将应收与实收输入的数据相比较用来结算当前的订单,同时将数据传回数据库,对应的票额-1 -->
            <el-button width="30px" @click="ending">结算</el-button>
          </div>
        </div>
    </div>

</div>

  
  </template>
  
  
  <script setup>
     import {ref, reactive, onBeforeMount, getCurrentInstance,  computed} from "vue";
      import request from "../request";
      import {ElNotification, ElMessage} from "element-plus";

      const dialogFormVisible = ref(false)
      const state = reactive({
        table: [],
        form: {
          trainId: '',
          departure: '',
          leaveTime: '',
          terminal:'',
          arriveTime:'',
          cost:'',
          PID:'',
          seat:'',
          type:'',
          id: ''
        },
        total: 0,
        number: 0
      })

     const deleteSale = (index,id) => {
        //console.log(id)
       request.delete("/ticket/" + id,{params: {id: id}}).then(res => {
         if(res.code){
           load()
           ElMessage.success("删除成功")
         }
         else{
           ElMessage.error("删除失败")
         }
       })
     }
    
      // 填写规则
      const rules = reactive({
    
      })
      
      // 表单提交按钮
      const { proxy } = getCurrentInstance()
      const Confirm = () => {
        proxy.$refs.ruleFormRef.validate((valid) => {
              if(valid){
                request.put('/ticket',state.form).then(res => {
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
        )}

      const load = () => {
        request.get('/ticket').then( res => {
          console.log(res)
          state.table = res.data
          Sum()
        })

      }

      const ending = () => {

        if(state.number == state.total){
          ElNotification({
            type: 'success',
            message: '订单完成: ' + state.number + '元'
          })
          request.delete("/ticket")
          load()
        }
        else{
          ElNotification({
            type: 'error',
            message: '请重新确认实收金额'
          })
        }
      }

      const Sum = () => {
        request.get("/ticket/sum").then(res => {
          state.total = res.data
        })
      }
      
      const handler = (row) => {
        console.log(row)
        dialogFormVisible.value = true
        state.form.trainId = row.trainId
        state.form.departure = row.departure
        state.form.terminal = row.terminal
        state.form.arriveTime = row.arriveTime
        state.form.leaveTime = row.leaveTime
        state.form.id = row.id
        state.form.type = 0
        state.form.PID = ''
        state.form.trainId = ''
      }
    
    //   测试数据
    
      const filterTableData = computed(() =>
          state.table.filter(
              (data) =>
                  !search.value || data.id.toString().toLowerCase().includes(search.value)
                  || data.date.toLowerCase().includes(search.value.toLowerCase())
                  || data.trainId.toLowerCase().includes(search.value.toLowerCase())
                  || data.departure.toString().toLowerCase().includes(search.value.toLowerCase())
                  || data.leaveTime.toLowerCase().includes(search.value.toLowerCase())
                  || data.terminal.toLowerCase().includes(search.value.toLowerCase())
                  || data.arriveTime.toLowerCase().includes(search.value.toLowerCase())
                  || data.boxID.toLowerCase().includes(search.value.toLowerCase())
                  || data.setID.toLowerCase().includes(search.value.toLowerCase())
                  || data.settype.toLowerCase().includes(search.value.toLowerCase())
                  || data.PID.toLowerCase().includes(search.value.toLowerCase())
                  || data.tickeType.toLowerCase().includes(search.value.toLowerCase())
                  || data.reason.toLowerCase().includes(search.value.toLowerCase())
          )
      )
      
      onBeforeMount( () => {
        load()
      })
  </script>
  
  <style>
  .sale_container{
        height: 100%;
        min-height: 480px;
        /* background-color: rgb(85, 210, 66); */
        display: flex;
        flex-direction: column;
        align-items: center;
        /* justify-content: center; */
    }
    .border{
      padding-top: 10px;
      padding-left: 20px;
      padding-right: 20px;
      padding-bottom: 10px;
      height: 90%;
    }
    .table{
      height: 100%;
      min-height: 475px;
      width: 1220px;
      margin-top: 10px;
      background: white;
      border-radius: 5px;
      border: 1px solid #3b99fc;
    }
  </style>