<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../static/style.css">
    <title>Добавить задачу</title>
</head>
<body>
    <form action="/tasks/add" method="post">
        <h3>Добавить задачу</h3>
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div>
            <label>
                Название задачи:
                <input type="text" name="title" placeholder="Введите название" required>
            </label>
        </div>
        <div>
            <label>
                Описание:
                <textarea name="description" placeholder="Введите описание"></textarea>
            </label>
        </div>
        <div>
            <label>
                Срок выполнения:
                <input type="datetime-local" name="deadline">
            </label>
        </div>
        <button type="submit">Добавить задачу</button>
    </form>
</body>
</html>