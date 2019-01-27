<%@include file="../tags/commonTags.jsp"%>

<c:if test = "${isUserNotExist}">
	<div class="alert alert-danger alert-dismissible fade show"role="alert">
	<strong><spring:message code="opps"/></strong><spring:message code="user.login.userNotFound"/>
	<button type="button"class="close"data-dismiss="alert"aria-label="Close">
	<span aria-hidden="true">&times;</span>
	</button>
	</div>
</c:if>

<div class="d-flex justify-content-center">
	<form action="<c:url value="/user/login"/>" method="post">
	<div class="form-group">
	<label for="email">
		<spring:message code="user.login.email"/>
	</label>
	<input type="email"class="form-control"id="email"name="username"aria-describedby="emailHelp"placeholder="Enter email"required>
	</div>
	<div class="form-group">
	<label for="password">
		<spring:message code="user.login.password"/>
	</label>
	<input type="password"class="form-control"id="password"name="password"placeholder="Password"required>
	</div>
	<button type="submit"class="btnbtn-primary"><spring:message code="user.login.submit"/></button>
	<div>
		<a href="<c:url value="/user/register"/>">
			<spring:message code="user.login.wantToRegister"/>
		</a>
	</div>
	</form>
</div>
