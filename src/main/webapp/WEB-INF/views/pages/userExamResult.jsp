<%@include file="../tags/commonTags.jsp"%>

<div class="container">
<div class="form-group" align="center">
<h3>Exam Result</h3>
</div>
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/logout"/>" method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th><spring:message code="userExamResult.sNo" /></th>
				<th><spring:message code="userExamResult.userName" /></th>
				<th><spring:message code="exam.number.of.questions" /></th>
				<th><spring:message code="exam.question.marks" /></th>
				<th><spring:message code="exam.passing.marks" /></th>
				<th><spring:message code="exam.question.marks.obtained" /></th>
				<th><spring:message code="exam.result" /></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${examResults}" var="resultex">
				<tr>
					<td>${resultex.srNo}</td>
					<td>${resultex.fullName}</td>
					<td>${resultex.exam.questionCount}</td>
					<td>${resultex.exam.totalMarks}</td>
					<td>${resultex.exam.passingMarks}</td>
					<td>${resultex.marksObtained}</td>
					<td>${resultex.result}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="form-group">
		<form action="<c:url value="/user/examlist"/>"
			method="get">
			<button type="submit" class="btnbtn-primary">
				<spring:message code="back" />
			</button>
		</form>
	</div>
</div>

