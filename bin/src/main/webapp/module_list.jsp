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
							
							<button id="add_module" data-toggle="modal"  class="btn green"  > <i class="icon-plus"></i> 新增模块
                            </button>
							<!--  -->
							<table class="table table-striped table-hover table-bordered"
								id="sample_editable_1">
								<thead>
									<tr>
										<th>序号</th>
										<th>模块名称</th>
										<th>安装路径</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${modules}" var="m" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${m.moduleName}</td>
											<td>${m.installPath}</td>
											<td>
											      <button data-mid="${m.mid}" id="start${status.index}" data-appid="${m.appId}"   class="btn mini green startModuleBtn">
                                                        <i class="icon-play"></i> 启动
                                                  </button> | 
                                                  <button data-mid="${m.mid}"  buttonData="${status.index}" data-appid="${m.appId}"   class="btn mini green stopModuleBtn">
                                                        <i class="icon-stop"></i>停止
                                                  </button> | 
                                                  <button data-appid="${m.appId}" data-mid="${m.mid}"   class="btn mini green editAppBtn">
                                                        <i class="icon-edit"></i> 编辑
                                                   </button>|   
                                                  <button data-appid="${m.appId}" data-mid="${m.mid}"  class="btn mini green deleteAppBtn">
                                                    <i class="icon-trash"></i> 删除
                                                   </button>
                                                   <button data-appid="${m.appId}" data-mid="${m.mid}"  class="btn mini green configAppBtn" onclick="redirect_conf()">
                                                    <i class="icon-cog"></i> 配置
                                                   </button>
											</td>	
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!--  -->
							<!--  -->
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
		            
		            //启动按钮
                    $('.startModuleBtn').click(function(){
                    	 var $this = $(this);
                    	 window.location.href = '<c:url value="/module/gostart/"/>'+ $this.data('appid')+"_"+$this.data('mid');
                   /*   $.ajax({
						type : "post",
						url : '<c:url value="/module/start"/>',
						data : {
							"appId" : $this.data('appid'),
							"mid" : $this.data('mid')
						},
						dataType : "text",
						success : function(data) {
							alert(data)
						}
					});
	                     $this.attr("disabled","disabled");
		                 $this.attr("class","btn mini gray startModuleBtn"); */ 
		                 
		                 
                    });
		            
                    //停止按钮 调用bat
                    $('.stopModuleBtn').click(function(){
                    	 var $this = $(this);
                    	 var appId= $this.data('appid')
                    	 var mid=$this.data('mid')
                    	 var index = $this.attr('buttonData')
     					$.ajax({
    						type : "post",
    						url : '<c:url value="/module/stop"/>',
    						data : {
    							"appId" : appId,
    							"mid" : mid
    						},
    						dataType : "text",
    						success : function(data) {
    							if(data=="success"){
    								alert("stop success!")
    							 	<%--  $('#start'+index).removeAttr("disabled");
    									<img src="<%=UI_PATH%>image/loading.gif"/>
    								$('#start'+index).attr("class","btn mini green startModuleBtn");  --%> 	
    							}
    						}
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
		            	var $this = $(this);
		            	window.location.href='<c:url value="/module/config"/>/' + $this.data('appid')+"_"+$this.data('mid');
		            });
			
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>