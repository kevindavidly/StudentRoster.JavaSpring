<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
<!-- BootStrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- BootStrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container-fluid">

		<h1 class="pt-4">${student.first_name}${student.last_name}</h1>

		<!------------------------ Add student to course form ------------------------>

		<div>
			<form action="/students/${student.id}/process" method="post"">
				<label>Course</label> 
				<select class="form-control mb-2 col-6" name="course_id">
					<c:forEach items="${courses}" var="course">
						<option value="${course.id}">${course.name}</option>
					</c:forEach>
				</select> 
				<input type="submit" value="Submit" class="btn btn-info" />
			</form>
		</div>

		<table class="table mt-5">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${student.courses}" var="course">
					<tr>
						<td><a href="/courses/${course.id}">${course.name}</a></td>
						<td>
							<form action="/students/${course.id}/delete" method="get">
								<input type="hidden" name="student_id" value="${student.id}" />
								<input type="hidden" name="course_id" value="${course.id}" />
								<input type="submit" class="btn btn-link" value="Delete" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>