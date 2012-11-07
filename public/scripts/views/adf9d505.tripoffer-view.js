(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Views.TripOfferView = (function(_super) {

    __extends(TripOfferView, _super);

    function TripOfferView() {
      return TripOfferView.__super__.constructor.apply(this, arguments);
    }

    TripOfferView.prototype.el = $('#content');

    TripOfferView.prototype.events = {
      "click #trip-offer-submit": "createOnSubmit"
    };

    TripOfferView.prototype.initialize = function() {
      _.bindAll(this);
      return this.render();
    };

    TripOfferView.prototype.render = function() {
      var context;
      context = {};
      Backbone.Validation.bind(this);
      $(this.el).html(Handlebars.templates['tripOfferView'](context));
      return $('.timepicker-default').timepicker({
        showMeridian: false,
        showSeconds: false,
        minuteStep: 5
      });
    };

    TripOfferView.prototype.createOnSubmit = function() {
      var from, timeArrival, timeDeparture, to;
      from = $('#from').val();
      to = $('#to').val();
      timeArrival = $('#offer-arrival-time').val();
      timeDeparture = $('#offer-departure-time').val();
      this.model.set({
        offerDestinationAddress: to,
        offerOriginAddress: from
      });
      return this.model.saveWithOriginAndDestination(from, to, function(id) {
        $('#offerAdded').modal();
        return $("#offerAdded").on("hidden", function() {
          return shuttledriveWeb.app.navigate('tripoffer/' + id, {
            trigger: true
          });
        });
      });
    };

    return TripOfferView;

  })(Backbone.View);

}).call(this);
