<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>编码方案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="vcodeplanController.do?doUpdate" tiptype="1" beforeSubmit="checkdata()">
					<input id="id" name="id" type="hidden" value="${vcodeplanPage.id }">
					<input id="vdef1" name="vdef1" type="hidden" value="${vcodeplanPage.vdef1 }">
					<input id="vdef2" name="vdef2" type="hidden" value="${vcodeplanPage.vdef2 }">
					<input id="vdef3" name="vdef3" type="hidden" value="${vcodeplanPage.vdef3 }">
					<input id="vdef4" name="vdef4" type="hidden" value="${vcodeplanPage.vdef4 }">
					<input id="vdef5" name="vdef5" type="hidden" value="${vcodeplanPage.vdef5 }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								最大级数:
							</label>
						</td>
						<td class="value">
						     	 <input id="biglevel" name="biglevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.biglevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最大级数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								最大长度:
							</label>
						</td>
						<td class="value">
						     	 <input id="biglength" name="biglength" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.biglength}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最大长度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单级最大长度:
							</label>
						</td>
						<td class="value">
						     	 <input id="bigeachlength" name="bigeachlength" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.bigeachlength}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单级最大长度</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								第一级:
							</label>
						</td>
						<td class="value">
						     	 <input id="onelevel" name="onelevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.onelevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第一级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第二级:
							</label>
						</td>
						<td class="value">
						     	 <input id="twolevel" name="twolevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.twolevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第二级</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								第三级:
							</label>
						</td>
						<td class="value">
						     	 <input id="threelevel" name="threelevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.threelevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第三级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第四级:
							</label>
						</td>
						<td class="value">
						     	 <input id="fourlevel" name="fourlevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.fourlevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第四级</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								第五级:
							</label>
						</td>
						<td class="value">
						     	 <input id="fivelevel" name="fivelevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.fivelevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第五级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第六级:
							</label>
						</td>
						<td class="value">
						     	 <input id="sixlevel" name="sixlevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.sixlevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第六级</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								第七级:
							</label>
						</td>
						<td class="value">
						     	 <input id="sevenlevel" name="sevenlevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.sevenlevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第七级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第八级:
							</label>
						</td>
						<td class="value">
						     	 <input id="eightlevel" name="eightlevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.eightlevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第八级</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								第九级:
							</label>
						</td>
						<td class="value">
						     	 <input id="ninelevel" name="ninelevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.ninelevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第九级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								第十级:
							</label>
						</td>
						<td class="value">
						     	 <input id="tenlevel" name="tenlevel" type="text" style="width: 150px" class="inputxt"  value='${vcodeplanPage.tenlevel}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">第十级</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/cost/custom/vcodeplan/vcodeplan.js"></script>		
<script type="text/javascript">
     
  function checkdata(){
	 var biglevel=$("#biglevel").val()!=""?$("#biglevel").val():0;
	 var biglength=$("#biglength").val()!=""?$("#biglength").val():0;
	 var bigeachlength=$("#bigeachlength").val()!=""?$("#bigeachlength").val():0;
	 var onelevel=$("#onelevel").val()!=""?$("#onelevel").val():0;
	 var twolevel=$("#twolevel").val()!=""?$("#twolevel").val():0;
	 var threelevel=$("#threelevel").val()!=""?$("#threelevel").val():0;
	 var fourlevel=$("#fourlevel").val()!=""?$("#fourlevel").val():0;
	 var fivelevel=$("#fivelevel").val()!=""?$("#fivelevel").val():0;
	 var sixlevel=$("#sixlevel").val()!=""?$("#sixlevel").val():0;
     var sevenlevel=$("#sevenlevel").val()!=""?$("#sevenlevel").val():0;
     var eightlevel=$("#eightlevel").val()!=""?$("#eightlevel").val():0;
     var ninelevel=$("#ninelevel").val()!=""?$("#ninelevel").val():0;
     var tenlevel=$("#tenlevel").val()!=""?$("#tenlevel").val():0;
	  if(isNotEmpty(onelevel)){
	    if(!isPositiveInteger(onelevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(twolevel)){
	    if(!isPositiveInteger(twolevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(threelevel)){
	    if(!isPositiveInteger(threelevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(fourlevel)){
	    if(!isPositiveInteger(fourlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(fivelevel)){
	    if(!isPositiveInteger(fivelevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(sixlevel)){
	    if(!isPositiveInteger(sixlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(sevenlevel)){
	    if(!isPositiveInteger(sevenlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(eightlevel)){
	    if(!isPositiveInteger(eightlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(ninelevel)){
	    if(!isPositiveInteger(eightlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(tenlevel)){
	    if(!isPositiveInteger(tenlevel)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
	 if(isNotEmpty(bigeachlength)){
	    if(!isPositiveInteger(bigeachlength)){
		    alertTipTop("请输入正整数！");
			return false;
		}
	 }
     if(isNotEmpty(biglevel)){
         if(!isPositiveInteger(biglevel)){
		      alertTipTop("请输入正整数！");
			  return false;
		 }else{
            if(biglevel>10){
              alertTipTop("最大级数为10！");
			  return false;
			}else{
                if(biglevel==1){
					if(isNotEmpty(twolevel)||isNotEmpty(threelevel)||isNotEmpty(fourlevel)||isNotEmpty(fivelevel)||isNotEmpty(sixlevel)||isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为1");
						 return false;
				    }else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(onelevel>biglength){
						        alertTipTop("各级之和不能大于最大长度！");
								return false;
						       }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					}
				}
				 if(biglevel==2){
					if(isNotEmpty(threelevel)||isNotEmpty(fourlevel)||isNotEmpty(fivelevel)||isNotEmpty(sixlevel)||isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为2");
						 return false;
					}
					else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)>biglength){
						         alertTipTop("各级之和不能大于最大长度！");
								 return false;
						        }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==3){
					if(isNotEmpty(fourlevel)||isNotEmpty(fivelevel)||isNotEmpty(sixlevel)||isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为3");
						 return false;
					}else{
                        if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)>biglength){
						             alertTipTop("各级之和不能大于最大长度！");
									 return false;
						        }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==4){
					if(isNotEmpty(fivelevel)||isNotEmpty(sixlevel)||isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为4");
						 return false;
					}else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)>biglength){
						            alertTipTop("各级之和不能大于最大长度！");
									return false;
						       }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==5){
					if(isNotEmpty(sixlevel)||isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为5");
						 return false;
                    }else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)+parseInt(fivelevel)>biglength){
						             alertTipTop("各级之和不能大于最大长度！");
									 return false;
						       }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==6){
					if(isNotEmpty(sevenlevel)||isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为6");
						 return false;
					}else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)+parseInt(fivelevel)+parseInt(sixlevel)>biglength){
						             alertTipTop("各级之和不能大于最大长度！");
									 return false;
						       }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==7){
					if(isNotEmpty(eightlevel)||isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为7");
						 return false;
					}else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)+parseInt(fivelevel)+parseInt(sixlevel)+parseInt(sevenlevel)>biglength){
						              alertTipTop("各级之和不能大于最大长度！");
									  return false;
						       }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==8){
					if(isNotEmpty(ninelevel)||isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为8");
						 return false;
					}else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)+parseInt(fivelevel)+parseInt(sixlevel)+parseInt(sevenlevel)+parseInt(eightlevel)>biglength){
						              alertTipTop("各级之和不能大于最大长度！");
									  return false;
						        }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				if(biglevel==9){
					if(isNotEmpty(tenlevel)){
						 alertTipTop("最大级数为9");
						 return false;
					}else{
						if(isNotEmpty(biglength)){
							if(isPositiveInteger(biglength)){
							   if(parseInt(onelevel)+parseInt(twolevel)+parseInt(threelevel)+parseInt(fourlevel)+parseInt(fivelevel)+parseInt(sixlevel)+parseInt(sevenlevel)+parseInt(eightlevel)+parseInt(ninelevel)>biglength){
						               alertTipTop("各级之和不能大于最大长度！");
									   return false;
						        }
							}else{
							    alertTipTop("请输入正整数！");
								return false;
							} 
						}   
					    
					}
				}
				
			}
		 }
	 }
	 if(isNotEmpty(bigeachlength)){
	    if(!isPositiveInteger(bigeachlength)){
		    alertTipTop("请输入正整数！");
			return false;
		}else{
		   if(isNotEmpty(onelevel)&&onelevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(twolevel)&&twolevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(threelevel)&&threelevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(fourlevel)&&fourlevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(fivelevel)&&fivelevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(sixlevel)&&sixlevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(sevenlevel)&&sevenlevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(eightlevel)&&eightlevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(ninelevel)&&ninelevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }
		   if(isNotEmpty(tenlevel)&&tenlevel>bigeachlength){
			   alertTipTop("不能超过单级最大长度！");
			   return false;
		   }

		}
	 }
  }
  //非空判断
	function isNotEmpty(s)
	{
	return ((s != undefined && s != null && s != "") ? true : false);
	} 
  //正整数校验
   function isPositiveInteger(n){
     var re = /^[0-9]+$/ ;
     return re.test(n);
   } 
</script>