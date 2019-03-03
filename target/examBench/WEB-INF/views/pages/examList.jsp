<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/exams/0"/>" method="get">
			<button type="submit" class="btn btn-primary">Add Exam</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th><spring:message code="exam.code" /></th>
				<th><spring:message code="exam.name" /></th>
				<th><spring:message code="exam.number.of.questions" /></th>
				<th><spring:message code="exam.duration" /></th>
				<th><spring:message code="exam.action" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${exams}" var="exam">
				<tr>
					<td>${exam.id}</td>
					<td>${exam.examName}</td>
					<td>${exam.questionCount}</td>
					<td>${exam.durationInMin}</td>
					<td>
						<a href="<c:url value="/user/exams/${exam.id}"/>">
							<spring:message code="exam.view" />
						</a>
						&nbsp;|&nbsp;
						<a href="<c:url value="/user/exams/${exam.id}/questions"/>">
							<spring:message code="exam.view.question" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>