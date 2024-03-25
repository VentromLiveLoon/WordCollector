<script>
    export default{
        data(){
            return{
                form:{
                    username:'',
                    password:'',
                }
            };
        },
        methods:{
            handleLogin(){
                console.log('login sender to server...');
                console.log(this.form.username);
                console.log(this.form.password)
                fetch("http://localhost:8080/user/login",{
                    method:'post',
                    credentials:'include',
                    headers:{
                        "Content-Type":"application/x-www-form-urlencoded"
                    },
                    body:new URLSearchParams(this.form).toString()
                }).then((response)=>{
                    if (response.status!=200){
                        alert(response.status)
                    }
                    return response.text()
                }).then((data)=>{
                    if(data=="ok"){
                        this.$router.push('/home')
                    }else{
                        alert(data)
                    }
                })
            }
        },
        created(){
            let cookies = document.cookie.split(";")
            let formdata = new URLSearchParams();
            cookies.forEach(cookie =>{
                let [name,value] = cookie.split('=')
                formdata.append(name,value);
            });
            console.log("cookie username::"+formdata.username)
            if (formdata.username!=undefined){
                this.$router.push('/home');
            }
        }
    };
</script>
<template>
    <div id="header">
        <h1>Login</h1>
    </div>
    <div id="formbody">
        <form @submit.prevent="handleLogin">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="form.username" required>

            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="form.password" required>

            </div>
            <div id="submit">
                <button  type="submit">Login</button>
            </div>
            <div >
                <RouterLink to="/register">Click to register</RouterLink>
            </div>
        </form>
    </div>
</template>
<style>
    #header{
        width: 100%;
        display: flex;
        justify-content: center;
    }
    #header h1{
        font-size: 100px;

    }

    #formbody{
        width: 100%;
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
    #formbody form div{
        margin-top: 20px;
    }
    #submit{
        width: 100%;
        display: flex;
        justify-content: center;
    }
    #submit button{
        width: 40%;
        display: flex;
        justify-content: center;
        color: white;
        background-color: green;
        border: none;
    }
    #submit button:hover{
        width: 40%;
        display: flex;
        justify-content: center;
        color: rgb(255, 255, 255);
        background-color: rgb(4, 51, 4);
        border: none;
    }
</style>
