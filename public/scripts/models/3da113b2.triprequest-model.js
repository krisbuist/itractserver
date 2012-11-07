(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Models.TripRequestModel = (function(_super) {

    __extends(TripRequestModel, _super);

    function TripRequestModel() {
      return TripRequestModel.__super__.constructor.apply(this, arguments);
    }

    TripRequestModel.prototype.defaults = {
      requestId: '',
      requestUser: '1',
      requestOriginLong: '',
      requestOriginLat: '',
      requestOriginWindow: '1',
      requestDestinationLong: '',
      requestDestinationLat: '',
      requestDestinationWindow: '1',
      requestStartTimeMin: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 12:00:00 GMT+01")),
      requestStartTimeMax: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 12:00:00 GMT+01")),
      requestEndTimeMin: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 13:00:00 GMT+01")),
      requestEndTimeMax: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 13:00:00 GMT+01")),
      requestNumberOfSeats: 4,
      requestState: ''
    };

    TripRequestModel.prototype.validation = {
      requestDestinationAddress: {
        required: true,
        msg: 'Please enter an arrival location'
      },
      requestOriginAddress: {
        required: true,
        msg: 'Please enter a departure location'
      }
    };

    TripRequestModel.prototype.urlRoot = function() {
      return shuttledriveWeb.rootPath + '/trip_request';
    };

    TripRequestModel.prototype.saveWithOriginAndDestination = function(origin, destination, callback) {
      this.getLatLong(this, origin, function(caller, result) {
        caller.set({
          'requestOriginLat': result.Ya
        });
        return caller.set({
          'requestOriginLong': result.Za
        });
      });
      return this.getLatLong(this, destination, function(caller, result) {
        caller.set({
          'requestDestinationLat': result.Ya
        });
        caller.set({
          'requestDestinationLong': result.Za
        });
        return caller.save(caller.toJSON(), {
          success: function() {
            return callback(caller.get('requestId'));
          },
          error: function() {
            return console.log('error');
          }
        });
      });
    };

    TripRequestModel.prototype.getLatLong = function(caller, address, callback) {
      var geocoder,
        _this = this;
      geocoder = new google.maps.Geocoder();
      return geocoder.geocode({
        address: address
      }, function(results, status) {
        var result;
        if (status === google.maps.GeocoderStatus.OK) {
          result = results[0].geometry.location;
          return callback(caller, result);
        }
      });
    };

    TripRequestModel.prototype.parse = function(resp, xhr) {
      if (resp.tripMatches) {
        return {
          requestId: resp.tripRequest.requestId,
          requestUser: resp.tripRequest.requestUser,
          requestOriginAddress: resp.tripRequest.requestOriginAddress,
          requestOriginLong: resp.tripRequest.requestOriginLong,
          requestOriginLat: resp.tripRequest.requestOriginLat,
          requestOriginWindow: resp.tripRequest.requestOriginWindow,
          requestDestinationAddress: resp.tripRequest.requestDestinationAddress,
          requestDestinationLong: resp.tripRequest.requestDestinationLong,
          requestDestinationLat: resp.tripRequest.requestDestinationLat,
          requestDestinationWindow: resp.tripRequest.requestDestinationWindow,
          requestStartTimeMin: resp.tripRequest.requestStartTimeMin,
          requestStartTimeMax: resp.tripRequest.requestStartTimeMax,
          requestEndTimeMin: resp.tripRequest.requestEndTimeMin,
          requestEndTimeMax: resp.tripRequest.requestEndTimeMax,
          requestNumberOfSeats: resp.tripRequest.requestNumberOfSeats,
          requestState: resp.tripRequest.requestState
        };
      } else {
        return {
          requestId: resp.requestId,
          requestUser: resp.requestUser,
          requestOriginAddress: resp.requestOriginAddress,
          requestOriginLong: resp.requestOriginLong,
          requestOriginLat: resp.requestOriginLat,
          requestOriginWindow: resp.requestOriginWindow,
          requestDestinationAddress: resp.requestDestinationAddress,
          requestDestinationLong: resp.requestDestinationLong,
          requestDestinationLat: resp.requestDestinationLat,
          requestDestinationWindow: resp.requestDestinationWindow,
          requestStartTimeMin: resp.requestStartTimeMin,
          requestStartTimeMax: resp.requestStartTimeMax,
          requestEndTimeMin: resp.requestEndTimeMin,
          requestEndTimeMax: resp.requestEndTimeMax,
          requestNumberOfSeats: resp.requestNumberOfSeats,
          requestState: resp.requestState
        };
      }
    };

    return TripRequestModel;

  })(Backbone.Model);

}).call(this);
