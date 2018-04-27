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
	<span>学号：${currentLoginUser.sid }&nbsp;&nbsp;&nbsp;&nbsp;姓名：${currentLoginUser.sname }</span>
	<span>课程：${answerInfo.pname }</span>
	<span>分数：
		<c:choose>
			<c:when test="${answerInfo.score<60 }">
				<font color='red'>${answerInfo.score}</font>
			</c:when>
			<c:otherwise>
				<font color='greed'>${answerInfo.score}</font>
			</c:otherwise>
		</c:choose>
	</span>
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
			<span>您的选择 ：
				<c:choose>
					<c:when test="${item.cname eq item.ans }">
					 	<font color='green'>${item.ans }</font>
					</c:when>
					<c:otherwise>
						<font color='red'>${item.ans }</font>
					</c:otherwise>
				</c:choose>
			 &nbsp;&nbsp;&nbsp; 正确答案：${item.cname }</span>
		</c:if>
		<c:if test="${item.tid == 3 or item.tid == 4}">
			<span>您的答案 ：
				<c:choose>
					<c:when test="${item.cname eq item.ans }">
					 	<font color='green'>${item.ans }</font>
					</c:when>
					<c:otherwise>
						<font color='red'>${item.ans }</font>
					</c:otherwise>
				</c:choose>
			 &nbsp;&nbsp;&nbsp; 正确答案：${item.cname }</span>
		</c:if>
	</li>
</c:forEach>
</ul>
</article>
<script type="text/javascript" src="../../easyui/js/jquery-1.11.3.js"></script>
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