// 加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 */
function switchSysBar(flag) {
	var side = $('#side');
	var left_menu_cnt = $('#left_menu_cnt');
	if (flag == true) { // flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({
			width : '280px'
		});
		$('#top_nav').css({
			width : '77%',
			left : '304px'
		});
		$('#main').css({
			left : '280px'
		});
	} else {
		if (left_menu_cnt.is(":visible")) {
			left_menu_cnt.hide(10, 'linear');
			side.css({
				width : '60px'
			});
			$('#top_nav').css({
				width : '100%',
				left : '60px',
				'padding-left' : '28px'
			});
			$('#main').css({
				left : '60px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_show.png');
		} else {
			left_menu_cnt.show(500, 'linear');
			side.css({
				width : '280px'
			});
			$('#top_nav').css({
				width : '77%',
				left : '304px',
				'padding-left' : '0px'
			});
			$('#main').css({
				left : '280px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_hide.png');
		}
	}
}

$(function() {
	loadDate();

	// 显示侧边栏
	switchSysBar(true);

	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});

$(function() {
	$(".system_url").click(function() {
		var url = $(this).data("url")+".action";
		$("#rightMain").attr("src", url);
	});
});

// 为li绑定事件
$(function() {
	$("#TabPage2 li").click(
			function() {
				$("#TabPage2 li").removeClass("selected");
				$(this).addClass("selected");
				$.each($("#TabPage2 img"), function(index, item) {
					$(item).prop("src",
							"/images/common/" + (index + 1) + ".jpg");
				});
				$(this).find("msg").prop(
						"src",
						"/images/common/" + ($(this).index() + 1)
								+ "_hover.jpg");

				$("#nav_module img").prop(
						"src",
						"/images/common/module_" + ($(this).index() + 1)
								+ ".png");
				// 加载模块数据
				loadMenu($(this).data("rootmenu"));
			});
	
});
// 菜单树
var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		//绑定点击事件
		 callback:{
			 onClick:function (event,treeId,treeNode){
				 console.debug("=============");
				 console.debug(treeNode);
				 console.debug(arguments);
				 if(treeNode.action){
					 $("#rightMain").attr("src",treeNode.action+".action");
				 }
			}
		},
		//异步加载,指定要访问的资源和携带的参数,返回的值加载到父节点中
		async:{
			enable:true,
			url:"/systemMenu_getParentByParentSn.action",
			// 异步加载的请求,值=参数名
			autoParam:["sn=qo.parentSn"]
		}
};
	var system=[
		{ id:11, pId:0, name:"系统模块" ,isParent:true,sn:"system" },
		     
    ];
	var business=[
		 { id:12, pId:0, name:"业务模块",isParent:true,sn:"business" },
     ];
	var charts=[
		{ id:13, pId:0, name:"报表模块",isParent:true,sn:"charts" },
	];
	var zNodes ={   
	             system:system,
	             business:business,
	    		charts:charts
	};
	$(document).ready(function(){
		$.fn.zTree.init($(".ztree"), setting, zNodes.business);
	});
	
 function loadMenu(modleName){
 // 使用[]取出属性中的值,不能用.
	 $.fn.zTree.init($(".ztree"), setting, zNodes[modleName]);
 }
