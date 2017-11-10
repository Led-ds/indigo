<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="taskApp">
<head>
<link rel="stylesheet" type="text/css" href="./common/bootstrap/bootstrap-3.3.4-dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./task/directive/css/error.css">

<script src="common/jquery/jquery-1.11.3/jquery-1.11.3.min.js"></script>
<script src="common/bootstrap/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script src="common/angularjs/angular-1.4.7/angular.js"></script>
<script src="common/angularjs/angular-1.4.7/angular.min.js"></script>
<script src="common/angularjs/angular-1.4.7/angular-route.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular-route.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular-resource.js"></script>

<script src="task.js"></script>

<script src="task/directive/error.js"></script>
<script src="task/controller/shortenController.js"></script>

</head>
<body>
	<jsp:include page="task/view/template/header.jsp" />

	<div class="main_container">
		        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="" method="post" class="login-form">
			                        <div class="form-group">
<!-- 			                         onClick="windows.location("pagina");" -->
				                    	<input type="submit" value="Shorted" class="btn btn-primary" align="bottom" ng-click="location.href='task/view/page/shorten.jsp'"/>
			                        	<input type="submit" value="Retrieve" class="btn btn-primary" align="bottom" ng-click="location.href='retrive.jsp'" />
			                        </div>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	
	
<%-- 		<jsp:include page="task/view/page/shorten.jsp" /> --%>
<%-- 		<jsp:include page="task/view/page/retrieve.jsp" /> --%>
	</div>

</body>
</html>