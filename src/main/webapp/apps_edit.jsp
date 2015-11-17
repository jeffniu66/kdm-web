<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="appEditModal" class="modal hide fade" tabindex="-1" data-width="500">
    <div class="modal-header" >
        <button type="button" class="close" data-dismiss="modal"></button>
        <h3><c:choose><c:when test="${app.appId==null}">新增</c:when><c:otherwise>
        	编辑</c:otherwise></c:choose>应用</h3>
    </div>
    <div class="modal-body" style="height:400px;">
        <div style="height:400px" data-always-visible="1" data-rail-visible1="1">
            <form:form class="form-horizontal" id="app_form" 
            	modelAttribute="app" servletRelativeAction="/apps/save">
                <form:hidden path="appId" value="${app.appId}" />
                <div class="control-group">
                    <label class="control-label">应用名称:</label>
                    <div class="controls">
                       <form:input path="appName" class="m-wrap" value="${app.appName}" />
                    </div>
                </div>
                 <div class="control-group">
                    <label class="control-label">安装路径:</label>
                    <div class="controls">
                       <form:input path="installPath" class="m-wrap" value="${app.installPath}"/>
                    </div>
                </div>

                 <%-- <div class="control-group">
                    <label class="control-label">模块定义文件:</label>
                    <div class="controls">
                        <form:input path="moduleFile" class="m-wrap" value="${app.moduleFile}"/>
                    </div>
                </div>  --%>
                 <div class="control-group">
                    <label class="control-label">应用描述:</label>
                    <div class="controls">
                        <form:textarea path="desc" class="m-wrap" rows="3" value="${app.desc}"></form:textarea>
                    </div>
                </div>
                <div class="form-actions">
                    <button id="save_btn" title="ok" type="button" class="btn blue"><i class="icon-ok"></i> 保存</button>
                    <button title="cancel" type="button" class="btn" data-dismiss="modal">取消</button>
                </div>
            </form:form>
        </div>
    </div>
</div>                                    
