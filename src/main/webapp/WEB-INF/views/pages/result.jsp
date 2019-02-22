<%@include file="../tags/commonTags.jsp"%>

<div class="container">

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