(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Models.TripMatchesModel = (function(_super) {

    __extends(TripMatchesModel, _super);

    function TripMatchesModel() {
      return TripMatchesModel.__super__.constructor.apply(this, arguments);
    }

    TripMatchesModel.prototype.defaults = {
      originAddress: "",
      destinationAddress: ""
    };

    TripMatchesModel.prototype.url = function() {
      return shuttledriveWeb.rootPath + '/match/' + this.id;
    };

    return TripMatchesModel;

  })(Backbone.Model);

}).call(this);
