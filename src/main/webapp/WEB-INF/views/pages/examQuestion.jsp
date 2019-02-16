<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<c:set value="${fn:length(questions)}" var="questionCount"/>
	<c:if test="${questionCount eq 0}">
		<c:set value="${exam.questionCount}" var="questionCount"/>
	</c:if>
	<c:forEach begin="1" end="${questionCount}" var="qNo">
		<c:set value="${questions[qNo-1]}" var="question"/>
		<div class="form-group">
			<label for="exam">
				<spring:message code="exam.questin.no"/>${qNo}
			</label>
			<input type="hidden" id="questionId_${qNo}" name="questionId_${qNo}" value="${exam.id}">
			<textarea class="form-control mb-1" rows="3" id="examQuestion_${qNo}" name="examQuestion_${qNo}" placeholder="<spring:message code="exam.question"/>" required>${question.question}</textarea>
			<textarea class="form-control" rows="2" id="examKeyWords_${qNo}" name="examKeyWords_${qNo}" placeholder="<spring:message code="exam.keywords"/>" required>${question.keyWords}</textarea>
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
			var examKeyWords = $('#examKeyWords_' + i).val();
			
			exams += questionId + "-*-" + examQuestion + "-*-" + examKeyWords + "||*||";
		}
		console.log(exams);
		$('#examQuestions').val(exams);
		
		$('#examQuestionsForm').submit();
	});
	
	

});
</script>