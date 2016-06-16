$(function() {

	$(".note").draggable();

	$('.delete').click(function(){
	    return confirm("Are you sure you want to delete this note?");
	})
	
	$(".tagselect").chosen({width: "100%"});
	
	var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear();
    if(dd<10){dd='0'+dd} 
    if(mm<10){mm='0'+mm} 
    var today = dd+'.'+mm+1+' '+yyyy;
   
	$("#inputDeadline").datetimepicker({
        format : "dd.MM yyyy",
        setStartDate : today,
        autoclose : true,
        todayBtn : true,
        pickerPosition : "bottom-left",
        minView : 2
      });
	
})