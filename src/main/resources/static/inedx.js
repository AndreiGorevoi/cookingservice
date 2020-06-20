$('#getUsers').click(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8090/getUsers",
        success:function (data){
            console.log(data);
        }
    })

})
$('#createMenuButton').click(function () {
    $.ajax({
        type:"GET",
        url:"http://localhost:8090/getMenu",
        success:function (data){
            if(data==="WOW! bound must be positive"){
                alert("please, add more recipes in your menu")
            }
            console.log(data);
        }
    })

})

$('#addRecipe').click(function () {
    var newRecipe={
        "title" : $('#titleOfRecipe').val(),
        "link" : $('#linkOfRecipe').val(),
        "img" : $('#recipeImg').val(),
        "description" : $('#recipeDescription').val(),
        "mealTime" : $('#recipeMealTime').val(),
        "userId" : $('#userId').val(),
    }
    $.ajax({
        type:"POST",
        url:"http://localhost:8090/addRecipe",
        data:JSON.stringify(newRecipe),
        contentType:"application/json",
        success:function (data){
            console.log(data);
        },
        error:function (e) {
            alert("something wrong")
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
$('#deleteRecipeButton').click(function () {
    location.href='http://localhost:8090/adminPage'
})
