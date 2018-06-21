//全部选中和取消
function changeType(obj){
    var flag = obj.checked;
    var iboxs = document.getElementsByName("ibox");
    if(flag){//判断是否全选
        for(var i=0;i<iboxs.length;i++){
            iboxs[i].checked=true;
        }
    }else{
        for(var i=0;i<iboxs.length;i++){
            iboxs[i].checked=false;
        }
    }
}
//是否有被选中元素
function isChoosed(){
    var iboxs = document.getElementsByName("ibox");
    if(iboxs.length>0){
        for(var i=0;i<iboxs.length;i++){
            if(iboxs[i].checked){
                return true;
            }
        }
    }
    return false;
}
//获取所有选择的值用‘，’链接
function paramValue(param){
    if(!isChoosed()){
        alert("请选择要删除的数据！");
        return;
    }
    var iboxs = document.getElementsByName("ibox");
    if(iboxs.length>0){
        for(var i=0;i<iboxs.length;i++){
            if(iboxs[i].checked){
                param+=iboxs[i].value+",";
            }
        }
    }
    return param;
}