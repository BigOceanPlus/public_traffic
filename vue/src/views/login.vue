<template>
  <div id="layout">
    <el-container style="height: 100%; width: 100%;">

      <!-- 页面顶部 -->
      <el-header height="10%" id="login_header">

        <div id="header_icon">
          <!-- 顶部图标 -->
          <div>
            <img src="../../public/title.jpg" alt="" height="100" width="100" id="header_img">
          </div>
  
          <!-- 图标旁的元素 -->
          <div id="header_font"> 
             <!-- style="margin-top: 45px; width: 600px" -->
            <span class="header_p">铁路客票</span>
            <span class="header_p">发售和分析平台</span>
            <span>CHINA RAILWAY</span>
            <!--  style="margin-left: 55%; color: #888888; font-family: 'Times New Roman'" -->
          </div>
        </div>
      </el-header>

      <el-container>
        <!-- <el-aside >Aside</el-aside>width="10%" -->
        <el-main>
          <div id="login">
            <h1 style="text-align: center; margin-bottom:50px">
              <el-button type="primary" link style="width: 400px; height: 50px; font-size: 28px"><el-icon><Position /></el-icon>&nbsp;客流发售登录</el-button>
              <el-divider direction="vertical" style="background-color: #747bff; width: 2px; height: 50px"></el-divider>
              <el-button type="primary" link style="width: 400px; height: 50px; font-size: 28px"><el-icon><PieChart /></el-icon>&nbsp;客流分析登录</el-button>
            </h1>
            <el-form :model="user" :rules="rules" ref="ruleFormRef">
              <el-form-item prop="name" style="width: 30%; margin-left: 35%; margin-bottom: 40px">
                <el-input v-model="user.name" placeholder="用户名" :prefix-icon="User" maxlength="10" show-word-limit />
              </el-form-item>
              <el-form-item prop="password" style="width: 30%; margin-left: 35%; margin-bottom: 40px" >
                <el-input v-model="user.password" show-password placeholder="密码" :prefix-icon="Lock" maxlength="12" />
              </el-form-item>
              <el-form-item prop="copy" style="width: 30%; margin-left: 35%; margin-bottom: 40px" >
                <el-input v-model="user.copy" placeholder="密码确认" :prefix-icon="Lock" maxlength="12" show-word-limit />
              </el-form-item>

              <el-form-item>
                <el-col :span="13">
                  <el-form-item>
                  <el-button type="primary" style="width: 30%; margin-left: 65%; margin-bottom: 20px" @click="login">登 录</el-button>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item>
                    <el-button type="primary" style="width: 30%;  margin-bottom: 20px" @click="reset">重 置</el-button>
                  </el-form-item>
                </el-col>
              </el-form-item>

            </el-form>
          </div>
        </el-main>

        <el-aside width="10%">Aside</el-aside>
      </el-container>

      <el-footer height="5%">
        <span class="bhead">@author: BigOcean</span>
        <span class="bhead">@version: 1.0.0</span>
        <span class="bhead">@year: 2022 - 2022</span>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import {ref, reactive, getCurrentInstance} from 'vue';
import {User,Lock} from '@element-plus/icons-vue'
import {ElMessage} from 'element-plus'
import request from "../request";

const validatePass = (rule,value,callback) => {
  if (value === '') {
    callback(new Error('没有确认密码'))
  } else if (value !== user.password) {
    callback(new Error("两次密码输入不一致"))
  } else {
    callback()
  }
}

const rules = reactive({
  name: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '用户名长度至少为3', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 12, message: '密码长度至少为6', trigger: 'blur'}
  ],
  copy: [
    {validator: validatePass, trigger: 'blur'}
  ],
})

const { proxy } = getCurrentInstance()
const login = () => {
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      request.post('/user/login').then(res => {
        if(res.code){
          ElMessage({
            type: 'success',
            message: '登录成功'
          })
        }
        else{
          ElMessage({
            type: 'error',
            message: res.msg
          })
        }
      })
    }
    else{
      ElMessage({
        type: 'error',
        message: '登录失败'
      })
    }
  })
}

const user = reactive({
  name: '',
  password: '',
  copy: '',
})

const reset = () => {
  user.name=''
  user.password=''
  user.copy=''
}

</script>

<style>
  /* 全局 */
*{
  margin: 0;
  padding: 0;
}

#layout{
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* 页面顶部 */
#login_header{
  display: flex;
  flex-direction: column;
  align-items: center;
  background: yellow;
}

#header_icon{
  width: 1190px;  
  display: flex;
  align-items: center;
}
#header_font{
  margin-left: 20px;
  display: flex;
  flex-direction: column;
}
.header_p{
  text-align: left;
  line-height: 30px;
  font-size: 30px;
  font-weight: bold;
  font-family: 微软雅黑;
  color: #F56C6C;
}


/* div#layout{
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
}

#login{
  margin-top: 3%;
  margin-right: 10%;
  margin-left: 10%;
  width: 80%;
  height: 90%;
  border: 3px solid black;
}

span.bhead{
  margin-left: 70px;
  margin-right: 70px;
  font-family: 微软雅黑;
  font-size: 12px;
}

.struct{
  border-radius: 4px;
  min-height: 36px;
  margin: 5px;
}
.color1{
  background-color: #606266;
}
.color2{
  background-color: #909399;
}

p.fhead{
  margin-left: 55%;
  margin-bottom: 10px;
  margin-top: 10px;
  text-align: left;
  line-height: 1;
  font-size: 30px;
  font-weight: bold;
  font-family: 微软雅黑;
  color: #F56C6C;
}

img#header_img{
  float: left;
  margin-left: 5%;
}

.el-header{
  background-color: white;
}

.el-aside{
  background-color: dodgerblue;
  text-align: center;
}

.el-main{
  text-align: center;
  margin: 10px;
  padding: 20px;
}

.el-footer{
  background-color: skyblue;
  text-align: center;
} */
</style>
