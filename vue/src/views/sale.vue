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
        <el-form-item label="车厢" :label-width="150" prop="boxID" v-if="flag == 1">
          <el-input v-model="state.form.boxID" />
        </el-form-item>
        <el-form-item label="席位" :label-width="150" prop="setID">
          <el-input v-model="state.form.setID" />
        </el-form-item>
        <el-form-item label="席别" :label-width="150" prop="settype">
            <el-select v-model="state.form.tickeType" placeholder="请选择票种">
                <el-option label="一等" value="一等" />
                <el-option label="二等" value="二等" />
            </el-select>
        </el-form-item>
        <el-form-item label="身份证号" :label-width="150" prop="PID">
          <el-input v-model="state.form.PID" />
        </el-form-item>
        <el-form-item label="票种">
          <el-select v-model="state.form.tickeType" placeholder="请选择票种">
            <el-option label="全" value="全" />
            <el-option label="半" value="半" />
          </el-select>
        </el-form-item>
        <el-form-item label="事由" :label-width="150" prop="reason">
          <el-input v-model="state.form.reason" />
        </el-form-item>
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
          <el-table :data="tableData" border stripe height="430" style="width: 100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
            <el-table-column type="index"  label="序号" align="center"/>
            <el-table-column prop="trainId" label="车次" width="65" align="center"/>
            <el-table-column prop="departure" label="发站" width="85" />
            <el-table-column prop="leaveTime" label="发点" width="65" align="center"/>
            <el-table-column prop="terminal" label="到站" width="85" />
            <el-table-column prop="arriveTime" label="到时" width="65" align="center"/>
            <el-table-column prop="boxID" label="车厢" width="55" align="center"/>
            <el-table-column prop="setID" label="席位" width="55" align="center"/>
            <el-table-column prop="settype" label="席别" width="55" align="center"/>
            <el-table-column prop="PID" label="身份证号" width="175" />
            <el-table-column prop="tickeType" label="票种" width="65" align="center"/>
            <el-table-column prop="reason" label="事由" width="65" align="center"/>
            <el-table-column prop="amount1" sortable label="票价" align="center"/>
            
            <el-table-column label="操作" width="135" align="center">
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
          <el-button class="mt-4" style="width: 100%" height="20px" @click="handler2">新增票</el-button>
        </div>
    </div>

</div>
  
  </template>
  
  
  <script setup>
     import {ref, reactive, onBeforeMount, getCurrentInstance,  computed} from "vue";
      import request from "../request";
      import {ElNotification, ElMessage} from "element-plus";
      
      const dialogFormVisible = ref(false)
      const flag = ref(0)
      const search = ref('')
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
          reason:'',
        }
      })
    
      // 填写规则
      const rules = reactive({
    
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
    }
    .table{
      height: 475px;
      width: 1220px;
      margin-top: 10px;
      background: white;
      border-radius: 5px;
      border: 1px solid #3b99fc;
    }
  </style>