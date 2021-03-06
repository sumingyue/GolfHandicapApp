<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 02/07/14
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Welcome!</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.css" rel="stylesheet">

        <!-- CSS -->
        <link href="${pageContext.request.contextPath}/resources/css/template.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <div class="row">

                <!-- Navigation -->
                <div class="navbar navbar-inverse">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="navbar-collapse collapse navbar-inverse-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/myHandicap/history">My Handicap</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/changeAccountPassword">Change Password</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/disableUserAccount">Delete Account</a></li>
                                </ul>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                            <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a></li>
                        </ul>
                    </div>
                </div>

                <div class="jumbotron">
                    <h1>Submit Score Card</h1>
                    <p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
                    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/myHandicap/calculate">Calculate Handicap</a></p>
                </div>

            </div>

            <div class="row">

                <h2>How we calculate your handicap</h2>

                <p>
                    Test Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test

                     <div class="well">
                        Calculation example goes here.
                     </div>

                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test
                    Test TestTest Test Test TestTest TestTest TestTest TestTest TestTest TestTest TestTest TestTest Test


                    <br/>
                    <br/>
                    U/N - TestTest123<br/>
                    P/W - qwerty12

                </p>

            </div>

            <div class="row">
                <blockquote class="pull-right">
                    <p>I have a tip that can take five strokes off anyone's game: It's called an eraser.</p>
                    <footer>Arnold Palmer</footer>
                </blockquote>
            </div>

            <jsp:include page="common/footer.jsp"/>

        </div>

    </body>

</html>
