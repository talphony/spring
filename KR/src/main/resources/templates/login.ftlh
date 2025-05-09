<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>KR - Вход</title>
</head>
<body>
<h4>Авторизация</h4>
<form action="/login" method="post">
    <label>
        <input type="text" name="username" placeholder="Введите логин" required>
    </label><br>
    <label>
        <input type="password" name="password" placeholder="Введите пароль" required>
    </label><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Войти</button>
</form>
<p><a href="/registration">Зарегистрируйтесь</a></p>
</body>
</html>