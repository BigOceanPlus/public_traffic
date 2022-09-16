<template>
  <div>
    <span>I sit at my window this morning where the world like a passer-by stopsfor a moment, nods to me and goes.</span>
    <el-divider />
    <span>There little thoughts are the rustle of leaves; they have their whisper of joy in my mind.</span>
  </div>
  <el-button @click="to('/test2/test3')"></el-button>
  <el-button @click="to('/test2/test4')"></el-button>
  <el-divider />
  <router-view />
</template>

<script setup>
import {useRouter} from "vue-router";

const router = useRouter()
const to = (url)=>{
  router.push(url)
}
</script>

<style scoped>

</style>