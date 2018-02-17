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
        
        
        newDiv.html( "<input type='checkbox' id='content-"+ (contentCount+1) +"'>&nbsp;&nbsp;<input type='text' name='answersText' class='form-control' id='content-"+ (contentCount+1) +"'>"
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
    }); // END



//New Question Form TO JSON
    $("#NewQuestion").submit(function(e){
        
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/NewQuestion',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });
        e.preventDefault();

    });

//New Same Ability Form TO JSON
    $("#NewSameAbility").submit(function(e){
        e.preventDefault();
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/NewSameAbility',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });


});


//New Improvement Form TO JSON
    $("#NewImprovement").submit(function(e){
        e.preventDefault();
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/NewImprovement',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });


});

    //Change Question Language Form TO JSON
    $("#ChangeQuestionLanguage").submit(function(e){
        e.preventDefault();
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/ChangeQuestionLanguage',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });


});


    //Change Question to TF Form TO JSON
    $("#ChangeResponseTypeTF").submit(function(e){
        e.preventDefault();
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/ChangeResponseTypeTF',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });


});

        //Change Question to YN Form TO JSON
    $("#ChangeResponseTypeYN").submit(function(e){
        e.preventDefault();
        var form = $(this);
        var action = form.attr("action");
        var data = form.serializeArray();

        $.ajax({
                    url: 'rest/ChangeResponseTypeYN',
                    dataType: 'json',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(getFormData(data)),
                    success: function(data){
                        console.log("DATA POSTED SUCCESSFULLY"+data);
                    },
                    error: function( jqXhr, textStatus, errorThrown ){
                        console.log( errorThrown );
                    }
        });


});

});



//utility function
function getFormData(data) {
   var unindexed_array = data;
   var indexed_array = {};

   $.map(unindexed_array, function(n, i) {
    indexed_array[n['name']] = n['value'];
   });

   return indexed_array;
};