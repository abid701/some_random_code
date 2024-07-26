$(document).ready(function () {
    var versuche = 3;
    var goldenNumber = Math.floor(Math.random() * 10) + 1;
    // goldenNumber = Math.random() * 10;
    // goldenNumber = Math.floor(goldenNumber) + 1;
    console.log(goldenNumber);

    $('#eingabeBnt').on('click', spiel);

    $('#eingabeInput').addEventListener('keypress', function (e) {
        if (e.key === 'Enter') {
            var eingabe = $('#eingabeInput').val();
            eingabe = Number(eingabe);
            if (eingabe < 1 || eingabe > 10) {
                $('#output').html('Die Zahl muss zwischen 1 und 10 sein.');
            } else {
                versuche--;
                // console.log(typeof eingabe);
                if ((eingabe === goldenNumber) && (versuche === 2)) {
                    $('#output').html('<img src="bingo.jpg" alt="Bingo" width="500px"> <p> <br> Du hast gewonnen </p>');
                    $('#eingabeBnt').prop('disabled', true);
                }
                else if ((eingabe === goldenNumber) && (versuche >= 0)) {
                    $('#output').html('Du hast gewonnen! Die Zahl ist ' + goldenNumber);
                    $('#eingabeBnt').prop('disabled', true);
                } else if ((eingabe != goldenNumber) && (versuche > 0)) {
                    if (goldenNumber > eingabe) {
                        $('#output').html('Die Zahl ist großer als ' + eingabe + ' <h3>du hast noch ' + versuche + ' Versuch(e)</h3>');
                    } else {
                        $('#output').html('Die Zahl ist kleiner als ' + eingabe + ' <h3>du hast noch ' + versuche + ' Versuch(e)</h3>');
                    }
                } else {
                    $('#output').html('Game Over <h3> Die Zahl ist ' + goldenNumber + '</h3>');
                    $('#eingabeBnt').prop('disabled', true);
                }
            }
        }
    });


    function spiel(e) {
        var eingabe = $('#eingabeInput').val();
        eingabe = Number(eingabe);
        if (eingabe < 1 || eingabe > 10) {
            $('#output').html('Die Zahl muss zwischen 1 und 10 sein.');
        } else {
            versuche--;
            // console.log(typeof eingabe);
            if ((eingabe === goldenNumber) && (versuche === 2)) {
                $('#output').html('Du hast gewonnen <img src="bingo.jpg" alt="Bingo" width="500px">');
                $('#eingabeBnt').prop('disabled', true);
            }
            else if ((eingabe === goldenNumber) && (versuche >= 0)) {
                $('#output').html('Du hast gewonnen! Die Zahl ist ' + goldenNumber);
                $('#eingabeBnt').prop('disabled', true);
            } else if ((eingabe != goldenNumber) && (versuche > 0)) {
                if (goldenNumber > eingabe) {
                    $('#output').html('Die Zahl ist großer als ' + eingabe + ' <h3>du hast noch ' + versuche + ' Versuch(e)</h3>');
                } else {
                    $('#output').html('Die Zahl ist kleiner als ' + eingabe + ' <h3>du hast noch ' + versuche + ' Versuch(e)</h3>');
                }
            } else {
                $('#output').html('Game Over <h3> Die Zahl ist ' + goldenNumber + '</h3>');
                $('#eingabeBnt').prop('disabled', true);
            }
        }
    }
}); 
