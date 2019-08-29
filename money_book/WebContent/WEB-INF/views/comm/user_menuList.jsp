<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  
  
        <li class="active ripple menu_dashboard" onclick="clickMenu('dashboard');">
          <a class="tree-toggle nav-header" href="javascript:clickMenu('dashboard');">
          	<span class="fa-tachometer fa"></span> Report
          </a>
        </li>
        <li class="ripple menu_history" onclick="clickMenu('history');">
          <a class="tree-toggle nav-header" href="javascript:clickMenu('history')">
            <span class="fa-calendar fa"></span> History 
          </a>
        </li>
    <!--     
    	<li class="ripple menu_report" onclick="clickMenu('report');">
          <a class="tree-toggle nav-header" href="javascript:clickMenu('report')">
            <span class="fa-bar-chart fa"></span> Report
          </a>
        </li>
 -->