<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="include/html_head.inc"%>
<link rel="stylesheet" href="<%=UI_PATH%>css/select2_metro.css" />
<link rel="stylesheet" href="<%=UI_PATH%>css/DT_bootstrap.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-footer-fixed page-boxed page-full-width">
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
							<h3 class="page-title">当前部署应用列表</h3>
							<!-- <ul class="breadcrumb">
								<li><i class="icon-home"></i> <a href="javascript:;">apps</a>
								</li>
							</ul> -->
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
			          		
			          		<button id="addAppBtn" data-toggle="modal" class="btn green">
                              	<i class="icon-plus"></i> 新增应用
                            </button>
			                <!-- <a id="addAppBtn" data-toggle="modal" class="btn green" href="#form_base">
                              	新增应用<i class="icon-plus"></i>
                            </a> -->
							 
							<table class="table table-striped table-advance table-hover table-bordered" id="sample_editable_1">
								<thead>
									<tr>
										<th>序号</th>
										<th>应用名称</th>
										<th>安装路径</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${appList}" var="app" varStatus="status">
									<tr class="">
										<td><c:out value="${status.count}"/></td>
										<td> <a href="<c:url value='/app/${app.appName}'/>">${app.appName}</a></td>
										<td>${app.installPath}</td>
										<td>
											<button data-appid="${app.appId}" class="btn mini green editAppBtn">
				                              	<i class="icon-edit"></i> 编辑
				                            </button> | 
											<button data-appid="${app.appId}" class="btn mini green deleteAppBtn">
				                              	<i class="icon-trash"></i> 删除
				                            </button>
				                            <a  href="<c:url value='/app/${app.appName}'/>" class="btn mini green messageAppBtn">
				                              	<i class="icon-external-link"></i> 详情
				                            </a>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							
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
  	<script src="<%=UI_PATH%>js/form-validation.js"
                type="text/javascript"></script>              
		
	<!-- END CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/app.js"></script>
	<script>

		jQuery(document).ready(function() {
			
			App.init();

			if(!$('#AppEditDiv').length) {
				$(document.body).append('<div id="AppEditDiv"></div>');
			}
			$('#AppEditDiv').delegate('#save_btn', 'click', function(){
				$('#app_form').submit();
				//alert("保存成功！")
				$('#form_base').modal('hide');
			});
			
			// 请求新增页面
			$('#addAppBtn').click(function(){
				window.location.href='<c:url value="/apps/add"/>';
				/* $.ajax({
					type : "GET",
					url  : '<c:url value="/apps/add"/>',
					dataType : 'text'
				}).done(function(htmlfragment){
					$('#AppEditDiv').html(htmlfragment);
					$('#appEditModal').modal('show');
				}); */
			});
			
			// 请求编辑页面
			$('.editAppBtn').click(function(){
				var $this = $(this);
				$.ajax({
					type : "GET",
					url  : '<c:url value="/apps/edit"/>/' + $this.data('appid')
				}).done(function(htmlfragment){
					$('#AppEditDiv').html(htmlfragment);
					$('#appEditModal').modal('show');
				});
			});
			
            // 删除操作
            $('.deleteAppBtn').click(function(e){
            	e.preventDefault();
                if (confirm("确定要删除此条数据 ?") == false) {
                    return;
                }
                var $this = $(this);
                window.location.href='<c:url value="/apps/delete"/>/' + $this.data('appid');
                alert("删除成功！");
            });
			
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>