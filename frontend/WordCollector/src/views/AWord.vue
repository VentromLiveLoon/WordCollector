<script setup>
    import { reactive } from 'vue';
    import { useRoute,useRouter } from 'vue-router';
    import  SearchBox  from "../components/SearchBox.vue"

    const router = useRouter()

    

    let word = reactive({
        "id":0,
        "word":'null',
        "interpretation":'null',
        "prefix":'null',
        "root":'null',
        "subfix":'null',
        "explain":'null',
        "sentences":[{
            "sentence":'null',
            "translated":'null',
        }]
    })
    

    console.log(word)

    function preWord(){
        fetch('http://localhost:8080/word/pre',{
            method:'post',
            credentials:'include',
            headers:{
                'Content-Type': 'application/json',
            },
            body:JSON.stringify(word)
        })
        .then((response)=>{
            if (response.status!=200){
                alert(response.status);
                return 
            }
            return response.json()
        })
        .then((json)=>{
            console.log(json)
            packageWord(json);
        })
    }
    function nextWord(){
        fetch('http://localhost:8080/word/next',{
            method:'post',
            credentials:'include',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(word)
        })
        .then((response)=>{
            if (response.status!=200){
                alert(response.status);
                return 
            }
            return response.json()
        })
        .then((json)=>{
            console.log(json)
            packageWord(json);
        })
    }
    function deleteWord(){
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
            alert(status);
        })
    }
    function addWord(){
        router.push("/word/add")
    }


    function packageWord(jsonword){
        word.id=jsonword.id;
        word.word=jsonword.word;
        word.interpretation=jsonword.interpretation;
        word.prefix=jsonword.prefix;
        word.root=jsonword.root;
        word.subfix = jsonword.subfix;
        word.explain=jsonword.explain;
        word.sentences=jsonword.sentences;
    }

    nextWord();
</script>
<template>
    <h1>Word check here!!</h1>
    
    <RouterLink to="/word/list">Click to watch list</RouterLink>

    <div id="word-body">

        <SearchBox @response="(search_json_data)=>packageWord(search_json_data) "></SearchBox>

        <div id="word"> 
            <h1>{{ word.word }}</h1>
        </div>
        <div id="interpretation"> 
            <h2>{{ word.interpretation }}</h2>
        </div>
        <div id="word-struct"> 
            <h2>Word Struct::</h2>
            <p>{{ word.prefix }}</p>
            <p>{{ word.root }}</p>
            <p>{{ word.subfix }}</p>
        </div>
        <div id="explain">
            <h2>Explain::</h2>
            <p>
                {{ word.explain }}
            </p>
        </div>
        <div id="sentences">
            <h2>Sentences::</h2>
            <div class="sentence" v-for="(sentence,index) in word.sentences" v-bind:key="index">
                <p></p>
                <p>{{ sentence.sentence }}</p>
                <p>{{ sentence.translated }}</p>
            </div>
        </div>
        <div id="operate">
            <button @click="preWord">Pre</button>
            <button @click="nextWord">Next</button>
            <button id="delete" @click="deleteWord">Delete</button>
            <button id="add" @click="addWord">Add</button>
        </div>
    </div>
    
</template>
<style scoped>

#word-body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;

  background-color: #f4f4f4;
}

#word, #interpretation, #word-struct, #explain, #sentences, #operate {
  margin: 10px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

#word h1, #interpretation h2, #word-struct h2, #explain h2, #sentences h2 {
  margin-top: 0;
}

.sentence p {
  margin: 5px 0;
}

button {
  margin: 10px;
  padding: 10px 80px;
  margin-left: 5%;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;

}
#delete{
    margin: 10px;
  padding: 10px 80px;
  margin-left: 5%;
  border: none;
  border-radius: 5px;
  background-color: #e60c0c;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
#add{
    margin: 10px;
  padding: 10px 80px;
  margin-left: 5%;
  border: none;
  border-radius: 5px;
  background-color: #0ce61e;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

@media (min-width: 768px) {
  #word-body {
    flex-direction: row;
    justify-content: space-between;
  }

  #word, #interpretation, #word-struct, #explain, #sentences {
    flex: 1;
    margin: 10px;
  }

  #operate {
    margin-top: 0;
    bottom: 0;
    width: 100%;
  }
}



</style>

