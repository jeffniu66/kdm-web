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
					<li class="active"><a
						href="<c:url value='/app/${appPkg.appName}'/>"> <i
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
								<li><i class="icon-home"></i> ${appPkg.appName}</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">

							<button id="add_module" data-toggle="modal" class="btn green">
								<i class="icon-plus"></i> 新增模块
							</button>
							<!--  <input id="btnSendCode1" type="button" class="btn green" value="发送验证码" onclick="sendMessage1()" /> -->
							<!--  -->
							<table class="table table-striped table-hover table-bordered"
								id="sample_editable_1">
								<thead>
									<tr>
										<th>序号</th>
										<th>模块名称</th>
										<th>描述</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${modules}" var="m" varStatus="status">
										<tr id="tr${status.index}">
											<td>${status.count}</td>
											<td>${m.moduleName}</td>
											<td>${m.descs}</td>
											<c:choose>
											    <c:when test='${m.status==0}'>
												<td ><font color='red'>服务停止</font></td>
												</c:when>
											    <c:when test='${m.status==1}'>
												<td ><font color='green'>服务启动</font></td>
												</c:when>
											</c:choose>
											<td>
												<input type="button" data-mid="${m.mid}" id="start${status.index}" data-status="${m.status}"
													data-index="${status.index}" data-appid="${appPkg.appId}" value="启动"
													class="btn mini green startModuleBtn" />|
												<input type="button" data-mid="${m.mid}" data-index="${status.index}" 
													data-appid="${appPkg.appId}" value="停止"  data-status="${m.status}"
													 id="stop${status.index}"  class="btn mini green stopModuleBtn" />|
												<button data-appid="${appPkg.appId}" data-mid="${m.mid}"
													class="btn mini green editAppBtn">
													<i class="icon-edit"></i> 编辑
												</button>|
												<button data-appid="${appPkg.appId}" data-index="${status.index}" data-mid="${m.mid}"
													class="btn mini green deleteAppBtn">
													<i class="icon-trash"></i> 删除
												</button>|
												<button data-appid="${appPkg.appId}" data-mid="${m.mid}" data-cfgsize="${m.mconfigs.cfgList.size()}"
													class="btn mini green configAppBtn">
													<i class="icon-cog"></i> 配置
												</button>|
												<button  data-mid="${m.mid}" id="start${status.index}" 
													data-index="${status.index}" data-appid="${appPkg.appId}" 
													class="btn mini green copyModuleBtn" >
													<i class="icon-copy"></i> 复制
												</button>	
												<button  data-mid="${m.mid}" id="start${status.index}" 
													data-index="${status.index}" data-appid="${appPkg.appId}" 
													class="btn mini green showModuleBtn" >
													输出信息详细
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
          	// 保存按钮
               $('#ModuleEditDiv').delegate('#save_btn', 'click', function(){
                   $('#module_form').submit();
                   $('#moduleEditModal').modal('hide');
               });  
          
	            // 请求新增页面
	            $('#add_module').click(function(){
	            	 var appId="${appPkg.appId}"
	            	window.location.href='<c:url value="/module/add"/>/' +appId;
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
                    	var status=$this.data('status');
                    	if(status==0){
                    		window.location.href = '<c:url value="/module/showMessage/"/>'+ 
                        	$this.data('appid')+"_"+$this.data('mid');
                    	}else{
                    		alert("服务启动，请先停止!")
                    		return;
                    	}
    		/* 			$.ajax({
    						type : "post",
    						url : '<c:url value="/module/start"/>',
    						data : {
    							"appId" : appId,
    							"mid" : mid
    						},
    						dataType : "text",
    						success : function(data) {
    							//alert(data)
    						}
    					}); */
                  });
		            
                    //停止按钮 调用bat
                    $('.stopModuleBtn').click(function(){
                    	 var $this = $(this);
                    	 var status=$this.data('status');
                    	 if(status==1){
                    		 window.location.href = '<c:url value="/module/stop/"/>'
                            	 + $this.data('appid')+"_"+$this.data('mid'); 
                    	 }else{
                    		 alert("服务停止,请先启动!");
                    		 return;
                    	 }
                    });
		            
		            // 删除操作
		            $('.deleteAppBtn').click(function(e){
		            	 e.preventDefault();
		                  if (confirm("确定要删除此条数据 ?") == false) {
		                      return;
		                  }
		                var $this = $(this);  
                    	window.location.href = '<c:url value="/module/delete/"/>'
                    	 + $this.data('appid')+"_"+$this.data('mid'); 
		            });
		           //复制操作 
		          $('.copyModuleBtn').click(function(){
		        	  var $this = $(this);
	                    $.ajax({
	                            type : "GET",
	                            url  : '<c:url value="/module/copy"/>/' + $this.data('appid')+"_"+$this.data('mid')
	                    }).done(function(htmlfragment){
	                            $('#ModuleEditDiv').html(htmlfragment);
	                            $('#moduleEditModal').modal('show');
	                    });
		      	  });  
		            
 		          //启动信息展示  
		           $('.showModuleBtn').click(function(){
		        	   var $this = $(this);
		               window.location.href = '<c:url value="/module/logMessage/"/>'+ 
		               $this.data('appid')+"_"+$this.data('mid'); 
		        	   
		            }); 
		            
		         // 配置操作
		            $('.configAppBtn').click(function(){
		            	var $this = $(this);
		            	var cfgsize = $this.data('cfgsize');
		            	if(cfgsize==0){
		            		alert("没有配置文件！");
		            		return;
		            	}
		            	window.location.href='<c:url value="/module/config"/>/' + $this.data('appid')+"_"+$this.data('mid');
		            });
			
		});
		
		//=======
		/* 	var InterValObj; //timer变量，控制时间
			var count = 5; //间隔函数，1秒执行
			var curCount;//当前剩余秒数

			function sendMessage() {
			  　curCount = count;
			　　//设置button效果，开始计时
			     $("#btnSendCode").attr("disabled", "true");
			     $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
			     InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			　　  //向后台发送处理数据
			     $.ajax({
			     　　type: "POST", //用POST方式传输
			     　　dataType: "text", //数据格式:JSON
			     　　url: 'Login.ashx', //目标地址
			    　　 data: "dealType=" + dealType +"&uid=" + uid + "&code=" + code,
			    　　 error: function (XMLHttpRequest, textStatus, errorThrown) { },
			     　　success: function (msg){ }
			     });
			}

			//timer处理函数
			function SetRemainTime() {
			            if (curCount == 0) {                
			                window.clearInterval(InterValObj);//停止计时器
			                $("#btnSendCode").removeAttr("disabled");//启用按钮
			                $("#btnSendCode").val("重新发送验证码");
			            }
			            else {
			                curCount--;
			                $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
			            }
			        }
			
			 */
		//======	
		
		
		
		
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>