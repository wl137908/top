<%@ page session="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
  <meta content="yes" name="apple-mobile-web-app-capable" />
  <meta content="black" name="apple-mobile-web-app-status-bar-style" />
  <meta content="telephone=no" name="format-detection" />
  <meta content="email=no" name="format-detection" />
  <title>会员中心</title>
 </head>
<body>
  返回结果:
  <br/>
  code:<%=request.getAttribute("code")%>
  <br/>
  openid:<%=request.getAttribute("openid")%>
  <br/>
  access_token:<%=request.getAttribute("access_token")%>
  <br/>
  user_resp:<%=request.getAttribute("user_resp")%>
  <br/>

</body>