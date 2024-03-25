<script setup>
    import { reactive } from 'vue';

    let search_word = reactive({
        "id":0,
        "word":'',
        "interpretation":'null',
        "prefix":'null',
        "root":'null',
        "subfix":'null',
        "explain":'null',
    })
    let showSuggestions = reactive(true)
    let suggestions = reactive([])

    let awordEmit = defineEmits(
        ['response']
    )


    function suggest(){
        console.log(search_word)
        fetch('http://localhost:8080/word/suggest',{
            method:'post',
            credentials:'include',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(search_word)
        }).then((response)=>{
            if (response.status!=200){
                alert(response.status)
            }
            return response.json()
        }).then((jsondata)=>{
            suggestions=jsondata
            console.log(suggestions)
        })
    }

    function search(){
        console.log(suggestions[0])
        fetch('http://localhost:8080/word/search',{
            method:'post',
            credentials:'include',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(suggestions[0])
        }).then((response)=>{
            if (response.status!=200){
                alert(response.status)
            }
            return response.json()
        }).then((jsondata)=>{
            console.log(jsondata)
            awordEmit('response',jsondata)
        })
    }

    function selectSuggestion(suggestion){
        console.log(suggestion)
        fetch('http://localhost:8080/word/search',{
            method:'post',
            credentials:'include',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(suggestion)
        }).then((response)=>{
            if (response.status!=200){
                alert(response.status)
            }
            return response.json()
        }).then((jsondata)=>{
            console.log(jsondata)
            awordEmit('response',jsondata)
        })

    }
</script>

<template>
    <div class="search-box">
            <label for="search-input">Search:</label>
            <input type="text" id="search-input" placeholder="Search word ..." v-model="search_word.word" @input="suggest">
            <button @click="search">Search</button>
            <div class="suggestions" v-if="showSuggestions">
                <div class="suggestion" v-for="(suggestion,index) in suggestions" :key="index" @click="selectSuggestion(suggestion)">
                    {{ suggestion.word }}-{{ suggestion.interpretation }}
                </div>
            </div>
        </div>
</template>


<style scoped>
.search-box {
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.search-box label {
  margin-bottom: 10px;
  font-weight: bold;
}

.search-box input[type="text"] {
  width: 80%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-box button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.search-box button:hover {
  background-color: #0056b3;
}

.suggestions {
  margin-top: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #f9f9f9;
  padding: 10px;
  max-height: 200px;
  overflow-y: auto;
}

.suggestion {
  padding: 5px;
  cursor: pointer;
  border-bottom: 1px solid #eee;
}

.suggestion:last-child {
  border-bottom: none;
}

.suggestion:hover {
  background-color: #e9e9e9;
}
</style>
