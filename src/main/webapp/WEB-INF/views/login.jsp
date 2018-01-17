<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>高端装备设计知识库管理系统--登录</title>
		<link rel="stylesheet" href="resources/css/lib/materialize.min.css" />
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="resources/css/src/login.css" />
		
	</head>

	<body>
		<div><h3 id="tips">为了更好的用户体验，请使用谷歌、火狐的最新版浏览器</h3></div>
		<div class="row">
			<form id="loginPanel" class="col s6">
				<div class="row">
					<div class="input-field col s12 center">
						<p id="SystemTitle">高端装备设计知识库管理系统</p>
					</div>
				</div>
				<div class="row" style="margin-top: 20px;">
					<div class="input-field col s12">
						<i class="small material-icons prefix">person_pin</i>
						<input id="username" type="text" class="validate">
						<label id="usernameLabel" for="text">账号</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<i class="small material-icons prefix">lock</i>
						<input id="password" type="password" class="validate">
						<label id="passwordLabel" for="password">密码</label>
					</div>
				</div>
				<div id="messageDiv">
					<p id="loginMsg"></p>
				</div>
				<div class="row">
					<div class="input-field col s12 center">
						<a id="submitBtn" class="waves-effect waves-light btn"><i class="material-icons right">send</i>提交</a>
					</div>
				</div>
			</form>
		</div>
		<div id="circleProgress" class="preloader-wrapper big active">
			<div class="spinner-layer spinner-green-only">
				<div class="circle-clipper left">
					<div class="circle"></div>
				</div>
				<div class="gap-patch">
					<div class="circle"></div>
				</div>
				<div class="circle-clipper right">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">

		var agent = navigator.userAgent.toLowerCase();
		var ui =document.getElementById("tips");
		if (agent.indexOf("msie") > 0){
			
			ui.style.display=" ";
		}
		else{
			ui.style.display="none";
		}
		</script>
	<script type="text/javascript" src="resources/js/lib/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/materialize.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/three3d/three.min.js"></script>
	<script type="text/javascript" src="resources/js/src/login.js" ></script>
</html>