$(document).ready(() => {
    $('.bubble').hide();
    $('#login').click(() => {
        $('.login').show()
    })
    $('#register').click(() => {
        $('.register').show()
    })
    $('#add-category-btn').click(() => {
        $('.add-category-container').show()
    })
});