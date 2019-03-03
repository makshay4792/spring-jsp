<%@include file="../tags/commonTags.jsp"%>

<c:if test = "${registerSuccessfully}">
	<div class="alert alert-success alert-dismissible fade show"role="alert">
	<spring:message code="user.register.successfully"/>
	<button type="button"class="close"data-dismiss="alert"aria-label="Close">
	<span aria-hidden="true">&times;</span>
	</button>
	</div>
</c:if>

<div class="d-flex justify-content-center">
	<form action="<c:url value="/user/register"/>" method="post">
	<div class="form-group">
	<label for="fullName">
		<spring:message code="user.register.fullName"/>
	</label>
	<input type="text"class="form-control"id="fullName"name="fullName"aria-describedby="emailHelp"placeholder="<spring:message code="user.register.fullName"/>"required>
	</div>
	<div class="form-group">
	<label for="email">
		<spring:message code="user.register.email"/>
	</label>
	<input type="email"class="form-control"id="email"name="email"aria-describedby="emailHelp"placeholder="<spring:message code="user.register.email"/>"required>
	<small id="emailHelp" class="form-text text-muted"><spring:message code="user.register.email.message"/></small>
	</div>
	<div class="form-group">
	<label for="password">
		<spring:message code="user.register.password"/>
	</label>
	<input type="password"class="form-control"id="password"name="password"placeholder="<spring:message code="user.register.password"/>"required>
	</div>
	<button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button>
	</form>
</div>
