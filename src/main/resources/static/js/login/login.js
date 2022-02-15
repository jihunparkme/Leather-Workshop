$(function() {
    $('.naver').click(function () {
        loginNaver();
    });

    $('.kakao').click(function () {
        loginKakao();
    });

    $('.google').click(function () {
       loginGoogle();
    });
});

function loginNaver() {
    location.href = "/oauth2/authorization/naver"
}

function loginKakao() {
    location.href = "/oauth2/authorization/kakao"
}

function loginGoogle() {
    location.href = "/oauth2/authorization/google"
}
