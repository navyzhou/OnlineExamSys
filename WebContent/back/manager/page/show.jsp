<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>源辰信息科技有限公司-在线考试系统</title>
<link rel="shortcut icon" type="image/x-icon" href="../../../easyui/images/yc.png"/>
<link rel="stylesheet" type="text/css" href="../../../css/exam.css" />
</head>
<body>
<header>
	<span>试卷编号：${testPaperInfo.pid }</span>
	<span>试卷名称：${testPaperInfo.pname }</span>
	<span>开考时间：${testPaperInfo.testTime }</span>
	<span>考试时长：${testPaperInfo.longExam }</span>
</header>
<div id="question_ans">
	<ul>
	<c:forEach items="${questionInfo }" var="item" varStatus="index">
		<li id="a_${item.qid }"><a href="#t_${item.qid }"><label>第${index.index+1 }题</label><span></span></a></li>
	</c:forEach>
	</ul>
</div>
<article>
<ul id="question_list">
<c:forEach items="${questionInfo }" var="item" varStatus="index">
	<li>
		<h4><a name="t_${item.qid }">${index.index+1 }、${item.qname }</a></h4>
		<c:if test="${item.tid == 1 or item.tid == 2}">
			<span>A、 ${item.ans1 }</span>
			<span>B、 ${item.ans2 }</span>
			<span>C、 ${item.ans3 }</span>
			<span>D、 ${item.ans4 }</span>
			<span><font color='green'>正确答案：${item.ans }</font></span>
		</c:if>
		<c:if test="${item.tid == 3}">
			<c:if test="${item.ans eq 0 }">
				<span><font color='green'>正确答案：错误</font></span>
			</c:if>
			<c:if test="${item.ans eq 1 }">
				<span><font color='green'>正确答案：正确</font></span>
			</c:if>
		</c:if>
		<c:if test="${item.tid == 4}">
			<span><font color='green'>正确答案：${item.ans }</font></span>
		</c:if>
	</li>
</c:forEach>
</ul>
</article>
<script type="text/javascript" src="../../../easyui/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#question_list input:text").blur(function() {
		var id = this.id;
		$("#a_" + id +"-4 span").text(this.value);
	});
})
</script>
</body>
</html>