(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Views.MatchView = (function(_super) {

    __extends(MatchView, _super);

    function MatchView() {
      return MatchView.__super__.constructor.apply(this, arguments);
    }

    MatchView.prototype.events = {
      "click .apply-join": "createOnJoin"
    };

    MatchView.prototype.render = function() {
      var context;
      context = {
        'request': this.model.get('tripRequest'),
        'matches': this.model.get('tripMatches')
      };
      console.log(context);
      return $(this.el).html(Handlebars.templates['matchView'](context));
    };

    MatchView.prototype.createOnJoin = function(element) {
      var id, match;
      id = $(element.currentTarget).attr("id");
      match = new shuttledriveWeb.Models.TripMatchModel({
        id: id
      });
      return match.fetch({
        success: function(data) {
          data.set({
            'matchState': 'POTENTIAL'
          });
          data.set({
            'confirm': true
          });
          return data.save(data.toJSON, {
            success: function() {
              $(element.currentTarget).attr("disabled", "true");
              $(element.currentTarget).html('&#x2713; Joined');
              return $(element.currentTarget).toggleClass('btn-primary btn-success');
            },
            error: function() {
              return console.log(' error');
            }
          });
        },
        error: function(data, error) {
          console.log(data);
          return console.log(error);
        }
      });
    };

    return MatchView;

  })(Backbone.View);

}).call(this);
