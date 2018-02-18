  $(function() {

$('#yesnoinput').hide();
$('#truefalseinput').hide();
$('#freeinput').hide();
$('#mcinput').hide();


$('#type').change(function() {
    opt = $(this).val();
    if (opt=="YN") {
        $('#yesnoinput').show();
        $('#truefalseinput').hide();
        $('#mcinput').hide();
        $('#freeinput').hide();
    } else 
    if (opt=="TF") {     
        $('#truefalseinput').show();
        $('#yesnoinput').hide();
        $('#mcinput').hide();
        $('#freeinput').hide();
    } else
     if (opt=="QCM") {
        $('#mcinput').show();
        $('#truefalseinput').hide();
        $('#yesnoinput').hide();
        $('#freeinput').hide();
    } else
     if (opt=="Free") {
        $('#freeinput').show();
        $('#truefalseinput').hide();
        $('#yesnoinput').hide();
        $('#mcinput').hide();
    }
    else {
        $('#yesnoinput').hide();
        $('#truefalseinput').hide();
        $('#freeinput').hide();
        $('#mcinput').hide();
    }
  });
});



$(document).ready(function(){
    var contentCount=1;
    
    $("#addContent-1").click(function(e){
        // [Add input]
        // START input-group
        var newDiv = $(document.createElement("div")).attr({id:"input-group-"+(contentCount+1), class:"input-group", style:"margin-bottom:10px;"});
        
        
        newDiv.html( "<select id='content-"+ (contentCount+1) +"' name='answersCorrect'> <option value='true'>True</option> <option value='false' selected>False</option> </select>"
        		  + "&nbsp;&nbsp;<input type='text' name='answersText' class='form-control' id='content-"+ (contentCount+1) +"'>"
                    +"<span class='input-group-btn'>"
                    +   "<button class='btn btn-danger' id='removeContent-" + (contentCount+1) + "' type='button'>"
                    +       "<span class='fa fa-minus'></span>"
                    +   "</button>"
                    +"</span>"
                    );
        
        $("#input-group-"+contentCount).before(newDiv);
        ++contentCount;
        $("input[name='contentCount']").val(contentCount);
        // END input-group
        
        // [Remove input]
        $("#removeContent-" + contentCount).click(function(e){
            if(contentCount==1){
                alert("No more input to remove");
                return false;
            }   
 
            $("#input-group-" + contentCount).remove();
            --contentCount;
             $("input[name='contentCount']").val(contentCount);
        });
    }); // END $("#addContent11").click(function(e){
});



