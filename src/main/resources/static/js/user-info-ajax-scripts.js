$(document).ready(() => {

    $('#items-for-sale').on('click', (e) => {
        e.preventDefault();
        let url = e.target.href;
        $('.users-info-render-container').load(url);
    });


})