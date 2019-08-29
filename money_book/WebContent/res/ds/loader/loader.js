/**
 *  @author : Debug Slave
 */


var DS_loader = {};

DS_loader.drawLoader = function(target, option){
	
	var targetDom = document.getElementById(target); 
	
	var parentDom = targetDom.parentElement; 
	
	if (targetDom) {
		addClass(targetDom, "loader_box");
		targetDom.style.height = "300px";
		var html = "";
		
		if (option) {
			html = "<div class=\"loader";
			if(option.typeNumber) {
				html+= option.typeNumber;
			} else {
				html+= "1";
			} 
			html+= "\"></div>";
			
		} else {
			html = "<div class=\"loader1\"></div>";
		}
		
		
		targetDom.innerHTML = html;
	}
	
	
}


function addClass(target, className) {
	target.classList.add(className);
}