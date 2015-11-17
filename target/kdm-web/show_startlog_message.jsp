<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="include/html_head.inc"%>
<link rel="stylesheet" href="<%=UI_PATH%>css/select2_metro.css" />
<link rel="stylesheet" href="<%=UI_PATH%>css/DT_bootstrap.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body
	class="page-header-fixed page-footer-fixed page-boxed page-full-width">
	<!-- BEGIN HEADER -->
	<%@include file="include/layout_top.inc"%>
	<!-- END HEADER -->
	<div class="container">
		<!-- BEGIN CONTAINER -->
		<div class="page-container row-fluid">
			<!-- BEGIN PAGE -->
			<div class="page-content">
				<!-- BEGIN PAGE CONTAINER-->
				<div class="container-fluid">
					<!-- BEGIN PAGE HEADER-->
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN STYLE CUSTOMIZER -->
							<!-- END BEGIN STYLE CUSTOMIZER -->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">${moduleName}启动输出信息</h3>
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid" >
						<div class="span12" >
							<a href="<c:url value='/app/${appName}?mid=${mid}&status=${status}'/>" class="btn green">
								<i class="icon-long-arrow-left"></i>返回模块列表
							</a>

							<div class="accordion-inner" id="message_show" >
								<table id="message_tb">
								
								
								</table>
							</div>

						</div>
					</div>
					<!-- END PAGE CONTENT-->
				</div>
				<!-- END PAGE CONTAINER-->
			</div>
			<!-- END PAGE -->
		</div>
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@include file="include/layout_bottom.inc"%>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/jquery-1.10.1.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="<%=UI_PATH%>js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="<%=UI_PATH%>js/excanvas.min.js"></script>
	<script src="<%=UI_PATH%>js/respond.min.js"></script>  
	<![endif]-->
	<script src="<%=UI_PATH%>js/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.cookie.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/additional-methods.min.js"
		type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/form-validation.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/app.js"></script>
	<script>
		jQuery(document).ready(function() {
			var appId = ${appId}
			var mid = ${mid}
			App.init();
			Message.init(appId, mid);

			//定时器
			window.setInterval(function() {
				Message.init(+new Date(),appId,mid);
			}, 500);
			//定义初始化执行方法
		});
		
		//显示输出信息
		var Message = function(appId,mid) {
			return {
				init : function(tm,appId,mid) {
					var url = '<c:url value="/module/getlogmessage"/>'
					if (tm) {
						url += "?tm=" + tm;
					}
					$.ajax({
						type : "POST",
						url : url,
						data : {
							"appId" : appId,
							"mid" : mid
						},
						dataType : "text",
						success : function(data) {
							 var obj = eval(data);
							var len = obj.length; 
							for (var i = 0; i < len; i++) {
								var message = obj[i];
								 $("#message_tb").append("<tr><td>" + message + "</td></tr>"); 
							} 
							
						}
					});
				}
			};
		}();
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>