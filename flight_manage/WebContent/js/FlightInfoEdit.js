$(function () {

    $('[name="flight-id"]').blur(function () {
        $(this).val($(this).val().toUpperCase());
    });

    $('form').validator({
        custom: {
            //经停地不相同
            unequals: function ($el) {
                var start = $('[name="flight-from"]').val();
                var to = $('[name="flight-to"]').val();
                var via = $el.val();
                if(start == via || to == via)
                    return "经停地不能与始发地相同";
            },
            //检查航空公司代码
            checkcode: function ($el) {
                var selectedCode = $('[name="flight-airline"]').children(':checked').attr('airline-code');
                var flightNo = $el.val().toUpperCase();
                if(selectedCode != undefined && flightNo.substr(0, 2) != selectedCode)
                    return '请填写正确的航班号*';
            },
            //检查有无重复航班
            checkonly: function ($el) {
                console.log("checkonly");
                // var url_in = $el.data("checkonly");
                // var id_in = $('[name="flight-id"]').val();
                // var time_in = $('[name="flight-dep-time"]').length ? $('[name="flight-dep-time"]') : $('[name="flight-arr-time"]');
                // if(id_in != '' && time_in != ''){
                //     var valid = $.ajax({
                //         url: url_in,
                //         type: 'GET',
                //         processData: true,
                //         data: {
                //             id: id_in,
                //             time: time_in
                //         },
                //         async: false
                //     });
                // }
                // console.log(valid);
                // if(!valid)
                //     return "已存在重复航班信息*";
            }
        }
    });
    
    //值机柜台tokenfield
    if ($('#inp-flight-counter').length != 0){
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
            $('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
            $('#inp-flight-counter-mirror').focus();
            $('#inp-flight-counter-mirror').blur();
        });

        $('#btn-save').click(function () {
            $('#inp-flight-counter-mirror').val($('#inp-flight-counter').val());
            $('#inp-flight-counter-mirror').focus();
            $('#inp-flight-counter-mirror').blur();
        });
    }

});