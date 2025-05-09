<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Мои проекты</title>
    <link rel="stylesheet" href="../static/style.css">
</head>
<body>
<h1>Список проектов</h1>

<div class="project-list">
    <#list memberProjects as project>
        <div class="project-item">
            <a href="/projects/${project.projectId}/tasks">${project.projectName}</a>
        </div>
    </#list>
</div>

<a href="addProject">Создать проект</a>
</body>
</html>