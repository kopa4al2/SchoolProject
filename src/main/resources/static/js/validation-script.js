$(document).ready(() => {
    $('.valid-input').on('click', (e) => {
        $(e.target.parentNode).find('.error-span').text("");
    });
    $('.valid-input').on('blur', (e) => {

        let eventFirer = e.target;
        switch (eventFirer.id) {
            case "register-username" :
                usernameValidation.validate(eventFirer);
                break;
            case "register-email" :
                emailValidation.validate(eventFirer);
                break;
            case "register-password" :
                passwordValidation.validate(eventFirer);
                break;
            case "register-confirm-password" :
                confirmPasswordValidation.validate(eventFirer);
                break;
        }
    })

});

let confirmPasswordValidation = {
    validate:
        function(eventFirer) {

            let errorSpan = $(eventFirer.parentNode).find('.error-span');
            if (eventFirer.value != $('#register-password')[0].value) {
                $(errorSpan).text("Passwords dont match");
                this.isValid = false;
            }
            else {
                this.isValid = true;
            }
        },
    isValid: false
};
let passwordValidation = {
    validate:
        function(eventFirer) {

            let errorSpan = $(eventFirer.parentNode).find('.error-span');

            if (eventFirer.value.length < 6) {
                $(errorSpan).text("Password must be atleast 6 symbols");
                this.isValid = false;
            }
            else {
                if(eventFirer.value === $('#register-confirm-password')[0].value) {
                    $($('#register-confirm-password')[0].parentNode).find('.error-span').text("");
                    confirmPasswordValidation.isValid = true;
                }
                this.isValid = true;
            }
        },
    isValid: false
};

let emailValidation = {
    validate:
        function (eventFirer) {
            let emailRegex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            let errorSpan = $(eventFirer.parentNode).find('.error-span');

            if (!emailRegex.test(eventFirer.value)) {
                $(errorSpan).text("Invalid email");
                this.isValid = false;
            }
            else {
                this.isValid = true;
            }
        },
    isValid: false
};
let usernameValidation = {
    validate:
        function(eventFirer) {

            let errorSpan = $(eventFirer.parentNode).find('.error-span');

            if (eventFirer.value.length < 3 || eventFirer.value.length > 16) {
                $(errorSpan).text("Username must be between 3 and 16 characters")
                this.isValid = false;
            }
            //TODO: Search for duplicates in db
            else {
                this.isValid = true;
            }
        },
    isValid: false
};

function isFormValidated() {
    if(usernameValidation.isValid && passwordValidation.isValid
        && emailValidation.isValid && confirmPasswordValidation.isValid) {
        return true;
    } else {
        $('.modal-dialog').addClass("animated");
        $('.modal-dialog').addClass("shake");

        setTimeout(()=> {

            $('.modal-dialog').removeClass("animated");
            $('.modal-dialog;').removeClass("shake");
        }, 1000);
        return false;
    }
}