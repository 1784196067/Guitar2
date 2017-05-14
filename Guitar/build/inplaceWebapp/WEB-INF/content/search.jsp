<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title></title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />
<style>
  header{
    position: relative;
    margin-bottom: 20px;
    display: block;
  }
  header h3,h4{
    display: inline-block;
    padding: 5px;
  }
  header h5{
    position: absolute;
    right: 12px;
    bottom: 12px;
    padding: 3px;
    vertical-align:text-top;
  }
  h5 img{
    width: 25px;
    height: 25px;
    margin-bottom: 2px;
  }
  #main,header{
    border-left: 1px dashed gray;
    border-right: 1px dashed gray;
  	box-shadow: 2px 2px 2px black;
  }
  .pull1{
    width:50px;
    height:130px;
  }
  .pull2{
    height: 150px;
  }
  article{
    margin-top: 13px;
    padding: 20px;
  }
  ul{
  	
	list-style-type:none;
	-moz-column-count: 3;
	-webkit-column-count: 3;
	-moz-column-gap: 2em;
	-webkit-column-gap: 2em;
	-moz-column-rule: 1px solid red;
	-webkit-column-rule: 1px solid red;
	text-align:center;
 }
 ul li{
    padding: 5px;
 }
 ul, menu, dir {
    -webkit-padding-start: 0px;
    -moz-padding-start: 0px;
    -webkit-padding-end: 0px;
    -moz-padding-end: 0px;
 }
 .li{
 	background-color: pink;
 }
 #sections{
 	height: auto;
 	display: -moz-box;
 	display: -webkit-box;
 	-moz-box-align: center;
 	-webkit-box-align: center;
 	-moz-box-pack: center;
 	-webkit-box-pack: center;
 }
 #name,#form1,#form2,#form3{
 	padding: 13px;
 	text-align:left;
 }
 #form1,#form2,#form3{
 	border: 1px solid gray;
 	box-shadow: 2px 2px 2px black;
 	width: 400px;
 }
 #name *,#form1 *,#form2 *,#form3 *{
 	margin-right: 10px;
 	margin-bottom: 13px;
  }
 #model2,#model3{
 	display: none;
 }
</style>
<!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<body class="row"  onload="init()">
  <div class="col-lg-2 col-md-1 col-xs-0"></div>

  <div class="col-lg-8 col-md-10 col-xs-12" id="main">
    <header>
      <h3><img src="images/header.png" alt="GUITAR之家" /></h3>
       <h3><img src="images/header2.png" alt="GUITAR之家" /></h3>
      <h5>
        <a href="javascript:void(0)">登录 |</a>
        <a href="javascript:void(0)"> 注册  </a>
      </h5>  
    </header>
    <div style="display:inline-block;position: absolute;top:0px;right:-50px;">
      <img src="images/pull.png" alt="试着拖拽我"  id="pull" class="pull1"/>
    </div>
    <article class="row"  style="height:auto;min-height:600px;">
			<ul id="ul">
				<li id="ul_item1" class="li">添加/修改乐器类别</li>
				<li id="ul_item2">添加商品乐器</li>
				<li id="ul_item3">商品乐器搜索</li>
			</ul>
		<section id="sections">
			<section id="model1">
				<label style="margin-left:13px;margin-bottom:13px;">乐器类别名：</label>
				<select id="kind1" style="width:90px;text-align: left;" onchange="change(1)">
				
				</select>&nbsp;&nbsp;<span class='glyphicon glyphicon-plus' onclick='addNew()'></span>
				<form id="form1">
				  <div id="characters1" class="input">				    
					
				  </div>
					<span class="btn btn-info" style="float:right;" onclick="save(1, 'save')" id="save"><span class="glyphicon glyphicon-ok"></span>保存</span>
				</form>
			</section>
			<section id="model2">
				<label style="margin-left:13px;margin-bottom:13px;">乐器类别名：</label>
				<select id="kind2" style="width:90px;text-align: left;" onchange="change(2)">
				
				</select>
				<form id="form2">
					<div id="characters2" class="input">
						
					</div>
					<span class="btn btn-info" style="float:right;" onclick="save(2, 'addInstrument')" id="save2"><span class="glyphicon glyphicon-ok"></span>保存</span>
				</form>
			</section>
			<section id="model3">
				<label style="margin-left:13px;margin-bottom:13px;">乐器类别名：</label>
				<select id="kind3" style="width:90px;text-align: left;" onchange="change(3)">
				
				</select>
				<form id="form3">
					<div id="characters3" class="input">
						
					</div>
					<span class="btn btn-info" style="float:right;" onclick="save(3, 'search')" id="save3"><span class="glyphicon glyphicon-search"></span>搜索</span>
				</form>				
			</section>
	   </section>
	   <div id="Searchresult">
				
	   </div>
    </article>
  </div>

  <div class="col-lg-2 col-md-1 col-xs-0"></div>

	<script type="text/javascript" src="js/jquery-2.2.3.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/search.js"></script>
	<script type="text/javascript">
	
  </script>
</body>
</html>