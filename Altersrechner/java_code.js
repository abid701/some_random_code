$(document).ready(function () {
    console.log($('#birthday').val() + "?");
    console.log($('#birthday').val());
    $('#getAge').on("click", function () {
        var current_date = new Date();
        var current_day = current_date.getDate() + 1;
        var current_month = current_date.getMonth() + 1;
        var current_year = current_date.getFullYear();
        console.log($('#birthday').val());

        var date = new Date($('#birthday').val());
        var day = date.getDate() + 1;
        var month = date.getMonth() + 1;
        var year = date.getFullYear();

        if (current_date < date) {
            $('#output').html("Du kannst nicht in diesem Datum geboren sein");
        }
        else if (current_year === year && current_month === month && current_day > day) {
            var result = current_day - day;
            $('#output').html("Du bist " + result + " Tage alt.");
        }
        else if (current_year === year && current_month > month) {
            var result = current_month - month;
            $('#output').html("Du bist " + result + " Monate alt.");
        }
        else if ((current_date > date && month) || (current_month <= month && current_day < day)) {
            console.log(current_year - year);
            var result = current_year - year;
            $('#output').html("Du bist " + result + " Jahre alt.");
        }
        else {
            var result = (current_year - year) + 1;
            $('#output').html("Du bist " + result + " Jahre alt.");
        }
    });
})