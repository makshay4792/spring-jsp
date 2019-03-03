<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex justify-content-center">
		<form action="<c:url value="/user/updateExams/${exam.id}"/>" name="exam" method="post">
			<div class="form-group">
				<label for="exam"> <spring:message code="exam.name" />
				</label> 
				<input type="text" class="form-control" id="exam" name="examName"
					aria-describedby="exam"
					placeholder="<spring:message code="exam.name"/>"
					value="${exam.examName}" required>
			</div>
			<div class="form-group">
				<label for="Number of Questions"> <spring:message
						code="exam.number.of.questions" />
				</label> 
				<input type="number" class="form-control" id="questionCount"
					name="questionCount" aria-describedby="questionCount"
					placeholder="<spring:message code="exam.number.of.questions"/>"
					value="${exam.questionCount}" required>
			</div>
			<div class="form-group">
				<label for="duration"> <spring:message code="exam.duration" />
				</label> <input type="number" class="form-control" id="durationInMin"
					name="durationInMin"
					placeholder="<spring:message code="exam.duration"/>"
					value="${exam.durationInMin}" required>
			</div>
			<div class="form-group">
				<label for="totalMarks"> <spring:message code="exam.total.marks" />
				</label> <input type="number" class="form-control" id="totalMarks"
					name="totalMarks"
					placeholder="<spring:message code="exam.total.marks"/>"
					value="${exam.totalMarks}" required>
			</div>
			<div class="form-group">
				<label for="passingMarks"> <spring:message code="exam.passing.marks" />
				</label> <input type="number" class="form-control" id="passingMarks"
					name="passingMarks"
					placeholder="<spring:message code="exam.passing.marks"/>"
					value="${exam.passingMarks}" required>
			</div>
			<button type="submit" class="btnbtn-primary">
				<spring:message code="user.register.submit" />
			</button>
		</form>
	</div>
</div>