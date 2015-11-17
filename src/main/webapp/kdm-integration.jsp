<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="include/html_head.inc"%>
<link rel="stylesheet" href="<%=UI_PATH%>css/select2_metro.css" />
<link rel="stylesheet" href="<%=UI_PATH%>css/DT_bootstrap.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-boxed">
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
					<li>
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li class="start "><a href="javascript:;"> <i
							class="icon-home"></i> <span class="title">KDM_WEB</span>
					</a></li>
					<li class="active ">
						<!-- <a href="javascript:;"> --> <a
						href="<c:url value='/apps/infegration'/>"> <i
							class="icon-cogs"></i> <span class="title">kdm-infegration</span>
							<span class="selected"></span>
					</a>
					</li>
					<li class="">
						<!-- <a href="javascript:;"> --> <a
						href="<c:url value='/issue/add'/>"> <i
							class="icon-bookmark-empty"></i> <span class="title">kdm-rest</span>
					</a>
					</li>
					<li class="">
						<!-- <a href="javascript:;"> --> <a
						href="<c:url value='/issue/add'/>"> <i class="icon-table"></i>
							<span class="title">kdm-vzdb</span>
					</a>
					</li>
					<li class="">
						<!-- <a href="javascript:;"> --> <a
						href="<c:url value='/issue/add'/>"> <i class="icon-briefcase"></i>
							<span class="title">Other</span>
					</a>
					</li>
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN PAGE -->
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div id="portlet-config" class="modal hide">
					<div class="modal-header">
						<button data-dismiss="modal" class="close" type="button"></button>
						<h3>portlet Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here will be a configuration form</p>
					</div>
				</div>
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN PAGE CONTAINER-->
				<div class="container-fluid">
					<!-- BEGIN PAGE HEADER-->
					<div class="row-fluid">
						<div class="span12">
							<!-- BEGIN STYLE CUSTOMIZER -->
							<!-- END BEGIN STYLE CUSTOMIZER -->
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">KDM_WEB</h3>
							<ul class="breadcrumb">
								<li><i class="icon-home"></i> <a href="javascript:;">kdm-infegration</a>
								</li>
							</ul>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					</div>
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE CONTENT-->
					<div class="row-fluid">
						<div class="span12">
							<div class="news-item-page">
								<a id="startBtn" class="btn blue" href="javascript:;"><i
									class="icon-play"></i>启动</a>
							</div>
						</div>

						<label>show_files</label>
						<div class="span11">
						
						<div class="portlet box green">

                                                        <div class="portlet-title">
                                                                <div class="caption"><i class="icon-folder-close"></i>files</div>
                                                        </div>

                                                        <div class="portlet-body">
                                                        
                                                          <c:forEach var="file" items="${fileList}">
                                                                <div class="accordion" id="accordion1">
                                                                        <div class="accordion-group">
                                                                                <div class="accordion-heading">
                                                                                        <a class="accordion-toggle collapsed" data-toggle="collapse"  href="<c:out  value='/'/>">
                                                                                        <i class="icon-folder-close"></i>
                                                                                          <c:out value="${file}" />
                                                                                        </a>
                                                                                    </div>
<%--                                                                                  <div id="<c:out value='${file}' />" class="accordion-body collapse">
                                                                                        <div class="accordion-inner">
                                                                                              <table>
                                                                                                <c:forEach var="files" items="${fileList}">
                                                                                                        <tr>  <td><c:out value="${files}" /></td></tr>
                                                                                                </c:forEach>
                                                                                              </table>
                                                                                        </div>
                                                                                </div> --%> 
                                                                        </div>
                                                                </div>
                                                           </c:forEach>     
                                                        </div>
                                                </div>
						
						

<%-- 							<div class="portlet">

								<div class="portlet-title"></div>

								<div class="portlet-body">

									<table
										class="table table-striped table-bordered table-advance table-hover">

										<thead>

											<tr>

												<th><i class="icon-file"></i> files</th>

												<th class="hidden-phone"></th>

												<th></th>

												<th></th>

											</tr>

										</thead>

										<tbody>
			                                                        <c:forEach var="file" items="${fileList}">
											<tr>
						                                              <td >
							                                             <a href='<c:url value="/"/>' >
							                                                  <c:out value="${file}" />
							                                               </a>
						                                              </td>
											</tr>
										</c:forEach>				

										</tbody>

									</table>

								</div>

							</div> --%>

							<!-- END SAMPLE TABLE PORTLET-->

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
	<div class="footer">
		<div class="container">
			<div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>
			<div class="footer-tools">
				<span class="go-top"> <i class="icon-angle-up"></i>
				</span>
			</div>
		</div>
	</div>
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
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>