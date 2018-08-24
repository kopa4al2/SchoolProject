'use strict';
Dropzone.autoDiscover = false;
let userId;
$(document).ready(() => {
    $('#product-images-dropzone').dropzone({
        autoProcessQueue: false,
        uploadMultiple: true,
        parallelUploads: 100,
        maxFiles: 100,
        init: function () {
            $('.dz-hidden-input').on('change', () => {
                let dropzone = this;
                // First change the button to actually tell Dropzone to process the queue.
                this.element.querySelector("input[type=submit]").addEventListener("click", function (e) {

                    e.preventDefault();
                    dropzone.processQueue();
                    setTimeout(() => {
                        window.location.href = '../sales';
                    }, 1000)

                });
            });

        }
    });

    $('#change-user-pic-modal').on('show.bs.modal', function (e) {
        //Load some more info from sender of the event
        userId = $(e.relatedTarget).data('message');

        //Attach it as dropzone once modal is shown
        $('#upload-user-profile-pic-dropzone').dropzone({
            url: $('#upload-user-profile-pic-dropzone')[0].action,
            autoProcessQueue: false,
            uploadMultiple:false,
            init: function () {
                $('#submit-user-pic').on('click', (e) => {
                    this.processQueue();
                })
            },
            complete:function () {
                location.reload();
            }
        });

    });
});