<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" isErrorPage="false" errorPage="Unique.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>


<script>
	function passwordcheck() {
		if (document.getElementById('newPassword').value != document
				.getElementById('confirmPassword').value) {
			window.alert("Password Do not Match");
		}
	}
</script>

</head>


<style>
.container {
	width: 500px;
	background-color: blanchedalmond;
}

.footer {
	width: 100%;
	background-color: black;
}

a {
	color: white;
	font-size: 15px;
}

li {
	list-style-type: none;
}

.navbar {
	overflow: hidden;
	background-color: #333;
}

.navbar a {
	float: left;
	font-size: 16px;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

.dropdown {
	float: left;
	overflow: hidden;
}

.navbar a:hover, .dropdown:hover .dropbtn {
	background-color: red;
}
</style>


<body>

	<div class="navbar">

		<a href="Home_Page.html">Home</a>
		<button type="button" class="btn btn-warning " data-toggle="modal"
			data-target="#addModal">Register</button>
		<button type="button" class="btn btn-success " data-toggle="modal"
			data-target="#loginModal">Login</button>
		<a href="#">About Us</a> <a href="FAQ.html">FAQ's</a> <a href="#">Establishments</a>
		<a href="#">Institutions</a>

	</div>


	<div class="modal fade" id="addModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title">What are you ?</h4>
				</div>

				<div class=" modal-content text-center">

					<a style="color: black; font-size: 20px;"
						href="FINAL_REG_CANDIDATE.html">Candidate</a> <br> <a
						style="color: black; font-size: 20px;"
						href="Establishment_Registration.html">Establishment</a> <br>

					<a style="color: black; font-size: 20px;"
						href="Institution_Registration.html">Institution</a>


				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="loginModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title">Login as ?</h4>
				</div>

				<div class=" modal-content text-center">

					<a style="color: black; font-size: 20px;"
						href="Candidate_Login.html">Candidate</a> <br> <a
						style="color: black; font-size: 20px;"
						href="Establishment_Login.html">Establishment</a> <br> <a
						style="color: black; font-size: 20px;"
						href="Institution_Login.html">Institution</a>


				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>





	<div
		style="background-image: url(C:/Users/vshadmin/Desktop/FINAL_HTML/book.jpg)"
		class=" jumbotron">

		<h2>Skill India Online Portal</h2>
		<h3>Candidate Registration</h3>
	</div>



	<form:form method="POST" action="add"
		modelAttribute="candidate">
		<c:if test="${!empty candidate.candidateName}">
			<br />
			<form:label path="UserId">
				<spring:message text="UserId" />
			</form:label>
			<form:input path="UserId" readonly="true" size="10" disabled="true" />
			<form:hidden path="UserId" />
		</c:if>


		<div style="padding: 20px;" class="container text-left">

			<div class="form-group">
				<form:label path="candidateName"> Candidate Name: <span
						style="color: red">*</span>
					<spring:message text=" candidateName" />
				</form:label>
				<form:input required="" class="form-control" path="candidateName" />
			</div>

			<div class="form-group">

				<form:label path="FatherName">
					<spring:message text="Father Name" />Father's Name: <span
						style="color: red">*</span>
				</form:label>

				<form:input required="" class="form-control" path="FatherName" />


			</div>


			<div class="form-group">
				<form:label path="phoneNumber"> Contact Number: <span
						style="color: red">*</span>
					<spring:message text="Phone Number" />
				</form:label>
				<form:input required="" pattern="[7-9]{1}[0-9]{9}"
					class="form-control" path="phoneNumber" />
			</div>

			<div class="form-group">
				<form:label path="EmailId"> Email Id: <span
						style="color: red">*</span>
					<spring:message text="Email-Id" />
				</form:label>
				<form:input required="" class="form-control" path="EmailId" />
			</div>

			<div class="form-group">
				<form:label path="newPassword">Password: <span
						style="color: red">*</span>
					<spring:message text="Password" />
				</form:label>

				<form:input required="" id="newpassword" class="form-control"
					path="newPassword" type="password" />
			</div>


			<div class="form-group">
				<form:label path="confirmPassword">Confirm Password: <span
						style="color: red">*</span>
					<spring:message text="Confirm Password" />
				</form:label>

				<form:input required="" id="confirmPassword" class="form-control"
					path="confirmPassword" type="password" />
			</div>


			<div class="form-group">
				<form:label path="dob"> Date of Birth: <span
						style="color: red">*</span>
					<spring:message text="Date of Birth" />
				</form:label>

				<form:input required="" class="form-control" path="dob" type="date" />

			</div>




			<div class="form-group">

				<form:label path="EducationalDetails" type="number">Education Details: <span
						style="color: red">*</span>
					<spring:message text="eduDetails" />
				</form:label>
				<form:input required="" class="form-control"
					path="EducationalDetails" />


			</div>


			<div class="form-group">
				<form:label path="aadhaarNumber">
					<spring:message text="Aadhaar Number" />Aadhar Number: <span
						style="color: red">*</span>
				</form:label>

				<form:input required="" class="form-control"
					pattern="^\d{4}-\d{4}-\d{4}$" path="aadhaarNumber" type="tel" />
			</div>



			<div class="form-group">
				<form:label path="Gender"> Gender: <span
						style="color: red">*</span>
					<spring:message text="Gender" />
				</form:label>
				<form:input required="" class="form-control" path="Gender" />
			</div>

			<div class="form-group">

				<form:label path="address.localAddress">Address: <span
						style="color: red">*</span>
					<spring:message text="Local Adress" />
				</form:label>

				<form:input required="" class="form-control"
					path="address.localAddress" />

			</div>


			<div class="form-group">

				<form:label path="address.city">City: <span
						style="color: red">*</span>
					<spring:message text="City" />
				</form:label>

				<form:input required="" class="form-control" path="address.city" />

			</div>


			<div class="form-group">

				<form:label path="address.state">State: <span
						style="color: red">*</span>
					<spring:message text="State" />
				</form:label>

				<form:input required="" class="form-control" path="address.state" />
			</div>


			<div class="form-group">

				<form:label path="bankDetails.bankName">Bank Name: <span
						style="color: red">*</span>
					<spring:message text="bankName" />
				</form:label>

				<form:input required="" class="form-control"
					path="bankDetails.bankName" />

			</div>


			<div class="form-group">

				<form:label path="bankDetails.bankBranch">Branch Name: <span
						style="color: red">*</span>
					<spring:message text="bankBranch" />
				</form:label>

				<form:input required="" class="form-control"
					path="bankDetails.bankBranch" />

			</div>

			<div class="form-group">

				<form:label path="bankDetails.bankIFSC">Bank IFSC Code: <span
						style="color: red">*</span>
					<spring:message text="bankIFSC" />
				</form:label>

				<form:input required="" class="form-control"
					path="bankDetails.bankIFSC" />


			</div>

			<div class="form-group">

				<form:label path="bankDetails.accountNumber" type="number">Account Number: <span
						style="color: red">*</span>
					<spring:message text="Account number" />
				</form:label>

				<form:input required="" class="form-control"
					path="bankDetails.accountNumber" />


			</div>


			<div class="form-group">

				<button type="button" class="btn btn-success">Upload
					(Passport-Size Photo)</button>
				<br> <br>

			</div>



			<input type="submit" class="button" value="Submit" />



		</div>

	</form:form>


	<footer>

	<div class="footer" style="float: left; margin-top: 10px;">
		<ul style="margin-top: 10px;">

			<li><a href="">Disclaimer</a></li>
			<br>
			<li><a href="">Policy</a></li>
			<br>

			<li><a href="">FAQ's</a></li>
			<br>

			<li><a href="">Contact Us</a></li>
			<br>


		</ul>

		<div style="float: right; color: white;">
			<p style="font-size: 15px;">Copyright &copy; 2019 Skill India .
				All Rights Reserved.</p>

		</div>


	</div>





	</footer>



</body>

</html>