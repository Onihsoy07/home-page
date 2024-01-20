<template>
  <div>
    <div v-for="board in data.boardList" :key="board.id" style="border: solid black 1px; border-collapse: collapse;">
      <div>{{ board.title }}</div>
      <div>{{ board.contents }}</div>
    </div>

    <div>
      <label for="title">제목</label>
      <input type="text" v-model="data.title" />
      <label for="contents">내용</label>
      <input type="text" v-model="data.contents" />
      <button type="button" @click="saveBoard">저장</button>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue';

const data = reactive({
  boardList: [],
  title: '',
  contents: '',
});

function getBoardList() {
  fetch('http://localhost:9000/boards')
  .then(response => response.json())
  .then(response => {
    data.boardList = response.data;
    console.log('get ', response);
  });
}

function saveBoard() {
  fetch('http://localhost:9000/boards', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => response.json())
  .then(response => {
    data.boardList = response.data;
    console.log('post ', response);
    getBoardList();
  })
  .catch(error => console.log(error));
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
