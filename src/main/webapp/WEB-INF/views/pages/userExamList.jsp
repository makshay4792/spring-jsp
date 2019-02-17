<%@include file="../tags/commonTags.jsp"%>
<div class="container">
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
			<c:forEach items="${userExams}" var="userExams">
				<tr>
					<td>${userExams.exam.id}</td>
					<td>${userExams.exam.exam}</td>
					<td>${userExams.exam.questionCount}</td>
					<td>${userExams.exam.durationInMin}</td>
					<td>
						<a href="<c:url value="/user/register"/>">
							<c:choose>
								<c:when test="${userExams.examStatus eq 0}">
									<spring:message code="user.exam.take" />
								</c:when>
								<c:when test="${userExams.examStatus eq 2}">
									<spring:message code="user.exam.retake" />
								</c:when>
								<c:otherwise>
									<spring:message code="user.exam.resume" />
								</c:otherwise>
							</c:choose>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>