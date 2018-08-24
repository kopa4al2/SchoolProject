$(document).ready(() => {
    $('#product-images').on('show.bs.modal', function () {
        let imgs = $('.thumb-image');
        for (img of imgs) {
            if (img.naturalWidth > 1000 || img.naturalHeight > 1000) {
                $(img).magnify();
            }
        }
    });
    $('#confirm-modal').on('show.bs.modal', function (e) {
        //Load some more info from sender of the event
        let additionalModalInfo = $(e.relatedTarget).data('message');
        let successIdentifier = $(e.relatedTarget).data('on-success');
        $('.more-info').text(additionalModalInfo);
        let href = $(e.relatedTarget).data('href');

        $(this).find('.btn-ok').click(() => {

            let successMessage ="success-message-identifier";
            let formData = new FormData();
            formData.append("success-message-identifier", successIdentifier);
            $.post({
                url: href,
                data:formData,
                processData:false,
                contentType: 'application/json',
                success:function (data,status,xhr) {
                    let successMessage = xhr.getResponseHeader('success');
                    let url = "/" + xhr.getResponseHeader('redirect-url')
                        .replace(/^\/([^\/]*).*$/, '$1');

                        appendSuccessBox(successMessage);
                        $('#confirm-modal').modal('hide');
                        setTimeout(() => {
                            window.location.replace(url);
                        },2000)




                }
            })
        })

    });


});

function appendSuccessBox(successMessage) {
    appendBox(successMessage, 'success-message');
}
function appendErrorBox(successMessage) {
    appendBox(successMessage, 'error-message');
}

function appendBox(message, cssClass) {
    let messageBox = $('<div>');
    $(messageBox).appendTo($('body'));
    $(messageBox).text(message);
    $(messageBox).addClass('animated-box');
    $(messageBox).addClass(cssClass);
    $(messageBox).animate({top: "+=50"}, 1000);

    $('<div>').appendTo($(messageBox));

    $('.animated-box').click(() => {
        $(messageBox).fadeOut();
    });

    setTimeout(() => {
        $('.animated-box').animate({top: "-=50"}, 1000, () => {
            $('.animated-box').remove();
        })
    }, 3000);
}

function addCategory() {
    $('#selected-categories').text($('#select-category').val())
}


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