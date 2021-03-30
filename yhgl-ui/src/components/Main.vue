<template>
    
<el-container class="main_container">
  <el-header>
  <div>
    <img src="../assets/img/01.gif">
    <span>长泽雅美</span>
  </div>
  <el-button type="info" @click="logout()">退出登录</el-button>
  </el-header>
  <el-container>

    <el-aside width="200px">
    <el-menu
      background-color="#545c64" text-color="#fff" active-text-color="#F8F8FF" 
      unique-opened :router="true" >
      <el-submenu v-for="mainmenu in menuList" :index="mainmenu.id+''" :key="mainmenu.id">
        <template slot="title">
          <i :class="icons[mainmenu.id]"></i>&nbsp;
          <span>{{mainmenu.title}}</span>
        </template>
        <el-menu-item v-for="submenu in mainmenu.submenus" :index="submenu.path" :key="submenu.id">
          <template slot="title">
          <i :class="icons[submenu.id]"></i>&nbsp;
          <span>{{submenu.title}}</span>
          </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
    </el-aside>

    <el-main style="padding: 0%">
    <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
    
</template>

<script>
export default {
    data() {
      return {
        menuList: [],
        icons: {
          "100": "iconfont icon-liebiao",
          "200": "iconfont icon-shangpin1",
          "101": "iconfont icon-yonghuming1",
          "102": "iconfont icon-quanxian",
          "103": "iconfont icon-liebiao2",
          "201": "iconfont icon-shangpingouwudai",
          "202": "iconfont icon-shangpin",
        },
        activePath:'/welcome',
      }
    },
    created() {
      this.getMenuList()
    },
    methods: {
        logout(){
            window.sessionStorage.clear();
        this.$router.push("/Login");
    },
    async getMenuList(){
      const {data:res}=await this.$http.get("/menu/getMenus");
      console.log(res);
      if(res.code!=200){
        return this.$message.error("获取菜单列表失败");
      }
      this.menuList=res.data;
    }
    },
        
}
</script>

<style lang="less">
    .el-header{
        background-color: #373d41;
        display: flex;
        justify-content: space-between;// 左右贴边
        padding: 0%;
        color: #fff;
        font-size: 24px;
    > div { //左侧div加布局
        display: flex;
        align-items: center;
        .el-button{
            
        }
        img{
        width: 55px;
        height: 55px;
        border-radius: 50%;
        }
        .span{
           
        }
    }
    }
    .el-aside{
        background-color: #333744;
        .el-menu{
    border-right: none;// 对其右边框
  }
    }
    .el-main{
        background-color: #eaedf1;

    }
    .main_container{
        height: 100%;
    }
    .el-button{
    width: 100px;
    height: 45px;
    position: relative;
    top: 7.5px;
    }
</style>