<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/logout"/>" method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
	</div>
	
	<c:set value="${fn:length(questions)}" var="questionCount" />
	<c:if test="${questionCount eq 0}">
		<c:set value="${exam.questionCount}" var="questionCount" />
	</c:if>
	<c:forEach begin="1" end="${questionCount}" var="qNo">
		<c:set value="${questions[qNo-1]}" var="question" />
		<div class="form-group">
			<label for="exam"> <spring:message code="exam.questin.no" />${qNo}
			</label> <input type="hidden" id="questionId_${qNo}" name="questionId_${qNo}"
				value="${exam.id}">
			<textarea class="form-control mb-1" rows="3" id="examQuestion_${qNo}"
				name="examQuestion_${qNo}"
				placeholder="<spring:message code="exam.question"/>" required>${question.question}</textarea>
			<textarea class="form-control" rows="2" id="examKeyWords_${qNo}"
				name="examKeyWords_${qNo}"
				placeholder="<spring:message code="exam.keywords"/>" required>${question.keyWords}</textarea>
		</div>
	</c:forEach>
	<button id="creatQuestion" type="button" class="btnbtn-primary">
		<spring:message code="user.register.submit" />
	</button>

	<form id="examQuestionsForm"
		action="<c:url value="/user/exams/${exam.id}/questions/all"/>"
		method="post">
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

var countDownDate = new Date().getTime()+(1000*60*90); //1551022659254
window.alert(countDownDate); //1820746
var x = setInterval(function() {
	var now = new Date().getTime();
	 var distance = countDownDate - now;
	 console.log(distance);
	  // Time calculations for days, hours, minutes and seconds
	  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
	  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

	  // Display the result in the element with id="demo"
	  document.getElementById("demo").innerHTML = hours + ": "
	  + minutes + ": " + seconds + "s ";

	  // If the count down is finished, write some text 
	  if (distance < 0) {
	    clearInterval(x);
	    document.getElementById("demo").innerHTML = "EXPIRED";
	  }
	}, 1000);
</script>