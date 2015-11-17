<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="include/html_head.inc"%>
<link rel="stylesheet" href="<%=UI_PATH%>css/select2_metro.css" />
<link rel="stylesheet" href="<%=UI_PATH%>css/DT_bootstrap.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-footer-fixed page-boxed ">
	<!-- BEGIN HEADER -->
	<%@include file="include/layout_top.inc"%>
	<!-- END HEADER -->
	<div class="container">
		<!-- BEGIN CONTAINER -->
		<div class="page-container row-fluid">
			<!-- BEGIN SIDEBAR -->
			<div class="page-sidebar nav-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu">
					
					<li class="active"><a href="<c:url value='/app/${appPkg.appName}'/>"> <i
							class="icon-home"></i> <span class="title">${appPkg.appName}应用</span>
					</a></li>
					<c:forEach items="${modules}" var="m">
						<li><a
							href="<c:url value='/app/${appPkg.appName}?m=${m.moduleName}'/>">
								<i class="icon-cogs"></i> <span class="title">${m.moduleName}</span>
								<span class="selected"></span>
						</a></li>
					</c:forEach>
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN PAGE -->
			<div class="page-content">
				
				<!-- BEGIN PAGE CONTAINER-->
				<div class="container-fluid">
					<!-- BEGIN PAGE HEADER-->
					<div class="row-fluid">
						<div class="span12">
							<h3 class="page-title"></h3>
							<ul class="breadcrumb">
								<li><i class="icon-home"></i> 当前模块 <a href="javascript:;">${moduleName}</a>
								</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">

							<div class="portlet box green" id="tb_files">

								<div class="portlet-title">
									<div class="caption">
										<i class="icon-folder-close"></i>directory
									</div>
								</div>

								<div class="portlet-body">

									<c:forEach var="file" items="${fileList}" varStatus="loopStatus">
										<div class="accordion" id="accordion1">
											<div class="accordion-group">
												<div class="accordion-heading">
													<a class="" data-algid=" ${file}" href="javascript:;">
														<i class="icon-folder-close"></i> ${file.getName()}
													</a>
												</div>
												<c:if test="${file.isDirectory()}">
												<c:forEach var="child" items="${file.listFiles()}">
												<div class="accordion-inner">${child.getName()}</div>
												</c:forEach>
												</c:if>
											</div>
										</div>
									</c:forEach>
								</div>
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
	<!-- END CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/app.js"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();

			//文件按钮  
			$('#tb_files').delegate('.delete', 'click', function(e) {
				var $this = $(this);
				var index = $this.attr("dataId");
				
				var classInfo = $("#dataInfo_"+index).attr("class");
				if(classInfo == "accordion-body collapse in"){
					$("#dataInfo_"+index).attr("class", "accordion-body collapse");
					return;
				}

				$("#dataInfo_"+index).attr("class", "accordion-body collapse in");
				
				var appName = "${appName}"
				var moduleName = "${moduleName}"
				var fileName = $this.data('algid');
				$.ajax({
					type : "GET",
					url : '<c:url value="/files"/>',
					data : {
						"appName" : appName,
						"m" : moduleName,
						"f" : fileName
					},
					dataType : "text",
					success : function(data) {
						var obj = eval(data);
						var len = obj.length;
					 	var html = [];
						  for (var i = 0; i < len; i++) {
							  var val = obj[i];
							  html.push("<tr><td> <a href=''>" + val+ "</a> </td> </tr>");
						  }
						  $("#inner_tb_"+index+"  tbody").html(html.join(''));
					}
				});
			})

		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>