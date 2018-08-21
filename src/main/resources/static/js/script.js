Dropzone.autoDiscover = false;
$(document).ready(() => {

$('#items-for-sale').on('click', (e) => {
    e.preventDefault();
    let url = e.target.href;
    $('.users-info-render-container').load(url);
});

    $('#my-awesome-dropzone').dropzone({
        autoProcessQueue: false,
        uploadMultiple: true,
        parallelUploads: 100,
        maxFiles: 100,
        init:function () {
            $('.dz-hidden-input').on('change', () => {
                let dropzone = this;
                // First change the button to actually tell Dropzone to process the queue.
                this.element.querySelector("input[type=submit]").addEventListener("click", function(e) {
                    // Make sure that the form isn't actually being sent.
                    e.preventDefault()
                    dropzone.processQueue();
                    setTimeout(()=> {
                        window.location.href = '../sales';
                    },1000)

                });
            });

        }
    })
    //OLD UPLOAD IMAGES BAR
    // $('#upload-images').on('change', () => {
    //
    //     //Get count of selected files
    //     let countFiles = $('#upload-images')[0].files.length;
    //
    //     let imgPath = $('#upload-images')[0].value;
    //     let extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
    //     const image_holder = $(".preview-images");
    //     image_holder.empty();
    //
    //     if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
    //         if (typeof (FileReader) !== "undefined") {
    //
    //             //loop for each file selected for uploaded.
    //             for (let i = 0; i < countFiles; i++) {
    //
    //                 const reader = new FileReader();
    //                 reader.onload = function (e) {
    //                     $("<img />", {
    //                         "src": e.target.result,
    //                         "class": "thumb-image"
    //                     }).appendTo(image_holder);
    //                 }
    //
    //                 image_holder.show();
    //                 reader.readAsDataURL($('#upload-images')[0].files[i]);
    //
    //             }
    //
    //         } else {
    //             alert("This browser does not support FileReader.");
    //         }
    //     } else {
    //         alert("Pls select only images");
    //     }
    // })

    $('#confirm-modal').on('show.bs.modal', function (e) {
        //Load some more info from sender of the event
        let additionalModalInfo = $(e.relatedTarget).data('message');
        $('.more-info').text(additionalModalInfo);
        let href = $(e.relatedTarget).data('href');
        $(this).find('.btn-ok').click(() => {

            var modal = $(e.delegateTarget);
            $(modal).addClass('loading');
            $.post({
                url: href
            }).then(() => {
                $(modal).modal('hide').removeClass('loading');
                location.reload();
            })
        })

    });
});

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

function addCategory() {
    $('#selected-categories').text($('#select-category').val())
}

