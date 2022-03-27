$(function() {
    addPage(page++); // init page

    $("#portfolio-flters li").click(function (e) {
        if (category == $(this).text()) {
            return;
        }

        changedCategory($(this).text());

        e.preventDefault();
        $('#portfolio-flters').children('li').each(function() {
            $(this).removeClass('filter-active');
        });

        $(this).addClass('filter-active');
    });
});

let category = 'ALL';

let page = 0;
let totalPage = 0;
const articlesPerPageSize = 10;

function changedCategory(ctg) {
    category = ctg;
    page = 0;

    document.getElementById('article-list').innerHTML = '';
    addPage(page++);
}

function getDocumentHeight() {
    const body = document.body;
    const html = document.documentElement;

    return Math.max(
        body.scrollHeight, body.offsetHeight,
        html.clientHeight, html.scrollHeight, html.offsetHeight
    );
};

function getScrollTop() {
    return (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
}

function getItem(data) {
    let item = document.createElement('a');
    item.href = '/product/' + data.id;
    item.className = 'portfolio-info portfolio-details-lightbox';

    let infoName = document.createElement('h4');
    infoName.innerText = data.name;
    item.appendChild(infoName)

    let infoCtgy = document.createElement('p');
    infoCtgy.innerText = data.productCategory.title;
    item.appendChild(infoCtgy)
    return item;
}

function getContentsImage(thumbnailFileName) {
    let image = new Image();
    image.className = 'thumbnail article-list__item__image--loading';
    image.alt = '';
    image.src = thumbnailFileName;
    image.onload = function () {
        image.classList.remove('article-list__item__image--loading');
    };
    return image;
}

function getThumbnail(thumbnailFileName) {
    let image = new Image();
    image.className = 'thumbnail article-list__item__image--loading';
    image.alt = '';
    image.src = '/file/img/product/' + thumbnailFileName;
    image.onload = function () {
        image.classList.remove('article-list__item__image--loading');
    };
    return image;
}

function getArticleContents(data, contentsFileName) {

    let itemDiv = document.createElement('div');
    itemDiv.className = 'col-lg-4 col-md-6 portfolio-item ' + data.productCategory.title;

    let wrapDiv = document.createElement('div');
    wrapDiv.className = 'portfolio-wrap child-center';

    let image = getContentsImage(contentsFileName);
    let item = getItem(data);
    wrapDiv.appendChild(image);
    wrapDiv.appendChild(item);

    itemDiv.appendChild(wrapDiv);

    return itemDiv;
}

function getArticle(data) {

    let itemDiv = document.createElement('div');
    itemDiv.className = 'col-lg-4 col-md-6 portfolio-item ' + data.productCategory.title;

    let wrapDiv = document.createElement('div');
    wrapDiv.className = 'portfolio-wrap child-center';

    let image = getThumbnail(data.thumbnailFileName);
    let item = getItem(data);
    wrapDiv.appendChild(image);
    wrapDiv.appendChild(item);

    itemDiv.appendChild(wrapDiv);

    return itemDiv;
}

function addPage(page) {
    $.ajax({
        type: 'GET',
        url: '/photos/list/' + category + '/',
        async: false,
        data: {
            page: page,
            size: articlesPerPageSize,
        },
        dataType: 'json',
    }).done(function (result) {
        totalPage = result.totalPages;
        let productList = result.result;
        $('#noData').css('display', 'none');
        if (productList.length == 0) {
            $('#noData').css('display', 'block');
            return;
        }

        let articleList = document.getElementById('article-list');
        for (let i = 0; i < productList.length; i++) {
            let product = productList[i];
            let article = getArticle(product);
            articleList.appendChild(article);

            for (let j = 0; j < product.contentsFileNames.length; j++) {
                let article = getArticleContents(product, product.contentsFileNames[j]);
                articleList.appendChild(article);
            }
        }
    });
}

window.onscroll = function () {
    if ((getScrollTop() + 300) < getDocumentHeight() - window.innerHeight) return;
    // 스크롤이 페이지 하단에 도달할 경우 새 페이지 로드
    if (totalPage == page) {
        return;
    }

    addPage(page++);
};