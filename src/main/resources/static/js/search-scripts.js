
function filterUsers(page, size) {
    let searchFilter = $('#search-select').val();
    let container = $('#all-users-table');
    let baseUrl = "/admin/search/";
    switch (searchFilter) {
        case'email':
            baseUrl += "?email=";
            break;
        case'username':
            baseUrl += "?username=";
            break;
    }
    let filter = $('#search-admin')[0].value.toUpperCase();
    baseUrl += filter;
    search(page, size, container, baseUrl);
}

function filterProducts(page, size) {

    let filter = $('#search-products-input')[0].value.toUpperCase();
    let container = $('#product-container');
    let baseUrl = "/sales/get-all/search/?title=";
    baseUrl += filter;
    search(page, size, container, baseUrl)
}

function filterReviews(page, size) {

    let filter = $('#search-review')[0].value.toUpperCase();
    let container = $('.render-review-container');
    let baseUrl = "/reviews/search/?title=";
    baseUrl += filter;
    search(page, size, container, baseUrl)
}
//Search criteria is in the template  ENTITIY-CRITERIA
function searchEntity(page, size, searchEntity) {

    switch (searchEntity) {
        case'products':
            filterProducts(page, size);
            break;
        case'users':
            filterUsers(page, size);
            break;
    }
}

function search(page, size, container, baseUrl) {
    if (baseUrl == null) {

    }
    if (page != null && size != null) {
        //If there is another query parameter
        if (baseUrl.includes('?'))
            baseUrl += `&page=${page}&size=${size}`;
        else
            baseUrl += `?page=${page}&size=${size}`;
    }
    $(container).load(baseUrl);
}