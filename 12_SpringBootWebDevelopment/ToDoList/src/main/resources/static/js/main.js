$(function () {

    const appendTask = function (data) {
        let taskCode = '<a href="#" class="task-link" data-id="' +
            data.id + '">' + data.name + '</a>';

        $('#task-list').append('<div>' + taskCode + '</div>');
    };

    //Loading books on load page
    // $.get('/tasks/', function (response) {
    //     for (let i in response) {
    //         appendTask(response[i]);
    //     }
    // });

    //Show adding task form
    $('#show-add-task-form').click(function () {
        $('#task-form').css('display', 'flex');
    });

    //Show delete task form
    $('#show-delete-task-form').click(function () {
        $('#task-form-delete').css('display', 'flex');
    });

    //Show update task form
    $('#show-update-task-form').click(function () {
        $('#task-form-update').css('display', 'flex');
    });


    //Show list tasks
    $('#show-list-tasks').click(function () {
        $.ajax({
            method: "GET",
            url: '/tasks/',
            success: function (response) {
                for (let j in response) {
                    appendTask(response[j]);
                }
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Список задач не найден!');
                }
            }
        });
        return false;
    });


    //Closing adding book form
    $('#task-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });


    //Getting task
    $(document).on('click', '.task-link', function () {
        let link = $(this);
        let taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function (response) {
                let code = '<span><br>№ ' + link.data('id') + '; Время в часах: ' +
                    response.hour + '; Описание:' +
                    response.description + ';</span>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });


    //Adding task
    $('#save-task').click(function () {
        const data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function (response) {
                $('#task-form').css('display', 'none');
                const task = {};
                task.id = response;

            }
        });
        return false;
    });


    //Update task
    $('#update-task').click(function () {
        const data = $('#task-form-update form input').serialize();

        $.ajax({
            method: "PUT",
            url: '/tasks/',
            data: data,

            success: function (response) {
                $('#task-form-update').css('display', 'none');
            },
            error: function (response) {
                $('#task-form-update').css('display', 'none');
                if (response.status === 404) {
                    alert('Дело не найдено при обновлении!');
                }
            }
        });
        return false;
    });


    //Delete list tasks
    $('#delete-list-tasks').click(function () {
        $.ajax({
            method: "DELETE",
            url: '/tasks/',
            error: function (response) {
                if (response.status === 404) {
                    alert('Нет объектов для удаления');
                }
            }
        });
        return false;
    });


    //Delete task
    $('#delete-task').click(function () {
        const data = $('#task-form-delete form input[name=\'id\']');
        let taskId = data.val();
        $.ajax({
            method: "DELETE",
            url: '/tasks/' + taskId,
            data: data,

            success: function (response) {
                $('#task-form-delete').css('display', 'none');
                let task = {};
                task.id = response;
                $('#task-list-two')
                    .append('<div>' + response + ' is deleted' + '</div>');
            },
            error: function (response) {
                $('#task-form-delete').css('display', 'none');

                if (response.status === 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });
});