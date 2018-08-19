$(document).ready(() => {
    $('#upload-images').on('change', () => {

        //Get count of selected files
        let countFiles = $('#upload-images')[0].files.length;

        let imgPath = $('#upload-images')[0].value;
        let extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
        const image_holder = $(".preview-images");
        image_holder.empty();

        if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
            if (typeof (FileReader) !== "undefined") {

                //loop for each file selected for uploaded.
                for (let i = 0; i < countFiles; i++) {

                    const reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "thumb-image"
                        }).appendTo(image_holder);
                    }

                    image_holder.show();
                    reader.readAsDataURL($('#upload-images')[0].files[i]);

                }

            } else {
                alert("This browser does not support FileReader.");
            }
        } else {
            alert("Pls select only images");
        }
    })

    $('#confirmModal').on('show.bs.modal', function(e){
        //Load some more info from sender of the event
        let additionalModalInfo = $(e.relatedTarget).data('message');
        $('.more-info').text(additionalModalInfo);
        let href = $(e.relatedTarget).data('href');
        $(this).find('.btn-ok').click(() => {

            var modal = $(e.delegateTarget);
            $(modal).addClass('loading');
            $.post({
                url:href
            }).then(() => {
                $(modal).modal('hide').removeClass('loading');
                location.reload();
            })
        })

    });
});

