<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<li class="active"><a
						href="<c:url value='/app/${appPkg.appName}'/>"> <i
							class="icon-home"></i> <span class="title">${appPkg.appName}应用</span>
					</a></li>
					<%-- <c:forEach items="${module}" var="m"> --%>
						<li>
							<a
								href="<c:url value='/app/${appPkg.appName}?m=${module.moduleName}'/>">
									<i class="icon-cogs"></i> <span data-appid="${appPkg.appId}" class="title">${module.moduleName}</span>
									<span class="selected"></span>
							</a>
						</li>
					<%-- </c:forEach> --%>
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
							<!-- BEGIN STYLE CUSTOMIZER -->
							<!-- END BEGIN STYLE CUSTOMIZER -->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title"></h3>
							<ul class="breadcrumb">
								 <li><i class="icon-home"></i> ${appPkg.appName}
								</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
							<button id="back_config" data-appname="${appPkg.appName}" data-toggle="modal" class="btn green">
								<i class="icon-undo"></i>返回列表
							</button>
							<button id="edit_config" data-toggle="modal" class="btn green">
								<i class="icon-edit"></i> 编辑
							</button>
							<button id="edit_xml" data-toggle="modal" data-appid="${appPkg.appId}" data-mid="${module.mid}" class="btn green">
								<i class="icon-edit"></i> 高级编辑
							</button>
							<form:form class="form-horizontal" id="conf_form"
								modelAttribute="mConfigs"
								servletRelativeAction="/module/config_update">
								<form:hidden  path="appPkg.appId" value="${appPkg.appId}" />
            	 				<form:hidden  path="module.mid" value="${module.mid}" />
								<div class="control-group">
									<c:forEach items="${mConfigs.getCfgList()}" var="m"
										varStatus="i">
										<form:hidden path="cfgList[${i.index}].filePath" class=" m-wrap"   value="${m.getFilePath()}"/> 
										<c:forEach items="${m.getItemList()}" var="c" varStatus="j">
											<label class="control-label">${c.getDesc()}</label>
											<div class="controls">
												<c:if test='${empty c.getKeyValue()}'>
													<form:input 
														path="cfgList[${i.index}].itemList[${j.index}].keyValue"
														placeholder="large" class="m-wrap large"
														value="${c.getDefValue()}" onfocus="this.blur()"/>
												</c:if>
												<c:if test='${not empty c.getKeyValue()}'>
													<form:input 
														path="cfgList[${i.index}].itemList[${j.index}].keyValue"
														placeholder="large" class="m-wrap large"
														value="${c.getKeyValue()}" onfocus="this.blur()"/>
												</c:if>
												<span class="help-inline"><font color="red">*</font>&nbsp;${c.getDefValue()}</span>
												<form:hidden
													path="cfgList[${i.index}].itemList[${j.index}].keyName"
													class=" m-wrap" value="${c.getKeyName()}" />
												<form:hidden
													path="cfgList[${i.index}].itemList[${j.index}].enable"
													class=" m-wrap" value="${c.isEnable()}" />
												<form:hidden
													path="cfgList[${i.index}].itemList[${j.index}].defValue"
													class=" m-wrap" value="${c.getDefValue()}" />
												<form:hidden
													path="cfgList[${i.index}].itemList[${j.index}].desc"
													class=" m-wrap" value="${c.getDesc()}" />
											</div>
										</c:forEach>
									</c:forEach>
									<div class="controls">
										<%-- <form:input  path="desc" class="m-wrap" value="${mcitem.defValue}"/> --%>
									</div>
								</div>

								<div class="form-actions" style="display:none">
									<button id="save_btn" title="ok" type="button" class="btn blue">
										<i class="icon-ok"></i> 保存
									</button>
									<button id="cancel_btn" title="cancel" type="button" class="btn"
										data-dismiss="modal">取消</button>
								</div>
							</form:form>
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
            
		            //添加配置
		              $('#save_btn').click(function(){
		                   $('#conf_form').submit();
		                   alert("保存成功！");
               			}); 
		            
					//显示保存按钮
		            $('#edit_config').click(function(){
		            	$("input").removeAttr("onfocus");
		            	$(".form-actions").show();
		            });
					
		          //取消
		            $('#cancel_btn').click(function(){
		            	$("input").attr("onfocus","this.blur()");
		            	$(".form-actions").hide();
		            });
					
					//返回列表
					$('#back_config').click(function(){
						var $this = $(this);
		            	window.location.href='<c:url value="/app"/>/' + $this.data('appname');
		            });
					
					//显示保存按钮
		            $('#edit_xml').click(function(e){
		            	var $this = $(this);
		            	 e.preventDefault();
		                  if (confirm("非开发人员请谨慎使用此功能!") == false) {
		                      return;
		                  }
		                  window.location.href='<c:url value="/module/config/read_xml"/>/' + $this.data('appid')+"_" + $this.data('mid');
		                  
		            });	
		        
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>