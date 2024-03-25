<script setup>
    import {reactive} from 'vue'
    import { ref } from 'vue';

    let words = ref([])

  fetch("http://localhost:8080/word/list",{
      method:"post",
      credentials:'include',
      headers:{
        "Content-Type":"application/json"
      },
      body:JSON.stringify({"id":0,"word":"","interpretation":""})
    }).then((response)=>{
        if (response.status!=200){
          alert(response.status);
          return 
        }
        return response.json()
    }).then((jsondata)=>{
      console.log(jsondata)
      words.value=words.value.concat(jsondata)
      console.log(words.value)
    }).catch((error)=>{
      alert("No more!!")
    })



    function listWord(){
      fetch("http://localhost:8080/word/list",{
        method:"post",
        credentials:'include',
        headers:{
          "Content-Type":"application/json"
        },
        body:JSON.stringify(words.value.slice(-1)[0])
      }).then((response)=>{
          if (response.status!=200){
            alert(response.status);
            return 
          }
          return response.json()
      }).then((jsondata)=>{
        console.log(jsondata)
        words.value=words.value.concat(jsondata)
        console.log(words.value)
      }).catch((error)=>{
        alert("No more!!")
      })
    }

    function deleteWord(word,index){
      console.log(word)
        fetch('http://localhost:8080/word/delete',{
            method:'post',
            credentials:'include',
            headers:{
                'Content-Type':"application/json"
            },
            body:JSON.stringify(word)
        })
        .then((response)=>{
            if (response.status!=200){
                alert(response.status);
                return 
            }
            return response.text()
        })
        .then((status)=>{
            console.log(status)
            if (status=="ok"){
              words.value.splice(index,1)
              alert(status);
            }
        })
    }
</script>


<template>
    <div>
        <h1>You word list</h1>
        <table>
            <tr class="tableheader" style="font-size: 40px;">
                <td>Word</td>
                <td>Interpretation</td>
                <td>Operate</td>
            </tr>
            <tr v-for="(word,index) in words" :key="index">
                <td>{{ index+1 }}</td>
                <td>{{ word.word }}</td>
                <td>{{  word.interpretation }}</td>
                <td><button @click="deleteWord(word,index)" class="operate">Delete</button></td>
            </tr>
        </table>
        <button @click="listWord">Load more</button>
    </div>
</template>


<style scoped>
  table {
    width: 100%;
    border-collapse: collapse;
  }

  table, th, td {
    border: 1px solid #ddd;
  }

  th, td {
    padding: 4px;
    text-align: left;
    font-size: 30px;
  }

  th {
    background-color: #f2f2f2;
  }

  button {
    padding: 10px 20px;
    margin-top: 10px;
    border: none;
    border-radius: 3px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
  }

  button:hover {
    background-color: #0056b3;
  }

  button:active {
    background-color: #003d82;
  }

  .operate{
    margin: 20px;
    border-radius: 3px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
    float: right;
  }

  
</style>
