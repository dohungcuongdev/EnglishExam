<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div id="app">
		<form method="post" action="/" @submit="formSubmit">
			Question 1 <input type="text" name="Q1" v-model="studentAnwser[0]">
			<br> Question 2 <select v-model="studentAnwser[1]">
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
			</select> Question 3 <input v-model="studentAnwser[2]" type="radio" name="gender" value="A"> A<br> 
				<input v-model="studentAnwser[2]" type="radio" name="gender" value="B">
			B<br> <input v-model="studentAnwser[2]" type="radio" name="gender" value="C"> C
			<button action="submit">Submit</button>
			<br>
		</form>
		{{studentResult}}
	</div>
</body>
</html>
<script>
    new Vue({
        el: '#app',
        data() {
            return {
            	studentAnwser: [],
            	studentResult: '0',
            }
        },
        methods: {
            formSubmit(e) {
                e.preventDefault();
                console.log(this.studentAnwser);
                var api_url = '${pageContext.request.contextPath}/api/result/' + this.studentAnwser;
                console.log(api_url);
                axios.get(api_url)
                .then(response => {
                    this.studentResult = response.data
                })
                .catch(error => {
                    console.log(error)
                })
            }
        }
    })
</script>