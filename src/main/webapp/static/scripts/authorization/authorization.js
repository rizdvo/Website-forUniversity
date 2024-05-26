$(document).ready(function () {
    let formPanelTwo = '.form-panel.two';

    $(function () {
        const panelTwo = $(formPanelTwo).prop('scrollHeight');


        $(formPanelTwo).not('.form-panel.two.active').on('click', function (e) {
            e.preventDefault();

            $('.form-toggle').addClass('visible');
            $('.form-panel.one').addClass('hidden');
            $('.form-panel.two').addClass('active');
            $('.form').animate({
                'height': panelTwo
            }, 200);
        });

        $('.form-toggle').on('click', function (e) {
            e.preventDefault();
            $(this).removeClass('visible');
            $('.form-panel.one').removeClass('hidden');
            $('.form-panel.two').removeClass('active');
            $('.form').animate({
                'height': '450px'
            }, 200);
        });
    });
});

document.body.addEventListener('click', function (event) {
    if (event.target.classList.contains('auth-btn')) {
        event.preventDefault();
        $.ajax({
            url: '/auth',
            type: 'GET',
            dataType: 'html',
            async: true,
            success: function () {
                window.location.assign("/auth")
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    if (event.target.classList.contains('login')) {
        event.preventDefault();
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");

        console.log(usernameInput.value);
        console.log(passwordInput.value);

        $.ajax({
            url: '/auth/login',
            type: 'GET',
            contentType: 'text/html',
            data: {
                username: usernameInput.value,
                password: passwordInput.value
            },
            success: function (response) {
                let $html = $(response);

                if ($html.find("#main7878").length > 0) {
                    window.location.assign("/home")
                } else {
                    window.location.assign("/auth-message")
                }
            }
        });
    }

    if (event.target.classList.contains('register')) {
        event.preventDefault();
        const usernameRegisterInput = document.getElementById("usernameRegister");
        const passwordRegisterInput = document.getElementById("passwordRegister");
        const confirmPasswordRegisterInput = document.getElementById("confirmPasswordRegister");
        const emailRegisterInput = document.getElementById("emailRegister");

        $.ajax({
            url: "/auth/register",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                "name": usernameRegisterInput.value,
                "password": passwordRegisterInput.value,
                "confirmPassword": confirmPasswordRegisterInput.value,
                "email": emailRegisterInput.value
            }),
            success: function (response) {
                $("body").html(response)
            }
        });
    }
});
