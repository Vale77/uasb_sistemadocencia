/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        jQuery('#dialog').effect("shake", {
            times: 3
        }, 100);
    } else {
        dlg.hide();
    }
}
function handleMessages(xhr, status, args) {
    if (args.isValid) {
        errorWidget.show();
    } else {
        errorWidget.hide();
    }
}

function open_win2(archivo, titulo) {
    window.open(archivo, titulo, 'dependent=yes, menubar=no, toolbar=no,location=no');
    return false;
}

function open_win(archivo, titulo) {
    window.open(archivo, "_blank", 'height=800,width=1024,top=10, dependent=yes, menubar=no, toolbar=no,location=no');
}

function selectOne(form, button) {
    turnOffRadioForForm(form);
    button.checked = true;
}
function turnOffRadioForForm(form) {
    for (var i = 0; i < form.elements.length; i++) {
        form.elements[i].checked = false;
    }
}


function radiobuttonClick(thisObj) {
    var form = document.getElementById("formTabla");
    for (var i = 0; i < form.elements.length; i++) {
        form.elements[i].checked = false;
//        getRow(form.elements[i]).className = "ui-datatable-data ui-widget-content";
    }
    thisObj.checked = true;                       //set this radio button to selected state
//    getRow(thisObj).className = "ui-widget-content ui-state-highlight"; //highlight the selected row
}

function getRow(element) {
    currentRow = element.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
    return currentRow;
}

//$(document).ready(function () {
//    initUnSelectRowsEvent();
//});

function initUnSelectRowsEvent() {
    $('.ui-datatable-viaticos tr td div.ui-row-editor span.ui-icon-pencil').each(function () {
        $(this).off("click", unSelectRow());
        $(this).click(unSelectRow());
    });
}

function unSelectRow() {
    var row = $(this);
    $('.ui-datatable-viaticos tr td div.ui-row-editor span.ui-icon-pencil').each(function () {
        if ($(row).parent().parent().parent().attr('data-ri') !== $(this).parent().parent().parent().attr('data-ri')) {
            var thisRow = $(this).parent().parent().parent();

            if ($(thisRow).hasClass('ui-state-highlight') || $(thisRow).hasClass('ui-row-editing')) {
                $('td div.ui-cell-editor-input', thisRow).hide();
                $('td div.ui-cell-editor-output', thisRow).show();
                $(thisRow).removeClass('ui-state-highlight');
                $(thisRow).removeClass('ui-row-editing');
                $('td div.ui-row-editor span.ui-icon-check', thisRow).hide();
                $('td div.ui-row-editor span.ui-icon-close', thisRow).hide();
                $('td div.ui-row-editor span.ui-icon-pencil', thisRow).show();
            }
        }
    });
}

function unSelectRow2() {
    if ($(event.target).hasClass('ui-state-highlight')) {
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-input').hide();
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-output').show();
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-pencil').show();
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-check').hide();
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-close').hide();
        $('.ui-datatable-viaticos tbody.ui-datatable-data tr').removeClass('ui-state-highlight');
    }
    return false;


}

function unSelectRow3() {
//    if ($(event.target).hasClass('ui-icon-pencil')) {
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-input').hide();
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-output').show();
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-pencil').show();
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-check').hide();
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-close').hide();
//        $('.ui-datatable-viaticos tbody.ui-datatable-data tr').removeClass('ui-state-highlight');
//        return false;
//    }

    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child td .ui-cell-editor-input').show();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child td .ui-cell-editor-output').hide();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child td span.ui-row-editor span.ui-icon-pencil').hide();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child td span.ui-row-editor span.ui-icon-check').show();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child td span.ui-row-editor span.ui-icon-close').show();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr:last-child').addClass('ui-state-highlight');


}
function selectRow3() {
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-input').hide();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr td .ui-cell-editor-output').show();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-pencil').show();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-check').hide();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr td span.ui-row-editor span.ui-icon-close').hide();
    $('.ui-datatable-viaticos tbody.ui-datatable-data tr').removeClass('ui-state-highlight');

}
