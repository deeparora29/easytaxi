$(function(){
$(".headText").first().addClass("visible");
$(".headText").last().addClass("hidden");
$(".headText a:eq(1)").click(function(){
$(".headText:first").addClass("hidden");
$(".headText:last").addClass("visible");
})
})