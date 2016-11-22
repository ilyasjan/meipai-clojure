$(document).ready(function(){
    var left=window.screen.width/2-640/2;
    var top=window.screen.height/2-450/1.5;
    $(".loading").css('top',top);
    $(".loading").css('left',left);
    $("#download").click(function(e){
        var murl=$("#url").val();
        $("#res").html("");
        if (! murl.startsWith("http://www.meipai.com/media/")){
            alert("你丫的耍我吗");
            return false;
        }
        $(".loading").show();
        $.ajax({
            url:"./ser",
            data:{url:murl},
            success:function(e,d){
                $("#res").html("<a href='"+e+"' target='blank'>"+e+"</a>");
                $(".loading").hide();
            }
        });
    });
});
