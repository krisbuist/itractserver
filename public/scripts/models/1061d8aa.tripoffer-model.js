(function() {
  var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  shuttledriveWeb.Models.TripOfferModel = (function(_super) {

    __extends(TripOfferModel, _super);

    function TripOfferModel() {
      return TripOfferModel.__super__.constructor.apply(this, arguments);
    }

    TripOfferModel.prototype.defaults = {
      offerId: '',
      offerUser: '1',
      offerOriginLong: '',
      offerOriginLat: '',
      offerOriginWindow: 500,
      offerDestinationLong: '',
      offerDestinationLat: '',
      offerDestinationWindow: 500,
      offerStartTimeMin: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 12:00:00 GMT+01")),
      offerStartTimeMax: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 12:00:00 GMT+01")),
      offerEndTimeMin: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 13:00:00 GMT+01")),
      offerEndTimeMax: shuttledriveWeb.Helpers.TimeHelper.formatUnixTimestamp(new Date("Wed, 07 Nov 2012 13:00:00 GMT+01")),
      offerNumberOfSeats: 4,
      offerState: ''
    };

    TripOfferModel.prototype.validation = {
      offerDestinationAddress: {
        required: true,
        msg: 'Please enter an arrival location'
      },
      offerOriginAddress: {
        required: true,
        msg: 'Please enter a departure location'
      }
    };

    TripOfferModel.prototype.urlRoot = function() {
      return shuttledriveWeb.rootPath + '/trip_offer';
    };

    TripOfferModel.prototype.saveWithOriginAndDestination = function(origin, destination, callback) {
      this.getLatLong(this, origin, function(caller, result) {
        caller.set({
          'offerOriginLat': result.Ya
        });
        return caller.set({
          'offerOriginLong': result.Za
        });
      });
      return this.getLatLong(this, destination, function(caller, result) {
        caller.set({
          'offerDestinationLat': result.Ya
        });
        caller.set({
          'offerDestinationLong': result.Za
        });
        return caller.save(caller.toJSON(), {
          success: function() {
            return callback(caller.get('offerId'));
          },
          error: function() {
            return console.log('error');
          }
        });
      });
    };

    TripOfferModel.prototype.getLatLong = function(caller, address, callback) {
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

    TripOfferModel.prototype.parse = function(resp, xhr) {
      if (resp.tripMatches) {
        return {
          offerId: resp.tripOffer.offerId,
          offerUser: resp.tripOffer.offerUser,
          offerOriginAddress: resp.tripOffer.offerOriginAddress,
          offerOriginLong: resp.tripOffer.offerOriginLong,
          offerOriginLat: resp.tripOffer.offerOriginLat,
          offerOriginWindow: resp.tripOffer.offerOriginWindow,
          offerDestinationAddress: resp.tripOffer.offerDestinationAddress,
          offerDestinationLong: resp.tripOffer.offerDestinationLong,
          offerDestinationLat: resp.tripOffer.offerDestinationLat,
          offerDestinationWindow: resp.tripOffer.offerDestinationWindow,
          offerStartTimeMin: resp.tripOffer.offerStartTimeMin,
          offerStartTimeMax: resp.tripOffer.offerStartTimeMax,
          offerEndTimeMin: resp.tripOffer.offerEndTimeMin,
          offerEndTimeMax: resp.tripOffer.offerEndTimeMax,
          offerNumberOfSeats: resp.tripOffer.offerNumberOfSeats,
          offerState: resp.tripOffer.offerState
        };
      } else {
        return {
          offerId: resp.offerId,
          offerUser: resp.offerUser,
          offerOriginAddress: resp.offerOriginAddress,
          offerOriginLong: resp.offerOriginLong,
          offerOriginLat: resp.offerOriginLat,
          offerOriginWindow: resp.offerOriginWindow,
          offerDestinationAddress: resp.offerDestinationAddress,
          offerDestinationLong: resp.offerDestinationLong,
          offerDestinationLat: resp.offerDestinationLat,
          offerDestinationWindow: resp.offerDestinationWindow,
          offerStartTimeMin: resp.offerStartTimeMin,
          offerStartTimeMax: resp.offerStartTimeMax,
          offerEndTimeMin: resp.offerEndTimeMin,
          offerEndTimeMax: resp.offerEndTimeMax,
          offerNumberOfSeats: resp.offerNumberOfSeats,
          offerState: resp.offerState
        };
      }
    };

    return TripOfferModel;

  })(Backbone.Model);

}).call(this);
