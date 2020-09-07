(function ($) {
    $.fn.sidebarMenu = function (options) {
        options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
        var target = $(this);
        //target.addClass('nav');
        //target.addClass('nav-list');
        if (options.data) {
            init(target, options.data);
        }
        else {
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(target, data);
            });
        }
        var url = window.location.pathname;

        /***
         <li class="start active ">

         <a href="index.html">

         <i class="icon-home"></i>

         <span class="title">Dashboard</span>

         <span class="selected"></span>

         </a>

         </li>
         */
        function init(target, data) {
            $.each(data, function (i, data) {
            	console.log(target);
            	console.log(data);
            	console.log(data.name);
            	console.log(data.target);
            	//consol.log(item);
                var li = $('<li></li>');
                var a = $('<a></a>');
                var url = $('<i></i>');
                //icon.addClass('glyphicon');
                url.addClass(data.target);
                
                var text = $('<span></span>');
                text.addClass('title').text(data.name);
                if (data.menus && data.menus.length > 0) {
                    li.addClass("sub-menu");
                     a.append(url);
                    a.append(text);
                    a.attr('href', 'javascript:;');
                    //a.addClass('dropdown-toggle');
                    var arrow = $('<span></span>');
                    arrow.addClass('arrow');
                    a.append(arrow);
                    li.append(a);
                    var menus = $('<ul></ul>');
                    menus.addClass('sub');
                    init(menus, data.menus);
                    li.append(menus);
                }
                else {
                	
                    var href = 'javascript:addTabs({id:\'' + data.menuId + '\',title: \'' + data.name + '\',close: true,url: \'' + data.target + '\'});';

                    if(data.url!=null&&data.target.indexOf('url')>-1){
                        a.append(url);
                    }
                    a.append(text);
                    a.attr('href', href);
                    //if (item.istab)
                    //  a.attr('href', href);
                    //else {
                    //  a.attr('href', item.url);
                    //  a.attr('title', item.text);
                    //  a.attr('target', '_blank')
                    //}
                    li.append(a);
                }
                target.append(li);
            });
        }
    }

    $.fn.sidebarMenu.defaults = {
        url: null,
        param: null,
        data: null
    };
})(jQuery);