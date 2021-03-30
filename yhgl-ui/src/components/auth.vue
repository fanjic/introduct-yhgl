<template>
    <div id="box">
    <div id="zhu" :style="{width: '500px', height: '400px'}">
        hello
    </div>
    <div id="bing" :style="{width: '500px', height: '400px'}">
        hello
    </div>
    <button @click="doBing()">GO</button>

    </div>
    
</template>

<script>
export default {
     name: 'hello',
     data () {
    return {
    //   dress: ["衬衫","羊毛衫","雪纺衫","工装裤","休闲鞋","卫衣"],
    //   dressNum: [5, 20, 36, 10, 25, 20],
      person: [
          {dress: "卫裤", dressNum: 89},
          {dress: "工装裤", dressNum: 459},
          {dress: "牛仔裤", dressNum: 159},
          {dress: "休闲裤", dressNum: 79},
          {dress: "九分裤", dressNum: 129},
      ]
    }
    },
    mounted() {
        this.doZhu();
        this.doBing();
    },
    methods: {
        doZhu(){
            let per=this.person;
            let dres=[];
            let dresNum=[];
            for(let i=0;i<per.length;i++){
                dres[i]=per[i].dress;
                dresNum[i]=per[i].dressNum;
            }
            console.log(dres);
            this.drawZhu(dres,dresNum);
        },
        doBing(){
            let pers=this.person;
            let perOnes=[];
            
            for(let i=0;i<pers.length;i++){
                let perOne={
                name:'',
                value:''
                };
                perOne.name = pers[i].dress;
                perOne.value = pers[i].dressNum;
                perOnes.push(perOne);
                //perOne={};
            }
            console.log(perOnes);
            this.drawBing(perOnes);
        },
        drawZhu(dres,dresNum){
            // 基于准备好的dom，初始化echarts实例
            let zhu = this.$echarts.init(document.getElementById('zhu'));
            zhu.setOption({
             xAxis: {
                type: 'category',
                data: dres
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: dresNum,
                type: 'bar'
            }]
          });
          },
        drawBing(perOnes){
             let bing = this.$echarts.init(document.getElementById('bing'));
            bing.setOption({
                    title: {
        text: '裤子价格',
        subtext: '纯属虚构',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '50%',
            data: perOnes,
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
            })
        }

    },//methods
}//script
</script>

<style lang="less">
    
    .box{
        margin: 0px;
        padding: 0px;
        display: flex;
        .zhu{
        width: 50%;
        justify-content: left;
        // display: inline-block;
        }
        .bing{
        width: 50%;
        float: left;
        justify-content: flex-end;
        }
    }
</style>