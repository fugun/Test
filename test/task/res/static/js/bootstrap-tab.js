var addTabs = function (options) {
    console.log("test");
    //var rand = Math.random().toString();
    //var id = rand.substring(rand.indexOf('.') + 1);
    var url = window.location.protocol + '//' + window.location.host;
    options.url = url + options.url;
    id = options.id;
    $(".active").removeClass("active");
    //如果TAB不存在，创建一个新的TAB
    if (!$("#tab_" + id)[0]) {
        //固定TAB中IFRAME高度
        //<li><a href="#portlet_tab2_2" data-toggle="tab">Tab 2</a></li>
        mainHeight = $(document.body).height() - 90;
        //创建新TAB的title
        //title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + options.title;
        title = '<li id=tabtitle_' + id + '><button class="close" onclick="javascript:closeTab(' + id + ');"></button><a href="#tab_' + id + '" data-toggle="tab">' + options.title + '</a></li>';
        //是否允许关闭
        //if (options.close) {
        //    title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
        //}
        title += '</a></li>';
        //是否指定TAB内容
        if (options.content) {
            content = '<p role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</p>';
        } else {//没有内容，使用IFRAME打开链接
            //content = '<p role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="' + mainHeight +
            //    '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></p>';
            /*
             <div class="tab-pane active" id="tab_4_1">

             <p>I'm in Section 1.</p>

             <p>

             Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat.

             </p>

             </div>
             */
            content = '<div class="tab-pane" id="tab_' + id + '"><iframe id=ifr_' + id + ' name=ifr_' + id + ' src="' + options.url + '" width="100%" height="' + mainHeight +
                '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes" onload="javascript:dyniframesize(\'ifr_' + id + '\');"></iframe></div>';
        }
        //加入TABS
        $(".nav-tabs").append(title);
        $(".tab-content").append(content);
    }
    //激活TAB
    $("#tabtitle_" + id).addClass('active');
    $("#tab_" + id).addClass("active");
}

var closeTab = function (id) {
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') == "tabtitle_" + id) {
        $("#tabtitle_" + id).prev().addClass('active');
        $("#tab_" + id).prev().addClass('active');
    }
    //关闭TAB
    $("#tabtitle_" + id).remove();
    $("#tab_" + id).remove();
}

//function dyniframesize(down) {
//    //var pTar = null;
//    //if (document.getElementById) {
//    //    pTar = document.getElementById(down);
//    //}
//    //else {
//    //    eval('pTar = ' + down + ';');
//    //}
//    //if (pTar && !window.opera) {
//    //    //begin resizing iframe
//    //    pTar.style.display = "block"
//    //    if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
//    //        //ns6 syntax
//    //        pTar.height = pTar.contentDocument.body.offsetHeight + 20;
//    //        pTar.width = pTar.contentDocument.body.scrollWidth + 20;
//    //    }
//    //    else if (pTar.Document && pTar.Document.body.scrollHeight) {
//    //        //ie5+ syntax
//    //        pTar.height = pTar.Document.body.scrollHeight;
//    //        pTar.width = pTar.Document.body.scrollWidth;
//    //    }
//    //}
//
//    var ifr=document.getElementById(down);
//    var iDoc=ifr.contentDocument||ivr.document;
//    var height=calcPageHeight(iDoc);
//    ifr.style.height=height+'px';
//}
//
//function calcPageHeight(doc) {
//    var cHeight = Math.max(doc.body.clientHeight, doc.documentElement.clientHeight)
//    var sHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight)
//    var height = Math.max(cHeight, sHeight)
//    return height
//}
//var ifr = document.getElementById('ifr')
//ifr.onload = function () {
//    var iDoc = ifr.contentDocument || ifr.document
//    var height = calcPageHeight(iDoc)
//    ifr.style.height = height + 'px'
//}
//$(function () {
//    mainHeight = $(document.body).height() - 45;
//    $('.main-left,.main-right').height(mainHeight);
//    $("[addtabs]").click(function () {
//        addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
//    });

//    $(".nav-tabs").on("click", "[tabclose]", function (e) {
//        id = $(this).attr("tabclose");
//        closeTab(id);
//    });
//});
