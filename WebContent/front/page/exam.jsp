<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>源辰信息科技有限公司-在线考试系统</title>
<link rel="shortcut icon" type="image/x-icon" href="../../easyui/images/yc.png"/>
<link rel="stylesheet" type="text/css" href="../../css/exam.css" />
</head>
<body>
<header>
<span>当前考生：${currentLoginUser.sname }</span>
${testPaperInfo.pname }
<label id="surplustime"><span >${testPaperInfo.longExam }</span>分钟(剩余时间)</label>
</header>
<div id="question_ans">
	<ul>
	<c:forEach items="${questionInfo }" var="item" varStatus="index">
		<li id="a_${item.qid }-${item.tid }"><a href="#t_${item.qid }"><label>第${index.index+1 }题：</label><span></span></a></li>
	</c:forEach>
	</ul>
</div>
<article>
<ul id="question_list">
<c:forEach items="${questionInfo }" var="item" varStatus="index">
	<li>
		<h4><a name="t_${item.qid }">${index.index+1 }、${item.qname }</a></h4>
		<c:if test="${item.tid == 1}">
			<input type="radio" name="${item.qid }" value="${item.qid }-A-1"> &nbsp;A ${item.ans1 }<br/>
			<input type="radio" name="${item.qid }" value="${item.qid }-B-1"> &nbsp;B ${item.ans2 }<br/>
			<input type="radio" name="${item.qid }" value="${item.qid }-C-1"> &nbsp;C ${item.ans3 }<br/>
			<input type="radio" name="${item.qid }" value="${item.qid }-D-1"> &nbsp;D ${item.ans4 }<br/>
		</c:if>
		<c:if test="${item.tid == 2}">
			<input type="checkbox" name="${item.qid }" value="${item.qid }-A-2"> &nbsp;A ${item.ans1 }<br/>
			<input type="checkbox" name="${item.qid }" value="${item.qid }-B-2"> &nbsp;B ${item.ans2 }<br/>
			<input type="checkbox" name="${item.qid }" value="${item.qid }-C-2"> &nbsp;C ${item.ans3 }<br/>
			<input type="checkbox" name="${item.qid }" value="${item.qid }-D-2"> &nbsp;D ${item.ans4 }<br/>
		</c:if>
		<c:if test="${item.tid == 3}">
			<input type="radio" name="${item.qid }" value="${item.qid }-正确-3"> &nbsp;正确  &nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="${item.qid }" value="${item.qid }-错误-3"> &nbsp;错误
		</c:if>
		<c:if test="${item.tid == 4}">
			<input type="text" style="width: 90%" value="" id="${item.qid }"><br/>
		</c:if>
	</li>
</c:forEach>
</ul>
<div>
	<input type="button" value="交卷" onclick="assignment()"/>
</div>
</article>
<script type="text/javascript" src="../../easyui/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
var time = 0;
var second = 60;

var timer;

$(function(){
	time = parseInt($.trim($("#surplustime>span").text()));
	time--;
	timer=setInterval("countDown()",1000);

	// 给每个input绑定一个选择事件
	 $("#question_list input:radio,#question_list input:checkbox").click(function() {
		 var val = this.value;
		 val = val.split("-");
		 if (val[2] == "2") { // 说明是多选
			 var obj = $("#a_" + val[0] + "-" +val[2] + " span:eq(0)");
			 if (this.checked){ // 如果当前对象是选中的
				var temp = obj.text() + val[1];
			 	if (temp.length == 1){
			 		obj.text(temp);
			 	} else {
				 	var arr = [];
				 	// 然后排序
				 	for (var i=0, len=temp.length; i<len; i++){
				 		arr[arr.length] = temp.substr(i,1);
				 	}
				 	arr.sort();
				 	obj.text(arr.join(""));
			 	}
			 } else { // 如果是取消选择中的
				 obj.text( obj.text().replace(val[1],""));
			 }
		 
		 } else { // 说明是单选或判断
			 $("#a_" + val[0] + "-" +val[2] + " span").text(val[1]);
		 }
	 });
	
	$("#question_list input:text").blur(function() {
		var id = this.id;
		$("#a_" + id +"-4 span").text(this.value);
	});
})

// 禁止f5刷新
document.onkeydown = function (e) {
	var ev = window.event || e;
	var code = ev.keyCode || ev.which;
	if (code == 116) {
		ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
		cancelBubble = true;
		return false;
	}
}

// 禁止右击刷新
document.oncontextmenu=function(){
	return false
};


function countDown(){
	second--;
	if(second == 0){
		time--;
		second = 59;
	}
	
	var str1 = second<10?'0'+second:second;
	var str2 = time<10?'0'+time:time;
	
	$("#surplustime>span").text(str2+":"+str1);
	
	if (time==0 && second==0){
		clearInterval(timer);
		
		//自动交卷
		assignment();
	}
}

function assignment(){
	var ansStr="";
	var objs = $("#question_ans li");
	for(var i=0,len=objs.length; i<len; i++){
		var pid = $(objs[i]).attr("id");
		pid = pid.substring(pid.indexOf("_")+1);
		
		var ans = $(objs[i]).find("span").eq(0).text();
		if(ans==""){
			if(!confirm("您第 "+(i+1)+" 题未作答，确定要继续提交试卷吗？")){
				return false;
			}
			ans = " ";
		}
		pid = pid.split("-");
		
		if (pid[1]=="3"){ // 说明是判断题
			if ("正确"==ans){
				ans =1;
			} else if ("错误"==ans){
				ans =0;
			}
		}
		
		ansStr+=pid[0]+"-"+ans+"-"+pid[1]+",";
	}
	ansStr=ansStr.substring(0,ansStr.lastIndexOf(","));
	clearInterval(timer);
	
	var longTime = $("#surplustime>span").text();
	longTime = longTime.substring(0,longTime.indexOf(":"));
	$.post("../../examServlet", {op:"assignment", ans:ansStr, longTime:longTime}, function(data){
		data = parseInt($.trim(data));
		if (data>0){
			alert("交卷成功，考试结束...");
			window.close();
		} else {
			alert("试卷提交失败,请稍后重试...");
		}
	});
}
 
</script>
</body>
</html>