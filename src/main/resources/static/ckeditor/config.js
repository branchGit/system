/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
    // config.language = 'zh-cn';
    // config.uiColor = '#f8f8f8';
    // config.height = '600';
    // config.toolbarCanCollapse = true;

    config.removeButtons = 'About,Maximize,ShowBlocks';
    // 工具栏（基础'Basic'、全能'Full'、自定义）plugins/toolbar/plugin.js
    //config.toolbar = 'Basic';
    config.extraPlugins += (config.extraPlugins ? ',lineheight': 'lineheight');
    //config.toolbar = 'Full';

    config.toolbar = [
        ['Source','-','Preview'],
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink'],
        ['Image','Flash','Table'],
        ['Outdent','Indent'],
        ['Styles','Format','Font','FontSize','lineheight'],
        ['TextColor','BGColor']
    ];

    
    //工具栏是否可以被收缩
    config.toolbarCanCollapse = true;
    //工具栏的位置
    config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    // config.skin = 'moono';
    // config.toolbar = 'Full';
    config.width = '100%';
    config.height = '300px';
    // config.filebrowserImageUploadUrl='/backstage/ckeditorUploadImg.action?Type=Image';
    //
    config.filebrowserImageUploadUrl='/system/uploadImg';
     config.font_names = '宋体/宋体;隶书/隶书;微软雅黑/微软雅黑;幼圆/幼圆;黑体/黑体;仿宋_GB2312/仿宋_GB2312;楷体_GB2312/楷体_GB2312;方正姚体/方正姚体;方正舒体/方正舒体;华文彩云/华文彩云;华文仿宋/华文仿宋;华文琥珀/华文琥珀;华文楷体/华文楷体;华文隶书/华文隶书;华文宋体/华文宋体;华文细黑/华文细黑;华文新魏/华文新魏;华文行楷/华文行楷;华文中宋/华文中宋;Times New Roman/Times New Roman';
   
    config.allowedContent = true;

};


CKEDITOR.stylesSet.add( 'default', [
	/* Block Styles */
	{ name : '首行缩进'	, element : 'p', styles : { 'text-indent' : '20pt' } },
	/* Inline Styles */
	{ name : '标注黄色'	, element : 'span', styles : { 'background-color' : 'Yellow' } },
	{ name : '标注绿色'	, element : 'span', styles : { 'background-color' : 'Lime' } },
	/* Object Styles */
	{ name : '图片左对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-right: 5px', 'border' : '2', 'align' : 'left' } },
	{ name : '图片有对齐', element : 'img', attributes : { 'style' : 'padding: 5px; margin-left: 5px', 'border' : '2', 'align' : 'right' } },
	{ name : '无边界表格', element : 'table', styles: { 'border-style': 'hidden', 'background-color' : '#E6E6FA' } }
]);