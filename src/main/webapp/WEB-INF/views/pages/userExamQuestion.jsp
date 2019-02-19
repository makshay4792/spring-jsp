<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<c:set value="${fn:length(questions)}" var="questionCount"/>
	<c:forEach begin="1" end="${questionCount}" var="qNo">
		<c:set value="${questions[qNo-1]}" var="question"/>
		<div class="form-group">
			<label for="exam">
				<spring:message code="exam.questin.no"/>${qNo}
			</label>
			<input type="hidden" id="questionId_${qNo}" name="questionId_${qNo}" value="${exam.id}">
			<textarea class="form-control mb-1" rows="3" id="question_${qNo}" name="examQuestion_${qNo}" placeholder="<spring:message code="exam.question"/>" readonly required>${question.question}</textarea>
			<textarea class="form-control" rows="4" id="examAnswer_${qNo}" name="examAnswer_${qNo}" placeholder="<spring:message code="exam.answer"/>" required></textarea>
		</div>
	</c:forEach>
	<button id="creatQuestion" type="button"class="btnbtn-primary"><spring:message code="user.register.submit"/></button>
	
	<form id="examQuestionsForm" action="<c:url value="/user/exams/${exam.id}/questions"/>" method="post">
		<input type="hidden" id="examQuestions" name="examQuestions" value="">
	</form>
</div>

<script>
$(document).ready(function() {
	$('#creatQuestion').click(function() {
		var exams = ""
		for (var i = 1; i <= ${exam.questionCount}; i++) {
			var questionId = $('#questionId_' + i).val();
			var examQuestion = $('#examQuestion_' + i).val();
			var examAnswer = $('#examAnswer_' + i).val();
			
			exams += questionId + "-*-" + examQuestion + "-*-" + examAnswer + "||*||";
		}
		console.log(exams);
		$('#examQuestions').val(exams);
		
		$('#examQuestionsForm').submit();
	});
	
	

});
</script>