<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="moduleEditModal" class="modal hide fade" tabindex="-1"
	data-width="500">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"></button>
		<h3>模块信息</h3>
	</div>
	<div class="modal-body" style="height: 400px">
		<div style="height: 400px" data-always-visible="1" data-rail-visible1="1">
			<form:form class="form-horizontal" id="module_form"
				modelAttribute="mfb" servletRelativeAction="/module/copy">
			   <form:hidden path="mappId" value="${mfb.mappId}" /> 
				<form:hidden path="module.mid" value="${mfb.module.mid }" />
				<form:hidden path="module.appId" value="${mfb.mappId}" />
				<input type="hidden" id="installPath" name="installPath" value="${mfb.module.installPath}" />
				 <div class="control-group">
					<label class="control-label">模块名称:</label>
					<div class="controls">
						<form:input path="module.moduleName" class=" m-wrap"   value="${mfb.module.moduleName }"   />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">安装路径:</label>
					<div class="controls">
						<form:input path="module.installPath" class=" m-wrap"    value="${mfb.module.installPath }"   />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">启动脚本:</label>
					<div class="controls">
						<form:input path="module.startScript"    class=" m-wrap"   value="${mfb.module.startScript }"    />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">停止脚本:</label>
					<div class="controls">
						<form:input path="module.stopScript" class=" m-wrap"   value="${mfb.module.stopScript }"     />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">模块描述:</label>
					<div class="controls">
						<form:textarea path="module.descs" class=" m-wrap" rows="3"   value="${mfb.module.descs }"     />  
					</div>
				</div> 
				<div class="form-actions"  >
					<button id="save_btn" title="ok" type="button" class="btn blue">
						<i class="icon-ok"></i> 保存
					</button>
					<button title="cancel" type="button" class="btn"
						data-dismiss="modal">取消</button>
				</div>
			</form:form>
			<!--  -->
		</div>
	</div>
</div>
