(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Views.TripRequestFormView = (function(_super) {

    __extends(TripRequestFormView, _super);

    function TripRequestFormView() {
      return TripRequestFormView.__super__.constructor.apply(this, arguments);
    }

    TripRequestFormView.prototype.el = $('#content');

    TripRequestFormView.prototype.events = {
      "click #trip-request-submit": "createOnSubmit"
    };

    TripRequestFormView.prototype.initialize = function() {
      _.bindAll(this);
      return this.render();
    };

    TripRequestFormView.prototype.render = function() {
      var context;
      context = {};
      Backbone.Validation.bind(this);
      $(this.el).html(Handlebars.templates['triprequest-form-view'](context));
      return $('.timepicker-default').timepicker({
        showMeridian: false,
        showSeconds: false,
        minuteStep: 5
      });
    };

    TripRequestFormView.prototype.createOnSubmit = function() {
      var from, to;
      from = $('#from').val();
      to = $('#to').val();
      this.model.set({
        requestOriginAddress: from,
        requestDestinationAddress: to
      });
      return this.model.saveWithOriginAndDestination(from, to, function(id) {
        return shuttledriveWeb.app.navigate('triprequest/' + id, {
          trigger: true
        });
      });
    };

    return TripRequestFormView;

  })(Backbone.View);

}).call(this);
