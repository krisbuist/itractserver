
/*
Toyota TV Helpers
*/


/*
Date Format
Converts UNIX Epoch time to DD.MM.YY
1343691442862 -> 31.07.12
Usage: {{dateFormat yourDate}}
*/


(function() {

  Handlebars.registerHelper("dateFormat", function(context) {
    var date, day, month, year;
    date = new Date(context * 1000);
    day = date.getDate();
    month = date.getMonth() + 1;
    year = String(date.getFullYear()).substr(2, 3);
    return (day < 10 ? "0" : "") + day + "." + (month < 10 ? "0" : "") + month + "." + year;
  });

  /*
  Date Format (datetime)
  Converts UNIX Epoch time to YYYY-MM-DD
  1343691442862 -> 2012-7-31
  Usage: {{dateFormatTime yourDate}}
  */


  Handlebars.registerHelper("dateFormatTime", function(context) {
    var date;
    date = new Date(context * 1000);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
  });

  /*
  */


  Handlebars.registerHelper("dateFormatTimeOnly", function(context) {
    var date;
    date = new Date(context * 1000);
    return date.getHours() + ":" + date.getMinutes();
  });

  /*
  Duration Format
  Converts UNIX Epoch time to H.MM.SS
  127478 -> 2:07
  Usage: {{durationFormat yourTime}}
  */


  Handlebars.registerHelper("durationFormat", function(context) {
    var duration, hours, mins, secs;
    duration = Math.floor(context);
    hours = (duration >= 3600 ? Math.floor(duration / 3600) : null);
    mins = (hours ? Math.floor(duration % 3600 / 60) : Math.floor(duration / 60));
    secs = Math.floor(duration % 60);
    return (hours ? hours + ":" : "") + (mins < 10 ? "0" : "") + mins + ":" + (secs < 10 ? "0" : "") + secs;
  });

  /*
  Duration Format ISO
  Converts UNIX time to ISO 8601 date format
  127478 -> PT2M7S
  Usage: {{durationFormatISO yourTime}}
  */


  Handlebars.registerHelper("durationFormatISO", function(context) {
    var duration, hours, mins, secs;
    duration = Math.floor(context / 1000);
    hours = Math.floor(duration / 3600);
    mins = (hours ? Math.floor(duration % 3600 / 60) : Math.floor(duration / 60));
    secs = Math.floor(duration % 60);
    return "PT" + hours + "H" + mins + "M" + secs + "S";
  });

  Handlebars.registerHelper("capitalize", function(str) {
    str = (!(str != null) ? "" : String(str));
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  });

}).call(this);
