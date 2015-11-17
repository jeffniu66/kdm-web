<%@page contentType="text/html" pageEncoding="UTF-8"%>

	<!-- BEGIN FOOTER -->
        <div class="footer">
                <div class="container">
                        <div class="footer-inner">2015 &copy; 北京奥奥技异科技发展有限公司.</div>
                        <div class="footer-tools">
                                <span class="go-top"> <i class="icon-angle-up"></i>
                                </span>
                        </div>
                </div>
        </div>
	</body>
	</html>
	<!-- END FOOTER -->
	
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="<%=UI_PATH%>js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="<%=UI_PATH%>js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="<%=UI_PATH%>js/excanvas.min.js"></script>
	<script src="<%=UI_PATH%>js/respond.min.js"></script>  
	<![endif]-->   
	<script src="<%=UI_PATH%>js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="<%=UI_PATH%>js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="<%=UI_PATH%>js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<script src="<%=UI_PATH%>js/app.js"></script>
	
	<script>
		$(function(){
			activeNav();
			$('.nav li').hover(function(){
				$(this).addClass("over");
			},function(){
				$(this).removeClass("over");
			});
		});
		
		function activeNav(){
			var locatonHref = window.location.pathname;
			// "/rmp-manager/algorithm/update/11";
			var tempArr = locatonHref.split("/");
			tempArr = tempArr.slice(0,3);
			locatonHref = tempArr.join('/') + "/";
			var lis = $('.nav > li'), len = lis.length;
			for(var i=0; i<len; i++){
				var $li = $(lis.get(i));
				//console.log($li.find('a').attr('href'));
				var ahref = $li.find('a').attr('href');
				if( ahref == "" ) continue;
				if(ahref.indexOf(locatonHref) != -1){
					$li.addClass("active");
				}
			}
		}
		
	</script>
	