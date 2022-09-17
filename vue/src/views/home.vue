<template>

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
                          <span v-else-if="12 <= hours && hours <= 18">午班</span>
                          <span v-else>晚班</span>
                        </span>

                    </div>
                    <div class="header_menu"> 
                        <!-- <a href="javascriput:;" class="iconfont icon-icon-test15"></a> -->
                        <ul>
                            <li>
                                <span>欢迎使用！用户：</span>
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

        <el-menu-item index="1-1">售普通票</el-menu-item>

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
          <template #title>余票查询</template>
          <el-menu-item index="5-1-1">到站余票查询</el-menu-item>
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

        <el-menu-item index="6-1">暂停售票</el-menu-item>
        <el-menu-item index="6-2">结账退出</el-menu-item>
        <el-menu-item index="6-3">不结账退出</el-menu-item>
      </el-sub-menu>

      <!-- 帮助 -->
      <el-menu-item>帮助</el-menu-item>
    </el-menu>
  </div>

  <!-- 中间容器 -->
  <div class="home_container">
    <div class="table" >
      <div class="border">
        <!-- 表格 -->
        <el-table :data="tableData" border stripe height="430" style="width: 100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column type="index"  label="序号" align="center"/>
          <el-table-column prop="date" label="时间" width="65" align="center"/>
          <el-table-column prop="trainID" label="车次" width="65" align="center"/>
          <el-table-column prop="startAddress" label="发站" width="85" />
          <el-table-column prop="startTime" label="发点" width="65" align="center"/>
          <el-table-column prop="endAddress" label="到站" width="85" />
          <el-table-column prop="endTime" label="到时" width="65" align="center"/>
          <el-table-column prop="boxID" label="车厢" width="55" align="center"/>
          <el-table-column prop="setID" label="席位" width="55" align="center"/>
          <el-table-column prop="settype" label="席别" width="55" align="center"/>
          <el-table-column prop="PID" label="身份证号" width="175" />
          <el-table-column prop="tickeType" label="票种" width="65" align="center"/>
          <el-table-column prop="reason" label="事由" width="65" align="center"/>
          <el-table-column prop="amount1" sortable label="票价" align="center"/>
          
          <el-table-column align="right" width="135">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button
              >
              <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-button class="mt-4" style="width: 100%" height="20px" @click="onAddItem">新增票</el-button>
      </div>
      <div id="sale_info">
        <span>应收(￥):</span>
        <span>000.0</span>
        <span>实收：</span>
        <input type="number">
        <el-button width="30px">结算</el-button>
      </div>
    </div>
  </div>

  <!-- 页面底部 -->
  <div class="home_footer">
    <div id="footer_info">
      <span>@售票窗口</span>
      <span>@author：yy</span>
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
import {onBeforeMount, onMounted} from 'vue';
  import {useRoute} from "vue-router";

  const route = useRoute()
  console.log(route.query)


  //测试用数据
  const tableData= [
    {
      date: '05-03',
      trainID: 'G102',
      startAddress: '上海虹桥',
      startTime:'06:26',
      endAddress:'北京南',
      endTime:'12:29',
      boxID:'03',
      setID:'01A',
      settype:'二等',
      PID:110108200201010000,
      tickeType:'全',
      reason:'',
      amount1:553.0,
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-04',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
    {
      date: '2016-05-01',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    },
  ]
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
  html{
    min-height: 713px;
    max-height: 713px;
  }
/* ---顶部内容------------------------------------------------- */
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
    background: url(img/logo.jpg);/*logo的位置url*/
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
    background-image: url(img/train.png);
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
  widows: 100%;
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
/* ---中部样式------------------------------------------------- */
.home_container{
    height: 100%;
    min-height: 537px;
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
#sale_info{
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