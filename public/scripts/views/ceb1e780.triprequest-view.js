(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Views.TripRequestView = (function(_super) {

    __extends(TripRequestView, _super);

    function TripRequestView() {
      return TripRequestView.__super__.constructor.apply(this, arguments);
    }

    TripRequestView.prototype.model = shuttledriveWeb.Models.TripRequestModel;

    TripRequestView.prototype.initialize = function() {
      _.bindAll(this, 'render');
      this.model.bind('change', this.render);
      return this.render();
    };

    TripRequestView.prototype.render = function() {
      return $(this.el).html(Handlebars.templates['triprequestView'](this.model.toJSON()));
    };

    return TripRequestView;

  })(Backbone.View);

}).call(this);
