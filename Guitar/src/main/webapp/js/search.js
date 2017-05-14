/**
 * 
 */
	$("ul li").each(function(index){
		var ele = $("#ul_item" + (index+1));
		ele.click(function(){
			$(this).addClass("li");
			$(this).siblings().removeClass("li");
			var id = $(this).attr("id");
			var secIndex = id.substring(id.length-1);
			var section = $("#model" + secIndex);
			section.show();
			section.siblings().hide();
			if(index < 2){
				$("#Searchresult").hide();
			}else{
				$("#Searchresult").show();
			}
		})		
	});

	var i = 1;
	function add(e){
			var bool = true;
			(
			  function(){
				  $(e).parent().parent().find("input").each(function(index){
						var str = $(this).val() || "";
						if(str.length <= 0){
							$(this).css("border", "solid 1px red");
							$("#save").attr("disabled", true);
							bool = false;
						}else{
							$(this).css("border", "solid 1px gray");
						}
					})
			  }	
			)();
			if(bool){
				$("#save").attr("disabled", false);
				$("#characters1").append("<div><label>乐器属性名：</label><input type='text' name='character" + i +"' required/><span class='glyphicon glyphicon-plus' onclick='add(this)'></span><span class='glyphicon glyphicon-remove' onclick='remove(this)'></span></div>");
				i++;
			}
	}
	
	
	
	function addNew(){
		$("#kind1").val(undefined);
		$("#characters1").empty().append("<div><label>乐器类别名：</label><input type='text' name='spec' required /><hr style='margin-top:0px;'/></div><div><label>乐器属性名：</label><input type='text' name='character" + i +"' required/><span class='glyphicon glyphicon-plus' onclick='add(this)'></span><span class='glyphicon glyphicon-remove' onclick='remove(this)'></span></div>");
		i++
	}
	function remove(e){
		$(e).parent("div").remove();
	}
	var json="";
	function search(){
		var desc = $(":input[name='search']").val();
		$("#Searchresult").empty();
	  $.post("guitar/search_search",{"desc" : desc},
	      function(data, statusText) {          
	        json=data;  
	        var jsonData = eval(json);	       
	        $.each(jsonData,function(i,n){  
	        	$("#Searchresult").append("<div class='col-xs-6 col-md-3'><a href='#' class='thumbnail'><img src='images/guitar.jpg' style='height: 180px; width: 100%; display: block;'></a><div class='caption'>" + "builder: " + n.builder + "<br/>serialNumber:" +  n.serialNumber + "<br/>model:" +  n.model + "<br/>type:" +   n.type + "<p>" + " price:" +  n.price + "<br/>backWood:" +  n.backWood + "<br/>topWood:" +n.topWood + "</p></div></div>");        
	           });
	  	  }); 
	   }
	
    $("img#pull").click(function(){
        $("header").toggle();
        $("img#pull").toggleClass('pull2');
    })
    
    $.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  
     
  function save(index, method){
	  var characters = JSON.stringify($('#form'+index).serializeObject());
	  var ele = $("#kind"+index);
      var spec = ele.val() || $("input[name='spec']").val();
      alert("spec=" + spec);
      if(index != 3){
    	  $.ajax({  
              url : "guitar/search_"+method,  
              type : "POST",    
              data : "characters="+ characters + "&type=" + spec,  
              success : function(data, status) {  
                  if (status == "success") {  
                	 ele.after("<span style='color: red;'>成功！</span>");
                	 ele.next().hide(5000);
                	 clicked = true;
                	 init();
                  }  
              },  
              error : function(data) {  
            	  ele.after("<span style='color: red;'>失败！</span>");
            	  ele.next().hide(5000);
              }  
         });
      }else{
    	  $.ajax({  
              url : "guitar/search_"+method,  
              type : "POST",    
              data : "characters="+ characters + "&type=" + spec,  
              success : function(data, status) {  
            	  json=data;  
      	          var jsonData = eval(json);
      	          $("#Searchresult").empty();
      	          $.each(jsonData,function(i,n){ 
      	        	$("#Searchresult").show();
      	        	var str = "";
      	        	for(var key in n.others){  
      	        	    str += key + "："+ n.others[key] + "<br/>";  
      	        	}  
      	        	$("#Searchresult").append("<div class='col-xs-6 col-md-3'><a href='#' class='thumbnail'><img src='images/guitar.jpg' style='height: 180px; width: 100%; display: block;'></a><div class='caption' style='border-bottom:1px black solid'>" + "serialNumber: " + n.serialNumber + "<br/>price:" +  n.price + "</div>" + str + "</div>");        
      	           });
              },  
              error : function(data) {  
            	  alert("发生错误了！");
              }  
         });
      }
      
  }
     
  var clicked = true;
  var json;
  var jsonData;
  var content1 = "";
  var content = "";
  var content3 = "";
  var content2 = "";
  function init(){
   if(clicked){
	   $.post("guitar/search_getAllKind",{}, function(data, statusText){
			json=data;  
	        jsonData = eval(json);	
	        $("#kind1").empty(); 
	        $("#kind2").empty();
	        $("#kind3").empty();
	        $.each(jsonData,function(i,n){
	        	$("#kind1,#kind2,#kind3").append("<option id='" + n.Type + "' value='" + n.Type +"'>" + n.Type + "</option>").val(""); 
	           });
			});			
	}	
   var opt1 = $("#kind1").val();
	if(opt1 != undefined){
		$.each(jsonData,function(i,n){  
			if(n.Type == opt1){
					$("#characters1").empty();
					$("#characters1").show();
					$("#characters1").append(content2);
				}
		});
	}
   clicked = false; 
//   $("input[name='spec']").bind("blur", function(){
//		var str = $(this).val() || "";
//		if(str.length <= 0){
//			$(this).css("border", "solid 1px red");
//			$("#save").attr("disabled", true);
//		}else{
//			$(this).css("border", "solid 1px gray");
//		}
//	});
  };
  
  function change(index){
	  var opt=$("#kind"+index).val();
	  content1 = "";
	  content = "";
	  content3 = "";
	  content2 = "";
	  $.each(jsonData,function(i,n){  
			if(n.Type == opt){
				for (var key in n.jsonData) {
					content += "<div><label>" + n.jsonData[key] +"： </label><input type='text' name='" + n.jsonData[key] + "' " + " required/></div>";
			        content1 += "<div><label>乐器属性名： </label><input type='text' name='" + n.jsonData[key] + "' value='" + n.jsonData[key] +"' required/><span class='glyphicon glyphicon-plus' onclick='add()'></span><span class='glyphicon glyphicon-remove' onclick='remove(this)'></span></div>";
				}
				content2 = "<div><label>SerialNumber： </label><input type='text' name='SerialNumber' required /></div><div><label>Price： </label><input type='number' min='0' name='Price' required /></div>";
				content2 += content;
				content3 += content;
				if(index == 1){
					$("#characters1").empty().append(content1);
				}else if(index == 2){
					$("#characters2").empty().append(content2);
				}else{
					$("#characters3").empty().append(content3);
				}		
				}
	 });
  }