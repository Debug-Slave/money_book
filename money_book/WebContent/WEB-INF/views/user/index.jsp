<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/comm/user_header.jsp"/>
<body>
<script type="text/javascript">
	var outgoingCategoryArr = [];
	<c:forEach var="cate" items="${outgoingCategoryList}">
		outgoingCategoryArr.push({code : "${cate.cateCode}", name : "${cate.cateName}"});
	</c:forEach>
	
	var incomeCategoryArr = [];
	<c:forEach var="cate" items="${incomeCategoryList}">
		incomeCategoryArr.push({code : "${cate.cateCode}", name : "${cate.cateName}"});
	</c:forEach>
</script>
<jsp:include page="/WEB-INF/views/comm/user_topMenu.jsp"/>

<div class="container-fluid mimin-wrapper">
	
	<div id="left-menu">
		<div class="sub-left-menu scroll">
			  <ul class="nav nav-list">
		        <li><div class="left-bg"></div></li>
		        <li class="time">
		          <h1 class="animated fadeInLeft"></h1>
		          <p class="animated fadeInRight"></p>
		        </li>
				<jsp:include page="/WEB-INF/views/comm/user_menuList.jsp"/>
			</ul>
		</div>  		
	</div>
	
	<!-- start: content -->
	<div id="content" style="padding-top: 20px;">
	</div>          
</div>

      <!-- start: Mobile -->
      <div id="mimin-mobile" class="reverse">
        <div class="mimin-mobile-menu-list">
            <div class="col-md-12 sub-mimin-mobile-menu-list animated fadeInLeft">
            	 <ul class="nav nav-list">
            		<jsp:include page="/WEB-INF/views/comm/user_menuList.jsp"/>
            	</ul>
            </div>
        </div>       
      </div>
      <button id="mimin-mobile-menu-opener" class="animated rubberBand btn btn-circle btn-danger">
        <span class="fa fa-bars"></span>
      </button>
       <!-- end: Mobile -->
	
    <jsp:include page="/WEB-INF/views/comm/user_footer.jsp"/>
    
    
	<script type="text/javascript">
		$(function(){
			loaderColorCustomInit();
			callMenu("/dashboard");
		});
		
	</script>
  </body>
</html>
