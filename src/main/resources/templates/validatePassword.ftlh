<!DOCTYPE html>
<head>
    <meta charset="UTF-8"></meta>
    <title>Password validation</title>
</head>

<body>
    <#if result??>
    <div>
        <span name="result">${result}</span>
    </div>
    <#else/>
    <div>
        <form action="/validatePassword" method="post">
            <span>password</span>
            <input type="text" name="password"></input>
            <br>
            <input type="submit" value="validate"></input>
        </form>
    </div>
    </#if>
</body>
</html>