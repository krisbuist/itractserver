(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Collections.MatchCollection = (function(_super) {

    __extends(MatchCollection, _super);

    function MatchCollection() {
      return MatchCollection.__super__.constructor.apply(this, arguments);
    }

    MatchCollection.prototype.requestId = 0;

    MatchCollection.prototype.url = function() {
      return shuttledriveWeb.rootPath + '/match/' + this.requestId;
    };

    MatchCollection.prototype.initialize = function(models, options) {
      var requestId;
      requestId = options.id;
      this.fetch({
        success: function(attrs) {
          var matches, _i, _len, _ref;
          models = {};
          models.matches = [];
          console.log(attrs.get(0));
          _ref = attrs.get(0).get('tripMatches');
          for (_i = 0, _len = _ref.length; _i < _len; _i++) {
            matches = _ref[_i];
            models.matches.push(new shuttledriveWeb.Models.TripMatchModel(matches));
          }
          models.request = new shuttledriveWeb.Models.TripRequestModel(attrs.get('tripRequest'));
          this.model = models;
          return console.log(this.models);
        },
        error: function(attrs, error) {
          return console.log(attrs);
        }
      });
      return console.log(this);
    };

    return MatchCollection;

  })(Backbone.Collection);

}).call(this);
