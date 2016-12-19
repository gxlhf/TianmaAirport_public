$(function () {
    $('[name="flight-no"]').blur(function () {
        $(this).val($(this).val().toUpperCase());
    });

    //值机柜台tokenfield
    $('#inp-flight-counter')
    .on('tokenfield:createtoken', function (e) {
        var valid = false;
        for (var i = 0; i < counterList.length; i++) {
            if(counterList[i] == e.attrs.value){
                valid = true;
                counterList.splice(i,1);
                i--;
            }
        }
        if(!valid){
            $('#inp-flight-counter-tokenfield').val('');
            return false;
        }
    })
    .on('tokenfield:removetoken',function (e) {
        counterList.push(e.attrs.value);
    })
    .tokenfield({
        autocomplete: {
            source: counterList,
            delay: 0,
            autoFocus: true
        },
        showAutocompleteOnFocus: true,
        limit: 5
    });

    $('#inp-flight-counter-tokenfield').blur(function () {
        $('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
        $('#inp-flight-counter-mirror').focus();
        $('#inp-flight-counter-mirror').blur();
    });

    $('#btn-save').click(function () {
        $('#inp-flight-counter-mirror').focus();
        $('#inp-flight-counter-mirror').blur();
    });


});