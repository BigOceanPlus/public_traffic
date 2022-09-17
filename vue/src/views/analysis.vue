<template>
    <div class="common-layout">
      <el-container>
        <!-- 顶部容器 -->
        <el-header style="background:#061436">
          <span>铁路售票数据统计及分析平台</span>          
        </el-header>

        <!-- 中间容器 -->
        <el-main>

          <!-- 统计目前已售出的票数\人次(票数*2)\总计卖出的票价------------------------ -->
          <div id="up">
              <span>今日已售出<span>12</span>张票</span>
              <span>总计运输<span>24</span>人次</span>
              <span>今日总计收入<span>2400</span>元</span>
          </div>

          <!-- 统计不同发站售出票数 -->
          <div ref="num_chart"  id="num_chart"></div>
          <!-- 统计每日售票变化（数据库数据造假） -->
          <div ref="change_chart"  id="change_chart"></div>

          <!-- 显示各发站售票数 -->
          <div ref="o_chart"  id="o_chart"></div>
        </el-main>


        <!-- 底部容器 -->
        <el-footer style="background:#061436" width="8%">

        </el-footer>
      </el-container>
    </div>
  </template>

  <script>
    import * as echarts from "echarts"
    export default{
      mounted(){
        let num_chart=echarts.init(this.$refs.num_chart);
        let change_chart=echarts.init(this.$refs.change_chart);
        // let map_chart=echarts.init(this.$refs.map_chart);
        let o_chart=echarts.init(this.$refs.o_chart);

        num_chart.setOption({
          title:{
            text:"各站售票统计",
            // backgroundColor:"#9ebde9e1",
            borderColor:"red",
            x:"center",
            textStyle:{
              color:"white",
              fontSize:15,
            }
          },
          xAxis:{
            data:["北京南","天津南","衡水","济南西","苏州北","南京南","上海虹桥"],
            axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#ffffff'
                            }
                      },
            },
          yAxis:{

          },
          series:{
            name:"售票数",
            type:"bar",
            // /////////////////////////////各个地点的售票数
            data:[6300,4800,3200,6000,2000,5000,4500]
          },
          tooltip:{
            //触发类型
            trigger:"axis",
            axisPointer:{
              type:"shadow"
            }
          },
          grid:{
            //与绝对定位相似，top，left，right，bottom 设定是根据上级盒子宽高来计算
            left: '8%',
            right: '8%',
            bottom: '8%',
            // containLabel: true
          }
        })

        change_chart.setOption({
          title: {
            text: ''
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['北京南', '天津南', '衡水', '济南西', '苏州北','南京南','上海虹桥'],
            textStyle:{
              color:'white'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
            axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#ffffff'
                            }
                      },
          },
          yAxis: {
            type: 'value'
          },
          // ////////////////////////////////////数据读取及修改
          series: [
            {
              name: '北京南',
              type: 'line',
              stack: 'Total',
              data: [1200, 1320, 1010, 1340, 900, 2300, 2100]
            },
            {
              name: '天津南',
              type: 'line',
              stack: 'Total',
              data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
              name: '衡水',
              type: 'line',
              stack: 'Total',
              data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
              name: '济南西',
              type: 'line',
              stack: 'Total',
              data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
              name: '苏州北',
              type: 'line',
              stack: 'Total',
              data: [820, 932, 901, 934, 1290, 1330, 1320]
            },
            {
              name: '南京南',
              type: 'line',
              stack: 'Total',
              data: [660, 900, 901, 1000, 1400, 1200, 1320]
            },
            {
              name: '上海虹桥',
              type: 'line',
              stack: 'Total',
              data: [800, 900, 910, 930, 1300, 1300, 1300]
            }
          ]
        })

        // map_chart.setOption({

        // })

        o_chart.setOption({
          tooltip: {
            trigger: 'item'
          },
          legend: {
            left: '1%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
            orient:"verical",  
            textStyle:{
                color:'white'
            }
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center',
                textStyle: {
                    color: "#42436A"
                },
              },
              emphasis: {
                label: {
                  color:"white",
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [
                { value: 1048, name: '北京南' },
                { value: 735, name: '天津南' },
                { value: 580, name: '衡水' },
                { value: 484, name: '济南西' },
                { value: 300, name: '苏州北' },
                { value: 300, name: '南京南' },
                { value: 300, name: '上海虹桥' },
              ]
            }
          ]
        })
      }
    }
  </script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
  .el-container{
    height: 100vh;
    display: flex;
    flex-direction: column;
    background: #061436;
  }
  .el-header{
    flex-shrink: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 30px;
    border-bottom: 2px solid #3b99fc;
  }
  .el-header span{
    color:#23ecff;
    font-family: "宋体";
  }
  .el-main{
    display: flex;
    flex-direction: column;
    flex: 1;
    background: no-repeat center url(../../public/img/map.png);
    background-size:60% 100%; 
  }
  .el-footer{
    flex-shrink: 0;
  }
  #num_chart{
    width: 540px;
    height: 300px;
    border: 1px solid #3b99fc;
    border-radius: 20px;
    background: rgba(177, 168, 168, 0.189);
    box-shadow: 1px 1px 1px rgba(237, 235, 235, 0.479);
    margin-right: 20px;
    position: absolute;
    left: 10px;
    bottom: 20px;
  }
  #change_chart{
    width: 540px;
    height: 300px;
    border: 1px solid #3b99fc;
    border-radius: 20px;
    background: rgba(177, 168, 168, 0.189);
    box-shadow: 1px 1px 1px rgba(237, 235, 235, 0.479);
    display: flex;
    position: absolute;
    right: 10px;
    bottom: 20px;
  }
  /* #map_chart{

  } */
  #o_chart{
    width: 300px;
    height: 300px;
    border: 1px solid #3b99fc;
    border-radius: 20px;
    background: rgba(177, 168, 168, 0.189);
    box-shadow: 1px 1px 1px rgba(237, 235, 235, 0.479);
    position: absolute;
    right: 10px;
    top: 10px;
  }
  #up{
    height: 300px;
    position: absolute;
    top: 60px;
    left: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }

  #up>span{
    height: 40px;
    width: 240px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    text-indent: 4px;
    font-size: 20px;
    color: aliceblue;
    background:#3b99fc;
    border-radius: 20px;
  }
  #up>span>span{
    color:#061436;
    font-weight: 700;
    font-size: 22px;
  }
</style>