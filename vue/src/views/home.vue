<template>
  <div class="Head">
    <!-- 头部信息栏 -->
    <div class="header">
          <div class="header_top">
              <div class="header_center">
                  <!-- logo -->
                  <a href="https://www.12306.cn/index/" title="友情链接12306官网"><div class="logo"></div></a>
                  <!-- 右侧内容 -->
                  <div class="header_right">
                      <!-- 信息栏 -->
                      <div  id="status">
                          <span>北京南站</span>
                          <span>003号窗口</span>
                        <span>
                          <span v-if="hours >= 6 && hours <= 12">早班</span>
                          <span v-else-if="12 < hours && hours <= 18">午班</span>
                          <span v-else>晚班</span>
                        </span>
                      </div>
                      <div class="header_menu"> 
                          <!-- <a href="javascriput:;" class="iconfont icon-icon-test15"></a> -->
                          <ul>
                              <li>
                                  <span>欢迎使用！工号：</span>
                                  <span id="number">
                                      {{route.query?.name}}
                                  </span>
                              </li>
                          </ul>
                      </div>
                  </div>
              </div>
          </div>
    </div>
  
    <!-- 顶部二级菜单 -->
    <div class="header_sub_menu">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect" id="header_sub_menu">
  
        <!-- 售票 -->
        <el-sub-menu index="1">
          <template #title>售票</template>
  
          <!-- 售普通票的按钮默认选中 -->
          <el-menu-item index="1-1"><router-link to="/home/sale">售普通票</router-link></el-menu-item>
  
          <el-sub-menu index="1-2">
            <template #title>售通票</template>
            <el-menu-item index="1-2-1">售普客到底通票</el-menu-item>
            <el-menu-item index="1-2-2">售普快到底通票</el-menu-item>
            <el-menu-item index="1-2-3">售普特到底通票</el-menu-item>
          </el-sub-menu>
  
          <el-sub-menu index="1-3">
            <template #title>签证</template>
            <el-menu-item index="1-3-1">普客到底签证</el-menu-item>
            <el-menu-item index="1-3-2">普快到底签证</el-menu-item>
            <el-menu-item index="1-3-3">普特到底签证</el-menu-item>
            <el-menu-item index="1-3-4">中途下车签证</el-menu-item>
            <el-menu-item index="1-3-5">始发签证</el-menu-item>
          </el-sub-menu>
  
          <el-sub-menu index="1-4">
            <template #title>售其他票</template>
            <el-menu-item index="1-4-1">售同席孩票</el-menu-item>
            <el-menu-item index="1-4-2">售团体票</el-menu-item>
            <el-menu-item index="1-4-3">售优惠票</el-menu-item>
            <el-menu-item index="1-4-4">售联程票</el-menu-item>
          </el-sub-menu>
  
        </el-sub-menu>
  
        <!-- 辅助 -->
        <el-sub-menu index="2">
          <template #title>辅助</template>
  
          <el-menu-item index="2-1">用途选择</el-menu-item>
          <el-menu-item index="2-2">客票取消</el-menu-item>
          <el-menu-item index="2-3">密码变更</el-menu-item>
          <el-menu-item index="2-4">特殊功能赋权</el-menu-item>
          <el-menu-item index="2-5">日历显示</el-menu-item>
          <el-sub-menu index="2-6">
            <template #title>初始化</template>
            <el-menu-item index="2-6-1">初始化学生读卡</el-menu-item>
            <el-menu-item index="2-6-2">初始化银行POS设备</el-menu-item>
            <el-menu-item index="2-6-3">初始化实名制设备</el-menu-item>
            <el-menu-item index="2-6-4">二维码平台认证</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2-7">软POS处理</el-menu-item>
        </el-sub-menu>
  
        <!-- 制票 -->
        <el-sub-menu index="3">
          <template #title>制票</template>
  
          <el-menu-item index="3-1">菜单</el-menu-item>
          <el-menu-item index="3-2">制票机重初始化</el-menu-item>
          <el-menu-item index="3-3">查看制票机票号</el-menu-item>
          <el-menu-item index="3-4">实名制车票挂失</el-menu-item>
          <el-menu-item index="3-5">失信补票</el-menu-item>
          <el-menu-item index="3-6">排票</el-menu-item>
          <el-menu-item index="3-7">合同制票</el-menu-item>
          <el-menu-item index="3-8">制票压单处理</el-menu-item>
          <el-menu-item index="3-9">应急换票</el-menu-item>
          <el-menu-item index="3-10">购票信息单打印</el-menu-item>
        </el-sub-menu>
  
        <!-- 处理 -->
        <el-sub-menu index="4">
          <template #title>处理</template>
          <el-menu-item index="4-1">菜单</el-menu-item>
          <el-menu-item index="4-2">废票处理</el-menu-item>
          <el-menu-item index="4-3">电子客票误售处理</el-menu-item>
          <el-menu-item index="4-4">空白票处理</el-menu-item>
          <el-menu-item index="4-5">补制空白票</el-menu-item>
          <el-menu-item index="4-6">制票故障处理</el-menu-item>
          <el-menu-item index="4-7">身份信息预采集</el-menu-item>
          <el-menu-item index="4-8">用户信息核验</el-menu-item>
          <el-menu-item index="4-9">用户抢注处理</el-menu-item>
          <el-menu-item index="4-10">优惠资质采集</el-menu-item>
          <el-menu-item index="4-11">废保处理</el-menu-item>
        </el-sub-menu>
  
        <!-- 查询 -->
        <el-sub-menu index="5">
          <template #title>查询</template>
  
          <el-sub-menu index="5-1">
            <!-- 跳转到车辆信息查询页,回退的时候依旧保留订单池中的订单,不会消失 -->
            <template #title>余票查询</template>
            <el-menu-item index="5-1-1"><router-link to="/home/selector">到站余票查询</router-link></el-menu-item>
            <el-menu-item index="5-1-2">车次余票查询</el-menu-item>
            <el-menu-item index="5-1-3">指定车次多日余票查询</el-menu-item>
          </el-sub-menu>
  
          <el-sub-menu index="5-2">
            <template #title>日志一览</template>
            <el-menu-item index="5-2-1">交易显示</el-menu-item>
            <el-menu-item index="5-2-2">废票一览</el-menu-item>
            <el-menu-item index="5-2-3">误售处理一览</el-menu-item>
            <el-menu-item index="5-2-4">电子客票交易一览</el-menu-item>
            <el-menu-item index="5-2-5">签证一览</el-menu-item>
          </el-sub-menu>
  
          <el-sub-menu index="5-3">
            <template #title>其他查询</template>
            <el-menu-item index="5-3-1">命令查询</el-menu-item>
            <el-menu-item index="5-3-2">票额共用查询</el-menu-item>
            <el-menu-item index="5-3-3">保单查询</el-menu-item>
            <el-menu-item index="5-3-4">优惠资质查询</el-menu-item>
            <el-menu-item index="5-3-5">全国重点地区人员查询</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
  
        <!-- 交班 -->
        <el-sub-menu index="6">
          <template #title>交班</template>
          <!-- 弹窗禁止窗口操作 -->
          <el-menu-item index="6-1">暂停售票</el-menu-item>
          <!-- 判断当前是否有未结算的订单,若有则拒绝退出 -->
          <el-menu-item index="6-2" @click="quit1">结账退出</el-menu-item>
          <!-- 不论是否有订单,直接退出 -->
          <el-menu-item index="6-3" @click="quit2">不结账退出</el-menu-item>
        </el-sub-menu>
  
        <!-- 帮助:点击下载说明文档-->
        <el-menu-item><a href="../../public/项目说明 登陆部分+售票模块(菜单).pdf" download="help.pdf" id="help">帮助</a></el-menu-item>
      </el-menu>
    </div>
  </div>
  
    <!-- 中间容器 -->
      <!-- 信息填空表格 -->
      <div  class="home_container">
        <router-view></router-view>

            <!-- 统计目前订单池中的订单的总票价 -->
        <div id="sale_info">
          <span>应收(￥):</span>
          <span>{{ total }}</span>
          <span>实收：</span>
          <input type="number">

          <!-- 将应收与实收输入的数据相比较用来结算当前的订单,同时将数据传回数据库,对应的票额-1 -->
          <el-button width="30px">结算</el-button>
        </div>
      </div>
    <!-- 页面底部 -->
    <div class="home_footer">
      <div id="footer_info">
        <span>@售票窗口</span>
        <span>@author:yy</span>
        <span>@2022-9-10</span>
      </div>
      <div id="footer_timer">
              <ul>
                  <li>
                      <span id="year">2022年9月8日</span>
                      <span id="week">星期四</span>
                  </li>
                  <li>
                      <span id="hour">12:00:00</span>
                  </li>           
              </ul>
          </div>
          <div id="footer_dayline">
              <div class='calendar' id='calendar'></div>
          </div>
    </div>
  </template>
  
  <script setup>
  import {onMounted, ref, reactive} from 'vue';
    import {ElNotification} from "element-plus";
    import {useRoute, useRouter} from "vue-router"
    const total = ref(0)
    const route = useRoute()
    const router = useRouter()

    const quit1 = () => {
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
    const quit2 = () => {
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
 
    onMounted(()=>{
      var year=document.querySelector('#year');
      var week=document.querySelector('#week');
      var hour=document.querySelector('#hour');
  
      var timer=setInterval(function(){
          var now=new Date();
  
          // year
          var actualMonth=now.getMonth()+1;
          year.innerHTML=now.getFullYear()+'年'+actualMonth+'月'+now.getDate()+'日';
          
          // week
          var Week=now.getDay();
          switch(Week){
              case 1: week.innerHTML='星期一';break;
              case 2: week.innerHTML='星期二';break;
              case 3: week.innerHTML='星期三';break;
              case 4: week.innerHTML='星期四';break;
              case 5: week.innerHTML='星期五';break;
              case 6: week.innerHTML='星期六';break;
              case 0: week.innerHTML='星期日';break;
          }
          
          // hour
          var Hour=now.getHours();
          var Minutes=now.getMinutes();
          var Seconds=now.getSeconds();
          var actualHour=Hour;
          var actualMinutes=Minutes;
          var actualSeconds=Seconds;
          if(Hour/10<1){
              actualHour='0'+Hour;
          }
          if(Minutes/10<1){
              actualMinutes='0'+Minutes;
          }
          if(Seconds/10<1){
              actualSeconds='0'+Seconds;
          }
          hour.innerHTML=actualHour+':'+actualMinutes+':'+actualSeconds;
      },1000);
  
    });

    const hours = new Date().getHours()
  
  </script>
  
  
  <style>
    /* 全局 */
    *{
      margin: 0;
      padding: 0;
    }
    #app{
      height: 100vh;
      display: flex;
      flex-direction: column;
    }
  /* ---顶部内容------------------------------------------------- */
  .Head{
       flex-shrink: 0; 
  }
  .header{
      display: flex;
      flex-direction: column;
  }
  
  .header_center{
      width: 1190px;
      margin: 0 auto;
      height: 80px;
      display: flex;
      flex-direction: row;
      align-items: center;
  }
  
  /* --左侧logo-- */
  .logo{
      width: 200px;
      min-width: 200px;
      height: 50px;
      margin-left: 5px;
      background: url(/img/logo.jpg);/*logo的位置url*/
      float: left;
  }
  
  /* --右侧内容-- */
  .header_right{
      width: 100%;
      margin-left: 30px;
      height: 42px;
      float: right;
      display: flex;
      flex-direction: row-reverse;
      align-items: center;
      background-image: url(/img/train.png);
  }
  /* -右侧信息栏- */
  .header_menu{
      width: 252px;
      height: 42px;
      display: flex;
      float: right;
  }
  .header_menu a{
      text-decoration: none;
      width: 42px;
      height: 42px;
      line-height: 42px;
      font-size: 30px;
      text-indent: 6px;
      float: left;
      margin-right: 10px;
  }
  .header_menu a:link{
      color: black;
  }
  .header_menu a:visited{
      color: black;
  }
  .header_menu ul{
      width: 200px;
      height: 42px;
      list-style: none;
      float: left;
      display: flex;
      flex-direction: row;
      align-items: center;
  }
  .header_menu ul li{
      float: left;
  }
  
  .header_menu ul li a{
     text-decoration: none;
      padding: 3px;
      border-right: 2px solid #3b99fc;
      font-size: 12px;
  }
  
  #status{
      float: right;
  }
  
  /* 导航栏 */
  .header_sub_menu{
    width: 100%;
    height: 40px;
    min-width: 1190px;
    display: flex;
    justify-content: center;
    background: #3b99fc;
  }
  #header_sub_menu{
    width: 1190px;
    border-top: 1px solid #3b99fc;
    border-bottom: 1px solid #3b99fc;
  }
  a{
    text-decoration: none !important;;
  }
  a:link,a:visited{
    color: black;
  }

  #help{
    text-decoration: none;
  }
  /* ---中部样式------------------------------------------------- */
  .home_container{
        height: 100%;
        min-height: 537px;
        display: flex;
        flex-direction: column;
        align-items: center;
        flex: 1;
  }
  #sale_info{
    height: 40px;
      width: 1080px;
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
  }
  #sale_info>span:nth-child(1){
    width: 80px;
    height: 40px;
    line-height: 40px;
    
  }
  #sale_info span:nth-child(2){
    width: 100px;
    height: 40px;
    line-height: 40px;
  }
  #sale_info span:nth-child(3){
    width: 50px;
    height: 40px;
    line-height: 40px;
  }
  #sale_info input{
    height: 25px;
    margin-right: 100px;
  }

  /* ---底部整体设置--------------------------------------------- */
  .home_footer{
      height: 56px;
      width: 100vw;
      min-width: 1190px;
      background: #3b99fc;
      display: flex;
      align-items: center;
      flex-shrink: 0;
  }
  
  #footer_info{
    height: 25px;
    color: aliceblue; 
  }
  #footer_info span{
    margin: 10px;
  }
  ul,li{
      margin: 0;
      padding: 0;
      list-style: none;
  }
  /* *************时间显示************* */
  #footer_timer{
      position: absolute;
      right: 10px;
      bottom: 10px;
      opacity: 0.8;
      background: rgba(237, 235, 235, 0.479);
      box-shadow: 1px 1px 1px black;
  }
  #footer_timer ul{
      display: flex;
      flex-direction: column;
      align-items: center;
      height: 80px;   
  }
  #footer_timer ul li:nth-child(1){
      display: flex;
      align-items: center;
      height: 20px;
      width: 205px;
  }
  #week{
      height: 20px;
      width: 80px;
      font-size: 14px;
      line-height: 20px;
      text-align: center;
  }
  #year{
      height: 20px;
      width: 125px;
      font-size: 14px;
      line-height: 20px;
      text-align: center;
  }   
  #footer_timer ul li:nth-child(2){
      display: flex;
      align-items: center;
      justify-content: center;
      height: 60px;
      width: 205px;
  }
  #hour{
      height: 60px;
      width: 205px;
      font-size: 46px;
      text-align: center;
  }

  </style>