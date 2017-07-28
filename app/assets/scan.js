   function jsText(text){
         var list=document.getElementsByName("scan");
          for (var i=0;i<list.length;i++)
         {
           jsi.showToast('长度='+list[i].value.length);length
         if( list[i].value.length==0){
         switch(list[i].id){
         case 'waybill':
         list[i].value="运单号"
         break;
         default:
        list[i].value=text;
         break;
         }
//             if(list[i].id=='waybill'){
//               list[i].value="运单号"
//             }else{
//             list[i].value=text;
//             }
             if((i+1)!=list.length){
              list[i+1].focus();
             }
          break;
         }
                  }
        }
function init()
{
 var list=document.getElementsByName("scan");
 list[0].focus();
}