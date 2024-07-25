$(document).ready(function () {
    console.log("It works!");
    var average;
    $('#btn1').on('click', function () {
        var note1 = Number($('#note1').val());
        var note2 = Number($('#note2').val());
        var note3 = Number($('#note3').val());
        var note4 = Number($('#note4').val());
        var array = [note1, note2, note3, note4];
        var counter = 0;
        for (const element of array) {
            if (element <= 100 && element >= 0) {
                counter++;
                if (counter === 4) {
                    average = (note1 + note2 + note3 + note4) / 4;
                    $('#note').html(average);
                    $('#note').show();
                    $('#fehler').hide();
                }
            }
            else {
                $('#fehler').show();
                $('#note').hide();
            }
        }
    });
    console.log(average);
    $('#btn2').on('click', function () {
        if (average > 91) {
            $('#bewertung').html("Sehr gut");
        }
        else if (average > 80 && average <= 91) {
            $('#bewertung').html("gut");
        }
        else if (average <= 80) {
            $('#bewertung').html("Befriedigend");
        }
    });

    $('#btn3').on('click', function () {
        $('#output').html('<img src="viel_erfolg.jpg" alt="erfolg" width="500px">');
    });
});