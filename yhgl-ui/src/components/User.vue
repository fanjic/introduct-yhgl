<template>
    <div>
    <el-card>
    <el-row :gutter="25">
        <el-col :span="10">
         <el-input placeholder="请输入搜索内容" v-model.trim="queryInfo.query" clearable @clear="getUsers()">
            <el-button slot="append" icon="el-icon-search" @click="getUsers()"></el-button>
         </el-input>
         </el-col>
         <el-col :span="4">
          <el-button type="primary" @click="">添加用户</el-button>
        </el-col>
        </el-row>

        <el-table :data="userList" border tripe>
            <el-table-column  type="index"></el-table-column>
            <el-table-column label="用户名" prop="name"></el-table-column>
            <el-table-column label="性别" prop="gender"></el-table-column>
            <el-table-column label="年龄" prop="age"></el-table-column>
            <el-table-column label="角色" prop="role"></el-table-column>
            <el-table-column label="技能" prop="skill"></el-table-column>
            <el-table-column label="邮箱" prop="email"></el-table-column>
            <el-table-column label="操作" >
            <template slot-scope="scope" >
            <el-button type="primary" icon="el-icon-edit" size="mini" 
            @click="showUpdateDialog(scope.row.id)">修改</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" 
            @click="delOne(scope.row.id)">删除</el-button>
            </template>
            </el-table-column>
        </el-table>

        <div>
             <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum"
                :page-sizes="[5, 6, 7, 8]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
    </el-pagination>
        </div>
    </el-card>


  <el-dialog title="修改用户信息" width="40%" :visible.sync="updateDialogVisible" @close="updateDialogClosed()">
       <el-form :model="userForm" :rules="updateRules" ref="updateFormRef" label-width="70px">
           <!-- 用户名 -->
        <el-form-item label="用户名" prop="name">
          <el-input v-model="userForm.name" disabled></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="性别">
      <el-radio-group v-model="userForm.gender" prop="gender">
        <el-radio label="男" value="男"></el-radio>
        <el-radio label="女" value="女"></el-radio>
      </el-radio-group>
        </el-form-item>
        <!-- 年龄 -->
        <el-form-item label="年龄" prop="age">
          <el-input v-model="userForm.age"></el-input>
        </el-form-item>
        <!-- 技能 -->
        <el-form-item label="技能" prop="skill">
          <el-input v-model="userForm.skill"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email"></el-input>
        </el-form-item>
       </el-form>

       <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser()">确 定</el-button>
      </span>
  </el-dialog>
    
    </div>
</template>

<script>
export default {
        data() {
          var checkEmail = (rule, value, callback) => {
            const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
            if (!value) {
              return callback(new Error('邮箱不能为空'))
            }
              setTimeout(() => {
              if (mailReg.test(value)) {
                callback()
            } else {
                callback(new Error('请输入正确的邮箱格式'))
            }
            }, 100)
          }

            var checkage = (rule, value, callback) => {
            const mailReg = /^[1-9][0-9]{0,2}$/
             if (!value) {
              return callback(new Error('年龄不能为空'))
            }
              setTimeout(() => {
              if (mailReg.test(value)) {
                callback()
            } else {
                callback(new Error('请输入正确年龄'))
            }
            }, 100)
          }

      return {
            userList: [],
            total: 0,
            queryInfo: {
            query: "",
            pageNum: 1,
            pageSize: 6,
            },
            userForm: {},
            updateDialogVisible: false,
        updateRules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        email: [
          { validator: checkEmail, trigger: 'blur' }
        ],
        age: [
          { validator: checkage, trigger: 'blur' }
        ]
       },
        }
    },  
    created() {
        this.getUsers();
    },
    methods: {
       async getUsers(){
        const { data: res } = await this.$http.get("/user/getUsersBy", {
        params: this.queryInfo});
        console.log(res);
        this.userList=res.data.users;
        this.total=res.data.total;
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize=newSize;
            this.getUsers();
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum=newPage;
            this.getUsers();
        },
        async delOne(id){
            console.log(id);
            const delConfirm=  await this.$confirm('确认删除', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err => err)
      // 确认为confirm 取消为 cancel
      if(delConfirm!='confirm'){
        return this.$message.info("已取消删除");
      }
    const {data:res} = await this.$http.delete("user/delOne?id="+id);
     if (res != "ok") {
        return this.$message.error("删除失败");
      }
      this.getUsers();
      this.$message.success("删除成功");
        },
     async showUpdateDialog(id){
         const {data:res} = await this.$http.get("user/findOne?id="+id);
         this.userForm=res.data;
         console.log(res.data);
         this.updateDialogVisible= true;
      } ,
      updateDialogClosed(){
      this.$refs.updateFormRef.resetFields();
    },
      updateUser(){
        this.$refs.updateFormRef.validate(async valid =>{
        if( !valid ) return;
        console.log(this.userForm);
        const {data:res} = await this.$http.post("user/updateOne",this.userForm);
         if (res != "ok") return this.$message.error("操作失败！！！");
        this.$message.success("操作成功！！！");
        //隐藏
        this.updateDialogVisible = false;
        this.getUsers();
        });
      }
    },
}
</script>

<style lang="less">
    .el-col{
    .el-button{
    position: relative;
    top: -1px;
    }}
    
    .el-table{
    margin-top: 15px;
    font-size: 12px;
    min-width: 350px;
    .el-button{
    width: 70px;
    height: 40px;
    }}
</style>