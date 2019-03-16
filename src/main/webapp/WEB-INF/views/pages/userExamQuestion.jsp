<%@include file="../tags/commonTags.jsp"%>

<div class="container">
	<div class="d-flex flex-row-reverse mb-2">
		<form action="<c:url value="/user/logout"/>" method="post">
			<button type="submit" class="btn btn-primary">Logout</button>
			<!-- <button type="submit"class="btnbtn-primary"><spring:message code="user.register.submit"/></button> -->
		</form>
		&nbsp;&nbsp;
		<p id="demo">Timer</p>
	</div>
	<c:set value="${fn:length(questions)}" var="questionCount" />
	<c:forEach begin="1" end="${questionCount}" var="qNo">
		<c:set value="${questions[qNo-1]}" var="question" />
		<div class="form-group">
			<label for="exam"> <spring:message code="exam.questin.no" />${qNo}
			</label> <input type="hidden" id="questionId_${qNo}" name="questionId_${qNo}"
				value="${exam.id}">
			<textarea class="form-control mb-1" rows="3" id="question_${qNo}"
				name="examQuestion_${qNo}"
				placeholder="<spring:message code="exam.question"/>" readonly
				required>${question.question}</textarea>
			<textarea class="form-control" rows="4" id="examAnswer_${qNo}"
				name="examAnswer_${qNo}"
				placeholder="<spring:message code="exam.answer"/>" required></textarea>
		</div>
	</c:forEach>
	<button id="creatQuestion" type="button" class="btnbtn-primary">
		<spring:message code="user.register.submit" />
	</button>

	<form id="examQuestionsForm"
		action="<c:url value="/user/exams/evaluate/${exam.id}/${userId}"/>"
		method="post" name="questions">
		<input type="hidden" id="examQuestions" name="examQuestions" value="">
	</form>
</div>

<script>
$(document).ready(function() {
	$('#creatQuestion').click(function() {
		//window.alert('hi'); //1820746
		var exams = ""
		for (var i = 1; i <= ${exam.questionCount}; i++) {
			var questionId = $('#questionId_' + i).val();
			var examQuestion = $('#examQuestion_' + i).val();
			var examAnswer = $('#examAnswer_' + i).val();
			
			exams += questionId + "@" + examQuestion + "@" + examAnswer + "#";
		}
		console.log(exams);
		$('#examQuestions').val(exams);
		
		$('#examQuestionsForm').submit();
	});
	
	

});

var time = ${timerinMinute}
var countDownDate = new Date().getTime()+(1000*60*time); //1551022659254
//window.alert(countDownDate); //1820746
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

	    	var exams = ""
			for (var i = 1; i <= ${exam.questionCount}; i++) {
				var questionId = $('#questionId_' + i).val();
				var examQuestion = $('#examQuestion_' + i).val();
				var examAnswer = $('#examAnswer_' + i).val();
				
				exams += questionId + "@" + examQuestion + "@" + examAnswer + "#";
			}
			console.log(exams);
			$('#examQuestions').val(exams);
			
	    $('#examQuestionsForm').submit();
	  }
	}, 1000);
	
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
});
</script>