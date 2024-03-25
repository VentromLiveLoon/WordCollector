<script setup>
    import {ref} from 'vue'

    let word = ref({
        "id":0,
        "word":'',
        "interpretation":'',
        "prefix":'',
        "root":'',
        "subfix":'',
        "explain":'',
        "sentences":[{
            "sentence":'',
            "translated":'',
        }]
    })
    function checkWordList(){
        if (word.value.word.length>255){
            alert("Word too long")
            return false;
        }else if (word.value.interpretation.length>255){
            alert("Interpretation too long")
            return false;
        }else if (word.value.prefix.length>255){
            alert("Prefix too long")
            return false;
        }else if (word.value.root.length>255){
            alert("Root too long")
            return false;
        }else if (word.value.subfix.length>255){
            alert("Subfix too long")
            return false;
        }else if (word.value.explain.length>255){
            alert("Explain too long")
            return false;
        }else {
            word.value.sentences.forEach((sentence)=>{
                if (sentence.sentence.length>255){
                    return false;
                }else if (sentence.translated.length>255){
                    return false;
                }
            })
        }
        if (word.value.word.length==0){
            alert("Word is void!!")
            return false;
        }else if (word.value.interpretation.length==0){
            alert("Interpretation is void!!")
            return false;
        }else if (word.value.prefix.length==0){
            alert("Prefix is void!!")
            return false;
        }else if (word.value.root.length==0){
            alert("Root is void!!")
            return false;
        }else if (word.value.subfix.length==0){
            alert("Subfix is void!!")
            return false;
        }else if (word.value.explain.length==0){
            alert("Explain is void!!")
            return false;
        }
        return true;
    }
    function submit(){
        if (checkWordList()==false){
            return 
        }
        console.log(word.value)
        fetch ('http://localhost:8080/word/add',{
            method:'post',
            credentials:'include',
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(word.value)
        })
        .then((response)=>{
            console.log(response.status)
            if (response.status!=200){
                alert(response.status)
                return 
            }
            return response.text()
        })
        .then((data)=>{
            console.log(data)
            if (data!="ok"){
                alert(data)
            }else{
                alert(data)
                word.value={"sentences":[{"sentence":'',"translated":'',}]}
            }
        })
    }

    function addLine(){
        word.value.sentences.push({
                sentence:'',
                translated:''
            })
    }
    function reduceLine(){
        word.value.sentences.pop({
                sentence:'sentence',
                translated:'translated'
            })
    }

</script>

<template>
    

        <div id="formbody">
            <h1>Fill the word info!!</h1>
            <form>
            <div id="word">
            <label>Word::</label>
            <input type="text" placeholder="Word" v-model="word.word">
            </div>
            <div id="interpretation">
                <label>Interpretation::</label>
                <input type="text" placeholder="Interpretation" v-model="word.interpretation">
            </div>
            <div id="prefix">
                <label>Prefix::</label>
                <input type="text" placeholder="Prefix" v-model="word.prefix">
            </div>
            <div id="root">
                <label>Root::</label>
                <input type="text" placeholder="Root" v-model="word.root">
            </div>
            <div id="subfix">
                <label>Subfix::</label>
                <input type="text" placeholder="Subfix" v-model="word.subfix">
            </div>
            <div id="explain">
                <label>Explain::</label>
                <input type="text" placeholder="Explain" v-model="word.explain">
            </div>
            <p></p>
            <div v-for="(sentence ,index) in word.sentences" :key="index">
                <form>
                    <div class="sentence">
                        <label>Sentence::</label>
                        <input  type="text" placeholder="Sentence" v-model="sentence.sentence">
                    </div>
                    <div class="translated">
                        <label>Translated::</label>
                        <input type="text" placeholder="Translated" v-model="sentence.translated">
                    </div>
                </form>
            </div>
        </form>
            <div id="Operate">
                <button id="addline" @click="addLine">Add Line</button>
                <button id="reduce" @click="reduceLine">Reduce Line</button>
                <button id="submit" @click="submit">Submit</button>
            </div>
        </div>

</template>


<style scoped>
  #formbody {
    max-width: 600px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  #formbody label {
    display: block;
    margin-bottom: 5px;
  }

  #formbody input[type="text"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }

  .sentence, .translated {
    margin-bottom: 10px;
  }

  .sentence label, .translated label {
    display: inline-block;
    margin-right: 5px;
  }


  #addline {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 3px;
    background-color: #0026ff;
    color: white;
    cursor: pointer;
  }
  #reduce {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 3px;
    background-color: #ff3c00;
    color: white;
    cursor: pointer;
  }
  #submit {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 3px;
    background-color: #0de732;
    color: white;
    cursor: pointer;
  }

  button:hover {
    background-color: #0056b3;
  }

  button:active {
    background-color: #003d82;
  }
</style>
