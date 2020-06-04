
var countDownDate = new Date("May 29, 2020 23:59:59").getTime();
var x = setInterval(function() {
  var now = new Date().getTime();
  var distance = countDownDate - now;
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  document.getElementsByClassName("countdown")[0].innerHTML = ("0" + days).slice(-2) + " DAYS, " + ("0" + hours).slice(-2) + " HOURS, " + ("0" + minutes).slice(-2) + " MINS, " + ("0" + seconds).slice(-2) + " SECS ";
  if (distance < 0) {
    clearInterval(x);
    document.getElementsByClassName("countdown")[0].innerHTML = "EXPIRED";
  }
}, 1000);