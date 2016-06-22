$(function() {

	$(".note").draggable();

	$('.delete').click(function(){
	    return confirm("Are you sure you want to delete this note?");
	});
	
	//$('input[type=number]').spinner();
	
	$(".tagselect").chosen({width: "100%"});
	
	var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear();
    if(dd<10){dd='0'+dd} 
    if(mm<10){mm='0'+mm} 
    var today = dd+'.'+mm+1+'.'+yyyy;
   
  $("#inputDeadline").datetimepicker({
        format : "dd.mm.yyyy",
        setStartDate : today,
        autoclose : true,
        todayBtn : true,
        pickerPosition : "bottom-left",
        minView : 2
  });
  
  $("#inputStart, #inputEnd").datetimepicker({
      format : "dd.mm.yyyy hh:ii",
      startView: 1,
      setStartDate : today,
      autoclose : true,
      todayBtn : true,
      pickerPosition : "bottom-left",
      minView : 2
});
  
  $('.sidebar a').hover(function(){
	    $(this).stop().animate({backgroundColor: '#900909', color: '#ffffff'},0,"easeOutBounce");
	}, function() {
	    $(this).stop().animate({backgroundColor: '#f8f8f8', color: '#555555'});
  });
  
  $('#calendar').fullCalendar({
		// put your options and callbacks here
		events : 'eventEntries', // url
		editable: true,
		defaultView: 'agendaWeek',
		
		firstDay : 1, // 1 == Monday
});
	
});