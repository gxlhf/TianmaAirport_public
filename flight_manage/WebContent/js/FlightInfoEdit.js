$(function () {
    $('[name="flight-no"]').blur(function () {
        $(this).val($(this).val().toUpperCase());
    });

    //经停地不相同
    $('form').validator({
        custom: {
            unequals: function ($el) {
                var start = $('[name="flight-from"]').val();
                var to = $('[name="flight-to"]').val();
                var via = $el.val();
                console.log($el);
                if(start == via || to == via)
                    return "经停地不能与始发地相同";
            }
        }
    });

    // $("form").validator().on('validate.bs.validator', function (e) {        
    //     //$('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
    //     $('#inp-flight-counter-mirror').focus();
    //     $('#inp-flight-counter-mirror').blur();
    // });
    // $("form").validator().on('validated.bs.validator', function (e) {        
    //     //$('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
    //     //$('#inp-flight-counter-mirror').focus();
    //     //$('[name="flight-id"]').focus();
    // });
    
    //值机柜台tokenfield
    if ($('#inp-flight-counter').length != 0){
        $('#inp-flight-counter')
        .on('tokenfield:createtoken', function (e) {
            console.log('createtoken');
            var valid = false;
            for (var i = 0; i < counterList.length; i++) {
                if(counterList[i] == e.attrs.value){
                    valid = true;
                    counterList.splice(i,1);
                    i--;
                }
            }
            $('#inp-flight-counter-tokenfield').autocomplete("option", 'source', counterList);
            if(!valid){
                $('#inp-flight-counter-tokenfield').val('');
                return false;
            }
        })
        .on('tokenfield:removetoken',function (e) {
            counterList.push(e.attrs.value);
            $('#inp-flight-counter-tokenfield').autocomplete("option", 'source', counterList);
        })
        .on('tokenfield:editedtoken',function (e) {
            counterList.push(e.attrs.value);
            $('#inp-flight-counter-tokenfield').autocomplete("option", 'source', counterList);
        })
        .tokenfield({
            autocomplete: {
                source: counterList,
                delay: 0,
                autoFocus: true
            },
            showAutocompleteOnFocus: true,
            delimiter: [",", " ", "-", "_"],
            limit: 5
        });

        $('#inp-flight-counter').tokenfield('setTokens', selectedCounterList);

        $('#inp-flight-counter-tokenfield').blur(function () {
            console.log('blur');
            $('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
            $('#inp-flight-counter-mirror').focus();
            $('#inp-flight-counter-mirror').blur();
        });

        // $('#btn-save').click(function () {
        //     $('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
        //     $('#inp-flight-counter-mirror').focus();
        //     $('#inp-flight-counter-mirror').blur();
        // });
    }

});