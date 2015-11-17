<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="infoModal" class="modal hide fade" tabindex="-1" data-width="500">
    <div class="modal-header" >
        <button type="button" class="close" data-dismiss="modal"></button>
        <h3>${info.title}</h3>
    </div>
    <div class="modal-body" style="height:400px;">
        <div style="height:400px" data-always-visible="1" data-rail-visible1="1">
            ${info.content}
        </div>
    </div>
</div>                                    
