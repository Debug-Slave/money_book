<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<jsp:include page="/WEB-INF/views/comm/header.jsp"/>	
	
	<body id="mimin" class="dashboard form-signin-wrapper">

		<div class="container">
			<form class="form-signin" id="form-signin" onsubmit="loginProc(); return false;">
				<div class="panel periodic-login">
					<div class="panel-body text-center">
						<h1 class="atomic-symbol">MB</h1>
						<p class="atomic-mass">Ver. 1.0</p>
						<p class="element-name">Money Book</p>
						
						<div class="form-group form-animate-text" style="margin-top:40px !important;">
							<input type="text" class="form-text" required="required" name="userId">
							<span class="bar"></span>
							<label>User ID</label>
						</div>
						
						<div class="form-group form-animate-text" style="margin-top:40px !important;">
							<input type="password" class="form-text" required="required" name="userPassword" id="loginPassword">
							<span class="bar"></span>
							<label>Password</label>
						</div>
						
						
						<input type="submit" class="btn col-md-12" value="SignIn"/>
					</div>
					<div class="text-center" style="padding:5px;">
						<a href="javascript:$('#sign_modal').modal('show');">사용자 등록</a>
					</div>
				</div>
			</form>
		</div>
		
		<form class="cmxform" name="signupForm" id="signupForm" novalidate="novalidate">
			<div class="modal" id="sign_modal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span>X</span>
							</button>
							<h4 class="modal-title">사용자 등록</h4>
						</div>
						<div class="modal-body">
							<div class="form-group form-animate-text" style="margin-top:40px !important;">
								<input autocomplete="false" type="text" class="form-text" id="userId" name="userId" required="required" aria-required="true">
								<span class="bar"></span>
								<label>ID</label>
							</div>
							
							<div class="form-group form-animate-text" style="margin-top:40px !important;">
								<input autocomplete="false" type="text" class="form-text" id="userName" name="userName" required="required" aria-required="true">
								<span class="bar"></span>
								<label>사용자 이름</label>
							</div>
							
							<div class="form-group form-animate-text" style="margin-top:40px !important;">
								<input type="password" class="form-text" id="userPassword" name="userPassword" required="required" aria-required="true">
								<span class="bar"></span>
								<label>패스워드</label>
							</div>
							
							<div class="form-group form-animate-text" style="margin-top:40px !important;">
								<input type="password" class="form-text" id="userConfirmPassword" name="userConfirmPassword" required="required" aria-required="true">
								<span class="bar"></span>
								<label>패스워드 확인</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >등록</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div>
		</form>
				
		
		<jsp:include page="/WEB-INF/views/comm/footer.jsp"/>
		
		<script type="text/javascript">
			
			function loginProc(){
				
				$.ajax({
					url : "/user/login",
					type : "POST",
					data : $("#form-signin").serialize(),
					success : function(data){
						alert("로그인에 성공하였습니다.");
						location.href="/";
					},
					error : function(request,status,error){
						$("#loginPassword").val("");
						alert(request.responseText);
					}
				});
				
				return false;
			}
		
			$(function(){
				
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-aero',
					radioClass: 'iradio_flat-aero'
				});
				
				$("#signupForm").validate({
					submitHandler: function(form) { 
						$.ajax({
							url : "/user/regist",
							type : "POST",
							data : $("#signupForm").serialize(),
							success : function(data){
								alert("사용자 등록에 성공하였습니다.\n입력하신 아이디로 로그인을 시도해보세요.");
								location.reload(true);
								
							},
							error : function(request,status,error){
								alert(request.responseText);
							}
						});
						
				        return false;  
				    },
					errorElement: "em",
					errorPlacement: function(error, element) {
					  $(element.parent("div").addClass("form-animate-error"));
					  error.appendTo(element.parent("div"));
					},
					success: function(label) {
					  $(label.parent("div").removeClass("form-animate-error"));
					},
					rules: {
						userId: {
							required: true,
							minlength: 5,
							maxlength : 20,
							regx :  /[^a-zA-Z0-9_]/gi
						},
						userName: {
							required: true,
							minlength: 2,
							maxlength : 10
						},
						userPassword: {
							required: true,
							minlength: 8,
							regx : /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,16}/
						},
						userConfirmPassword: {
							required: true,
							minlength: 8,
							equalTo: "#userPassword"
						}
					},
					messages: {
						userId: {
							required: "사용자 아이디를 입력해주세요.",
							minlength: "사용자 아이디는 최소 5 글자 이상 입력해주세요.",
							maxlength : "사용자 아이디는 최대 20 글자까지 가능합니다.",
							regx : "아이디는 영문자와 숫자 _를 제외한 문자를 입력할 수 없습니다."
						},
						userName: {
							required: "사용자 이름을 넣어주세요.",
							minlength: "사용자 이름은 2 글자 이상이어야 합니다.",
							maxlength : "사용자 이름은 최대 20글자까지 가능합니다."
						},
						userPassword: {
							required: "패스워드는 반드시 입력해야 합니다.",
							minlength: "패스워드는 8자 이상 입력해주세요.",
							regx : "패스워드는 영문과 숫자, 특수문자(!@#$%^*+=-) 중 하나를 포함해야 합니다."
						},
						userConfirmPassword: {
							required: "패스워드는 반드시 입력해야 합니다.",
							minlength: "패스워드는 8자 이상 입력해주세요.",
							equalTo: "패스워드가 동일하지 않습니다."
						}
					}
				});
			});
		</script>
		
	</body>
</html>