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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
<link href="${pageContext.request.contextPath}/resources/icons/faviconDisBook.png" rel="icon" type="image/x-icon" />
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/functions.js"></script>       
<h1>DisBook Registration Form</h1>
<hr>
<p>Please complete the information below to create a new account in DisBook.</p>
<form:form id="signupform" method='POST' action='register' modelAttribute="DisBookUser">
    <table>
      <tr>
        <td>Profile Name:</td>
        <td><input type="text" name="ProfileName" value="${DisBookUser.getProfileName()}" id="profileName" size="25" maxlength="49"></td>
      </tr>
      <tr>
        <td>Age:</td>
        <td><input type="text" name="age" value="${DisBookUser.getAge()}" id="user_Age" size="25" maxlength="25"></td>
      </tr>
      <tr>
        <td>E-mail:</td>
        <td><input type="text" name="email" value="${DisBookUser.getEmail()}" id="Email" size="25" maxlength="49"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>Username:</td>
        <td><input type="text" name="UserName" id="username" size="25" maxlength="49"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="Password" value="${DisBookUser.getPassword()}" id="disbook_password" size="25" maxlength="49"></td>
      </tr>
      <tr>
        <td>Confirm password:</td>
        <td>
        <input type="password" name="confirmpassword" id="disbook_passwordConfirm" size="25" maxlength="49"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
    <label>The username you chose already exists!!</label>
    <input type="hidden" name="status" value="unknown" id="DisBook_Status" size="20" maxlength="20">
  <p><input type="submit" name="continue" VALUE="Submit Registration Information" onClick="return checkInput()"></p>
  <p></p>
</form:form>
<hr>

    </body>
</html>