<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/exams/${examId}/questions/0"/>"
			method="get">
			<button type="submit" class="btn btn-primary">Add Question</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
		 &nbsp;&nbsp;
		<form action="<c:url value="/user/logout"/>"
			method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th><spring:message code="question.srno" /></th>
				<th><spring:message code="exam.question" /></th>
				<th><spring:message code="exam.question.marks" /></th>
				<th><spring:message code="exam.action" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${questions}" var="question">
				<tr>
					<td>${question.srNo}</td>
					<td>${question.question}</td>
					<td>${question.maxMarks}</td>
					<td><a
						href="<c:url value="/user/exams/${examId}/questions/${question.id}"/>">
							<spring:message code="exam.view" />
					</a>
					|
					<a
						href="<c:url value="/user/exams/${examId}/questions/delete/${question.id}"/>">
							<spring:message code="user.register.delete" />
					</a>
					</td>
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