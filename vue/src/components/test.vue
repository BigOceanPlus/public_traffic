<template>
  <el-form>
    <el-form-item>
      <slot name="label">
        自定义label飞:
        <!--<el-popover placement="top" :width="50" trigger="hover" content="我是说明提示">
          <template #reference>
            <el-icon style="vertical-align: sub; cursor: pointer"><Warning/></el-icon>
          </template>
        </el-popover>-->
        <el-icon style="vertical-align: sub; cursor: pointer"><Warning/></el-icon>
      </slot>
      <el-input style="width: 200px"/>
    </el-form-item>
  </el-form>
  <div ref="num_chart"  id="num_chart"></div>
</template>