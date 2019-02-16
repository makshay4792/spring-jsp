<%@include file="../tags/commonTags.jsp"%>
<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<button type="button" class="btn btn-primary">Add Exam</button>
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
					<td>${exam.exam}</td>
					<td>${exam.questionCount}</td>
					<td>${exam.durationInMin}</td>
					<td>
						<a href="<c:url value="/user/register"/>">
							<spring:message code="exam.view" />
						</a>
						&nbsp;|&nbsp;
						<a href="<c:url value="/user/register"/>">
							<c:choose>
								<c:when test="${questionCountDB gt 0}">
									<spring:message code="exam.view.question" />
								</c:when>
								<c:otherwise>
									<spring:message code="exam.add.question" />
								</c:otherwise>
							</c:choose>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>