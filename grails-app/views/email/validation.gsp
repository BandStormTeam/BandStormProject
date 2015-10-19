<!Doctype>
<html>
<head>
    <style>
        #container {
            background-color: whitesmoke;
            text-align: center;
            padding-bottom: 20px;
        }
    </style>
</head>

<body>
<div id="container">
    <br><br><br><br>
    <p>${username} Thank you for joining us!!</p>
    <p>Please click the link below to activate your account.</p>
    <form method="get" action="http://localhost:8080/BandStorm/user/activateAccount">
        <input type="hidden" name="username" value="${username}">
        <input type="submit" value="Activate my Account">
    </form>
</div>
</body>
</html>