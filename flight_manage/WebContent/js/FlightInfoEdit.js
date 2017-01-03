$(function () {
    function addError(sel, msg){  //sel:input元素选择器  msg:要显示的信息
        var elem = $(sel);
        var helpblock = elem.parent().next();
        var formgroup = elem.parents(".form-group");
        if(helpblock.html() == "*"){  //无报错信息
            formgroup.addClass("has-danger has-error");
            helpblock.html("<ul class='list-unstyled'><li>" + msg + "</li></ul>")
        }
        else{  //已有报错信息
            if(helpblock.find("li:contains(" + msg + ")").length == 0)
                helpblock.find("ul").append('<li>' + msg + '</li>');
        }
    }
    function removeError(sel, msg){  //sel:input元素选择器  msg:要显示的信息
        var elem = $(sel);
        var helpblock = elem.parent().next();
        var formgroup = elem.parents(".form-group");
        if(helpblock.html() == "*"){  //无报错信息
            return;
        }
        else{  //已有报错信息
            //移除所要求移除的报错信息
            helpblock.find("li:contains(" + msg + ")").remove();

            //若移除后无报错信息则移除has-danger has-error类
            if(helpblock.find('li').length == 0){
                helpblock.html("*");
                formgroup.removeClass("has-error has-danger");
            }
        }
    }


    $('[name="flight-id"]').blur(function () {
        $(this).val($(this).val().toUpperCase());
    });

    $('form').validator({
        delay: 1000,
        custom: {
            //经停地不相同
            unequals: function ($el) {
                var start = $('[name="flight-from"]').val();
                var to = $('[name="flight-to"]').val();
                var via = $el.val();
                if(start == via || to == via)
                    return "经停地不能与始发地或目的地相同";
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
                var url_in = $el.data("checkonly");
                var id_in = $('[name="flight-id"]').val().toUpperCase();
                var time_in = $('[name="flight-dep-time"]').length ? $('[name="flight-dep-time"]').val() : $('[name="flight-arr-time"]').val();
                var resp;
                var valid = -1; //1:不通过  0:通过  -1:未验证
                if(id_in.match("([A-z]{2}[0-9]{3,4}|[A-z][0-9]{4,5}|[0-9][A-z]{1}[0-9]{3,4})") && time_in != ''){
                    resp = $.ajax({
                        url: url_in,
                        type: 'GET',
                        processData: true,
                        data: "id=" + id_in + "&time=" + time_in,
                        async: false,
                        success: function (e) {
                            valid = e - 0;
                        },
                        error: function (e) {
                            alert('尝试验证输入时发生网络错误');
                            valid = 1;
                        }
                    });
                }

                var this_name = $el.attr('name');
                var id_elem = '[name="flight-id"]';
                var time_elem = $('[name="flight-dep-time"]').length ? '[name="flight-dep-time"]' : '[name="flight-arr-time"]';

                if(valid > 0){  //验证不通过
                    if(this_name == "flight-id")
                        addError(time_elem, "已存在重复航班信息*");
                    else
                        addError(id_elem, "已存在重复航班信息*");
                    return '已存在重复航班信息*';
                }
                else{  //验证通过或未验证
                    removeError(id_elem, "已存在重复航班信息*");
                    removeError(time_elem, "已存在重复航班信息*");
                }
            },
            //检查登机门是否被占用
            checkgate: function ($el) {
                var url_in = $el.data("checkgate");
                var gate_in = $el.val();
                gate_in = encodeURI(gate_in);
                if(gate_in != encodeURI($el.data('old'))){
                    var time_in = $('[name="flight-dep-time"]').length ? $('[name="flight-dep-time"]').val() : $('[name="flight-arr-time"]').val();
                    var resp;
                    var valid = -1; //1:不通过  0:通过  -1:未验证

                    if(time_in != '' && gate_in != ''){
                        resp = $.ajax({
                            url: url_in,
                            type: 'GET',
                            processData: true,
                            data: "gate=" + gate_in + "&time=" + time_in,
                            async: false,
                            success: function (e) {
                                valid = e - 0;
                            },
                            error: function (e) {
                                alert('尝试验证输入时发生网络错误');
                                valid = -10;
                            }
                        });
                    }

                    if(valid == -10)
                        return '验证时出现网络错误*';

                    if(valid > 0){  //验证不通过
                        return '登机门已被占用*';
                    }
                }
            }
        }
    });

    var time_elem = $('[name="flight-dep-time"]').length ? $('[name="flight-dep-time"]') : $('[name="flight-arr-time"]');
    time_elem.blur(function (e) {
        if($('[name="flight-gate"]').val() != '')
            $('[name="flight-gate"]').blur();
    });
    time_elem.datetimepicker().on('changeDate',function (e) {
        if($('[name="flight-gate"]').val() != '')
            $('[name="flight-gate"]').blur();
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


    //选择航空公司后重新验证航班号
    $("[name='flight-airline']").blur(function (e) {
        $('[name="flight-id"]').blur();
    });

});