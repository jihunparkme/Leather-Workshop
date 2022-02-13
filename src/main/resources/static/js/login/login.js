$(function() {
    $('.google').click(function () {
       loginGoogle();
    });
});

function loginGoogle() {
    location.href = "/oauth2/authorization/google"
}