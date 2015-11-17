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
							<h3 class="page-title">xml编辑界面</h3>
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid" >
						<div class="span12" >
							<div class="control-group" id="message_show" >
								<form id="edit_xml" action="<c:url value="/module/config/write_xml"/>" method="post" >
									<input type="hidden" name="appid" value="${appid}"/>
									<input type="hidden" name="mid" value="${mid}"/>
									<textarea name="xml_content" rows="35" cols="100"
										class="span8 m-wrap">${xml_content }</textarea>
								</form>	
							</div>
							
							<div class="form-actions"  >
								<button id="save_btn"  title="ok" type="button" class="btn green">
									<i class="icon-ok"></i> 保存
								</button>
								<button title="cancel" type="button" data-appid="${appid}" data-mid="${mid}" class="btn green"  id="cancel_btn"
									>取消</button>
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
			App.init();
			//保存按钮
			$('#save_btn').click(function(){
				$('#edit_xml').submit();
			});
			
			$('#cancel_btn').click(function(){
				var $this = $(this);
				window.location.href='<c:url value="/module/config/"/>'+ $this.data('appid')+"_" + $this.data('mid');
			});
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>