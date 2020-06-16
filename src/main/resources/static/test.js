$('#getUsers').click(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8090/getUsers",
        success:function (data){
            console.log(data);
        }
    })

})

$('#addRecipe').click(function () {
    var newRecipe =
        {
            "title" : $('#titleOfRecipe').val(),
            "link" : $('#linkOfRecipe').val(),
            "userId" : $('#userId').val()
        };
    $.ajax({

        type:"POST",
        url:"http://localhost:8090/addRecipe",

        data: JSON.stringify(newRecipe),
        contentType: "application/json",
        success:function (data){
            console.log(data);
            location.href="http://localhost:8090/";
        },
        error:function (e) {
            location.href="http://localhost:8090/";
        }
    })

})



$('#getRecipes').click(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8090/getRecipes",
        success:function (data){
            console.log(data);
        }
    })
})