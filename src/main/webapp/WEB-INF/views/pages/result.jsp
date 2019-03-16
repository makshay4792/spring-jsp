<%@include file="../tags/commonTags.jsp"%>

<div class="container">

	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/logout"/>" method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
	</div>
	<table class="table table-hover">
		<thead code="exam.result.overall">
			<tr>
				<th><spring:message code="exam.name" /></th>
				<th><spring:message code="exam.number.of.questions" /></th>
				<th><spring:message code="exam.question.marks" /></th>
				<th><spring:message code="exam.passing.marks" /></th>
				<th><spring:message code="exam.question.marks.obtained" /></th>
				<th><spring:message code="exam.result" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${exam.examName}</td>
				<td>${exam.questionCount}</td>
				<td>${exam.totalMarks}</td>
				<td>${exam.passingMarks}</td>
				<td>${exam.obtainedMarks}</td>
				<td>${result}</td>
			</tr>
		</tbody>
	</table>

	<table class="table table-hover" title="Detailed Result">
		<thead>
			<tr>
				<th><spring:message code="question.srno" /></th>
				<th><spring:message code="exam.question" /></th>
				<th><spring:message code="exam.question.marks" /></th>
				<th><spring:message code="exam.question.marks.obtained" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${questions}" var="question">
				<tr>
					<td>${question.srNo}</td>
					<td>${question.question}</td>
					<td>${question.maxMarks}</td>
					<td>${question.obtainedMarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script>
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
});
</script>