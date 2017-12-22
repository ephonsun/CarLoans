<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<!--[if IE]><link rel="shortcut icon" href="/icons/favicon.ico"><![endif]-->
<head>
<meta charset="utf-8">
<title>DisBook</title>
<!-- ${pageContext.request.contextPath}  -->
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<link href="/resources/icons/faviconDisBook.png" rel="icon" type="image/x-icon" />
</head>
<body>
<div class="container">
	<section id="content">
	 <!-- <H1>${pageContext.request.contextPath}</H1> -->
	 <center>
		<form:form id="loginForm" method="post" action="login" modelAttribute="UserCredentials">
			<h1>Login Form</h1>
			<h2>Car Loan Service User</h2>
			<br>
			<br>
			<div>
				<input type="text" placeholder="Username" required="" id="CarLoanService_username" name="username" />
			</div>
			<br>
			<div>
				<input type="password" placeholder="Password" required="" id="CarLoanService_password" name="password"/>
			</div>
			<br>
			<div>
				<input type="submit" value="Log in" /> <br><br>
				<a href="<%=request.getContextPath() %>/forgotpassword">Forgot your password?</a>
				<br>
				<a href="<%=request.getContextPath() %>/signup">Sign Up</a>
			</div>
		</form:form><!-- form -->
		</center>
	</section><!-- content -->
</div><!-- container -->
</body>
</html>
