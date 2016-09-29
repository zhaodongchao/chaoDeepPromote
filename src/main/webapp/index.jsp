<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ERP登录</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
</head>
<body>
<div class="container">
	<section id="content">
		<form action="erp_spring_login_url" method="post">
			<h1>中国移动ERP</h1>
			<div>
				<input type="text" placeholder="username" name="login_username" required="" id="username" />
			</div>
			<div>
				<input type="password" placeholder="password" name="login_password" required="" id="password" />
			</div>
			<div>
				<input type="submit" value="Log in" />
				<a href="#">Lost your password?</a>
				<a href="#">Register</a>
			</div>
		</form><!-- form -->
		<div class="button">
			<a href="#">Download source file</a>
		</div>
	</section>
</div><!-- container -->
</body>
</html>