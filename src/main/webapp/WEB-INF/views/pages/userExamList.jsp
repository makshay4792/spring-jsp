<%@include file="../tags/commonTags.jsp"%>
<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/logout"/>" method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
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
			<c:forEach items="${userExams}" var="userExams">
				<tr>
					<td>${userExams.exam.id}</td>
					<td>${userExams.exam.examName}</td>
					<td>${userExams.exam.questionCount}</td>
					<td>${userExams.exam.durationInMin}</td>
					<td><a
						href="<c:url value="/user/questionpaper/${userExams.exam.id}/${userId}"/>">
							<c:choose>
								<c:when test="${userExams.examStatus eq 0}">
									<spring:message code="user.exam.take" />
								</c:when>
								<c:when test="${userExams.examStatus eq 1}">
									<spring:message code="user.exam.result" />
								</c:when>
								<c:otherwise>
									<spring:message code="user.exam.resume" />
								</c:otherwise>
							</c:choose>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>