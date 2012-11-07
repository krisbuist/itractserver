(function() {

  shuttledriveWeb.Helpers.TimeHelper = {
    formatUnixTimestamp: function(date) {
      return Math.round(date.getTime() / 1000);
    },
    createDate: function(timeString) {
      var date, time;
      time = timeString.split(':');
      date = new Date();
      date.setSeconds(0);
      date.setHours(time[0]);
      date.setMinutes(time[1]);
      return date;
    },
    getDate: function(timeString) {
      var date;
      date = this.createDate(timeString);
      return this.formatUnixTimestamp(date);
    },
    addHour: function(timeString, hours) {
      var date;
      date = this.createDate(timeString);
      date.setHours(date.getHours() + hours);
      return this.formatUnixTimestamp(date);
    },
    removeHour: function(timeString, hours) {
      var date;
      date = this.createDate(timeString);
      date.setHours(date.getHours() - hours);
      return this.formatUnixTimestamp(date);
    }
  };

}).call(this);
