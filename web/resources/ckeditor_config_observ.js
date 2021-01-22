/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


CKEDITOR.editorConfig = function(config)
{
    config.toolbarCanCollapse = false;
    config.resize_enabled = false;
    config.toolbar = 'Full';
    config.toolbar_Full =
            [
                {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike']},
                {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
                {name: 'links', items: ['Link', 'Unlink']},
                {name: 'styles', items: ['Format', 'Font', 'FontSize']}
            ];
};