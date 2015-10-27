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
<div class="container">
    <h2>Bandstorm</h2>
</div>
<div id="container">
    <br><br><br><br>
    <p>${username} Thank you for joining us!!</p>
    <p>Please click the link below to activate your account.</p>
    <form method="get" action="${redirectUrl}">
        <input type="hidden" name="username" value="${username}">
        <input type="submit" value="Activate my Account">
    </form>
</div>

<div class="container" style="background-color: darkgray">
    <strong style="color: red">Problems clicking?</strong>
    <p>copy and past the link below</p>
    <Strong>${redirectUrl}?username=${username}</Strong>
</div>
</body>
</html>