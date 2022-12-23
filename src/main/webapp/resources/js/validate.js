validate_form = function () {
    // initialize the validator function
    validator.message['date'] = 'not a real date';
//     validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
//    $('form').on('blur', 'input[validate],input[required], input.optional, select.required', validator.checkField).on('change', 'select.required', validator.checkField).on('keypress', 'input[required][pattern]', validator.keypress);
    $('form').on('keyup', 'input[validate][type!="email"],input[required][type!="email"], input.optional, select.required', validator.checkFieldKeyup);
    $('form').on('keyup', 'input[type="email"]', validator.checkKeyUpEmailPhoneNumber);

//    $('form').on('mouseup', '#ui-datepicker-div', validator.checkFieldKeyup);
//    $('form').on('select', 'input[type="email"]', validator.checkKeyUpEmailPhoneNumber);
//    $('form').on('keyup', 'input[type!="email"]', validator.checkField);
//    $('form').on('keyup', 'input[validate][type!="email"],input[required][type!="email"], input.optional, select.required', validator.checkField);
//    $('form').off('keyup','input[type="email"]');
//    $('form').on('blur', 'input[validate],input[required], input.optional', validator.checkField).on('keypress', 'input[required][pattern]', validator.keypress);
//
//    $('.multi.required').on('keyup blur', 'input', function() {
//        validator.checkField.apply($(this).siblings().last()[0]);checkField
//    });
    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();
        return false;
    });
};
validate_before_submit = function () {
    try {
        $(':input').each(function () {
            $(this).val($.trim($(this).val()));
        });
        if (!validator.checkAll($(validator.form_id))) {
            return false;
        }
    } catch (e) {
    }

    return true;
};

unmarkSingle = function (fieldId) {
    try {
        validator.unmarkSingle($(fieldId));
    } catch (e) {
    }
};
unmarkAll = function () {
    try {
        validator.unmarkAll($(validator.form_id));
    } catch (e) {
    }
};

validate_before_submit_by_form_id = function (form_id) {
    $(':input').each(function () {
        $(this).val($.trim($(this).val()));
    });
    if (!validator.checkAll($(form_id))) {
        return false;
    }
    return true;
};


removeRequiredFlagFromField = function (form_id, field, value) {
    $(':input').each(function () {
        $(this).val($.trim($(this).val()));
    });
    if (!validator.removeRedFlagFromField($(form_id), field, value)) {
        return false;
    }
    return true;
};


trimAllElement = function () {
    $(':input').each(function () {
        $(this).val($.trim($(this).val()));
    });
};
validate_by_field = function () {
    for (var i = 0; i < arguments.length; i++) {
        validator.checkThisField($(validator.form_id), arguments[i]);
    }
}
check_by_field = function () {
    var isError = false;
    for (var i = 0; i < arguments.length; i++) {
        if (!isError) {
            isError = !validator.checkThisField($(validator.form_id), arguments[i]);
            $("#" + arguments[i]).focus();
        } else {
            validator.checkThisField($(validator.form_id), arguments[i]);
        }
    }
    return isError;
}
checkThisField = function (fieldId) {
    validator.checkThisField($(validator.form_id), fieldId);
}
checkThisFieldByFormId = function (formId, fieldId) {
    validator.checkThisField($(formId), fieldId);
}
hasRowEditIsSelected = function (idCheckbox, idMessage, textMessage, textMessage2) {
    var rowSelected = false;
    var countRowSelected = 0;
    $("[name=" + idCheckbox + "][aria-label!='Select All']").each(function () {
        if (this.checked) {
            rowSelected = true;
            countRowSelected = countRowSelected + 1;
        }
    });
    if (!rowSelected) {
//        new PNotify({
//            title: 'Thông báo',
//            text: "Yêu cầu chọn bản ghi để chỉnh sửa",
//            type: 'error'
//        });        
        setMessagePrimeFToView(idMessage, textMessage, "error");
        return false;
    }
    if (countRowSelected > 1) {
//        new PNotify({
//            title: 'Thông báo',
//            text: "Yêu cầu chọn duy nhất 1 bản ghi để chỉnh sửa",
//            type: 'error'
//        });
        setMessagePrimeFToView(idMessage, textMessage2, "error");
        return false;
    }
    hideMessage(idMessage);
    return true;
};
enableOrDisableDeleteButton = function (idCheckbox, idButton) {
    var rowSelected = false;
    $("[name=" + idCheckbox + "][aria-label!='Select All']").each(function () {
        if (this.checked) {
            rowSelected = true;
            return;
        }
    });

    try {
        if (!rowSelected) {
            PF(idButton).disable();
            //$(idButton).prop('disabled', true);
        } else {
            PF(idButton).enable();
            //$(idButton).prop('disabled', false);
        }
    } catch (e) {
    }
};

hasRowDeleteIsSelected = function (idCheckbox, idMessage, textMessage) {
    var rowSelected = false;
    $("[name=" + idCheckbox + "][aria-label!='Select All']").each(function () {
        if (this.checked) {
            rowSelected = true;
            return;
        }
    });
    if (!rowSelected) {
//        new PNotify({
//            title: 'Thông báo',
//            text: "Yêu cầu chọn bản ghi để xóa",
//            type: 'error'
//        });
        setMessagePrimeFToView(idMessage, textMessage, "error");
        return false;
    }
    hideMessage(idMessage);
    return true;
};

setMessagePrimeFToView = function (idMessage, textMessage, messageType) {//messageType = error, info, warning,fatal
    var html = '<div class="ui-messages-' + messageType + ' ui-corner-all">\n\
                    <a href="#" class="ui-messages-close" onclick="$(this).parent().slideUp();return false;">\n\
                        <span class="ui-icon ui-icon-close"></span></a><span class="ui-messages-' + messageType + '-icon"></span>\n\
                        <ul>\n\
                            <li><span class="ui-messages-' + messageType + '-summary">' + textMessage + '</span><span class="ui-messages-' + messageType + '-detail"> </span></li>\n\
                        </ul>\n\
                </div>';
    $(idMessage).html(html);
    $(idMessage).css("display", "block");
};
hideMessage = function (idMessage) {
    $(idMessage).css("display", "none");
};


showMessage = function (messageId) {
    //$('#errorForm\\:messageErr').show();
    $(messageId).show();
};

hideMessage = function (messageId) {
    //$('#errorForm\\:messageErr').hide();
    $(messageId).hide();
};

hasRowIsSelected = function (datatableId, messageId) {
    var rowSelected = false;
    var countRowSelected = 0;
    //$("[name=errorForm\\:datatable_checkbox][aria-label!='Select All']").each(function () {
    $("[name=" + datatableId + "_checkbox][aria-label!='Select All']").each(function () {
        if (this.checked) {
            rowSelected = true;
            countRowSelected = countRowSelected + 1;
        }
    });
    if (!rowSelected) {
        /*new PNotify({
         title: 'Thông báo',
         text: "Yêu cầu chọn bản ghi để chỉnh sửa",
         type: 'error'
         });*/
        //alert("Yêu cầu chọn bản ghi để chỉnh sửa");
        try {
            //setMessagePrimeFToView('#errorForm\\:messageErr', 'Yêu cầu chọn bản ghi để chỉnh sửa', 'error');
            //setMessagePrimeFToView('#errorForm\\:messageErr', "#{lang['common.msg.unselected']}", 'error');
            setMessagePrimeFToView(messageId, "#{lang['common.msg.unselected']}", 'error');
        } catch (e) {
        }
        return false;
    }
    if (countRowSelected > 1) {
        /*new PNotify({
         title: 'Thông báo',
         text: "Yêu cầu chọn duy nhất 1 bản ghi để chỉnh sửa",
         type: 'error'
         });*/
        //alert("Yêu cầu chọn duy nhất 1 bản ghi để chỉnh sửa");
        try {
            //setMessagePrimeFToView('#errorForm\\:messageErr', 'Yêu cầu chọn duy nhất 1 bản ghi để chỉnh sửa', 'error');
            //setMessagePrimeFToView('#errorForm\\:messageErr', "#{lang['common.msg.select.onlyone']}", 'error');
            setMessagePrimeFToView(messageId, "#{lang['common.msg.select.onlyone']}", 'error');
        } catch (e) {
        }
        return false;
    }
    return true;
};

validateRowEditSelect = function (datatableId, messageId, modalActionId) {
    hideMessage(messageId);
    if (!hasRowIsSelected(datatableId, messageId)) {
        return false;
    } else {
        openModalAction(modalActionId);
    }
};


hasRowIsDeleted = function (datatableId) {
    var rowSelected = false;
    //$("[name=errorForm\\:datatable_checkbox][aria-label!='Select All']").each(function () {
    $("[name=" + datatableId + "_checkbox][aria-label!='Select All']").each(function () {
        if (this.checked) {
            rowSelected = true;
            return;
        }
    });
    if (!rowSelected) {
        /*new PNotify(
         title: 'Thông báo',
         text: "Yêu cầu chọn bản ghi để xóa",
         type: 'error'
         });*/
        /*try{
         //setMessagePrimeFToView('#errorForm\\:messageErr', 'Yêu cầu chọn bản ghi để xóa', 'error');
         setMessagePrimeFToView('#errorForm\\:messageErr', "#{lang['common.msg.unselected']}", 'error');
         }catch(e){
         alert(e);
         }*/
        return false;
    }
    return true;
};
validateRowDeleteSelect = function (datatableId, messageId, modalDeleteId, errorMessage) {
    hideMessage(messageId);
    if (!hasRowIsDeleted(datatableId)) {
        //setMessagePrimeFToView('#errorForm\\:messageErr', "#{lang['common.msg.unselected']}", 'error');
        //setMessagePrimeFToView(messageId, "#{lang['common.msg.unselected']}", 'error');
        setMessagePrimeFToView(messageId, errorMessage, 'error');
        //$('#errorForm\\:messageErr').show();
        showMessage(messageId);
        return false;
    } else {
        //openModalAction('#modalDelete');
        openModalAction(modalDeleteId);
    }
};


//datatable
clearSelectedRow = function (widgetTableId) {
    PF(widgetTableId).unselectAllRows();
};