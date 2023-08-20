var main={
    init : function(){
        var _this = this;
        $('#btn-save').on('click',function (){
            _this.save();
        });
        $('#btn-update').on('click',function (){
            _this.update();
        });
        $('#btn-delete').on('click',function (){
            _this.delete();
        });
        $('#btn-join').on('click',function (){
            _this.join();
        });
    },
    save : function (){
        var data = {
            title: $('#title').val(),
            owner: $('#owner').val(),
            description: $('#description').val(),
            place: $('#place').val(),
            price: $('#price').val(),
            image: $('#image').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/items',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    join : function (){
        var data = {
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            picture: $('#picture').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/join',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update : function (){
        var data = {
            title: $('#title').val(),
            description: $('#description').val(),
            place: $('#place').val(),
            price: $('#price').val(),
            image: $('#image').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/items/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    delete : function (){
        var id = itemid;

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/items/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }

};
main.init();