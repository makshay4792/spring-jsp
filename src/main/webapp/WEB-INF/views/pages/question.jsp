<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex justify-content-center">
		<form action="<c:url value="/user/exams/${examId}/questions"/>" name="exam" method="post">
			<div class="form-group">
				<label for="Question"> <spring:message code="exam.question" />
				</label> 
				<input type="text" class="form-control" id="questionid" name="question"
					aria-describedby="exam"
					placeholder="<spring:message code="exam.question"/>"
					value="${question.question}" required>
			</div>
			<div class="form-group">
				<label for="Key Words"> <spring:message
						code="exam.keywords" />
				</label> 
				<input type="text" class="form-control" id="keywords"
					name="keys" aria-describedby="keywords"
					placeholder="<spring:message code="exam.keywords"/>"
					value="${question.keys}" required>
			</div>
			<div class="form-group">
				<label for="maxMarks"> <spring:message code="exam.question.marks" />
				</label> <input type="number" class="form-control" id="durationInMin"
					name="maxMarks"
					placeholder="<spring:message code="exam.question.marks"/>"
					value="${question.maxMarks}" required>
			</div>
			<button type="submit" class="btnbtn-primary">
				<spring:message code="user.register.submit" />
			</button>
		</form>
	</div>
</div>