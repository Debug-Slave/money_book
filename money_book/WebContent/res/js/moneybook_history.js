
//받은 날짜값을 YYYY-MM-DD 형태로 출력하기위한 함수.
Date.prototype.yyyymmdd = function() {
	var yyyy = this.getFullYear().toString();
	var mm = (this.getMonth() + 1).toString();
	var dd = this.getDate().toString();
	return yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-" + (dd[1] ? dd : "0" + dd[0]);
}

var historyArr = [] ;
var calViewName="month";
var calDate = convertDate(new Date());
var customBtns = {
	addHistory : {
		text : "추가",
		click : function(){
			var date = $("#calendar").fullCalendar("getDate");
			date = convertDate(date);
			$("#historyDate").val(date);
			$("#historyFormModal").modal("show");
		}
	},
	chgDayView : {
		text : "day",
		click : function(){
			calViewName = "listDay";
			callData(getCalInfo());
		}
	},
	chgWeekView : {
		text : "week",
		click : function(){
			calViewName = "listWeek";
			callData(getCalInfo());
		}
	},
	chgMonthView : {
		text : "month",
		click : function(){
			calViewName = "month";
			callData(getCalInfo());
		}
	}/*,
	chgTodayView : {
		text : "today",
		click : function(){
			calViewName = getCalInfo().viewName;
			calDate = convertDate(new Date());
			callData();
		}
	}*/
};
 var calendarOption = {
	themeSystem: "bootstrap3",
	header: {
		/*left: 'chgDayView,chgWeekView,chgMonthView chgTodayView',*/
		left: 'chgDayView,chgWeekView,chgMonthView',
		center: 'title',
		right : 'addHistory prevYear,prev,next,nextYear'
	},
	customButtons : customBtns,
	contentHeight : 700,
	eventLimit: 3,
	eventLimitText: "더보기",
	eventLimitClick: "popover",
	defaultView: calViewName,
	defaultDate: calDate,
	navLinks: true, 
	eventLimit: false,
	editable : false,
	eventSources : historyArr,
	dayClick: function(date, jsEvent, view) {
		calViewName = "listDay";
		calDate = convertDate(date);
		callData({viewName : calViewName,	date : calDate});
	},
	eventClick: function(event, jsEvent, view) {
		if($("#calendarWrapper").hasClass("col-md-12")) {
			$("#calendarWrapper").removeClass("col-md-12").addClass("col-md-8");
		}
		$(".infoPanel").css("display", "none");
		$("#historyInfo").css("display", "inline-block");
		
		var type = "outgoing";
		if(event.source.backgroundColor === "blue") {type = "income"}
		
		
		callDetailInfo("historyInfo", type, event.id, function(data){
			var historyPanel = $("#historyInfo");
			var panelTitle = historyPanel.find(".panel-header");
			
			panelTitle.removeClass("panel-primary").removeClass("panel-danger");
			panelTitle.find("h4").html(data.hisTitle);
			
			var infoTable = historyPanel.find("table");
			infoTable.find(".categoryName").html(data.cateName);
			infoTable.find(".hisType").html();

			if(type==="outgoing") {
				infoTable.find(".hisTypeRow").css("display", "table-row");
				infoTable.find(".hisType").html(data.hisType == "1" ? "현금/체크카드" : "신용카드");
				panelTitle.addClass("panel-danger");
			} else {
				infoTable.find(".hisTypeRow").css("display", "none");
				infoTable.find(".hisType").html("");
				panelTitle.addClass("panel-primary");
			}
			
			infoTable.find(".hisMoney").html(data.hisMoney.toLocaleString());
			infoTable.find(".hisDate").html(new Date(data.hisDate).yyyymmdd());
			infoTable.find(".hisMemo").html(data.hisMemo);
			
			historyPanel.find(".delBtn").attr("hIdx", data.hIdx).attr("type", type);
		});
	}
};
 
 
 
function setSelectBox() {
	$.each(outgoingCategoryArr, function(idx, val) {
		$("#outgoing_historyCatagory").append("<option value='"+val.code + "'>"+val.name +"</option>")
	});
	$.each(incomeCategoryArr, function(idx, val) {
		$("#income_historyCatagory").append("<option value='"+val.code + "'>"+val.name +"</option>")
	});
}

function resetModalValue() {
	$("#outgoing_historyCatagory").find("[selected='selected']").removeAttr("selected");
	$("#income_historyCatagory").find("[selected='selected']").removeAttr("selected");
	$("#historyType").find("[selected='selected']").removeAttr("selected");
	$("#historyType:first-child").attr("selected", "selected");
	chgHistoryType("outgoing");
	
	 $("#historyTitle").val("");
	 $("#historyDate").val("");
	 $("#historyMoney").val("");
	 $("#historyMemo").val("");
}


// 받은 날짜값을 date 형태로 형변환 해주어야 한다.
function convertDate(date) {
	var date = new Date(date);
	return date.yyyymmdd();
}

function chgHistoryType(value) {
	$("#"+value+"_type").length > 0 ? $("#outgoing_type").css("display", "inline-block") : $("#outgoing_type").css("display", "none");  
	$(".historyCategory").css("display" , "none");
	$("#"+value+"_historyCatagory").css("display" , "inline-block");
}

function getCalInfo() {
	var calInfo =  null;
	
	var date = $("#calendar").fullCalendar("getDate");
	var view = $("#calendar").fullCalendar("getView");
	
	if (view.name) {
		calInfo = {
				date : convertDate(date),
				viewName : view.name
		}
	}
	
	return calInfo;
}

function buildCalendar() {
	$("#calendar").fullCalendar("destroy");
	$('#calendar').fullCalendar(calendarOption);
	
	// prev(<) 버튼을 클릭하였을 경우
	$("button.fc-prev-button").on("click", function() {
		callData(getCalInfo());
	});
	// prevYear(<<) 버튼을 클릭하였을 경우
	$("button.fc-prevYear-button").on("click", function(){
		callData(getCalInfo());
	});
	
	// next(>) 버튼을 클릭하였을 경우
	$("button.fc-next-button").on("click", function() {
		callData(getCalInfo());
	});
	// nextYear(>>) 버튼을 클릭하였을 경우
	$("button.fc-nextYear-button").on("click", function() {
		callData(getCalInfo());
	});
	
}


function regHistory() {
	
	var type = $("#historyType").val();
	if (!type) {
		alert("지출, 수입 중 하나를 선택하세요."); 
		return false;
	}
	var category = $("#"+type+"_historyCatagory").val();
	if (!category) {
		alert("카테고리 중 하나를 선택하세요."); 
		return false;
	}
	
	var title = $("#historyTitle").val();
	if (!title) {
		alert("제목을 입력하세요."); 
		return false;
	}
	
	var dateStr = $("#historyDate").val();
	if (!dateStr) {
		alert("날짜를 입력하세요."); 
		return false;
	}
	
	var money = $("#historyMoney").val();
	if (!money) {
		alert("금액을 입력하세요."); 
		return false;
	} else {
		money = money.replace(/,/gi, "");
	}

	if (isNaN(money)) {
		alert("금액은 숫자만 입력 가능합니다."); 
		return false;
	}
	
	var memo = $("#historyMemo").val();
	var outgoingType = "";
	if (type == "outgoing") {
		outgoingType = $("[name='outgoingType']:checked").val();
		if (!outgoingType) {
			alert("지출 방법이 선택되지 않았습니다. \n현금/체크카드나 신용카드를 선택해주세요.");
			return false;
		}
	}
	
	$.ajax({
		url : "/"+type+"/"+category+"?outgoingType="+outgoingType,
		type : "POST",
		data : {
			hisTitle : title,
			hisDate : dateStr,
			hisMoney : money,
			hisMemo : memo,
		}, 
		success : function(data){
			callData();
			alert("성공적으로 등록하였습니다.");
			$("#historyFormModal").modal("hide");
		},
		error : function(request,status,error){
			if(request.status==401) {
				alert("로그인이 필요한 페이지입니다.");
				location.href="/";
			} else {
				alert(request.responseText);
				location.href="/";
			}
		}
	});
}


var pieData = [
    {
        value: 0,
        color: "#f0ad4e",
        highlight: "#f0ad4e",
        label: "현금/체크카드"
    },
    {
        value: 0,
        color: "#d9534f",
        highlight: "#d9534f",
        label: "신용카드"
    }

];


function callData(calInfo, extend) {
	if (!calInfo) {
		var tmpCalInfo = getCalInfo();
		if (tmpCalInfo == null) {
			calInfo = {
					viewName : "month",
					date : calDate
			}
		} else {
			calInfo = tmpCalInfo;
		}
	} 
	 
	if (extend) {
		if($("#calendarWrapper").hasClass("col-md-8")) {
			$("#calendarWrapper").removeClass("col-md-8").addClass("col-md-12");
		}
		$(".infoPanel").css("display", "none");
	}
	
	$.ajax({
		url : "/history/"+calViewName+"/"+calInfo.date.replace(/-/gi, ""),
		type : "GET",
		contentType: "application/json",
		dataType:"json" ,
		success : function(data){
			historyArr = new Array();
			historyArr.push({
				events : data.outgoingList,
				backgroundColor : 'red'
			}); 
			historyArr.push({
				events : data.incomeList,
				backgroundColor : 'blue'
			});
			calendarOption.defaultDate = calInfo.date;
			calendarOption.defaultView = calViewName;
			calendarOption.eventSources = historyArr;
			buildCalendar();
		},
		error : function(request,status,error){
			if(request.status==401) {
				alert("로그인이 필요한 페이지입니다.");
				location.href="/";
			} else {
				alert(request.responseText);
				location.href="/";
			}
		}
	});
}

function callDetailInfo(kind, type, param, callback) {
	$.ajax({
		url : "/"+kind+"/"+type+"/"+param,
		type : "GET",
		contentType: "application/json",
		dataType:"json" ,
		success : function(data){
			callback(data);
		},
		error : function(request,status,error){
			if(request.status==401) {
				alert("로그인이 필요한 페이지입니다.");
				location.href="/";
			} else {
				alert(request.responseText);
				location.href="/";
			}
		}
	});
}




function dashboardSearch(){
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	if(startDate <= endDate) {
		callReportData(startDate, endDate);
	} else {
		alert("시작일은 마지막일보다 이전이어야 합니다. \n날짜를 다시 확인해주세요.");
		return false;
	}
	
}

function callReportData(startDate, endDate){
	$.ajax({
		url : "/report/"+startDate+"/"+endDate,
		type : "GET",
		success : function(data){
			var incomeList = data.incomeList;
			var incomeTotal = 0;
			for (var i = 0; i < incomeList.length ; i++) {
				incomeTotal += incomeList[i].amount;
			}
			for (var i = 0; i < incomeList.length ; i++) {
				setIncomeProgressbar($("#income_"+incomeList[i].cateCode+"_progress"), 
						(incomeList[i].amount/incomeTotal)*100, incomeList[i].amount);
			}
			
			var outgoingList = data.outgoingList;
			var outgoingTotal = 0;
			for (var i = 0; i < outgoingList.length ; i++) {
				outgoingTotal += outgoingList[i].amount;
			}
			for (var i = 0; i < outgoingList.length ; i++) {
				setOutgoingProgressbar($("#outgoing_"+outgoingList[i].cateCode+"_progress"), 
						(outgoingList[i].amount/outgoingTotal)*100, outgoingList[i].amount);
			}
			
			
			var outgoingTypeList = data.outgoingType;
			for (var i=0; i < outgoingTypeList.length; i++) {
				if(pieData[i]) {
					pieData[i].value = outgoingTypeList[i].amout;
					pieData[i].label = outgoingTypeList[i].hisType == 0 ? "신용카드" : "현금/체크카드";
				}
			}
			
			drawPie(pieData);
			
			$("#totIncomePanel").html(incomeTotal.toLocaleString()+"원");
			$("#totOutgoingPanel").html(outgoingTotal.toLocaleString()+"원");
			$("#totBalancePanel").html((incomeTotal-outgoingTotal).toLocaleString()+"원");
			
		},
		error : function(request,status,error){
			if(request.status==401) {
				alert("로그인이 필요한 페이지입니다.");
				location.href="/";
			} else {
				alert(request.status);
				alert(request.responseText);
			}
		}
	});
}

function drawPie(pieData) {
	var ctx2 = $(".pie-chart")[0].getContext("2d");
    window.myPie = new Chart(ctx2).Pie(pieData, {
        responsive : true,
        showTooltips: true
    });
}

function setIncomeProgressbar(progressObj, value, amount) {
	if(isNaN(value)) value = 0;
	var className = "progress-bar-";
	
	if (value <= 10) {
		className += "danger";
	} else if (value <= 20) {
		className += "warning";
	} else if (value <= 40) {
		className += "info";
	} else if (value <= 60) {
		className += "success";
	} else if (value <= 100) {
		className += "primary";
	}
	
	var varObj = progressObj.find(".progress-bar");
	varObj.attr("aria-valuenow", value).attr("data-original-title", amount.toLocaleString()+"원");
	varObj.removeAttr("class").addClass("progress-bar").addClass(className);
	varObj.animate({
		width : value +"%"
	});
}


function setOutgoingProgressbar(progressObj, value, amount) {
	if(isNaN(value)) value = 0;
	var className = "progress-bar-";
	
	if (value >= 70) {
		className += "danger";
	} else if (value >= 60) {
		className += "warning";
	} else if (value >= 30) {
		className += "info";
	} else if (value >= 20) {
		className += "success";
	} else if (value >= 0) {
		className += "primary";
	}
	
	var varObj = progressObj.find(".progress-bar");
	
	varObj.attr("aria-valuenow", value).attr("data-original-title", amount.toLocaleString()+"원");
	varObj.removeAttr("class").addClass("progress-bar").addClass(className);
	varObj.animate({
		width : value +"%"
	});
}