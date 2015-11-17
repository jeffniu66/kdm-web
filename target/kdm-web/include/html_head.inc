<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
		String BASE_PATH = request.getContextPath();
		String UI_PATH = BASE_PATH + "/metronic/";
%>
<%out.clear();%><!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<!-- <meta content="width=device-width, initial-scale=1.0" name="viewport" /> -->
<title> [通用平台部署配置工具] </title>
<link rel="shortcut icon" href="<%=UI_PATH%>image/favicon.ico" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<%=UI_PATH%>css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/oge-style.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="<%=UI_PATH%>css/light.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=UI_PATH%>css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->