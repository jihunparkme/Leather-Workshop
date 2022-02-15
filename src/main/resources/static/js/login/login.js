$(function() {
    $('.google').click(function () {
       loginGoogle();
    });

    $('.naver').click(function () {
        loginNaver();
    });
});

function loginGoogle() {
    location.href = "/oauth2/authorization/google"
}

function loginNaver() {
    location.href = "/oauth2/authorization/naver"
}
