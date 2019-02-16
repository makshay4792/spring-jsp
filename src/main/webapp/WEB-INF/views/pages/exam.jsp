<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex justify-content-center">
	<form action="<c:url value="/admin/exams"/>" method="post">
	<div class="form-group">
	<label for="exam">
		<spring:message code="exam.name"/>
	</label>
	<input type="text"class="form-control"id="exam"name="exam"aria-describedby="exam"placeholder="<spring:message code="exam.name"/>" value="${exam.exam}" required>
	</div>
	<div class="form-group">
	<label for="email">
		<spring:message code="exam.number.of.questions"/>
	</label>
	<input type="number"class="form-control"id="questionCount"name="questionCount"aria-describedby="questionCount"placeholder="<spring:message code="exam.number.of.questions"/>" value="${exam.questionCount}" required>
	</div>
	<div class="form-group">
	<label for="password">
		<spring:message code="exam.duration"/>
	</label>
	<input type="number"class="form-control"id="durationInMin"name="durationInMin"placeholder="<spring:message code="exam.duration"/>" value="${exam.durationInMin}" required>
	</div>
	<button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button>
	</form>
</div>
</div>