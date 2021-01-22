/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*function test() {
        $(PrimeFaces.escapeClientId('formMant:tabView:editorObjEsp')).find('iframe').contents().find('body').css({'font-size': '12pt'});

}*/
$jQuery(document).ready(function($) {
    $(PrimeFaces.escapeClientId('formMant:tabView:editorTema')).find('iframe').contents().find('body').css({'font-size': '12pt'});
//    $(PrimeFaces.escapeClientId('formMant:tabView:editorObjEsp')).find('iframe').contents().find('body').css({'font-size': '12pt'});


    /*var imax = 2048;
     $('#formMant\\:tabView\\:editorDescrip').find('iframe').contents().find("body").on('keyup', function(e) {
     ilength = $('#formMant\\:tabView\\:editorDescrip').find('iframe').contents().find("body").text().length;
     $('#formMant\\:tabView\\:counterDescrip').html((imax - ilength)+' caracteres disponibles.');
     });*/
});

/*        var colapsed = false;
        function doCollapsed() {
            if (!colapsed) {
                layoutV.toggle('west');
            }
        }

        function doExpand() {
            if (colapsed) {
                layoutV.toggle('west');
            }
        }

        function toggleLayout() {
            if (colapsed) {
                colapsed = false;
            } else {
                colapsed = true;
            }
        }
                    <p:ajax event="toggle" oncomplete="toggleLayout();" />
        */