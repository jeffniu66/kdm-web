<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="infoModal" class="modal hide fade" tabindex="-1" >
    <div class="modal-header" >
        <button type="button" class="close" data-dismiss="modal"></button>
        <h3>${info.title}</h3>
    </div>
    <div class="modal-body" style="height:100px;">
        <div style="height:100px" data-always-visible="1" data-rail-visible1="1">
            <div style="text-align:center;"><font color="red" size="4">${info.content}</font><br/><br/>
            <!-- <button type="button" class="btn green" onclick="back_applist()">确定</button> -->
            </div>
        </div>
    </div>   
</div>                                    
