(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Routers.ApplicationRouter = (function(_super) {

    __extends(ApplicationRouter, _super);

    function ApplicationRouter() {
      return ApplicationRouter.__super__.constructor.apply(this, arguments);
    }

    ApplicationRouter.prototype.routes = {
      "triprequest/:id": "tripRequestRoute",
      "matches": "matchesRoute",
      "tripoffer": "tripOfferRoute",
      "": "indexRoute"
    };

    ApplicationRouter.prototype.tripRequestRoute = function(id) {
      var match, tripRequest;
      $('#content').html('');
      tripRequest = new shuttledriveWeb.Models.TripRequestModel({
        id: id
      });
      tripRequest.fetch({
        success: function(data) {
          var view;
          view = new shuttledriveWeb.Views.TripRequestView({
            model: data
          });
          return $(view.render()).appendTo('#content').hide().fadeIn();
        },
        error: function(data, error) {}
      });
      match = new shuttledriveWeb.Models.TripMatchesModel({
        id: id
      });
      return match.fetch({
        success: function(data) {
          var view;
          view = new shuttledriveWeb.Views.MatchView({
            model: data
          });
          return $(view.render()).appendTo('#content').hide().fadeIn();
        },
        error: function(data, error) {
          console.log(error);
          return console.log('fail');
        }
      });
    };

    ApplicationRouter.prototype.tripOfferRoute = function() {
      var tripOffer;
      tripOffer = new shuttledriveWeb.Models.TripOfferModel();
      return new shuttledriveWeb.Views.TripOfferView({
        model: tripOffer
      });
    };

    ApplicationRouter.prototype.indexRoute = function() {
      var tripRequest;
      tripRequest = new shuttledriveWeb.Models.TripRequestModel();
      return new shuttledriveWeb.Views.TripRequestFormView({
        model: tripRequest
      });
    };

    return ApplicationRouter;

  })(Backbone.Router);

}).call(this);
