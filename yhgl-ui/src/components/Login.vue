<template>
  <div class="login_container">
   <div class="login_box">
    <div class="avatar_box">
    <img src="../assets/img/01.gif">
    </div>
     <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="0px" class="login_form">
      <el-form-item prop="username">
        <el-input v-model.trim="loginForm.username" placeholder="请输入用户名称" prefix-icon="iconfont icon-yonghuming3"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model.trim="loginForm.password" placeholder="请输入登录密码" type="password" prefix-icon="iconfont icon-mima"></el-input>
      </el-form-item>
      <el-form-item class="login_btn">
          <el-button type="primary" @click="login()">登 录</el-button>
          <el-button type="info" @click="resetForm('loginForm')">重 置</el-button>
        </el-form-item>
      </el-form>
   </div>
  </div>
</template>

<script>
export default {
  data() {
      return {
          loginForm: {
        username: 'admin',
        password: 'admin',
      },
       loginRules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
       }
      };
  },
  methods: {
      resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    login(){
        this.$refs.loginForm.validate(async valid =>{
            if(!valid) return;
            const {data :res} =await this.$http.get("/user/login");
            console.log(res);  
            if(res == 'ok'){
              window.sessionStorage.setItem("flag","ok");
                this.$message.success("操作成功");
                this.$router.push("/Main");
                console.log(res)
            }
            else{
                this.$message.error("操作失败");
            }
        })
    }
  },

}
</script>

<style lang="less" >
    .login_container{
        background-color: #2b4b6b;
        height: 100%;
    }
    .login_box{
        width: 300px;
        height: 300px;
        border-radius: 3px;
        background-color: #fff;
        border: 1px solid #EEEEEE;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        .avatar_box{
        width: 130px;
        height: 130px;
        background-color: #EEEEEE;
        border: 1px solid #EEEEEE;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        border-radius: 50%;
        padding: 0px;
        position: absolute;
        left: 50%;
        transform: translate(-50%,-50%);
        img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #EEEEEE;
        }
        }
    }
    .login_btn{
        display: flex;
        justify-content: flex-end;
    }
    .login_form{
        position: absolute;
        bottom: 0;
        width: 100%;
        padding: 0 10px;
        box-sizing: border-box;
    }
    .el-button{
      width: 100px;
      height: 50px;
    }
    
</style>
