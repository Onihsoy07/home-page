<template>
  <div>
    <div v-for="board in data.boardList" :key="board.id" style="border: solid black 1px; border-collapse: collapse;">
      <div>{{ board.title }}</div>
      <div>{{ board.contents }}</div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';

const data = reactive({
  boardList: []
});

function getBoardList() {
  fetch('http://localhost:9000/boards')
  .then(response => {
    console.log(response);
    return response.json();
  })
  .then(response => {
    console.log(response);
    data.boardList = response.data;
  });
}

onMounted(() => {
  getBoardList();
});

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
