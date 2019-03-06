<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th><spring:message code="userExamResult.sNo" /></th>
				<th><spring:message code="userExamResult.userName" /></th>
				<th><spring:message code="userExamResult.examName" /></th>
				<th><spring:message code="userExamResult.noOfQuestions" /></th>
				<th><spring:message code="userExamResult.actualMarks" /></th>
				<th><spring:message code="userExamResult.optainMarks" /></th>
			</tr>
		</thead>
		
		<tbody>
			<c:set value="${fn:length(userExamResults)}" var="count" />
			<c:forEach begin="1" end="${count}" var="rNo">
				<c:set value="${userExamResults[rNo-1]}" var="userExamResult" />
				<tr>
					<td>${rNo}</td>
					<td>${userExamResult.userName}</td>
					<td>${userExamResult.examName}</td>
					<td>${userExamResult.noOfQuestions}</td>
					<td>${userExamResult.actualMarks}</td>
					<td>${userExamResult.optainMarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

