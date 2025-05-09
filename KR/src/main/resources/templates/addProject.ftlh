<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../static/style.css">
    <title>Создать проект</title>
</head>
<body>
<form action="/project/add" method="post">
    <h3>Создать новый проект</h3>
    <div>
        <label>
            Название проекта:
            <input type="text" name="projectName" placeholder="Введите название">
        </label>
    </div>
    <div>
        <label>
            Описание:
            <textarea name="description" placeholder="Введите описание"></textarea>
        </label>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Создать проект</button>
</form>