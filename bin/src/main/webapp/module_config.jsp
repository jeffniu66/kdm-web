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
				 <%-- <ul class="page-sidebar-menu">
					<li class="active"><a href="<c:url value='/app/${appPkg.appName}'/>"> <i
							class="icon-home"></i> <span class="title">${appPkg.appName}应用</span>
					</a></li>
					<c:forEach items="${module}" var="m">
						<li>
							<a
								href="<c:url value='/app/${module.appPkg.appName}?m=${module.moduleName}'/>">
									<i class="icon-cogs"></i> <span class="title">${module.moduleName}</span>
									<span class="selected"></span>
							</a>
						</li>
					</c:forEach>
				</ul>  --%>
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
								<%-- <li><i class="icon-home"></i> ${appPkg.appName}</li> --%>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
		 <form:form class="form-horizontal" id="conf_form" modelAttribute="mConfigs"
            	 servletRelativeAction="/module/config_update">
                <div class="control-group">
                ${mConfigs}
                <%-- <c:forEach items="${mConfigs.getCfgList()}" var="m" varStatus="i">
                	<c:forEach items="${m.getItemList()}" var="c" varStatus="j">
                	<label class="control-label">${c.getDesc()}</label>
						<div class="controls">
												<c:if test='${empty c.getKeyValue()}'>
													<form:input
														path="cfgList[${i.index}].itemList[${j.index}].keyValue"
														class=" m-wrap" value="${c.getDefValue()}" />
												</c:if>
												<span class="help-inline"><span style="color: red">*</span>Some hint here</span>
												<c:if test='${not empty c.getKeyValue()}'>
													<form:input
														path="cfgList[${i.index}].itemList[${j.index}].keyValue"
														class=" m-wrap" value="${c.getKeyValue()}" />
												</c:if>
												<span class="help-inline"><span style="color: red">*</span>Some hint here</span>
						</div>
                   	</c:forEach>
                 </c:forEach> --%>
                    <div class="controls">
                       <%-- <form:input  path="desc" class="m-wrap" value="${mcitem.defValue}"/> --%>
                    </div>
                </div>

                <div class="form-actions">
                    <button id="save_btn" title="ok" type="button" class="btn blue"><i class="icon-ok"></i> 保存</button>
                    <button title="cancel" type="button" class="btn" data-dismiss="modal">取消</button>
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
<!-- 	<div class="footer">
		<div class="container">
			<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>
			<div class="footer-tools">
				<span class="go-top"> <i class="icon-angle-up"></i>
				</span>
			</div>
		</div>
	</div> -->
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

            
            // 动态插入html
            if(!$('#ModuleEditDiv').length) {
                   $(document.body).append('<div id="ModuleEditDiv"></div>');
               }   
           //保存按钮
               $('#ModuleEditDiv').delegate('#save_btn', 'click', function(){
                   $('#module_form').submit();
                   $('#moduleEditModal').modal('hide');
               });  
          
		            // 请求新增页面
		            $('#add_module').click(function(){
		            	 var appId="${appPkg.appId}"
		                    $.ajax({
		                            type : "post",
		                            url  : '<c:url value="/module/add"/>',
 		                            data: {
		                                "appId": appId 
		                            },  
		                            dataType : 'text'
		                    }).done(function(htmlfragment){
		                            $('#ModuleEditDiv').html(htmlfragment);
		                            $('#moduleEditModal').modal('show');
		                    });
		            });
		            
		            //添加配置
		              $('#save_btn').click(function(){
		            	  var $this = $(this);
		                   $('#conf_form').submit();
               			}); 
			
		            // 请求编辑页面
		            $('.editAppBtn').click(function(){
		                    var $this = $(this);
		                    $.ajax({
		                            type : "GET",
		                            url  : '<c:url value="/module/edit"/>/' + $this.data('appid')+"_"+$this.data('mid')
		                    }).done(function(htmlfragment){
		                            $('#ModuleEditDiv').html(htmlfragment);
		                            $('#moduleEditModal').modal('show');
		                    }); 
		                    
		            });
		            
		            
		            // 删除操作
		            $('.deleteAppBtn').click(function(){
		                var $this = $(this);
		                $.ajax({
		                    type : "GET",
		                    url  : '<c:url value="/module/delete"/>/' + $this.data('appid')+"_"+$this.data('mid')
		                }).done(function(htmlfragment){
		                    App.showInfoWindow(htmlfragment);
		                });
		            });
		            
		         // 配置操作
		            $('.configAppBtn').click(function(){
		            	window.location.href='<c:url value="/module/config"/>';
		            });
			
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>