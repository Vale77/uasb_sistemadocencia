/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


CKEDITOR.editorConfig = function(config)
{
    config.contentsLanguage = 'es';
    config.toolbarCanCollapse = false;
    config.resize_enabled = false;
    config.toolbar = 'Full';
    config.font_names =
            'Arial/Arial, Helvetica, sans-serif;' +
            'Comic Sans MS/Comic Sans MS, cursive;' +
            'Courier New/Courier New, Courier, monospace;' +
            'Georgia/Georgia, serif;' +
            'Lucida Sans Unicode/Lucida Sans Unicode, Lucida Grande, sans-serif;' +
            'Tahoma/Tahoma, Geneva, sans-serif;' +
            'Times New Roman/Times New Roman, Times, serif;' +
            'Trebuchet MS/Trebuchet MS, Helvetica, sans-serif;' +
            'Verdana/Verdana, Geneva, sans-serif';
    config.toolbar_Full =
            [
                {name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo', 'Redo']},
                {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike']},
                {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
                {name: 'links', items: ['Link', 'Unlink']},
                {name: 'styles', items: ['Format', 'Font', 'FontSize']}
            ];
//    config.removePlugins = 'elementspath';

};

