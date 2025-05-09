<!DOCTYPE html>
<html lang="ru">
<head>
    <title>KR - Регистрация</title>
    <link rel="stylesheet" th:href="@{../static/style.css}">
</head>
<body>
<h4>Регистрация</h4>

<#if errorMessage??>
    <div class="error">
        ${errorMessage}
    </div>
</#if>

<div class="test">
</div>

<form action="/registration" method="post">
    <label>
        <input class="error" type="text" name="username" placeholder="Имя пользователя" required>
    </label><br>

    <label>
        <input type="email" name="email" placeholder="Email" required>
    </label><br>

    <label>
        <input type="text" name="full_Name" placeholder="Полное имя">
    </label><br>

    <label>
        <input type="password" name="password" placeholder="Пароль" required>
    </label><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Регистрация"/>
</form>
<a href="/login">Войти</a>
</body>
</html>