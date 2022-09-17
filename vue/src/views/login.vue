<template>
  <div id="layout">
    <el-container style="height: 100%; width: 100%;">

      <!-- 页面顶部 -->
      <el-header height="10%" id="login_header">

        <div id="header_icon">
          <!-- 顶部图标 -->
          <div>
            <img src="../../public/img/title.jpg" alt="" height="60" width="60" id="header_img">
          </div>

          <!-- 图标旁的元素 -->
          <div id="header_font">
            <span class="header_p">铁路客票</span>
            <span class="header_p">发售和分析平台</span>
            <span>CHINA RAILWAY</span>
          </div>

          <!-- 分隔符 -->
          <div id="header_border"></div>
          <div>
            <span id="header_p2">欢迎登录</span>
          </div>
        </div>
      </el-header>

      <el-container id="container">

        <el-main>

          <!-- 登录窗口 -->
          <div id="login">

            <!-- 登陆选项 -->
            <div id="logindiv_header"> 
              <span class="login_header">
                客流发售登录<el-icon><Position /></el-icon>&nbsp;
              </span>

              <el-divider direction="vertical" style="background-color: #3b99fc; width: 2px; height: 30px"></el-divider>

              <span class="login_header">
                客流分析登录<el-icon><PieChart /></el-icon>&nbsp;
              </span>

            </div>

            <!-- 登录表单 -->
            <el-form :model="user" :rules="rules" ref="ruleFormRef">
              <div id="login_input">
                <el-form-item prop="name" >
                  <el-input v-model="user.name" placeholder="用户名" :prefix-icon="User" maxlength="10" show-word-limit />
                </el-form-item>
                <el-form-item prop="password" >
                  <el-input v-model="user.password" show-password placeholder="密码" :prefix-icon="Lock" maxlength="12" />
                </el-form-item>
                <el-form-item prop="copy" >
                  <el-input v-model="user.copy" placeholder="密码确认" :prefix-icon="Lock" maxlength="12" show-word-limit show-password/>
                </el-form-item>
              </div>

              <div id="login_button">
                <el-form-item>
                  <el-col :span="13">
                    <el-form-item>
                      <el-button type="primary" @click="login">登 录</el-button>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item>
                      <el-button type="primary" @click="reset">重 置</el-button>
                    </el-form-item>
                  </el-col>
                </el-form-item>
              </div>

            </el-form>
          </div>

          <!-- 轮播图 -->
          <div class="swiper-container">
            <!-- 传送带 -->
            <div class="swiper-wrapper">
              <!-- 图片框 -->
              <div class="swiper-slide">
                <img src="../../public/img/Page0-1_.png" alt="">
              </div>
              <div class="swiper-slide">
                <img src="../../public/img/Page0-2_.png" alt="">
              </div>
              <div class="swiper-slide">
                <img src="../../public/img/Page0-3_.png" alt="">
              </div>
              <div class="swiper-slide">
                <img src="../../public/img/Page0-4_.png" alt="">
              </div>
              <div class="swiper-slide">
                <img src="../../public/img/Page0-5_.png" alt="">
              </div>
              <div class="swiper-slide">
                <img src="../../public/img/Page0-6_.png" alt="">
              </div>
            </div>
          </div>
        </el-main>

        <el-aside style="background-color: white; width: 15%"></el-aside>
      </el-container>

      <el-footer>
        <span class="bhead">@author: BigOcean/yy</span>
        <span class="bhead">@version: 1.0.0</span>
        <span class="bhead">@year: 2022 - 2022</span>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import {ref, reactive, getCurrentInstance, onUnmounted} from 'vue';
import {User,Lock} from '@element-plus/icons-vue'
import {ElNotification, ElMessage} from 'element-plus'
import request from "../request";
import { onMounted } from 'vue';
import router from "../router";

const flag = ref(0)
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
      request.post('/user/login', user).then(res => {
        if(res.code){
          //console.info(res.data[0].type)
          ElMessage({
            type: 'success',
            message: '登录成功'
          })
          localStorage.setItem("flag",res.data[0].type)

          localStorage.setItem("token","666")
          localStorage.setItem("startTime",new Date().getTime().toString())

          if(localStorage.getItem("flag") === "3")
            router.push('/manager')
          else if(localStorage.getItem("flag") === "2")
            router.push('/analysis')
          else if(localStorage.getItem("flag") === "1") {
            router.push({
              path: "/home",
              query: {
                name: user.name
              }
            })
          }
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
      ElNotification({
        title: "警告",
        type: 'error',
        message: '未通过表单校验'
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

onMounted(()=>{
  // 轮播图
  //获取元素
  var swiperWrapper=document.querySelector('.swiper-wrapper');
  var num=1;
  var length=0;
  swiperWrapper.style.transform='translateX('+ length +'px)';
  var timer=setInterval(function(){
    if(num<6){
      num++;
      length=length-1080;
      swiperWrapper.style.transform='translateX('+ length +'px)';
    }else{
      length=0;
      num=1;
      swiperWrapper.style.transform='translateX('+ length +'px)';
    }
  },5000);

  swiperWrapper.addEventListener('mouseenter',function(){
    clearInterval(timer);
  });
  swiperWrapper.addEventListener('mouseleave',function(){
    timer=setInterval(function(){
      if(num<6){
        num++;
        length=length-1080;
        swiperWrapper.style.transform='translateX('+ length +'px)';
      }else{
        length=0;
        num=1;
        swiperWrapper.style.transform='translateX('+ length +'px)';
      }
    },5000);
  });
})

</script>

<script>

</script>

<style>
/* 全局 */
*{
  margin: 0;
  padding: 0;
}

#layout{
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* 页面顶部 */
#login_header{
  display: flex;
  flex-direction: column;
  align-items: center;
  /* background: yellow; */
}

#header_icon{
  width: 1080px;
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
  line-height: 18px;
  font-size: 18px;
  font-family: 微软雅黑;
  color: #3b99fc;
}
#header_font span:nth-child(3){
  color: #888888;
  font-family: 'Times New Roman';
  font-size: 11px;
}
#header_border{
  height: 30px;
  padding-right: 10px;
  border-right: 2px solid #ccc;
}
#header_p2{
  padding-left: 10px;
  font-size: 30px;
  font-weight: 800;
  color: #3b99fc;
}


/* 页面container */
#container{
  height: 600px;
  width: 100%;
  background: #3b98fcec;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.el-main{
  width: 1080px;
  height: 600px;
  position: relative;
  display: flex;
  align-items: center;
  padding: 0;
  overflow-y: hidden;
}
/* 登录窗口 */
#login{
  width: 400px;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  height: 300px;
  background-color: rgba(226, 230, 237, 0.769);
  box-shadow: 7px 7px 17px rgba(52, 56, 66, 0.5);
  border-radius: 10px;
  position: absolute;
  z-index: 99;
  left: 60px;
}
#logindiv_header{
  height: 60px;
  width: 400px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  cursor: pointer;
}
.login_header{
  width: 150px;
  height: 60px;
  line-height: 60px;
  margin: 10px;
  font-size: 20px;
  border-radius: 0 !important;
}

#login_input{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}
#login_input .el-form-item{
  width: 250px;
  margin-bottom: 25px
}

#login_button{
  display: flex;
  justify-content: center;
  align-items: center;
}
#login_button .el-button:nth-child(1){
  margin: 0px 25px 5px 25px;
}

/* 背景轮播图 */
.swiper-container{
  width: 1080px;
  height: 640px;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
}
.swiper-container img{
  width: 1080px;
}
.swiper-wrapper{
  width:7560px;
  transition: all 1s;
}
.swiper-slide{
  float: left;
}

/* 页面底部 */
.el-footer{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 5%;
}
.el-footer span{
  margin: 10px;
}
</style>
