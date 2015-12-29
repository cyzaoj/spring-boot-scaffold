<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<form action="/signin/weibo" method="POST">
    <button type="submit">Sign in with weibo</button>
    <input type="hidden" name="scope" value="all"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
