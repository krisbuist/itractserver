(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Models.TripMatchModel = (function(_super) {

    __extends(TripMatchModel, _super);

    function TripMatchModel() {
      return TripMatchModel.__super__.constructor.apply(this, arguments);
    }

    TripMatchModel.prototype.url = function() {
      return shuttledriveWeb.rootPath + '/trip_match/' + this.id;
    };

    return TripMatchModel;

  })(Backbone.Model);

}).call(this);
